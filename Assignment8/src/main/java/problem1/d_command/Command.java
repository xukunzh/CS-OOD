package problem1.d_command;

import java.util.Objects;
import java.util.regex.Pattern;
import problem1.b_processor.CsvProcessor;
import problem1.b_processor.TemplateProcessor;
import problem1.c_generator.Generator;

/**
 * Class representing a command that processes input data and generates output based on provided templates and data.
 */
public class Command {

  private final String fileName;
  private final Pattern templatePattern;
  private final Pattern csvPattern;

  /**
   * Constructs a Command.
   *
   * @param fileName the file name
   * @param templatePattern the template pattern
   * @param csvPattern the csv pattern
   */
  public Command(String fileName, Pattern templatePattern, Pattern csvPattern) {
    this.fileName = fileName;
    this.templatePattern = templatePattern;
    this.csvPattern = csvPattern;
  }

  /**
   * Getter for file name.
   *
   * @return the file name
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Getter for template pattern.
   *
   * @return the template pattern
   */
  public Pattern getTemplatePattern() {
    return templatePattern;
  }

  /**
   * Getter for csv pattern.
   *
   * @return the csv pattern
   */
  public Pattern getCsvPattern() {
    return csvPattern;
  }

  /**
   * Processes the input template and CSV files, generates output documents, and saves them to the specified output directory.
   *
   * @param templateFilePath the path to the template file
   * @param csvFilePath      the path to the CSV file
   * @param outputDir        the path to the output directory
   */
  public void processAndGenerate(String templateFilePath, String csvFilePath, String outputDir) {
    TemplateProcessor templateProcessor = new TemplateProcessor(templateFilePath); // convert txt to string
    CsvProcessor csvProcessor = new CsvProcessor(csvFilePath, csvPattern); // convert csv to list of map
    Generator generator = new Generator(templateProcessor, csvProcessor, fileName, templatePattern);
    generator.generateAll(outputDir);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Command command = (Command) o;
    return Objects.equals(fileName, command.fileName) && Objects.equals(
        templatePattern, command.templatePattern) && Objects.equals(csvPattern,
        command.csvPattern);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, templatePattern, csvPattern);
  }

  @Override
  public String toString() {
    return "Command{" +
        "fileName='" + fileName + '\'' +
        ", templatePattern=" + templatePattern +
        ", csvPattern=" + csvPattern +
        '}';
  }
}
