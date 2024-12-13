package package_final;

import java.util.Objects;

/**
 * The RideFinishedEvent class represents an event indicating that a ride has finished in the simulation.
 */
public class RideFinishedEvent implements Event {
  private Ride ride;

  /**
   * Constructs a RideFinishedEvent with the specified ride.
   *
   * @param ride The ride that has finished.
   */
  protected RideFinishedEvent(Ride ride) {
    this.ride = ride;
  }

  /**
   * Retrieves the ride associated with the event.
   *
   * @return The finished ride.
   */
  protected Ride getRide() {
    return ride;
  }

  /**
   * Processes the event using the specified SimulatorService.
   *
   * @param service The SimulatorService used to process the event.
   */
  @Override
  public void processEvent(SimulatorService service) {
    service.handleRideFinished(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RideFinishedEvent that = (RideFinishedEvent) o;
    return Objects.equals(ride, that.ride);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ride);
  }
}
