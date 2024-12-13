package package_final;

/**
 * The Event interface represents an event in the simulation.
 * Implementing classes should define the behavior to process the event.
 */
public interface Event {
  /**
   * Processes the event using the specified SimulatorService.
   *
   * @param service The SimulatorService used to process the event.
   */
  void processEvent(SimulatorService service);
}
