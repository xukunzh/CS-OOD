package package_final;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The main class for running the rideshare dispatch simulator.
 */
public class RideshareDispatchSimulator {

  /**
   * Average speed in miles per hour.
   */
  protected static final long SPEED_MPH = 60;

  /**
   * Milliseconds per hour.
   */
  protected static final long MS_PER_HOUR = 3600000;

  /**
   * Milliseconds per second.
   */
  private static final int MILLISECONDS_TO_SECONDS = 1000;
  /**
   * Constants defining the minimum and maximum start locations, minimum and maximum distances,
   * and the order time increment in seconds for generating rides.
   */
  private static final int MIN_START_LOCATION = 0;
  private static final int MAX_START_LOCATION = 19;
  private static final int MIN_DISTANCE = 5;
  private static final int MAX_DISTANCE = 20;
  private static final int ORDER_TIME_INCREMENT_SECONDS = 30;


  /**
   * The entry point of the application.
   * Creates simulation configurations and runs simulations based on the configurations.
   *
   * @param args The command line arguments (not used).
   */

  public static void main(String[] args) {
    // Create configuration instances
    SimulationConfig config1 = new SimulationConfig(50, 25);
    SimulationConfig config2 = new SimulationConfig(50, 100);
    SimulationConfig config3 = new SimulationConfig(50, 250);
    // Run simulations based on the configurations
    runSimulation(config1);
    runSimulation(config2);
    runSimulation(config3);

//    // my solution for answering the question: What would be the optimal number of drivers on the roads, to balance your business
//    //operating costs, with the customer convenience?
//    SimulationConfig config1 = new SimulationConfig(50, 50);
//    SimulationConfig config2 = new SimulationConfig(50, 100);
//    SimulationConfig config3 = new SimulationConfig(50, 150);
//    SimulationConfig config4 = new SimulationConfig(50, 200);
//    SimulationConfig config5 = new SimulationConfig(50, 250);
//    SimulationConfig config6 = new SimulationConfig(50, 300);
//    SimulationConfig config7 = new SimulationConfig(50, 350);
//    SimulationConfig config8 = new SimulationConfig(50, 400);
//    SimulationConfig config9 = new SimulationConfig(50, 450);
//    SimulationConfig config10 = new SimulationConfig(50, 500);
//    // Run simulations based on the configurations
//    runSimulation(config1);
//    runSimulation(config2);
//    runSimulation(config3);
//    runSimulation(config4);
//    runSimulation(config5);
//    runSimulation(config6);
//    runSimulation(config7);
//    runSimulation(config8);
//    runSimulation(config9);
//    runSimulation(config10);
  }

  /**
   * Runs a simulation based on the given configuration.
   *
   * @param config The simulation configuration to use.
   */
  private static void runSimulation(SimulationConfig config) {
    SimulatorService simulator = new SimulatorService(config.getNumberOfDrivers());
    List<Ride> rides = generateRides(config.getNumberOfRides());

    System.out.println("\nStarting simulation with " + config.getNumberOfDrivers() + " drivers and " + config.getNumberOfRides() + " rides.");

    // Process each ride request
    for (Ride ride : rides) {
      RideRequestedEvent requestEvent = new RideRequestedEvent(ride.getRequestTime(), ride);
      simulator.processEvent(requestEvent);
      // Simulating ride duration
      long rideDuration = (long) (ride.getCustomer().getDistance() / SPEED_MPH * MS_PER_HOUR);
      // Assuming ride.getStartTime() + rideDuration returns a long value representing the end time
      long endTime = ride.getStartTime() + rideDuration;
      RideFinishedEvent finishedEvent = new RideFinishedEvent(ride); // Pass the Ride object directly
      simulator.processEvent(finishedEvent);
    }
    simulator.printStatistics();
  }

  /**
   * Generates a list of random rides.
   *
   * @param numberOfRides The number of rides to generate.
   * @return A list of randomly generated rides.
   */
  private static List<Ride> generateRides(int numberOfRides) {
    List<Ride> rides = new ArrayList<>();
    Random random = new Random();
    // My computer is using Chinese Time. Pacific Daylight Time (PDT) is usually 16 hours behind Beijing Time. So my result shows in 16:00:00
    LocalTime orderTime = LocalTime.MIDNIGHT; // Assume orders start at midnight
    for (int i = 0; i < numberOfRides; i++) {
      String customerId = "Customer" + i;
      int startNum = MIN_START_LOCATION + random.nextInt(MAX_START_LOCATION - MIN_START_LOCATION + 1);
      int endNum = startNum + MIN_DISTANCE + random.nextInt(MAX_DISTANCE - MIN_DISTANCE + 1); // Ensure distance is between mile range that I wish to test
      String startLocation = "Location" + startNum;
      String destination = "Location" + endNum;
      RideType rideType = RideType.values()[random.nextInt(RideType.values().length)];

      // Assume order time is incremented by ORDER_TIME_INCREMENT_SECONDS for each ride
      // This may need to be adjusted based on the specific requirements of the simulation
      Customer customer = new Customer(customerId, startLocation, destination, orderTime.toSecondOfDay() * MILLISECONDS_TO_SECONDS, rideType);
      rides.add(new Ride(customer, null, rideType, orderTime.toSecondOfDay() * MILLISECONDS_TO_SECONDS));

      // Increment order time for the next ride
      orderTime = orderTime.plusSeconds(ORDER_TIME_INCREMENT_SECONDS);
    }
    return rides;
  }

}
