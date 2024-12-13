import java.util.Arrays;

/**
 * Main class for launching the theater reservation system.
 */
public class Main {
  public static void main(String[] args) {
    // Create the theater with specific settings in the assignment example
    Theater theater = new Theater("Roxy", 15, 10, Arrays.asList(1, 5, 10));  // These rows are wheelchair accessible

    ReservationSystem reservationSystem = new ReservationSystem(theater);
    reservationSystem.start();
  }
}
