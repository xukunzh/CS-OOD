package package_final;

import java.util.Objects;

/**
 * The RideRequestedEvent class represents an event indicating that a ride has been requested in the simulation.
 */
public class RideRequestedEvent implements Event {
  private Ride ride;

  /**
   * Constructs a RideRequestedEvent with the specified request time and ride.
   *
   * @param requestTime The time when the ride was requested.
   * @param ride        The ride that has been requested.
   */
  protected RideRequestedEvent(long requestTime, Ride ride) {
    this.ride = ride;
  }

  /**
   * Retrieves the ride associated with the event.
   *
   * @return The requested ride.
   */
  protected Ride getRide() {
    return ride;
  }

  /**
   * Validates the ride parameters.
   * Throws an IllegalArgumentException if the ride or its parameters are invalid.
   *
   * @param ride The ride to validate.
   * @throws IllegalArgumentException If the ride or its parameters are invalid.
   */
  protected void validateRide(Ride ride) {
    if (ride == null || ride.getCustomer() == null || ride.getCustomer().getStartLocation() == null || ride.getRideType() == null) {
      throw new IllegalArgumentException("Invalid ride parameters");
    }
  }

  /**
   * Processes the event using the specified SimulatorService.
   *
   * @param service The SimulatorService used to process the event.
   */
  @Override
  public void processEvent(SimulatorService service) {
    service.handleRideRequested(this);
    validateRide(getRide());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RideRequestedEvent that = (RideRequestedEvent) o;
    return Objects.equals(ride, that.ride);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ride);
  }
}
