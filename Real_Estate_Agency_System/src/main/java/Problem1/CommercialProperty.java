package Problem1;

import java.util.Objects;

/**
 * Class representing a commercial property.
 */
public class CommercialProperty extends Property {
    private Integer numberOfOffices;
    private boolean suitableForRetail;

    /**
     * Constructs a new CommercialProperty object.
     *
     * @param address The address of the commercial property, as a String
     * @param size The size of the commercial property, as an Integer
     * @param numberOfOffices The number of offices of the commercial property, as an Integer
     * @param suitableForRetail If the commercial property is suitable for retail, as a boolean
     */
    public CommercialProperty(String address, Integer size, Integer numberOfOffices, boolean suitableForRetail) {
        super(address, size);
        validateNumberOfOffices(numberOfOffices);
        this.numberOfOffices = numberOfOffices;
        this.suitableForRetail = suitableForRetail;
    }

    /**
     * Helper function to validate the number of offices.
     *
     * @param numberOfOffices The number of offices to validate, as an Integer
     * @throws IllegalArgumentException If the number of offices is negative
     */
    private void validateNumberOfOffices(Integer numberOfOffices) throws IllegalArgumentException {
        if (numberOfOffices < 0)
            throw new IllegalArgumentException("Number of offices must be non-negative.");
    }

    /**
     * Gets the number of offices.
     *
     * @return The number of offices, as an Integer
     */
    public Integer getNumberOfOffices() {
        return numberOfOffices;
    }

    /**
     * Sets the number of offices.
     *
     * @param numberOfOffices The number of offices, as an Integer
     */
    public void setNumberOfOffices(Integer numberOfOffices) {
        validateNumberOfOffices(numberOfOffices);
        this.numberOfOffices = numberOfOffices;
    }

    /**
     * Gets if the commercial property is suitable for retail.
     *
     * @return True if suitable, false otherwise, as a boolean
     */
    public boolean isSuitableForRetail() {
        return suitableForRetail;
    }

    /**
     * Sets if the commercial property is suitable for retail.
     *
     * @param suitableForRetail True if suitable, false otherwise, as a boolean
     */
    public void setSuitableForRetail(boolean suitableForRetail) {
        this.suitableForRetail = suitableForRetail;
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
        CommercialProperty that = (CommercialProperty) o;
        return suitableForRetail == that.suitableForRetail && Objects.equals(numberOfOffices,
            that.numberOfOffices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfOffices, suitableForRetail);
    }

    @Override
    public String toString() {
        return "CommercialProperty{" +
            "numberOfOffices=" + numberOfOffices +
            ", suitableForRetail=" + suitableForRetail +
            '}';
    }
}
