package Problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListingTest {
  private ResidentialProperty expectedResidentialProperty;
  private RentalContract expectedRentalContract;
  private Listing<ResidentialProperty, RentalContract> testListing;

  @BeforeEach
  void setUp() {
    expectedResidentialProperty = new ResidentialProperty("226 Terry Ave", 150, 2, 2);
    expectedRentalContract = new RentalContract(4500.0, false, 15);
    testListing = new Listing<>(expectedResidentialProperty, expectedRentalContract);
  }

  @Test
  void getProperty() {
    assertEquals(expectedResidentialProperty, testListing.getProperty());
  }

  @Test
  void setProperty() {
    ResidentialProperty otherResidentialProperty = new ResidentialProperty("226 Terry Ave", 145, 2,
        2);
    testListing.setProperty(otherResidentialProperty);
    assertEquals(otherResidentialProperty, testListing.getProperty());
  }

  @Test
  void getContract() {
    assertEquals(expectedRentalContract, testListing.getContract());
  }

  @Test
  void setContract() {
    RentalContract otherRentalContract = new RentalContract(4500.0, true, 15);
    testListing.setContract(otherRentalContract);
    assertEquals(otherRentalContract, testListing.getContract());
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(testListing.equals(testListing));
  }

  @Test
  void testEquals_NullComparison() {
    assertFalse(testListing.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    assertFalse(testListing.equals("String"));
  }

  @Test
  void testEquals_DifferentProperty() {
    Listing<ResidentialProperty, RentalContract> otherListing = new Listing<>(new ResidentialProperty("227 Terry Ave", 150,
        2, 2), expectedRentalContract);
    assertFalse(testListing.equals(otherListing));
  }

  @Test
  void testEquals_DifferentContract() {
    Listing<ResidentialProperty, RentalContract> otherListing = new Listing<>(expectedResidentialProperty,
        new RentalContract(3500.0, false, 15));
    assertFalse(testListing.equals(otherListing));
  }

  @Test
  void testEquals_DifferentObjectsSameValues() {
    Listing<ResidentialProperty, RentalContract> otherListing = new Listing<>(expectedResidentialProperty, expectedRentalContract);
    assertTrue(testListing.equals(otherListing));
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash(expectedResidentialProperty, expectedRentalContract);
    assertEquals(expectedHashCode, testListing.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Listing{" +
        "property=" + expectedResidentialProperty +
        ", contract=" + expectedRentalContract +
        '}';
    assertEquals(expectedString, testListing.toString());
  }
}