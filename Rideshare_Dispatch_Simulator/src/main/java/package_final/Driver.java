package package_final;

import java.time.LocalTime;
import java.util.Objects;

/**
 * The Driver class represents a driver in the system.
 */
public class Driver {

  private static final long MAX_WORK_TIME = 90 * 60 * 1000;// Define the maximum work time in milliseconds (90 minutes)
  private String driverId;
  private boolean isAvailable;
  private long availableFrom;
  private String currentLocation;

  /**
   * Constructs a Driver object with the specified driver ID and initial location.
   *
   * @param driverId       The unique identifier of the driver.
   * @param initialLocation The initial location of the driver.
   */
  protected Driver(String driverId, String initialLocation) {
    this.driverId = driverId;
    this.isAvailable = true;
    this.currentLocation = initialLocation;
  }

  /**
   * Checks if the driver is available.
   *
   * @return True if the driver is available, otherwise false.
   */
  protected boolean isAvailable() {
    return isAvailable;
  }

  /**
   * Sets the availability of the driver.
   *
   * @param available         True if the driver is available, otherwise false.
   * @param nextAvailableTime The next available time of the driver.
   */
  protected void setAvailable(boolean available, long nextAvailableTime) {
    // Define the predefined start time (midnight), as generateRides did
    LocalTime orderTime = LocalTime.MIDNIGHT;
    long orderTimeInMillis = orderTime.toSecondOfDay() * 1000L;

    // Check if the driver is becoming available and the next available time
    // exceeds the maximum work time
    if (available && ((nextAvailableTime - orderTimeInMillis) > MAX_WORK_TIME)) {
      return;
    }

    // Update the availability status and next available time for the driver
    this.isAvailable = available;
    this.availableFrom = nextAvailableTime;
  }

  /**
   * Sets the availability of the driver.
   *
   * @param available True if the driver is available, otherwise false.
   */
  protected void setAvailable(boolean available) {
    this.isAvailable = available;
  }

  /**
   * Retrieves the driver ID.
   *
   * @return The driver ID.
   */
  protected String getDriverId() {
    return driverId;
  }

  /**
   * Retrieves the current location of the driver.
   *
   * @return The current location.
   */
  protected String getCurrentLocation() {
    return currentLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Driver driver = (Driver) o;
    return Objects.equals(driverId, driver.driverId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(driverId);
  }
}
