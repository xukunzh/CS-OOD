package package_final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RideFinishedEventTest {

  private Ride ride;
  private RideFinishedEvent finishedEvent;

  @BeforeEach
  void setUp() {
    // Initialize a Ride object for testing
    Customer customer = new Customer("123", "StartLocation1", "Destination2", 123456789, RideType.WAIT_AND_SAVE_PICKUP);
    Driver driver = new Driver("driver123", "InitialLocation");
    ride = new Ride(customer, driver, RideType.WAIT_AND_SAVE_PICKUP, 123456789);

    // Create a RideFinishedEvent object
    finishedEvent = new RideFinishedEvent(ride);
  }

  @Test
  void getRide() {
    // Test the getRide method
    assertEquals(ride, finishedEvent.getRide());
  }

  @Test
  void processEvent() {
    // Create a new SimulatorService object
    SimulatorService service = new SimulatorService(1);

    // Call the processEvent method on the RideRequestedEvent
    finishedEvent.processEvent(service);

    // Check if the totalRideCount has increased by 1 after calling processEvent()
    assertEquals(1, service.totalRides);
  }

  @Test
  void testEquals() {
    // Test the equals method
    RideFinishedEvent sameEvent = new RideFinishedEvent(ride);
    Ride differentRide = new Ride(ride.getCustomer(), ride.getDriver(), RideType.EXPRESS_PICKUP, 987654321);

    assertTrue(finishedEvent.equals(sameEvent));
    assertFalse(finishedEvent.equals(new RideFinishedEvent(differentRide)));
  }

  @Test
  void testHashCode() {
    // Test the hashCode method
    RideFinishedEvent sameEvent = new RideFinishedEvent(ride);

    assertEquals(finishedEvent.hashCode(), sameEvent.hashCode());
  }
}
