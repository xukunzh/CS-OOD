package Problem1;

/**
 * Class representing a sale contract.
 */
public class SaleContract extends Contract {

    /**
     * Constructs a new SaleContract object.
     *
     * @param askingPrice The asking price of the sale contract, as a double
     * @param priceNegotiable If the price is negotiable, as a boolean
     */
    public SaleContract(double askingPrice, boolean priceNegotiable) {
        super(askingPrice, priceNegotiable);
    }
<<<<<<< HEAD

    /**
     * Gets the price of the contract.
     *
     * @return The price of the contract, as a double
     */
    @Override
    public double getPrice() {
        return getAskingPrice();
    }
=======
>>>>>>> origin/Chen_Haoyuan
}
