package Problem1;

/**
 * Interface representing an agent responsible for managing listings.
 *
 * @param <P> The type of Property
 * @param <C> The type of Contract
 */
public interface IAgent<P extends Property, C extends Contract> {

  /**
   * Adds an appropriate listing to the agent's collection.
   *
   * @param listing The listing to add, as a Listing<P, C>
   */
  void addListing(Listing<P, C> listing);

  /**
   * Removes a listing from the agent's collection if present,
   * And adds earnings to the agent's total earnings.
   *
   * @param listing The listing to remove, as a Listing<P, C>
   * @throws IllegalArgumentException If the listing is not present in the agent's collection
   */
  void completeListing(Listing<P, C> listing) throws IllegalArgumentException;

  /**
   * Drops a listing from the agent's collection if present without adjusting his total earnings.
   *
   * @param listing The listing to drop, as a Listing<P, C>
   * @throws IllegalArgumentException If the listing is not present in the agent's collection
   */
  void dropListing(Listing<P, C> listing) throws IllegalArgumentException;

  /**
   * Gets the amount of earnings the agent would make if he completed all listings in his collection.
   *
   * @return The amount of earnings, as a double
   */
  double getTotalPortfolioValue();
}
