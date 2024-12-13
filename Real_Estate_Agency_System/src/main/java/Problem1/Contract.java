package Problem1;

<<<<<<< HEAD
/**
 * Class representing a contract.
 */
public abstract class Contract {
=======
import java.util.Objects;

/**
 * Class representing a contract.
 */
public class Contract {
>>>>>>> origin/Chen_Haoyuan
    private double askingPrice;
    private boolean priceNegotiable;

    /**
     * Constructs a new Contract object.
     *
     * @param askingPrice The asking price of the contract, as a double
     * @param priceNegotiable If the price is negotiable, as a boolean
     */
<<<<<<< HEAD
    protected Contract(double askingPrice, boolean priceNegotiable) {
=======
    public Contract(double askingPrice, boolean priceNegotiable) {
>>>>>>> origin/Chen_Haoyuan
        validateAskingPrice(askingPrice);
        this.askingPrice = askingPrice;
        this.priceNegotiable = priceNegotiable;
    }

    /**
     * Helper function to validate the asking price.
     *
     * @param askingPrice The asking price, as a double
     * @throws IllegalArgumentException If the asking price is negative
     */
    private void validateAskingPrice(double askingPrice) throws IllegalArgumentException {
        if (askingPrice < 0)
            throw new IllegalArgumentException("Asking price must be non-negative.");
    }

    /**
     * Gets the asking price.
     *
     * @return The asking price, as a double
     */
    public double getAskingPrice() {
        return askingPrice;
    }

    /**
     * Sets the asking price.
     *
     * @param askingPrice The asking price, as a double
     */
    public void setAskingPrice(double askingPrice) {
        validateAskingPrice(askingPrice);
        this.askingPrice = askingPrice;
    }

    /**
     * Gets if the price is negotiable.
     *
     * @return True if negotiable, false otherwise, as a boolean
     */
    public boolean isPriceNegotiable() {
        return priceNegotiable;
    }

    /**
     * Sets if the price is negotiable.
     *
     * @param priceNegotiable True if negotiable, false otherwise, as a boolean
     */
    public void setPriceNegotiable(boolean priceNegotiable) {
        this.priceNegotiable = priceNegotiable;
    }

<<<<<<< HEAD
    /**
     * Gets the price of the contract.
     * This method will be implemented by each subclass.
     *
     * @return The price of the contract, as a double
     */
    public abstract double getPrice();
=======
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contract contract = (Contract) o;
        return Double.compare(askingPrice, contract.askingPrice) == 0
            && priceNegotiable == contract.priceNegotiable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(askingPrice, priceNegotiable);
    }

    @Override
    public String toString() {
        return "Contract{" +
            "askingPrice=" + askingPrice +
            ", priceNegotiable=" + priceNegotiable +
            '}';
    }
>>>>>>> origin/Chen_Haoyuan
}
