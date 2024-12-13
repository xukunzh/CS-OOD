package Problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaleContractTest {
  private SaleContract testSaleContract;

  @BeforeEach
  void setUp() {
    testSaleContract = new SaleContract(2000.0, true);
  }

  @Test
  void testConstructor() {
    assertEquals(testSaleContract, testSaleContract);
  }
}