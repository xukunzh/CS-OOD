package problem1;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import problem1.a_input.CommandLineParser;
import problem1.d_command.Command;

/**
 * The entry point of the application that processes command-line arguments to generate emails
 * and letters based on provided templates and CSV data. This class handles the initialization
 * and coordination of the components involved in document generation.
 */
public class Main {

  private static final String CSV_FILE = "--csv-file";
  private static final String OUTPUT_DIR = "--output-dir";
  private static final String DOUBLE_DASH = "--";
  private static final String TEMPLATE = "-template";

  private static List<String> inputCommands;
  private static Map<String, String> inputOptions;
  private static String csvFilePath;
  private static String outputDir;

  private static final Pattern TEMPLATE_PATTERN = Pattern.compile("\\[\\[(\\w+)]]"); // [[ ]]
  private static final Pattern CSV_PATTERN = Pattern.compile("\"([^\"]*)\""); // " "

  /**
   * Main method that processes command-line arguments and orchestrates the generation of emails
   * and/or letters based on the provided templates and CSV data.
   *
   * @param args the command-line arguments, including flags for email and letter generation,
   *             paths to template files, the CSV file, and the output directory.
   */
  public static void main(String[] args) {
    parse(args);
    run();
  }

  /**
   * Parses the command-line arguments to extract input commands and options.
   *
   * @param args the command-line arguments
   */
  private static void parse(String[] args) {
    CommandLineParser commandLineParser = new CommandLineParser(args);
    inputCommands = commandLineParser.getInputCommands();
    inputOptions = commandLineParser.getInputOptions();
    csvFilePath = inputOptions.get(CSV_FILE);
    outputDir = inputOptions.get(OUTPUT_DIR);
  }

  /**
   * Runs the application by executing the appropriate command for each input command string.
   */
  private static void run() {
    for (String string : inputCommands) {
      Command command = new Command(string.replace(DOUBLE_DASH, ""), TEMPLATE_PATTERN, CSV_PATTERN);
      String templateFilePath = inputOptions.get(string + TEMPLATE);
      command.processAndGenerate(templateFilePath, csvFilePath, outputDir);
    }
  }
}
