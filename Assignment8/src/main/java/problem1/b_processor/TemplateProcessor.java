package problem1.b_processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Processes a template file, converting its content into a string.
 */
public class TemplateProcessor extends AbstractProcessor<String> {

  /**
   * Constructs a TemplateProcessor with the specified template file path.
   *
   * @param filePath the path to the template file
   */
  public TemplateProcessor(String filePath) {
    super(filePath);
  }

  /**
   * Converts the content of the BufferedReader (representing the template file) into a string.
   *
   * @param inputFile the BufferedReader to read the template file
   * @return the content of the template file as a string
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected String convert(BufferedReader inputFile) throws IOException {
    StringBuilder templateContent = new StringBuilder();
    String line;
    while ((line = inputFile.readLine()) != null)
      templateContent.append(line).append(System.lineSeparator());
    return templateContent.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TemplateProcessor that = (TemplateProcessor) o;
    return Objects.equals(filePath, that.filePath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filePath);
  }

  @Override
  public String toString() {
    return "TemplateProcessor{" +
        "TemplateFilePath='" + filePath + '\'' +
        '}';
  }
}
