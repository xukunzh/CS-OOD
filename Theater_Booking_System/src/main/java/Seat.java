/**
 * The Seat class represents a single seat in a theater.
 * Each seat has a name and a reserved status indicating if it is booked and by whom.
 */
public class Seat {
  // The name of the seat, typically represented by a capital letter (A-Z).
  private String name;

  // The name of the person for whom this seat is reserved, or null if it is not reserved.
  private String reservedFor;

  /**
   * Constructor for the Seat class.
   * @param name the name of the seat
   */
  public Seat(String name) {
    this.name = name;
    this.reservedFor = null; // Initially, the seat is not reserved.
  }

  /**
   * Returns the name of the seat.
   * @return the name of the seat
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the seat.
   * @param name the new name of the seat
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the person who has reserved this seat.
   * If no reservation is present, returns null.
   * @return the name of the person who reserved the seat or null if no reservation
   */
  public String getReservedFor() {
    return reservedFor;
  }

  /**
   * Reserves this seat for a specified person.
   * @param personName the name of the person for whom the seat is to be reserved
   */
  public void reserveFor(String personName) {
    this.reservedFor = personName;
  }

  /**
   * Frees up this seat by setting its reservation status to null.
   */
  public void release() {
    this.reservedFor = null;
  }

  /**
   * Checks if the seat is reserved.
   * @return true if the seat is reserved, false otherwise
   */
  public boolean isReserved() {
    return reservedFor != null;
  }

  /**
   * Returns a string representation of the seat's status.
   * @return a string indicating if the seat is reserved and by whom
   */
  @Override
  public String toString() {
    if (isReserved()) {
      return name + " (Reserved for " + reservedFor + ")";
    } else {
      return name + " (Available)";
    }
  }
}
