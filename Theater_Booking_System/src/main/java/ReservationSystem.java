import java.util.Scanner;

public class ReservationSystem {
  private Theater theater;
  private ReservationsService service;
  private Scanner scanner;

  public ReservationSystem(Theater theater) {
    this.theater = theater;
    this.service = new ReservationsService(this.theater);
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    System.out.println("Welcome to the " + theater.getName() + " reservation system!");
    while (true) {
      System.out.print("What would you like to do? (reserve <number>, show, done): ");
      String input = scanner.nextLine().trim();

      if (input.startsWith("reserve")) {
        handleReserve(input);
      } else if (input.equals("show")) {
        service.showSeats();
      } else if (input.equals("done")) {
        System.out.println("Thank you for using our reservation system. Have a great day!");
        break;
      } else {
        System.out.println("Invalid command. Please enter 'reserve <number>', 'show', or 'done'.");
      }
    }
  }

  private void handleReserve(String input) {
    try {
      String[] parts = input.split(" ");
      if (parts.length < 2) {
        System.out.println("Please specify the number of seats to reserve. Example: 'reserve 5'");
        return;
      }
      int numberOfSeats = Integer.parseInt(parts[1]);
      System.out.println("What’s your name?");
      String name = scanner.nextLine().trim();
      String reservationResult = service.reserveSeats(numberOfSeats, name);
      System.out.println(reservationResult);
    } catch (NumberFormatException e) {
      System.out.println("Invalid number format. Please enter a valid number of seats to reserve.");
    }
  }
}
