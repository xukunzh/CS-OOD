package Problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContractTest {
  private double expectedAskingPrice;
  private boolean expectedPriceNegotiable;
  private Contract testContract;

  @BeforeEach
  void setUp() {
    testContract = new Contract(1500.0, false);
    expectedAskingPrice = 1500.0;
    expectedPriceNegotiable = false;
  }

  @Test
  void testToString() {
    String expectedString = "Contract{" +
        "askingPrice=" + expectedAskingPrice +
        ", priceNegotiable=" + expectedPriceNegotiable +
        '}';
    assertEquals(expectedString, testContract.toString());
  }
}