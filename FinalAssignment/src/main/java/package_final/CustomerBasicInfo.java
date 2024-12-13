package package_final;

import java.util.Objects;

/**
 * CustomerBasicInfo class represents the basic information of a customer, including their ID, start location,
 * destination, order time, and ride type.
 */
public class CustomerBasicInfo {
  private String id;
  private String startLocation;
  private String destination;
  private int orderTime;
  protected RideType rideType;

  /**
   * Constructs a CustomerBasicInfo object with the specified parameters.
   *
   * @param id             The unique identifier of the customer.
   * @param startLocation  The starting location of the customer's ride.
   * @param destination    The destination of the customer's ride.
   * @param orderTime      The time when the customer's ride was ordered.
   * @param rideType       The type of ride requested by the customer.
   */
  protected CustomerBasicInfo(String id, String startLocation, String destination, int orderTime, RideType rideType) {
    this.id = id;
    this.startLocation = startLocation;
    this.destination = destination;
    this.orderTime = orderTime;  // Capture the order time when the customer is created
    this.rideType = rideType;
  }

  /**
   * Retrieves the ID of the customer.
   *
   * @return The ID of the customer.
   */
  protected String getId() {
    return id;
  }

  /**
   * Retrieves the start location of the customer's ride.
   *
   * @return The start location.
   */
  protected String getStartLocation() {
    return startLocation;
  }

  /**
   * Retrieves the destination of the customer's ride.
   *
   * @return The destination.
   */
  protected String getDestination() {
    return destination;
  }

  /**
   * Retrieves the order time when the customer's ride was ordered.
   *
   * @return The order time.
   */
  protected int getOrderTime() {
    return orderTime;
  }

  /**
   * Retrieves the type of ride requested by the customer.
   *
   * @return The ride type.
   */
  protected RideType getRideType() {
    return rideType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerBasicInfo that = (CustomerBasicInfo) o;
    return orderTime == that.orderTime &&
        Objects.equals(id, that.id) &&
        Objects.equals(startLocation, that.startLocation) &&
        Objects.equals(destination, that.destination) &&
        rideType == that.rideType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, startLocation, destination, orderTime, rideType);
  }
}
