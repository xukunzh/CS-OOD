package package_final;

import java.util.Objects;

/**
 * The Customer class represents a customer entity in the system, extending the basic customer information.
 * It includes additional functionality such as distance calculation between start and end locations.
 */
public class Customer extends CustomerBasicInfo {
  private double distance;

  /**
   * Constructs a Customer object with the specified parameters.
   *
   * @param id            The unique identifier of the customer.
   * @param startLocation The starting location of the customer's ride.
   * @param destination   The destination of the customer's ride.
   * @param orderTime     The time when the customer's ride was ordered.
   * @param rideType      The type of ride requested by the customer.
   */
  protected Customer(String id, String startLocation, String destination, int orderTime, RideType rideType) {
    super(id, startLocation, destination, orderTime, rideType);
    this.distance = calculateDistance(startLocation, destination);
  }

  /**
   * Calculates the distance between two locations.
   *
   * @param start The starting location.
   * @param end   The destination location.
   * @return The distance between the two locations.
   */
  private double calculateDistance(String start, String end) {
    int startNum = Integer.parseInt(start.replaceAll("\\D+", ""));
    int endNum = Integer.parseInt(end.replaceAll("\\D+", ""));
    return Math.abs(endNum - startNum);
  }

  /**
   * Retrieves the distance between the start and end locations.
   *
   * @return The distance.
   */
  protected double getDistance() {
    return distance;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Double.compare(customer.distance, distance) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), distance);
  }
}
