package problem1.d_command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandTest {
  private String fileName;
  private Pattern TEMPLATE_PATTERN;
  private Pattern CSV_PATTERN;
  private Command testCommand;

  @BeforeEach
  void setUp() {
    fileName = "testFile";
    TEMPLATE_PATTERN = Pattern.compile("\\[\\[(\\w+)]]");
    CSV_PATTERN = Pattern.compile("\"([^\"]*)\"");
    testCommand = new Command(fileName, TEMPLATE_PATTERN, CSV_PATTERN);
  }

  @Test
  void getFileName() {
    assertEquals(fileName, testCommand.getFileName());
  }

  @Test
  void getTemplatePattern() {
    assertEquals(TEMPLATE_PATTERN, testCommand.getTemplatePattern());
  }

  @Test
  void getCsvPattern() {
    assertEquals(CSV_PATTERN, testCommand.getCsvPattern());
  }

  @Test
  void processAndGenerate() {
    testCommand.processAndGenerate("src/test/resources/testTemplate.txt", "src/test/resources/testCsv.csv", "src/test/java/problem1/f_output");
    String outputFilePath1 = "src/test/java/problem1/f_output" + File.separator + "testFile_1.txt";
    String outputFilePath2 = "src/test/java/problem1/f_output" + File.separator + "testFile_2.txt";
    assertAll(
        () -> assertTrue(Files.exists(Path.of(outputFilePath1))),
        () -> assertTrue(Files.exists(Path.of(outputFilePath2)))
    );
  }

  @Test
  void testEquals() {
    assertTrue(testCommand.equals(testCommand));
    assertFalse(testCommand.equals(null));
    assertFalse(testCommand.equals("String"));
    Command sameCommand = new Command(fileName, TEMPLATE_PATTERN, CSV_PATTERN);
    assertTrue(testCommand.equals(sameCommand));
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash(fileName, TEMPLATE_PATTERN, CSV_PATTERN);
    assertEquals(expectedHashCode, testCommand.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Command{" +
        "fileName='" + fileName + '\'' +
        ", templatePattern=" + TEMPLATE_PATTERN +
        ", csvPattern=" + CSV_PATTERN +
        '}';
    assertEquals(expectedString, testCommand.toString());
  }
}