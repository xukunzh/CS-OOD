package package_final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RideTest {

  private Ride ride;
  private Customer customer;
  private Driver driver;

  @BeforeEach
  void setUp() {
    // Initialize a Customer object for testing
    customer = new Customer("123", "StartLocation1", "Destination2", 123456789, RideType.WAIT_AND_SAVE_PICKUP);
    driver = new Driver("driver123", "InitialLocation");
    ride = new Ride(customer, driver, RideType.WAIT_AND_SAVE_PICKUP, 123456789);
  }

  @Test
  void getCustomer() {
    // Test the getCustomer method
    assertEquals(customer, ride.getCustomer());
  }

  @Test
  void getDriver() {
    // Test the getDriver method
    assertEquals(driver, ride.getDriver());
  }

  @Test
  void setDriver() {
    // Test the setDriver method
    Driver newDriver = new Driver("newDriver123", "NewLocation");
    ride.setDriver(newDriver);
    assertEquals(newDriver, ride.getDriver());
  }

  @Test
  void getRideType() {
    // Test the getRideType method
    assertEquals(RideType.WAIT_AND_SAVE_PICKUP, ride.getRideType());
  }

  @Test
  void getRequestTime() {
    // Test the getRequestTime method
    assertEquals(123456789, ride.getRequestTime());
  }

  @Test
  void getStartTime() {
    // Test the getStartTime method
    assertEquals(0, ride.getStartTime()); // Modify this based on your actual implementation
  }

  @Test
  void setStartTime() {
    // Test the setStartTime method
    ride.setStartTime(123456789);
    assertEquals(123456789, ride.getStartTime());
  }

  @Test
  void getEndTime() {
    // Test the getEndTime method
    assertEquals(0, ride.getEndTime()); // Modify this based on your actual implementation
  }

  @Test
  void setEndTime() {
    // Test the setEndTime method
    ride.setEndTime(123456789);
    assertEquals(123456789, ride.getEndTime());
  }

  @Test
  void testEquals() {
    // Test the equals method
    Ride sameRide = new Ride(customer, driver, RideType.WAIT_AND_SAVE_PICKUP, 123456789);
    Ride differentRide = new Ride(customer, driver, RideType.EXPRESS_PICKUP, 987654321);

    assertTrue(ride.equals(sameRide));
    assertFalse(ride.equals(differentRide));
  }

  @Test
  void testHashCode() {
    // Test the hashCode method
    Ride sameRide = new Ride(customer, driver, RideType.WAIT_AND_SAVE_PICKUP, 123456789);

    assertEquals(ride.hashCode(), sameRide.hashCode());
  }
}
