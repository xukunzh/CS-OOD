package Problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResidentialPropertyTest {
  private String expectedAddress;
  private Integer expectedSize;
  private Integer expectedNumberOfBedrooms;
  private double expectedNumberOfBathrooms;
  private ResidentialProperty testResidentialProperty;

  @BeforeEach
  void setUp() {
    testResidentialProperty = new ResidentialProperty("226 Terry Ave", 150, 2, 2);
    expectedAddress = "226 Terry Ave";
    expectedSize = 150;
    expectedNumberOfBedrooms = 2;
    expectedNumberOfBathrooms = 2;
  }

  @Test
  void getAddress() {
    assertEquals(expectedAddress, testResidentialProperty.getAddress());
  }

  @Test
  void setAddress() {
    testResidentialProperty.setAddress("227 Terry Ave");
    assertEquals("227 Terry Ave", testResidentialProperty.getAddress());
  }

  @Test
  void getSize() {
    assertEquals(expectedSize, testResidentialProperty.getSize());
  }

  @Test
  void setSize() {
    testResidentialProperty.setSize(145);
    assertEquals(145, testResidentialProperty.getSize());
  }

  @Test
  void setSize_Invalid() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testResidentialProperty.setSize(-1));
    assertEquals("Size must be greater than 0.", exception.getMessage());
  }

  @Test
  void getNumberOfBedrooms() {
    assertEquals(expectedNumberOfBedrooms, testResidentialProperty.getNumberOfBedrooms());
  }

  @Test
  void setNumberOfBedrooms() {
    testResidentialProperty.setNumberOfBedrooms(3);
    assertEquals(3, testResidentialProperty.getNumberOfBedrooms());
  }

  @Test
  void setNumberOfBedrooms_Invalid() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testResidentialProperty.setNumberOfBedrooms(-1));
    assertEquals("Number of bedrooms must be non-negative.", exception.getMessage());
  }

  @Test
  void getNumberOfBathrooms() {
    assertEquals(expectedNumberOfBathrooms, testResidentialProperty.getNumberOfBathrooms());
  }

  @Test
  void setNumberOfBathrooms() {
    testResidentialProperty.setNumberOfBathrooms(1);
    assertEquals(1, testResidentialProperty.getNumberOfBathrooms());
  }

  @Test
  void setNumberOfBathrooms_Invalid() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> testResidentialProperty.setNumberOfBathrooms(-1));
    assertEquals("Number of bathrooms must be non-negative.", exception.getMessage());
  }

  @Test
  void testToString() {
    String expectedString = "ResidentialProperty{" +
        "numberOfBedrooms=" + expectedNumberOfBedrooms +
        ", numberOfBathrooms=" + expectedNumberOfBathrooms +
        '}';
    assertEquals(expectedString, testResidentialProperty.toString());
  }
}