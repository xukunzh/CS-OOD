package problem1.c_generator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.b_processor.CsvProcessor;
import problem1.b_processor.TemplateProcessor;

class GeneratorTest {
  private TemplateProcessor templateProcessor;
  private CsvProcessor csvProcessor;
  private String fileName;
  private Pattern TEMPLATE_PATTERN;
  private Generator testGenerator;

  @BeforeEach
  void setUp() {
    testGenerator = new Generator(templateProcessor, csvProcessor, fileName, TEMPLATE_PATTERN);
  }

  @Test
  void getFileName() {
    assertEquals(fileName, testGenerator.getFileName());
  }

  @Test
  void getTemplatePattern() {
    assertEquals(TEMPLATE_PATTERN, testGenerator.getTemplatePattern());
  }

  @Test
  void getTemplateProcessor() {
    assertEquals(templateProcessor, testGenerator.getTemplateProcessor());
  }

  @Test
  void getCsvProcessor() {
    assertEquals(csvProcessor, testGenerator.getCsvProcessor());
  }

  @Test
  void testGenerateAll_() throws IOException {
    // Prepare test data
    String outputDir = "src/test/java/problem1/e_output";
    String outputFilePath1 = outputDir + File.separator + "testFile_1.txt";
    String outputFilePath2 = outputDir + File.separator + "testFile_2.txt";

    // Mock TemplateProcessor
    TemplateProcessor templateProcessor = new TemplateProcessor("src/test/resources/testTemplate.txt");

    // Create a mock CsvProcessor
    CsvProcessor csvProcessor = new CsvProcessor("src/test/resources/testCsv.csv", Pattern.compile("\"([^\"]*)\"")) ;

    // Create a Generator instance
    Generator generator = new Generator(templateProcessor, csvProcessor, "testFile", Pattern.compile("\\[\\[(\\w+)]]"));

    // Call the generateAll method
    generator.generateAll(outputDir);

    // Assert the existence and content of the generated files
    assertAll(
        () -> assertTrue(Files.exists(Path.of(outputFilePath1))),
        () -> assertTrue(Files.exists(Path.of(outputFilePath2)))
    );

    // Read the content of the generated files
    StringBuilder fileContent1 = new StringBuilder();
    StringBuilder fileContent2 = new StringBuilder();
    try (BufferedReader reader1 = new BufferedReader(new FileReader(outputFilePath1));
        BufferedReader reader2 = new BufferedReader(new FileReader(outputFilePath2))) {
      String line;
      while ((line = reader1.readLine()) != null) {
        fileContent1.append(line).append(System.lineSeparator());
      }
      while ((line = reader2.readLine()) != null) {
        fileContent2.append(line).append(System.lineSeparator());
      }
      String rest = "Bye!" + System.lineSeparator();
      fileContent1.append(rest);
      fileContent2.append(rest);
    }

    // Assert the content of the generated files
    assertEquals("Hello, John!" + System.lineSeparator()
        + "I'm happy." + System.lineSeparator() + "Bye!" + System.lineSeparator(), new TemplateProcessor(outputFilePath1).process());
    assertEquals("Hello, Smith!" + System.lineSeparator()
        + "I'm sad." + System.lineSeparator() + "Bye!" + System.lineSeparator(), new TemplateProcessor(outputFilePath2).process());
  }

  @Test
  void testEquals() {
    assertEquals(testGenerator, testGenerator);
    assertNotEquals(testGenerator, null);
    assertNotEquals(testGenerator, "String");
    Generator sameGenerator = new Generator(templateProcessor, csvProcessor, fileName, TEMPLATE_PATTERN);
    assertEquals(testGenerator, sameGenerator);
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash(templateProcessor, csvProcessor, fileName, TEMPLATE_PATTERN);
    assertEquals(expectedHashCode, testGenerator.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Generator{" +
        "templateProcessor=" + templateProcessor +
        ", csvProcessor=" + csvProcessor +
        ", fileName='" + fileName + '\'' +
        ", templatePattern=" + TEMPLATE_PATTERN +
        '}';
    assertEquals(expectedString, testGenerator.toString());
  }
}