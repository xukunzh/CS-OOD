package package_final;

import java.util.Objects;

/**
 * The Ride class represents a ride request in the system.
 */
public class Ride {
  private Customer customer;
  private Driver driver;  // Can be null if no drivers are available initially
  private RideType rideType;
  private long requestTime;
  private long startTime;
  private long endTime;

  /**
   * Constructs a Ride object with the specified customer, driver, ride type, and request time.
   *
   * @param customer    The customer requesting the ride.
   * @param driver      The driver assigned to the ride (can be null if no drivers are available initially).
   * @param rideType    The type of ride requested.
   * @param requestTime The time when the ride request was made.
   */
  protected Ride(Customer customer, Driver driver, RideType rideType, long requestTime) {
    this.customer = customer;
    this.driver = driver;
    this.rideType = rideType;
    this.requestTime = requestTime;
  }

  /**
   * Retrieves the customer associated with the ride.
   *
   * @return The customer.
   */
  protected Customer getCustomer() {
    return customer;
  }

  /**
   * Retrieves the driver assigned to the ride.
   *
   * @return The driver.
   */
  protected Driver getDriver() {
    return driver;
  }

  /**
   * Sets the driver assigned to the ride.
   *
   * @param driver The driver to be assigned.
   */
  protected void setDriver(Driver driver) {
    this.driver = driver;
  }

  /**
   * Retrieves the ride type.
   *
   * @return The ride type.
   */
  protected RideType getRideType() {
    return rideType;
  }

  /**
   * Retrieves the time when the ride request was made.
   *
   * @return The request time.
   */
  protected long getRequestTime() {
    return requestTime;
  }

  /**
   * Retrieves the time when the ride started.
   *
   * @return The start time.
   */
  protected long getStartTime() {
    return startTime;
  }

  /**
   * Sets the time when the ride started.
   *
   * @param startTime The start time to be set.
   */
  protected void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  /**
   * Retrieves the time when the ride ended.
   *
   * @return The end time.
   */
  protected long getEndTime() {
    return endTime;
  }

  /**
   * Sets the time when the ride ended.
   *
   * @param endTime The end time to be set.
   */
  protected void setEndTime(long endTime) {
    this.endTime = endTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ride ride = (Ride) o;
    return requestTime == ride.requestTime &&
        Objects.equals(customer, ride.customer) &&
        Objects.equals(driver, ride.driver) &&
        rideType == ride.rideType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer, driver, rideType, requestTime);
  }
}
