package Problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommercialPropertyTest {
  private Integer expectedNumberOfOffices;
  private boolean expectedSuitableForRetail;
  private CommercialProperty testCommercialProperty;

  @BeforeEach
  void setUp() {
    testCommercialProperty = new CommercialProperty("227 Terry Ave", 150, 5, true);
    expectedNumberOfOffices = 5;
    expectedSuitableForRetail = true;
  }

  @Test
  void getNumberOfOffices() {
    assertEquals(expectedNumberOfOffices, testCommercialProperty.getNumberOfOffices());
  }

  @Test
  void setNumberOfOffices() {
    testCommercialProperty.setNumberOfOffices(7);
    assertEquals(7, testCommercialProperty.getNumberOfOffices());
  }

  @Test
  void setNumberOfOffices_Invalid() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testCommercialProperty.setNumberOfOffices(-1));
    assertEquals("Number of offices must be non-negative.", exception.getMessage());
  }

  @Test
  void isSuitableForRetail() {
    assertEquals(expectedSuitableForRetail, testCommercialProperty.isSuitableForRetail());
  }

  @Test
  void setSuitableForRetail() {
    testCommercialProperty.setSuitableForRetail(false);
    assertFalse(testCommercialProperty.isSuitableForRetail());
  }

  @Test
  void testToString() {
    String expectedString = "CommercialProperty{" +
        "numberOfOffices=" + expectedNumberOfOffices +
        ", suitableForRetail=" + expectedSuitableForRetail +
        '}';
    assertEquals(expectedString, testCommercialProperty.toString());
  }
}