package Problem1;

import java.util.Objects;

/**
 * Class representing a residential property.
 */
public class ResidentialProperty extends Property {
    private Integer numberOfBedrooms;
    private double numberOfBathrooms;

    /**
     * Constructs a new ResidentialProperty object.
     *
     * @param address The address of the residential property, as a String
     * @param size The size of the residential property, as an Integer
     * @param numberOfBedrooms The number of bedrooms of the residential property, as an Integer
     * @param numberOfBathrooms The number of bathrooms of the residential property, as an Integer
     */
    public ResidentialProperty(String address, Integer size, Integer numberOfBedrooms, double numberOfBathrooms) {
        super(address, size);
        validateNumberOfBedrooms(numberOfBedrooms);
        validateNumberOfBathrooms(numberOfBathrooms);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
    }

    /**
     * Helper function to validate the number of bedrooms.
     *
     * @param numberOfBedrooms The number of bedrooms, as an Integer
     * @throws IllegalArgumentException If the number of bedrooms is negative
     */
    private void validateNumberOfBedrooms(Integer numberOfBedrooms) throws IllegalArgumentException {
        if (numberOfBedrooms < 0)
            throw new IllegalArgumentException("Number of bedrooms must be non-negative.");
    }

    /**
     * Helper function to validate the number of bathrooms.
     *
     * @param numberOfBathrooms The number of bathrooms, as an Integer
     * @throws IllegalArgumentException If the number of bathrooms is negative
     */
    private void validateNumberOfBathrooms(double numberOfBathrooms) throws IllegalArgumentException {
        if (numberOfBathrooms < 0)
            throw new IllegalArgumentException("Number of bathrooms must be non-negative.");
    }

    /**
     * Gets the number of bedrooms.
     *
     * @return The number of bedrooms, as an Integer
     */
    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    /**
     * Sets the number of bedrooms.
     *
     * @param numberOfBedrooms The number of bedrooms, as an Integer
     */
    public void setNumberOfBedrooms(Integer numberOfBedrooms) {
        validateNumberOfBedrooms(numberOfBedrooms);
        this.numberOfBedrooms = numberOfBedrooms;
    }

    /**
     * Gets the number of bathrooms.
     *
     * @return The number of bathrooms, as an Integer
     */
    public double getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    /**
     * Sets the number of bathrooms.
     *
     * @param numberOfBathrooms The number of bathrooms, as an Integer
     */
    public void setNumberOfBathrooms(double numberOfBathrooms) {
        validateNumberOfBathrooms(numberOfBathrooms);
        this.numberOfBathrooms = numberOfBathrooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ResidentialProperty that = (ResidentialProperty) o;
        return Double.compare(numberOfBathrooms, that.numberOfBathrooms) == 0
            && Objects.equals(numberOfBedrooms, that.numberOfBedrooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfBedrooms, numberOfBathrooms);
    }

    @Override
    public String toString() {
        return "ResidentialProperty{" +
            "numberOfBedrooms=" + numberOfBedrooms +
            ", numberOfBathrooms=" + numberOfBathrooms +
            '}';
    }
<<<<<<< HEAD
=======

>>>>>>> origin/Chen_Haoyuan
}
