package package_final;

/**
 * SimulationConfig class represents the configuration for a simulation, including the number of drivers and rides.
 */
public class SimulationConfig {
  private int numberOfDrivers;
  private int numberOfRides;

  /**
   * Constructor to initialize the simulation configuration.
   *
   * @param numberOfDrivers The number of drivers to be simulated.
   * @param numberOfRides   The number of rides to be simulated.
   */
  protected SimulationConfig(int numberOfDrivers, int numberOfRides) {
    this.numberOfDrivers = numberOfDrivers;
    this.numberOfRides = numberOfRides;
  }

  /**
   * Retrieves the number of drivers for the simulation.
   *
   * @return The number of drivers.
   */
  protected int getNumberOfDrivers() {
    return numberOfDrivers;
  }

  /**
   * Retrieves the number of rides for the simulation.
   *
   * @return The number of rides.
   */
  protected int getNumberOfRides() {
    return numberOfRides;
  }
}
