package Problem1;

<<<<<<< HEAD
import java.util.Objects;
import java.util.Set;
=======
import java.util.List;
import java.util.Objects;
>>>>>>> origin/Chen_Haoyuan

/**
 * Class representing an agent responsible for managing listings.
 *
 * @param <P> The type of Property
 * @param <C> The type of Contract
 */
public class Agent<P extends Problem1.Property, C extends Contract> implements IAgent<P, C> {
    private String name;
<<<<<<< HEAD
    private Set<Listing<P, C>> helpListing;
=======
    private List<Listing<P, C>> helpListing;
>>>>>>> origin/Chen_Haoyuan
    private double commissionRate;
    private double totalEarnings;

    /**
     * Constructs a new Agent object.
     *
     * @param name The name of the agent, as a String
     * @param helpListing The collection of current listings of the agent, as a List<Listing<P, C>>
     * @param commissionRate The commission rate of the agent, as a double
     * @param totalEarnings The total earnings of the agent, as a double
     */
<<<<<<< HEAD
    public Agent(String name, Set<Listing<P, C>> helpListing, double commissionRate, double totalEarnings) {
=======
    public Agent(String name, List<Listing<P, C>> helpListing, double commissionRate, double totalEarnings) {
>>>>>>> origin/Chen_Haoyuan
        validateCommissionRate(commissionRate);
        validateTotalEarnings(totalEarnings);
        this.name = name;
        this.helpListing = helpListing;
        this.commissionRate = commissionRate;
        this.totalEarnings = totalEarnings;
    }

    /**
     * Helper function to validate the commission rate.
     *
     * @param commissionRate The commission rate, as a double
     * @throws IllegalArgumentException If the commission rate is not between 0 and 1 (inclusive)
     */
    private void validateCommissionRate(double commissionRate) throws IllegalArgumentException {
        if (commissionRate < 0 || commissionRate > 1)
            throw new IllegalArgumentException("Commission rate must be between 0 and 1 (inclusive).");
    }

    /**
     * Helper function to validate the total earnings.
     *
     * @param totalEarnings The total earnings, as a double
     * @throws IllegalArgumentException If the total earning is negative
     */
    private void validateTotalEarnings(double totalEarnings) throws IllegalArgumentException {
        if (totalEarnings < 0)
            throw new IllegalArgumentException("Total earnings must be non-negative.");
    }

    /**
     * Gets the name.
     *
     * @return The name, as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name The name, as a String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the collection.
     *
     * @return The collection, as a List<Listing<P, C>>
     */
<<<<<<< HEAD
    public Set<Listing<P, C>> getHelpListing() {
=======
    public List<Listing<P, C>> getHelpListing() {
>>>>>>> origin/Chen_Haoyuan
        return helpListing;
    }

    /**
     * Sets the collection.
     *
     * @param helpListing The collection, as a List<Listing<P, C>>
     */
<<<<<<< HEAD
    public void setHelpListing(Set<Listing<P, C>> helpListing) {
=======
    public void setHelpListing(List<Listing<P, C>> helpListing) {
>>>>>>> origin/Chen_Haoyuan
        this.helpListing = helpListing;
    }

    /**
     * Gets the commission rate.
     *
     * @return The commission rate, as a double
     */
    public double getCommissionRate() {
        return commissionRate;
    }

    /**
     * Sets the commission rate.
     *
     * @param commissionRate The commission rate, as a double
     */
    public void setCommissionRate(double commissionRate) {
        validateCommissionRate(commissionRate);
        this.commissionRate = commissionRate;
    }

    /**
     * Gets the total earnings.
     *
     * @return The total earnings, as a double
     */
    public double getTotalEarnings() {
        return totalEarnings;
    }

    /**
     * Sets the total earnings.
     *
     * @param totalEarnings The total earnings, as a double
     */
    public void setTotalEarnings(double totalEarnings) {
        validateTotalEarnings(totalEarnings);
        this.totalEarnings = totalEarnings;
    }

    /**
     * Adds an appropriate listing to the agent's collection.
     *
     * @param listing The listing to add, as a Listing<P, C>
<<<<<<< HEAD
     * @throws IllegalArgumentException If the listing already exists in the agent's collection
     */
    @Override
    public void addListing(Listing<P, C> listing) throws IllegalArgumentException {
        if (helpListing.contains(listing)) // no duplicates
            throw new IllegalArgumentException("Listing already exists in the Agent's collection.");
        helpListing.add(listing);
=======
     */
    @Override
    public void addListing(Listing<P, C> listing) {
        if (!helpListing.contains(listing))
            helpListing.add(listing);
>>>>>>> origin/Chen_Haoyuan
    }

    /**
     * Removes a listing from the agent's collection if present,
     * And adds earnings to the agent's total earnings.
     *
     * @param listing The listing to remove, as a Listing<P, C>
     * @throws IllegalArgumentException If the listing is not present in the agent's collection
     */
    @Override
    public void completeListing(Listing<P, C> listing) throws IllegalArgumentException {
        dropListing(listing); // if not found, drop will handle
        totalEarnings += calculateEarnings(listing);
    }

    /**
     * Helper function to calculate the amount of earnings.
     *
     * @param listing The listing to calculate, as a Listing<P, C>
     * @return The amount of earnings, as a double
<<<<<<< HEAD
     */
    private double calculateEarnings(Listing<P, C> listing) {
        return commissionRate * listing.getContract().getPrice(); // subtype polymorphism
=======
     * @throws IllegalArgumentException If the contract type is unknown
     */
    private double calculateEarnings(Listing<P, C> listing) throws IllegalArgumentException {
        C contract = listing.getContract();
        if (contract instanceof Problem1.SaleContract)
            return commissionRate * contract.getAskingPrice();
        else if (contract instanceof Problem1.RentalContract rentalContract)
            return commissionRate * contract.getAskingPrice() * rentalContract.getTerm();
        else
            throw new IllegalArgumentException("Unknown contract type.");
>>>>>>> origin/Chen_Haoyuan
    }

    /**
     * Drops a listing from the agent's collection if present without adjusting his total earnings.
     *
     * @param listing The listing to drop, as a Listing<P, C>
     * @throws IllegalArgumentException If the listing is not present in the agent's collection
     */
    @Override
    public void dropListing(Listing<P, C> listing) throws IllegalArgumentException {
        if (!(helpListing.contains(listing)))
            throw new IllegalArgumentException("Listing not found in Agent's collection.");
        helpListing.remove(listing);
    }

    /**
     * Gets the amount of earnings the agent would make if he completed all listings in his collection.
     *
     * @return The amount of earnings, as a double
     */
    @Override
    public double getTotalPortfolioValue() {
        double totalValue = 0.0;
        for (Listing<P, C> listing : helpListing)
            totalValue += calculateEarnings(listing);
        return totalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Agent<?, ?> agent = (Agent<?, ?>) o;
        return Double.compare(commissionRate, agent.commissionRate) == 0
            && Double.compare(totalEarnings, agent.totalEarnings) == 0
            && Objects.equals(name, agent.name) && Objects.equals(helpListing,
            agent.helpListing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, helpListing, commissionRate, totalEarnings);
    }

    @Override
    public String toString() {
        return "Agent{" +
            "name='" + name + '\'' +
            ", helpListing=" + helpListing +
            ", commissionRate=" + commissionRate +
            ", totalEarnings=" + totalEarnings +
            '}';
    }
}
