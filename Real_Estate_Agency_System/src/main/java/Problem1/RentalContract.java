package Problem1;

import java.util.Objects;

/**
 * Class representing a rental contract.
 */
public class RentalContract extends Contract {
    private Integer term;

    /**
     * Constructs a new RentalContract object.
     *
     * @param askingPrice The asking price of the rental contract, as a double
     * @param priceNegotiable If the price is negotiable, as a boolean
     * @param term The term in months, indicating the length of the rental contract, as an Integer
     */
    public RentalContract(double askingPrice, boolean priceNegotiable, Integer term) {
        super(askingPrice, priceNegotiable);
        validateTerm(term);
        this.term = term;
    }

    /**
     * Helper function to validate the term.
     *
     * @param term The term, as an Integer
     * @throws IllegalArgumentException If the term is less than 0
     */
    private void validateTerm(Integer term) throws IllegalArgumentException {
        if (term <= 0)
            throw new IllegalArgumentException("Term must be greater than 0.");
    }

    /**
     * Gets the term.
     *
     * @return The term, as an Integer
     */
    public Integer getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term The term, as an Integer
     */
    public void setTerm(Integer term) {
        validateTerm(term);
        this.term = term;
    }

<<<<<<< HEAD
    /**
     * Gets the price of the contract.
     *
     * @return The price of the contract, as a double
     */
    @Override
    public double getPrice() {
        return getAskingPrice() * term;
    }

=======
>>>>>>> origin/Chen_Haoyuan
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
        RentalContract that = (RentalContract) o;
        return Objects.equals(term, that.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), term);
    }

    @Override
    public String toString() {
        return "RentalContract{" +
            "term=" + term +
            '}';
    }
}
