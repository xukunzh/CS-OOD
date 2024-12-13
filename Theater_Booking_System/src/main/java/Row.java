import java.util.ArrayList;

/**
 * The Row class represents a row of seats in a theater.
 * It extends ArrayList to manage a collection of Seat objects, adding functionality for row numbers and accessibility.
 */
public class Row extends ArrayList<Seat> {
  // The row number in the theater, with 1 being the closest to the screen.
  private int rowNumber;

  // Indicator of whether this row is wheelchair accessible.
  private boolean isWheelchairAccessible;

  /**
   * Constructor for the Row class.
   * @param rowNumber the number of this row
   * @param numSeats the number of seats in this row
   * @param isWheelchairAccessible indicates if this row is wheelchair accessible
   */
  public Row(int rowNumber, int numSeats, boolean isWheelchairAccessible) {
    super(numSeats);
    this.rowNumber = rowNumber;
    this.isWheelchairAccessible = isWheelchairAccessible;
    initializeSeats(numSeats);
  }

  /**
   * Initializes seats in the row.
   * @param numSeats the number of seats to be initialized in this row
   */
  private void initializeSeats(int numSeats) {
    for (int i = 0; i < numSeats; i++) {
      // Creating seat names from A to the number of seats (e.g., A, B, C, ...)
      char seatName = (char) ('A' + i);
      this.add(new Seat(String.valueOf(seatName)));
    }
  }

  /**
   * Returns the row number.
   * @return the number of the row
   */
  public int getRowNumber() {
    return rowNumber;
  }

  /**
   * Checks if the row is wheelchair accessible.
   * @return true if the row is wheelchair accessible, false otherwise
   */
  public boolean isWheelchairAccessible() {
    return isWheelchairAccessible;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Row " + rowNumber + " (Accessible: " + (isWheelchairAccessible ? "Yes" : "No") + "): ");
    for (Seat seat : this) {
      if (isWheelchairAccessible) {
        sb.append(seat.isReserved() ? "X" : "=");  // Use "=" for available seats in accessible rows
      } else {
        sb.append(seat.isReserved() ? "X" : "_");  // Use "_" for available seats in non-accessible rows
      }
    }
    return sb.toString();
  }

}
