import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/**
 * Tests for the ReservationSystem using JUnit.
 * This class tests the core functionalities of the reservation system.
 */
public class ReservationSystemTest {
  private Theater theater;
  private ReservationsService service;

  @BeforeEach
  void setUp() {
    // Initialize the theater with example data before each test
    theater = new Theater("Test Theater", 5, 10, Arrays.asList(1, 3));  // Assume rows 1 and 3 are wheelchair accessible
    service = new ReservationsService(theater);
  }

  @Test
  void testReserveSeatsSuccess() {
    // Test reserving a number of seats successfully
    String response = service.reserveSeats(5, "Alice");
    assertTrue(response.contains("Reserved 5 seats for Alice"));
  }

  @Test
  void testReserveSeatsFailure() {
    // Test failing to reserve more seats than are available in any single row
    String response = service.reserveSeats(11, "Bob");
    assertEquals("Sorry, we don’t have that many seats together for you.", response);
  }

  @Test
  void testShowSeats() {
    // Test the display of seats after a reservation
    service.reserveSeats(5, "Charlie");
    String seats = service.showSeats();
    assertTrue(seats.contains("X X X X X"));  // Check if the seats are marked as reserved
  }
}
