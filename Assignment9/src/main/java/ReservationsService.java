public class ReservationsService {
  private Theater theater;

  public ReservationsService(Theater theater) {
    this.theater = theater;
  }

  /**
   * Attempts to reserve a specified number of seats for a given name.
   * The seats are selected based on the best available option close to the center.
   *
   * @param numberOfSeats the number of seats to reserve
   * @param name the name for the reservation
   * @return a message indicating success or failure of the reservation
   */
  public String reserveSeats(int numberOfSeats, String name) {
    Row bestRow = findBestAvailableRow(numberOfSeats);
    if (bestRow == null) {
      return "Sorry, we don’t have that many seats together for you.";
    }

    int seatsReserved = 0;
    for (Seat seat : bestRow) {
      if (!seat.isReserved() && seatsReserved < numberOfSeats) {
        seat.reserveFor(name);
        seatsReserved++;
      }
      if (seatsReserved == numberOfSeats) {
        break;
      }
    }
    return "Reserved " + numberOfSeats + " seats for " + name + " in row " + bestRow.getRowNumber() + ".";
  }

  /**
   * Finds the best available row that can accommodate the specified number of seats.
   *
   * @param numberOfSeats the number of seats needed
   * @return the best available Row, or null if no suitable row is found
   */
  private Row findBestAvailableRow(int numberOfSeats) {
    Row bestRow = null;
    int minDistanceToCenter = Integer.MAX_VALUE;
    int centerRowIndex = (theater.getRows().size() / 2);

    for (Row row : theater.getRows()) {
      int distanceToCenter = Math.abs(centerRowIndex - row.getRowNumber());
      int availableSeats = 0;

      for (Seat seat : row) {
        if (!seat.isReserved()) {
          availableSeats++;
        }
      }

      if (availableSeats >= numberOfSeats && distanceToCenter < minDistanceToCenter) {
        minDistanceToCenter = distanceToCenter;
        bestRow = row;
      }
    }
    return bestRow;
  }

  /**
   * Displays the current seating status of the theater.
   */
  public void showSeats() {
    System.out.println("Current Seating Layout:");
    for (Row row : theater.getRows()) {
      System.out.println(row.toString());  // This will display the state of each row
    }
  }
}
