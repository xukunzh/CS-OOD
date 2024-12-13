package package_final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private Customer customer;

  @BeforeEach
  void setUp() {
    // Initialize a Customer object for testing
    customer = new Customer("123", "StartLocation1", "Destination2", 123456789, RideType.WAIT_AND_SAVE_PICKUP);
  }

  @Test
  void getDistance() {
    // Test the getDistance method
    assertEquals(1, customer.getDistance()); // Modify this based on the actual calculation
  }

  @Test
  void testEquals() {
    // Test the equals method
    Customer sameCustomer = new Customer("123", "StartLocation1", "Destination2", 123456789, RideType.WAIT_AND_SAVE_PICKUP);
    Customer differentCustomer = new Customer("456", "DifferentStart0", "DifferentDestination9", 987654321, RideType.EXPRESS_PICKUP);

    assertTrue(customer.equals(sameCustomer));
    assertFalse(customer.equals(differentCustomer));
  }

  @Test
  void testHashCode() {
    // Test the hashCode method
    Customer sameCustomer = new Customer("123", "StartLocation1", "Destination2", 123456789, RideType.WAIT_AND_SAVE_PICKUP);

    assertEquals(customer.hashCode(), sameCustomer.hashCode());
  }
}
