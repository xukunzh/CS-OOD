package package_final;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * The SimulatorService class simulates a ride-sharing service.
 * It manages ride requests, driver assignments, and calculates statistics.
 */
public class SimulatorService {

  /**
   * A SimpleDateFormat object to format date and time.
   */
  protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  /**
   * The number of drivers in the simulation.
   */
  protected final int numberOfDrivers;


  /**
   * Priority blocking queue for ride requests.
   */

  protected PriorityBlockingQueue<Ride> rideQueue;

  /**
   * Priority blocking queue for waiting rides.
   */
  protected PriorityBlockingQueue<Ride> waitingQueue;

  /**
   * Map to store the end times of drivers.
   */
  protected Map<String, Long> driverEndTimes;

  /**
   * Map to store driver objects.
   */
  protected Map<String, Driver> drivers;

  /**
   * Map to store the number of rides per driver.
   */
  protected Map<String, Integer> driverRidesCount;

  /**
   * Total wait time for all rides.
   */
  protected long totalWaitTime = 0;

  /**
   * Total number of rides.
   */
  protected int totalRides = 0;

  /**
   * Total ride time for all rides.
   */
  protected long totalRideTime = 0;

  /**
   * Relative current time in milliseconds.
   */
  protected long currentTime = 0;

  protected static final long SPEED_MPH = 60;
  protected static final long MS_PER_HOUR = 3600000;
  final double MILLISECONDS_PER_MINUTE = 60000.0;

  /**
   * Constructs a SimulatorService object with the specified number of drivers.
   *
   * @param numberOfDrivers The number of drivers in the simulation.
   */
  protected SimulatorService(int numberOfDrivers) {
    this.numberOfDrivers = numberOfDrivers;
    this.rideQueue = new PriorityBlockingQueue<>(11, Comparator.comparingLong(Ride::getEndTime));
    this.waitingQueue = new PriorityBlockingQueue<>(11, (r1, r2) -> Integer.compare(r2.getRideType().getPriority(), r1.getRideType().getPriority()));
    this.driverEndTimes = new HashMap<>();
    this.drivers = new HashMap<>();
    this.driverRidesCount = new HashMap<>();
    initializeDrivers();
  }

  /**
   * Initializes the drivers for the simulation.
   */
  private void initializeDrivers() {
    for (int i = 0; i < numberOfDrivers; i++) {
      String driverId = "Driver" + i;
      String initialLocation = "InitialLocation" + (i % 51);
      Driver driver = new Driver(driverId, initialLocation);
      drivers.put(driverId, driver);
      driverEndTimes.put(driverId, 0L); // Initialize driver end times to 0
    }
  }

  /**
   * Processes the specified event.
   *
   * @param event The event to be processed.
   */
  protected void processEvent(Event event) {
    event.processEvent(this);
  }

  /**
   * Handles a ride requested event by assigning a driver to the ride or adding it to the waiting queue if no driver is available.
   *
   * @param event The RideRequestedEvent object representing the ride request event.
   */
  protected void handleRideRequested(RideRequestedEvent event) {
    Ride ride = event.getRide();
    currentTime = ride.getRequestTime(); // Set current time to the time the ride is requested

    Driver driver = findAvailableDriver(ride);
    if (driver != null) {
      assignDriverToRide(ride, driver);
      driver.setAvailable(false);
      rideQueue.add(ride);
    } else {
      waitingQueue.add(ride);
    }
  }

  /**
   * Handles a ride finished event by updating statistics and marking the driver as available for the next ride.
   *
   * @param event The RideFinishedEvent object representing the ride finished event.
   */
  protected void handleRideFinished(RideFinishedEvent event) {
    Ride ride = event.getRide();
    Driver driver = ride.getDriver();

    if (driver != null) {
      currentTime = ride.getEndTime(); // Update relative time to when the ride finishes
      long waitTime = ride.getStartTime() - ride.getRequestTime();
      long rideDuration = ride.getEndTime() - ride.getStartTime();
      totalRideTime += rideDuration;
      processWaitingQueue(driver);
      updateStatistics(waitTime, rideDuration, driver);

      // Mark the driver as available and set their next available time
      long nextAvailableTime = currentTime;
      driver.setAvailable(true, nextAvailableTime);
    } else {
      recoverUnassignedRide(ride);
    }
  }

  /**
   * Recovers an unassigned ride by finding an available driver and assigning the ride to them,
   * or adds it to the waiting queue if no available driver is found.
   *
   * @param ride The Ride object representing the unassigned ride.
   */
  protected void recoverUnassignedRide(Ride ride) {
    Driver newDriver = findAvailableDriver(ride);
    if (newDriver != null) {
      // Check if the driver is available before assigning the ride
      if (newDriver.isAvailable()) {
        assignDriverToRide(ride, newDriver);
        // Mark the driver as unavailable after assigning the ride
        newDriver.setAvailable(false);
      } else {
        // If the driver is not available, add the ride to the waiting queue
        waitingQueue.add(ride);
      }
    } else {
      waitingQueue.add(ride);
    }
  }

