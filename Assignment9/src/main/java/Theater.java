import java.util.ArrayList;
import java.util.List;

/**
 * The Theater class represents a theater with multiple rows of seats.
 * It maintains a collection of rows and provides methods to manage the theater's layout and accessibility features.
 */
public class Theater {
  // The name of the theater.
  private String name;

  // A list of rows in the theater.
  private List<Row> rows;

  // A list of integers indicating which rows are wheelchair accessible.
  private List<Integer> accessibleRows;

  /**
   * Constructor for the Theater class.
   * @param name the name of the theater
   * @param numRows the total number of rows in the theater
   * @param numSeatsPerRow the number of seats per row
   * @param accessibleRowsIndices indices of rows that are wheelchair accessible
   */
  public Theater(String name, int numRows, int numSeatsPerRow, List<Integer> accessibleRowsIndices) {
    this.name = name;
    this.rows = new ArrayList<>();
    this.accessibleRows = new ArrayList<>(accessibleRowsIndices);
    initializeRows(numRows, numSeatsPerRow);
  }

  public int getMaxSeatsPerRow() {
    if (rows.isEmpty()) return 0;
    return rows.get(0).size();  // Assuming all rows have the same number of seats
  }

  /**
   * Initializes rows in the theater.
   * @param numRows the number of rows to initialize
   * @param numSeatsPerRow the number of seats per row
   */
  private void initializeRows(int numRows, int numSeatsPerRow) {
    for (int i = 0; i < numRows; i++) {
      boolean isAccessible = accessibleRows.contains(i + 1);
      rows.add(new Row(i + 1, numSeatsPerRow, isAccessible));
    }
  }

  /**
   * Gets the name of the theater.
   * @return the name of the theater
   */
  public String getName() {
    return name;
  }

  /**
   * Returns a list of all rows in the theater.
   * @return a list of Row objects
   */
  public List<Row> getRows() {
    return rows;
  }

  /**
   * Returns a string representation of the theater's seating layout.
   * @return a string representing the seating layout of all rows
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Theater: " + name + "\n");
    for (Row row : rows) {
      sb.append(row.toString()).append("\n");
    }
    return sb.toString();
  }
}
