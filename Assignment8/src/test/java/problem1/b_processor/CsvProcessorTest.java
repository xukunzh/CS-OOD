package problem1.b_processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CsvProcessorTest {

  String testCsvFilePath;
  CsvProcessor csvProcessor;
  Pattern CSV_PATTERN;

  @BeforeEach
  void setUp() {
    // Assuming there's a test CSV file in the test resources directory
    CSV_PATTERN = Pattern.compile("\"([^\"]*)\"");
    testCsvFilePath = "src/test/java/problem1/a_input/insurance-company-members.csv";
    csvProcessor = new CsvProcessor(testCsvFilePath, CSV_PATTERN);
  }

  @Test
  void process_ValidCsv_ReturnsCorrectData() {
    List<Map<String, String>> result = csvProcessor.process();

    // Assuming the test CSV file has specific known content
    assertEquals(501, result.size());

    // Check the content of the first row
    Map<String, String> firstRow = result.get(0);
    assertEquals("James", firstRow.get("first_name"));
    assertEquals("Butt", firstRow.get("last_name"));
    // Add more assertions as necessary based on the expected CSV content
  }

  @Test
  void process_EmptyCsv_ReturnsEmptyList() {
    // Create an empty CSV file for this test
    Path emptyCsvPath = Path.of("src/test/resources/empty.csv");
    try {
      Files.createFile(emptyCsvPath);
    } catch (IOException e) {
      fail("Failed to create empty CSV file for testing.", e);
    }

    CsvProcessor emptyCsvProcessor = new CsvProcessor(emptyCsvPath.toString(), CSV_PATTERN);
    List<Map<String, String>> result = emptyCsvProcessor.process();

    assertTrue(result.isEmpty());

    // Clean up by deleting the empty CSV file
    try {
      Files.delete(emptyCsvPath);
    } catch (IOException e) {
      fail("Failed to delete the empty CSV file after testing.", e);
    }
  }

  @Test
  void testEquals() {
    CsvProcessor processor1 = new CsvProcessor("src/test/java/problem1/a_input/insurance-company-members.csv", CSV_PATTERN);

    assertEquals(csvProcessor, processor1); // Both have the same path, should be equal
    assertFalse(csvProcessor.equals(null));
    assertFalse(csvProcessor.equals(1));
    assertTrue(csvProcessor.equals(csvProcessor));
  }

  @Test
  void testHashCode() {
    CsvProcessor processor1 = new CsvProcessor("src/test/java/problem1/a_input/insurance-company-members.csv", CSV_PATTERN);
    CsvProcessor processor2 = new CsvProcessor("src/test/java/problem1/a_input/insurance-company-members.csv", CSV_PATTERN);

    assertEquals(processor2.hashCode(), processor1.hashCode()); // Same path, hash codes should be equal
  }

  @Test
  void testToString() {
    CsvProcessor processor = new CsvProcessor("path/to/csv.csv", CSV_PATTERN);

    String expectedString = "CsvProcessor{CsvFilePath='path/to/csv.csv'}";
    assertEquals(expectedString, processor.toString());
  }
}
