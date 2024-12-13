package package_final;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverUtilsTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void calculateDriverDistanceToCustomer() {
    // Test calculateDriverDistanceToCustomer method
    Driver driver = new Driver("driver123", "InitialLocation5");
    String customerLocation = "Destination8";
    double distance = DriverUtils.calculateDriverDistanceToCustomer(driver, customerLocation);
    assertEquals(3.0, distance); // Expected distance between InitialLocation5 and Destination8 is 3 units
  }

  @Test
  void calculateTravelTimeToCustomer() {
    // Test calculateTravelTimeToCustomer method
    Driver driver = new Driver("driver123", "InitialLocation5");
    Customer customer = new Customer("123", "StartLocation1", "Destination8", 123456789, RideType.WAIT_AND_SAVE_PICKUP);
    Ride ride = new Ride(customer, driver, RideType.WAIT_AND_SAVE_PICKUP, 123456789);
    long travelTime = DriverUtils.calculateTravelTimeToCustomer(driver, ride);
    assertEquals(240000L, travelTime);
  }
}
