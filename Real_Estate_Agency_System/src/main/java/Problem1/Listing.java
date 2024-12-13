package Problem1;

import java.util.Objects;

/**
 * Class representing a listing with a property and a contract.
 *
 * @param <P> The type of Property in the listing
 * @param <C> The type of Contract in the listing
 */
public class Listing<P extends Property, C extends Contract> {
    private P property;
    private C contract;

    /**
     * Constructs a new Listing<P, C> object.
     *
     * @param property The property of the listing
     * @param contract The contract of the listing
     */
    public Listing(P property, C contract) {
        this.property = property;
        this.contract = contract;
    }

    /**
     * Gets the property.
     *
     * @return The property
     */
    public P getProperty() {
        return property;
    }

    /**
     * Sets the property.
     *
     * @param property The property
     */
    public void setProperty(P property) {
        this.property = property;
    }

    /**
     * Gets the contract.
     *
     * @return The contract
     */
    public C getContract() {
        return contract;
    }

    /**
     * Sets the contract.
     *
     * @param contract The contract
     */
    public void setContract(C contract) {
        this.contract = contract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Listing<?, ?> listing = (Listing<?, ?>) o;
        return Objects.equals(property, listing.property) && Objects.equals(contract,
            listing.contract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, contract);
    }

    @Override
    public String toString() {
        return "Listing{" +
            "property=" + property +
            ", contract=" + contract +
            '}';
    }
}
