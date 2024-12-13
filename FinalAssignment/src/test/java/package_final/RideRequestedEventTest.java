package package_final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RideRequestedEventTest {

  private Ride ride;
  private RideRequestedEvent requestedEvent;

  @BeforeEach
  void setUp() {
    // Initialize a Ride object for testing
    Customer customer = new Customer("123", "StartLocation1", "Destination2", 123456789, RideType.WAIT_AND_SAVE_PICKUP);
    Driver driver = new Driver("driver123", "InitialLocation");
    ride = new Ride(customer, driver, RideType.WAIT_AND_SAVE_PICKUP, 123456789);

    // Create a RideRequestedEvent object
    requestedEvent = new RideRequestedEvent(123456789, ride); // Assuming 123456789 is the request time
  }

  @Test
  void getRide() {
    // Test the getRide method
    assertEquals(ride, requestedEvent.getRide());
  }

  @Test
  void validateRide() {
    // Case 1: Valid ride
    Customer customer1 = new Customer("123", "StartLocation1", "Destination1", 123456789, RideType.WAIT_AND_SAVE_PICKUP);
    Driver driver1 = new Driver("driver123", "InitialLocation");
    Ride validRide = new Ride(customer1, driver1, RideType.WAIT_AND_SAVE_PICKUP, 123456789);
    // Since all parameters are valid, validateRide() should not throw any exception
    assertDoesNotThrow(() -> requestedEvent.validateRide(validRide));

    // Case 2: Null ride
    Ride nullRide = null;
    // validateRide() should throw IllegalArgumentException
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> requestedEvent.validateRide(nullRide));
    assertEquals("Invalid ride parameters", exception.getMessage());

    // Case 3: Ride with null customer
    Customer customer2 = null;
    Driver driver2 = new Driver("driver123", "InitialLocation");
    Ride rideWithNullCustomer = new Ride(customer2, driver2, RideType.WAIT_AND_SAVE_PICKUP, 123456789);
    // validateRide() should throw IllegalArgumentException
    exception = assertThrows(IllegalArgumentException.class, () -> requestedEvent.validateRide(rideWithNullCustomer));
    assertEquals("Invalid ride parameters", exception.getMessage());

    // Add more test cases for other scenarios as needed
  }
  @Test
  void processEvent() {
    // Create a new SimulatorService object
    SimulatorService service = new SimulatorService(1);

    // Call the processEvent method on the RideRequestedEvent
    requestedEvent.processEvent(service);

    // Check the change in size of rideQueue and waitingQueue
    // Assuming handleRideRequested() method adds the ride to rideQueue if a driver is available, otherwise to waitingQueue
    if (service.findAvailableDriver(ride) != null) {
      assertEquals(1, service.rideQueue.size());
      assertEquals(0, service.waitingQueue.size());
    }

    // Create a ride
    Ride ride = new Ride(new Customer("123", "StartLocation1", "Destination1", 123456789, RideType.WAIT_AND_SAVE_PICKUP), null, RideType.WAIT_AND_SAVE_PICKUP, 123456789);
    // Call recoverUnassignedRide method
    service.recoverUnassignedRide(ride);
    // Check if the ride has been added to waitingQueue
    assertTrue(service.waitingQueue.contains(ride));
    assertEquals(1, service.waitingQueue.size());
  }


  @Test
  void testEquals() {
    // Test the equals method
    RideRequestedEvent sameEvent = new RideRequestedEvent(123456789, ride);
    Ride differentRide = new Ride(ride.getCustomer(), ride.getDriver(), RideType.EXPRESS_PICKUP, 987654321);

    assertTrue(requestedEvent.equals(sameEvent));
    assertFalse(requestedEvent.equals(new RideRequestedEvent(987654321, differentRide)));
  }

  @Test
  void testHashCode() {
    // Test the hashCode method
    RideRequestedEvent sameEvent = new RideRequestedEvent(123456789, ride);

    assertEquals(requestedEvent.hashCode(), sameEvent.hashCode());
  }
}
