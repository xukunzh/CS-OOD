package package_final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverTest {

  private Driver driver;

  @BeforeEach
  void setUp() {
    // Initialize a Driver object for testing
    driver = new Driver("123", "InitialLocation0");
  }

  @Test
  void isAvailable() {
    // Test the isAvailable method when driver is initially available
    assertTrue(driver.isAvailable());

    // Test the isAvailable method after setting driver as not available
    driver.setAvailable(false);
    assertFalse(driver.isAvailable());
  }

  @Test
  void setAvailable() {
    // Test the setAvailable method
    driver.setAvailable(false);
    assertFalse(driver.isAvailable());
  }

  @Test
  void testSetAvailable() {
    // Test the setAvailable method with next available time
    driver.setAvailable(false, System.currentTimeMillis() + 1000);
    assertFalse(driver.isAvailable());
  }

  @Test
  void getDriverId() {
    // Test the getDriverId method
    assertEquals("123", driver.getDriverId());
  }

  @Test
  void getCurrentLocation() {
    // Test the getCurrentLocation method
    assertEquals("InitialLocation0", driver.getCurrentLocation());
  }

  @Test
  void testEquals() {
    // Test the equals method
    Driver sameDriver = new Driver("123", "InitialLocation0");
    Driver differentDriver = new Driver("456", "DifferentLocation999");

    assertTrue(driver.equals(sameDriver));
    assertFalse(driver.equals(differentDriver));
  }

  @Test
  void testHashCode() {
    // Test the hashCode method
    Driver sameDriver = new Driver("123", "InitialLocation0");

    assertEquals(driver.hashCode(), sameDriver.hashCode());
  }
}
