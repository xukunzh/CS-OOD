package Problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertyTest {
  private String expectedAddress;
  private Integer expectedSize;
  private Property testProperty;

  @BeforeEach
  void setUp() {
    testProperty = new Property("226 Terry Ave", 200);
    expectedAddress = "226 Terry Ave";
    expectedSize = 200;
  }

  @Test
  void testToString() {
    String expectedString = "Property{" +
        "address='" + expectedAddress + '\'' +
        ", size=" + expectedSize +
        '}';
    assertEquals(expectedString, testProperty.toString());
  }
}