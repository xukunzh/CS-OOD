package Problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

<<<<<<< HEAD
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
>>>>>>> origin/Chen_Haoyuan
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgentTest {
  private String expectedName;
<<<<<<< HEAD
  private Set<Listing<Property, RentalContract>> expectedHelpListing;
=======
  private List<Listing<Property, RentalContract>> expectedHelpListing;
>>>>>>> origin/Chen_Haoyuan
  private double expectedCommissionRate;
  private double expectedTotalEarnings;
  private Agent<Property, RentalContract> testAgent;

  private RentalContract rentalContract;
  private SaleContract saleContract;
  private Listing<Property, RentalContract> testListing1;
  private Listing<Property, RentalContract> testListing2;
  private Listing<Property, SaleContract> testListing3;

  @BeforeEach
  void setUp() {
    expectedName = "Trader Joe";
<<<<<<< HEAD
    expectedHelpListing = new HashSet<>();
=======
    expectedHelpListing = new ArrayList<>();
>>>>>>> origin/Chen_Haoyuan
    expectedCommissionRate = 0.05;
    expectedTotalEarnings = 100000.0;
    testAgent = new Agent<>(expectedName, expectedHelpListing, expectedCommissionRate,
        expectedTotalEarnings);

    ResidentialProperty residentialProperty = new ResidentialProperty("123 Main St", 1200, 3, 1.5);
    CommercialProperty commercialProperty = new CommercialProperty("147 Mercer St", 1000, 2, true);
    rentalContract = new RentalContract(2000.0, true, 12);
    saleContract = new SaleContract(3000.0, false);
    testListing1 = new Listing<>(residentialProperty, rentalContract);
    testListing2 = new Listing<>(commercialProperty, rentalContract);
    testListing3 = new Listing<>(residentialProperty, saleContract);
    testAgent.addListing(testListing1);
  }

  @Test
  void getName() {
    assertEquals(expectedName, testAgent.getName());
  }

  @Test
  void setName() {
    testAgent.setName("Papa John");
    assertEquals("Papa John", testAgent.getName());
  }

  @Test
  void getHelpListing() {
    assertEquals(expectedHelpListing, testAgent.getHelpListing());
  }

  @Test
  void setHelpListing() {
<<<<<<< HEAD
    Set<Listing<Property, RentalContract>> newHelpListing = new HashSet<>();
=======
    List<Listing<Property, RentalContract>> newHelpListing = new ArrayList<>();
>>>>>>> origin/Chen_Haoyuan
    testAgent.setHelpListing(newHelpListing);
    assertEquals(newHelpListing, testAgent.getHelpListing());
  }

  @Test
  void getCommissionRate() {
    assertEquals(expectedCommissionRate, testAgent.getCommissionRate());
  }

  @Test
  void setCommissionRate() {
    testAgent.setCommissionRate(0.1);
    assertEquals(0.1, testAgent.getCommissionRate());
  }

  @Test
  void setCommissionRate_GreaterThanOne() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testAgent.setCommissionRate(1.5));
    assertEquals("Commission rate must be between 0 and 1 (inclusive).", exception.getMessage());
  }

  @Test
  void setCommissionRate_LessThanZero() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testAgent.setCommissionRate(-0.5));
    assertEquals("Commission rate must be between 0 and 1 (inclusive).", exception.getMessage());
  }

  @Test
  void getTotalEarnings() {
    assertEquals(expectedTotalEarnings, testAgent.getTotalEarnings());
  }

  @Test
  void setTotalEarnings() {
    testAgent.setTotalEarnings(150000.0);
    assertEquals(150000.0, testAgent.getTotalEarnings());
  }

  @Test
  void setTotalEarnings_Invalid() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testAgent.setTotalEarnings(-1));
    assertEquals("Total earnings must be non-negative.", exception.getMessage());
  }

  @Test
  void addListing() {
<<<<<<< HEAD
=======
    testAgent.addListing(testListing1);
>>>>>>> origin/Chen_Haoyuan
    testAgent.addListing(testListing2);
    assertTrue(testAgent.getHelpListing().contains(testListing2),
        "Agent should contain the newly added listing.");
  }

  @Test
