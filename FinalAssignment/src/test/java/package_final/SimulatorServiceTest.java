package package_final;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimulatorServiceTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void handleRideRequested_AssignsDriverWhenAvailable() {
    // Create a sample ride and driver
    Ride ride = new Ride(new Customer("123", "StartLocation1", "Destination2", 123456789,
        RideType.WAIT_AND_SAVE_PICKUP), null, RideType.WAIT_AND_SAVE_PICKUP, 123456789);
    Driver driver = new Driver("driver123", "InitialLocation");

    // Set the driver as available
    driver.setAvailable(true);
  }

  @Test
  void recoverUnassignedRide_assignToDriver() {
    // Create a new SimulatorService object
    SimulatorService service = new SimulatorService(1);

    // Create a ride
    Ride ride = new Ride(new Customer("123", "StartLocation1", "Destination1", 123456789, RideType.WAIT_AND_SAVE_PICKUP), null, RideType.WAIT_AND_SAVE_PICKUP, 123456789);

    // Call recoverUnassignedRide method
    service.recoverUnassignedRide(ride);

    // Waiting list should be empty
    assertTrue(service.waitingQueue.isEmpty());
  }

  @Test
  void recoverUnassignedRide_addToWaitingQueue() {
    // Create a new SimulatorService object with no driver
    SimulatorService service = new SimulatorService(0);

    // Create a ride
    Ride ride = new Ride(new Customer("123", "StartLocation1", "Destination1", 123456789, RideType.WAIT_AND_SAVE_PICKUP), null, RideType.WAIT_AND_SAVE_PICKUP, 123456789);

    // Call recoverUnassignedRide method
    service.recoverUnassignedRide(ride);

    // Check if the ride has been added to waitingQueue
    assertTrue(service.waitingQueue.contains(ride));
  }

  @Test
  void processWaitingQueue() {
    // Create a new SimulatorService object
    SimulatorService service = new SimulatorService(2);

    // Create a driver
    Driver driver = new Driver("driver123", "InitialLocation0");

    // Create some rides and add them to the waitingQueue
    Ride ride1 = new Ride(new Customer("123", "StartLocation1", "Destination1", 123456789, RideType.WAIT_AND_SAVE_PICKUP), driver, RideType.WAIT_AND_SAVE_PICKUP, 123456789);
    Ride ride2 = new Ride(new Customer("456", "StartLocation2", "Destination2", 987654321, RideType.WAIT_AND_SAVE_PICKUP), driver, RideType.WAIT_AND_SAVE_PICKUP, 987654321);
    service.waitingQueue.add(ride1);
    service.waitingQueue.add(ride2);

    // Store the initial size of rideQueue
    int initialRideQueueSize = service.rideQueue.size();

    // Call the processWaitingQueue method
    service.processWaitingQueue(driver);

    // Check if the rides have been added to rideQueue and totalRides has been incremented
    assertEquals(initialRideQueueSize + 2, service.rideQueue.size()); // Assuming both rides are added to rideQueue
    assertEquals(2, service.totalRides); // Assuming totalRides has been incremented by 2
  }
  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  void printStatistics() {
    // Assume that your SimulatorService instance is already set up and populated with data
    SimulatorService service = new SimulatorService(50);
    service.printStatistics();

    // Capture the output
    String output = outputStreamCaptor.toString().trim();

    // Assert that the output contains the expected strings
    assertTrue(output.contains("Average wait time for a ride:"));
    assertTrue(output.contains("Average number of rides per driver:"));
    assertTrue(output.contains("Average ride time:"));
  }
}