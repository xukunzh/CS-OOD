package problem1.b_processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Processes CSV files.
 */
public class CsvProcessor extends AbstractProcessor<List<Map<String, String>>> {

  private final Pattern csvPattern;

  /**
   * Constructs a CsvProcessor with the specified file path.
   *
   * @param filePath the path to the input file
   * @param csvPattern the csv pattern
   */
  public CsvProcessor(String filePath, Pattern csvPattern) {
    super(filePath);
    this.csvPattern = csvPattern;
  }

  /**
   * Converts the content of a CSV file into a list of maps, using the first row as keys.
   *
   * @param inputFile the BufferedReader to read the CSV file
   * @return a list of maps, each representing a row in the CSV file
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected List<Map<String, String>> convert(BufferedReader inputFile)
      throws IOException {
    List<Map<String, String>> csvListOfMaps = new ArrayList<>();

    // step 1: extract keys from header
    String header;
    if ((header = inputFile.readLine()) != null) {
      String[] keys = extractMatches(header);

      // step 2: extract values line by line
      String line;
      while ((line = inputFile.readLine()) != null) {
        String[] values = extractMatches(line);

        // step 3: add maps to list
        csvListOfMaps.add(mapColumns(keys, values));
      }
    }
    return csvListOfMaps;
  }

  /**
   * Extracts values from a CSV line using the predefined regex pattern.
   *
   * @param input the CSV line
   * @return an array of values extracted from the line
   */
  private String[] extractMatches(String input) {
    Matcher matcher = csvPattern.matcher(input);
    List<String> matches = new ArrayList<>();
    while (matcher.find())
      matches.add(matcher.group(1));
    return matches.toArray(new String[0]);
  }

  /**
   * Maps the column headers to their respective values for a single row in the CSV file.
   *
   * @param keys the array of column headers
   * @param values the array of values for a row
   * @return a map representing a single row, with column headers as keys and row values as values
   */
  private Map<String, String> mapColumns(String[] keys, String[] values) {
    Map<String, String> map = new HashMap<>();
    for (int i = 0; i < keys.length && i < values.length; i++)
      map.put(keys[i], values[i]);
    return map;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CsvProcessor that = (CsvProcessor) o;
    return Objects.equals(filePath, that.filePath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filePath);
  }

  @Override
  public String toString() {
    return "CsvProcessor{" +
        "CsvFilePath='" + filePath + '\'' +
        '}';
  }
}
