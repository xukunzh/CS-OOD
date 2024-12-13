package problem1.c_generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import problem1.b_processor.CsvProcessor;
import problem1.b_processor.TemplateProcessor;

/**
 * Class representing a generator that generates corresponding output.
 */
public class Generator {

  private final TemplateProcessor templateProcessor;
  private final CsvProcessor csvProcessor;
  private final String fileName;
  private final Pattern templatePattern;

  /**
   * Constructs a Generator with specified template, CSV processor and file name.
   *
   * @param templateProcessor the template processor to use for generating content
   * @param csvProcessor the CSV processor to use for reading data
   * @param fileName the file name
   * @param templatePattern the template pattern
   */
  public Generator(TemplateProcessor templateProcessor, CsvProcessor csvProcessor, String fileName,
      Pattern templatePattern) {
    this.templateProcessor = templateProcessor;
    this.csvProcessor = csvProcessor;
    this.fileName = fileName;
    this.templatePattern = templatePattern;
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
   * Returns the template processor used by this class.
   *
   * @return The template processor instance.
   */
  public TemplateProcessor getTemplateProcessor() {
    return templateProcessor;
  }

  /**
   * Returns the CSV processor used by this class.
   *
   * @return The CSV processor instance.
   */
  public CsvProcessor getCsvProcessor() {
    return csvProcessor;
  }

  /**
   * Generates all output files in the specified directory.
   *
   * @param outputDir the directory where the output files will be generated
   */
  public void generateAll(String outputDir) {
    // step 1: generate directory
    generateDir(outputDir);

    // step 2: replace placeholders map by map
    List<Map<String, String>> csvListOfMaps = csvProcessor.process();
    for (int i = 0; i < csvListOfMaps.size(); i++) {
      String updatedContent = replacePlaceHolders(csvListOfMaps.get(i));

      // step 3: generate files
      String outputFileName = String.format("%s_%d.txt", fileName, i + 1);
      String outputFilePath = outputDir + File.separator + outputFileName;
      generateFiles(updatedContent, outputFilePath);
    }
  }

  /**
   * Creates the output directory if it does not already exist.
   *
   * @param outputDir the directory to create
   */
  private void generateDir(String outputDir) {
    File dir = new File(outputDir);
    if (!dir.exists()) {
      boolean success = dir.mkdirs();
      if (!success)
        System.err.println("Failed to create directory: " + outputDir);
    }
  }

  /**
   * Replaces placeholders in the template with corresponding values from a CSV data map.
   *
   * @param csvMap a map containing CSV column names as keys and corresponding values as map values
   * @return the content with placeholders replaced, or null if any required data is missing
   */
  private String replacePlaceHolders(Map<String, String> csvMap) {
    StringBuilder updatedContent = new StringBuilder();
    Matcher matcher = templatePattern.matcher(templateProcessor.process());
    while (matcher.find()) {
      String placeholder = matcher.group(1);
      String replacement = csvMap.getOrDefault(placeholder, "");
      if (replacement.isBlank()) // avoid blank lines
        return null;
      else
        matcher.appendReplacement(updatedContent, replacement);
    }
    matcher.appendTail(updatedContent); // copy the rest
    return updatedContent.toString();
  }

  /**
   * Writes the updated content to a file at the specified path.
   *
   * @param updatedContent the content to write to the file
   * @param outputFilePath the file path where the content will be written
   */
  private void generateFiles(String updatedContent, String outputFilePath) {
    if (updatedContent == null) return; // avoid blank lines
    try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFilePath))) {
      outputFile.write(updatedContent);
    } catch (IOException ioe) {
      System.err.println("Error reading file: " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Generator generator = (Generator) o;
    return Objects.equals(templateProcessor, generator.templateProcessor)
        && Objects.equals(csvProcessor, generator.csvProcessor) && Objects.equals(
        fileName, generator.fileName) && Objects.equals(templatePattern,
        generator.templatePattern);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templateProcessor, csvProcessor, fileName, templatePattern);
  }

  @Override
  public String toString() {
    return "Generator{" +
        "templateProcessor=" + templateProcessor +
        ", csvProcessor=" + csvProcessor +
        ", fileName='" + fileName + '\'' +
        ", templatePattern=" + templatePattern +
        '}';
  }
}
