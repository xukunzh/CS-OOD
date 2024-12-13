package package_final;

/**
 * Utility class for calculating distances and travel times related to drivers.
 */
public class DriverUtils {

  /** Constant to convert minutes to milliseconds */
  protected static final long MILLISECONDS_PER_MINUTE = 60000L;

  /**
   * Calculates the distance between a driver's current location and a customer's location.
   *
   * @param driver          The Driver object representing the driver's information.
   * @param customerLocation The String representing the customer's location.
   * @return The distance between the driver and the customer.
   */
  protected static double calculateDriverDistanceToCustomer(Driver driver, String customerLocation) {
    int driverLocationIndex = extractLocationNumber(driver.getCurrentLocation());
    int customerLocationIndex = extractLocationNumber(customerLocation);
    return Math.abs(driverLocationIndex - customerLocationIndex);  // Calculate the absolute distance
  }

  /**
   * Calculates the travel time from the driver's current location to the customer's start location.
   *
   * @param driver The Driver object representing the driver's information.
   * @param ride   The Ride object representing the ride details.
   * @return The travel time in milliseconds.
   */
  protected static long calculateTravelTimeToCustomer(Driver driver, Ride ride) {
    // Assuming each location unit translates to 1 minute of travel
    int customerLocationNum = extractLocationNumber(ride.getCustomer().getStartLocation());
    int driverLocationNum = extractLocationNumber(driver.getCurrentLocation());
    return Math.abs(customerLocationNum - driverLocationNum) * MILLISECONDS_PER_MINUTE; // Convert minutes to milliseconds
  }

  /**
   * Extracts the numerical part from a location string.
   *
   * @param location The String representing the location.
   * @return The numerical part of the location.
   */
  private static int extractLocationNumber(String location) {
    return Integer.parseInt(location.replaceAll("\\D+", ""));
  }
}
