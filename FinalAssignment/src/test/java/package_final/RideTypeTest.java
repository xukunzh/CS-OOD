package package_final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RideTypeTest {

  @BeforeEach
  void setUp() {
    // Optional: any setup code you want to execute before each test method
  }

  @Test
  void getPriority() {
    // Test the getPriority method for each RideType enum constant
    assertEquals(4, RideType.EXPRESS_PICKUP.getPriority());
    assertEquals(3, RideType.STANDARD_PICKUP.getPriority());
    assertEquals(2, RideType.WAIT_AND_SAVE_PICKUP.getPriority());
    assertEquals(1, RideType.ENVIRONMENTALLY_CONSCIOUS_PICKUP.getPriority());
  }

  @Test
  void values() {
    // Test the values method to ensure all enum constants are present
    RideType[] rideTypes = RideType.values();
    assertEquals(4, rideTypes.length);
    assertEquals(RideType.EXPRESS_PICKUP, rideTypes[0]);
    assertEquals(RideType.STANDARD_PICKUP, rideTypes[1]);
    assertEquals(RideType.WAIT_AND_SAVE_PICKUP, rideTypes[2]);
    assertEquals(RideType.ENVIRONMENTALLY_CONSCIOUS_PICKUP, rideTypes[3]);
  }

  @Test
  void valueOf() {
    // Test the valueOf method to ensure correct enum constant retrieval
    assertEquals(RideType.EXPRESS_PICKUP, RideType.valueOf("EXPRESS_PICKUP"));
    assertEquals(RideType.STANDARD_PICKUP, RideType.valueOf("STANDARD_PICKUP"));
    assertEquals(RideType.WAIT_AND_SAVE_PICKUP, RideType.valueOf("WAIT_AND_SAVE_PICKUP"));
    assertEquals(RideType.ENVIRONMENTALLY_CONSCIOUS_PICKUP, RideType.valueOf("ENVIRONMENTALLY_CONSCIOUS_PICKUP"));
  }
}