<<<<<<< HEAD
  void addListing_Duplicates() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testAgent.addListing(testListing1));
    assertEquals("Listing already exists in the Agent's collection.", exception.getMessage());
  }

  @Test
=======
>>>>>>> origin/Chen_Haoyuan
  void completeListing_RentalContract() {
    double initialEarnings = testAgent.getTotalEarnings();
    testAgent.completeListing(testListing1);
    double expectedEarningsFromListing =
        testAgent.getCommissionRate() * rentalContract.getAskingPrice() * rentalContract.getTerm();

    assertFalse(testAgent.getHelpListing().contains(testListing1));
    assertEquals(initialEarnings + expectedEarningsFromListing, testAgent.getTotalEarnings());
  }

  @Test
  void completeListing_SaleContract() {
<<<<<<< HEAD
    Agent<Property, SaleContract> otherAgent = new Agent<>("Pamela", new HashSet<>(), 0.1, 200000.0);
=======
    Agent<Property, SaleContract> otherAgent = new Agent<>("Pamela", new ArrayList<>(), 0.1, 200000.0);
>>>>>>> origin/Chen_Haoyuan
    otherAgent.addListing(testListing3);
    double initialEarnings = otherAgent.getTotalEarnings();
    otherAgent.completeListing(testListing3);
    double expectedEarningsFromListing =
        otherAgent.getCommissionRate() * saleContract.getAskingPrice();

    assertFalse(otherAgent.getHelpListing().contains(testListing3));
    assertEquals(initialEarnings + expectedEarningsFromListing, otherAgent.getTotalEarnings());
  }

  @Test
  void dropListing() {
    assertTrue(testAgent.getHelpListing().contains(testListing1));
    testAgent.dropListing(testListing1);

    assertFalse(testAgent.getHelpListing().contains(testListing1));
  }

  @Test
  void dropListing_NotFound() {
    assertFalse(testAgent.getHelpListing().contains(testListing2));
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testAgent.completeListing(testListing2));
    assertEquals("Listing not found in Agent's collection.", exception.getMessage());
  }

  @Test
  void getTotalPortfolioValue() {
    testAgent.addListing(testListing2);

    double expectedEarningsFromResidential =
        testAgent.getCommissionRate() * rentalContract.getAskingPrice() * rentalContract.getTerm();
    double expectedEarningsFromCommercial =
        testAgent.getCommissionRate() * rentalContract.getAskingPrice() * rentalContract.getTerm();
    double expectedTotalValue = expectedEarningsFromResidential + expectedEarningsFromCommercial;

    assertEquals(expectedTotalValue, testAgent.getTotalPortfolioValue());
  }

  @Test
  void testEquals() {
    Agent<Property, RentalContract> sameAgent = new Agent<>(expectedName, expectedHelpListing,
        expectedCommissionRate, expectedTotalEarnings);
    Agent<Property, RentalContract> differentAgent = new Agent<>("Different Name",
<<<<<<< HEAD
        new HashSet<>(), 0.1, 50000.0);
=======
        new ArrayList<>(), 0.1, 50000.0);
>>>>>>> origin/Chen_Haoyuan

    assertTrue(testAgent.equals(testAgent), "Agents should be equal to itself.");
    assertFalse(testAgent.equals(null), "Agent compared with null should not be equal.");
    assertFalse(testAgent.equals("String"),
        "Agent compared with object of different class should not be equal.");
    assertNotEquals(testAgent, differentAgent,
        "Agents with different properties should not be equal.");
    assertTrue(testAgent.equals(sameAgent), "Agents with the same properties should be equal.");
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash(expectedName, expectedHelpListing,
        expectedCommissionRate, expectedTotalEarnings);
    assertEquals(expectedHashCode, testAgent.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Agent{" +
        "name='" + expectedName + '\'' +
        ", helpListing=" + expectedHelpListing +
        ", commissionRate=" + expectedCommissionRate +
        ", totalEarnings=" + expectedTotalEarnings +
        '}';
    assertEquals(expectedString, testAgent.toString());
  }
}