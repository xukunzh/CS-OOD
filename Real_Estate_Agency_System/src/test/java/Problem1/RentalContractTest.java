package Problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentalContractTest {
  private double expectedAskingPrice;
  private boolean expectedPriceNegotiable;
  private Integer expectedTerm;
  private RentalContract testRentalContract;

  @BeforeEach
  void setUp() {
    testRentalContract = new RentalContract(2000.0, true, 12);
    expectedAskingPrice = 2000.0;
    expectedPriceNegotiable = true;
    expectedTerm = 12;
  }

  @Test
  void getAskingPrice() {
    assertEquals(expectedAskingPrice, testRentalContract.getAskingPrice());
  }

  @Test
  void setAskingPrice() {
    testRentalContract.setAskingPrice(1800.0);
    assertEquals(1800.0, testRentalContract.getAskingPrice());
  }

  @Test
  void setAskingPrice_Invalid() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testRentalContract.setAskingPrice(-1));
    assertEquals("Asking price must be non-negative.", exception.getMessage());
  }

  @Test
  void isPriceNegotiable() {
    assertEquals(expectedPriceNegotiable, testRentalContract.isPriceNegotiable());
  }

  @Test
  void setPriceNegotiable() {
    testRentalContract.setPriceNegotiable(false);
    assertFalse(testRentalContract.isPriceNegotiable());
  }

  @Test
  void getTerm() {
    assertEquals(expectedTerm, testRentalContract.getTerm());
  }

  @Test
  void setTerm() {
    testRentalContract.setTerm(6);
    assertEquals(6, testRentalContract.getTerm());
  }

  @Test
  void setTerm_Invalid() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testRentalContract.setTerm(-1));
    assertEquals("Term must be greater than 0.", exception.getMessage());
  }

  @Test
  void testToString() {
    String expectedString = "RentalContract{" +
        "term=" + expectedTerm +
        '}';
    assertEquals(expectedString, testRentalContract.toString());
  }
}