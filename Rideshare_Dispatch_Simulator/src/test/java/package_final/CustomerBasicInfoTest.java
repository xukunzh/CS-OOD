package package_final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerBasicInfoTest {

  private CustomerBasicInfo customer;

  @BeforeEach
  void setUp() {
    // Initialize a CustomerBasicInfo object for testing
    customer = new CustomerBasicInfo("123", "StartLocation", "Destination", 123456789, RideType.EXPRESS_PICKUP);
  }

  @Test
  void getId() {
    assertEquals("123", customer.getId());
  }

  @Test
  void getStartLocation() {
    assertEquals("StartLocation", customer.getStartLocation());
  }

  @Test
  void getDestination() {
    assertEquals("Destination", customer.getDestination());
  }

  @Test
  void getOrderTime() {
    assertEquals(123456789, customer.getOrderTime());
  }

  @Test
  void getRideType() {
    assertEquals(RideType.EXPRESS_PICKUP, customer.getRideType());
  }


  @Test
  void testEquals() {
    CustomerBasicInfo sameCustomer = new CustomerBasicInfo("123", "StartLocation", "Destination", 123456789, RideType.EXPRESS_PICKUP);
    CustomerBasicInfo differentCustomer = new CustomerBasicInfo("456", "DifferentStart", "DifferentDestination", 987654321, RideType.STANDARD_PICKUP);

    assertTrue(customer.equals(sameCustomer));
    assertFalse(customer.equals(differentCustomer));
  }

  @Test
  void testHashCode() {
    CustomerBasicInfo sameCustomer = new CustomerBasicInfo("123", "StartLocation", "Destination", 123456789, RideType.EXPRESS_PICKUP);

    assertEquals(customer.hashCode(), sameCustomer.hashCode());
  }
}
