package package_final;

/**
 * The RideType enum represents different types of ride requests.
 * Each ride type has an associated priority.
 */
public enum RideType {
  EXPRESS_PICKUP(4),  // Highest priority
  STANDARD_PICKUP(3),
  WAIT_AND_SAVE_PICKUP(2),
  ENVIRONMENTALLY_CONSCIOUS_PICKUP(1);  // Lowest priority

  // Private variable, encapsulating the priority of each ride type
  private final int priority;

  /**
   * Constructs a RideType enum constant with the specified priority.
   *
   * @param priority The priority of the ride type.
   */
  RideType(int priority) {
    this.priority = priority;
  }

  /**
   * Retrieves the priority of the ride type.
   *
   * @return The priority value.
   */
  int getPriority() {
    return this.priority;
  }
}