  /**
   * Processes the waiting queue by assigning rides to the specified driver until the queue is empty,
   * if the driver is available.
   *
   * @param driver The Driver object representing the driver to whom the rides will be assigned.
   */
  protected void processWaitingQueue(Driver driver) {
    // Check if the driver is available before processing the waiting queue
    if (driver.isAvailable()) {
      while (!waitingQueue.isEmpty()) {
        Ride nextRide = waitingQueue.poll();  // Get the next ride in queue
        assignDriverToRide(nextRide, driver);
        rideQueue.add(nextRide);
        totalRides++;
      }
    }
  }

  /**
   * Finds the closest available driver to the specified ride's customer location.
   *
   * @param ride The Ride object representing the ride request.
   * @return The Driver object representing the closest available driver, or null if no available drivers are nearby.
   */
  protected Driver findAvailableDriver(Ride ride) {
    Driver closestDriver = null;
    double shortestDistance = Double.MAX_VALUE;

    for (Driver driver : drivers.values()) {
      if (driver.isAvailable() && driverEndTimes.get(driver.getDriverId()) <= currentTime) {  // Check if the driver is available
        double distance = DriverUtils.calculateDriverDistanceToCustomer(driver, ride.getCustomer().getStartLocation());
        if (distance < shortestDistance) {
          shortestDistance = distance;
          closestDriver = driver;
        }
      }
    }
    return closestDriver;  // Returns null if no available drivers are nearby
  }

  /**
   * Assigns a driver to the specified ride, calculates the start and end times of the ride,
   * and updates relevant driver information.
   *
   * @param ride   The Ride object representing the ride to be assigned.
   * @param driver The Driver object representing the driver to be assigned to the ride.
   */
  protected void assignDriverToRide(Ride ride, Driver driver) {
    long travelTimeToCustomer = DriverUtils.calculateTravelTimeToCustomer(driver, ride);
    long startTime = currentTime + travelTimeToCustomer;
    long rideDuration = (long) ((ride.getCustomer().getDistance() / SPEED_MPH) * MS_PER_HOUR);
    long endTime = startTime + rideDuration;

    ride.setDriver(driver);
    ride.setStartTime(startTime);
    ride.setEndTime(endTime);

    driverEndTimes.put(driver.getDriverId(), endTime);

    // Print assignment details
    String formattedRequestTime = dateFormat.format(new Date(ride.getRequestTime()));
    String formattedStartTime = dateFormat.format(new Date(startTime));
    String formattedEndTime = dateFormat.format(new Date(endTime));
    System.out.println(driver.getDriverId() +
        " assigned to Ride for " + ride.getCustomer().getId() + ", this order is requested at " + formattedRequestTime + ". " +
        driver.getDriverId() + " picked up " + ride.getCustomer().getId() + " at " + formattedStartTime +
        ", reached the destination at " + formattedEndTime +
        ", and the length of this ride is " + ride.getCustomer().getDistance() + " mile.");
  }


  /**
   * Updates statistics based on the wait time, ride duration, and the assigned driver.
   *
   * @param waitTime     The wait time for the ride.
   * @param rideDuration The duration of the ride.
   * @param driver       The Driver object representing the driver assigned to the ride.
   */
  private void updateStatistics(long waitTime, long rideDuration, Driver driver) {
    totalWaitTime += waitTime;
    totalRides++;
    totalRideTime += rideDuration;
    driverRidesCount.put(driver.getDriverId(), driverRidesCount.getOrDefault(driver.getDriverId(), 0) + 1);
  }

  /**
   * Prints the statistics of the simulation including average wait time, average number of rides per driver, and average ride time.
   */
  protected void printStatistics() {
    double averageWaitTime = totalRides > 0 ? (double) totalWaitTime / totalRides : 0;
    double averageRidesPerDriver = numberOfDrivers > 0 ? (double) totalRides / numberOfDrivers : 0;
    double averageWaitTimeInMinutes = averageWaitTime / MILLISECONDS_PER_MINUTE;
    double averageRideTimeInMinutes = totalRides > 0 ? (double) totalRideTime / totalRides / MILLISECONDS_PER_MINUTE : 0;
    System.out.println("Average wait time for a ride: " + String.format("%.2f", averageWaitTimeInMinutes) + "min");
    System.out.println("Average number of rides per driver: " + averageRidesPerDriver);
    System.out.println("Average ride time: " + String.format("%.2f", averageRideTimeInMinutes) + " minutes");
  }
}


