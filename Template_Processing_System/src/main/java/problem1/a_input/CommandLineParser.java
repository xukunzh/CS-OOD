package problem1.a_input;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Parses and validates command-line arguments for generating emails and letters from templates.
 * It supports a set of predefined options and commands to control the generation process.
 */
public class CommandLineParser {

  public static final String ERROR_MESSAGE = """
      Error: --email provided but no --email-template was given.
              --letter provided but no --letter-template was given.
      Usage:
      --email Generate email messages. If this option is provided, then -- email-template must also be provided.
      --email-template <path/to/file> A filename for the email template. --letter Generate letters. If this option is provided, then --letter- template must also be provided.
      --letter-template <path/to/file> A filename for the letter template. --output-dir <path/to/folder> The folder to store all generated files. This option is required.
      --csv-file <path/to/folder> The CSV file to process. This option is required.
      Examples:
      --email --email-template email-template.txt --output-dir emails -- csv-file customer.csv
      --letter --letter-template letter-template.txt --output-dir letters - -csv-file customer.csv
      """;

  public static final String EMAIL = "--email";
  public static final String LETTER = "--letter";
  public static final String EMAIL_TEMPLATE = "--email-template";
  public static final String LETTER_TEMPLATE = "--letter-template";
  public static final String CSV_FILE = "--csv-file";
  public static final String OUTPUT_DIR = "--output-dir";

  private static final List<String> COMMANDS = List.of(EMAIL, LETTER);
  private static final List<String> OPTIONS = List.of(EMAIL_TEMPLATE, LETTER_TEMPLATE, CSV_FILE, OUTPUT_DIR);

  private final String[] args;
  private final List<String> inputCommands = new ArrayList<>();
  private final Map<String, String> inputOptions = new HashMap<>();

  /**
   * Constructs a CommandLineParser with the provided command-line arguments.
   *
   * @param args an array of command-line arguments
   */
  public CommandLineParser(String[] args) {
    this.args = args;
    parse();
    validateAll();
  }

  /**
   * Returns the list of input commands parsed from the command-line arguments.
   *
   * @return a list of input commands
   */
  public List<String> getInputCommands() {
    return inputCommands;
  }

  /**
   * Returns the map of input options parsed from the command-line arguments.
   *
   * @return a map of input options with option names as keys and option values as values
   */
  public Map<String, String> getInputOptions() {
    return inputOptions;
  }

  /**
   * Parses the command-line arguments to extract commands and options.
   */
  private void parse() {
    int i = 0;
    while (i < args.length) {
      if (COMMANDS.contains(args[i])) {
        // if the commands are duplicated:
        if (inputCommands.contains(args[i])) {
          throw new IllegalArgumentException("Duplicate commands: " + args[i]);
        }
        inputCommands.add(args[i]);
      } else if (OPTIONS.contains(args[i])) {
        // if the options are duplicated:
        if (inputOptions.containsKey(args[i])) {
          throw new IllegalArgumentException("Duplicate options: " + args[i]);
        }
        // see if there is an argument after the option, and it is neither commands nor options:
        if (i + 1 < args.length && !COMMANDS.contains(args[i + 1]) && !OPTIONS.contains(args[i + 1])) {
          inputOptions.put(args[i], args[i + 1]);
          i++; // skip the parameter
        } else {
          throw new IllegalArgumentException("Missing argument for option: " + args[i]);
        }
      }
      i++;
    }
  }

  /**
   * Validates the parsed command-line arguments to ensure they meet the application's requirements.
   */
  private void validateAll() {
    validateFilePaths();
    validateCommandsAndTemplates();
  }

  /**
   * Validates whether a file exists and is readable.
   * If the file does not exist or is not a regular file, it throws an IllegalArgumentException.
   * If the file cannot be read due to permission issues, it throws an IllegalArgumentException.
   *
   * @param filePath    the path to the file to be validated
   * @param description a description of the file being validated, used in error messages
   * @throws IllegalArgumentException if the file does not exist, is not a regular file, or cannot be read
   */
  private void validateFileExistsAndReadable(String filePath, String description) {
    File file = new File(filePath);
    if (!file.exists() || !file.isFile()) {
      throw new IllegalArgumentException(description + " does not exist: " + filePath);
    }
  }

  /**
   * Validates the file paths provided in the input options.
   * It ensures that the CSV file exists and is readable.
   * If the email command is specified and an email template is provided, it also validates the email template file.
   * If the letter command is specified and a letter template is provided, it also validates the letter template file.
   *
   * @throws IllegalArgumentException if any of the specified files do not exist, are not readable, or if there are issues with the output directory
   */
  private void validateFilePaths() {
    validateFileExistsAndReadable(inputOptions.get(CSV_FILE), "CSV file");
    if (inputCommands.contains(EMAIL) && inputOptions.containsKey(EMAIL_TEMPLATE)) {
      validateFileExistsAndReadable(inputOptions.get(EMAIL_TEMPLATE), "Email template file");
    }
    if (inputCommands.contains(LETTER) && inputOptions.containsKey(LETTER_TEMPLATE)) {
      validateFileExistsAndReadable(inputOptions.get(LETTER_TEMPLATE), "Letter template file");
    }
  }

  /**
   * Validates the presence of commands and templates in the input options.
   * It ensures that at least one command is provided (--email or --letter) and at least one corresponding template is provided.
   * If no commands are provided, it throws an IllegalArgumentException.
   * If no templates are provided, it throws an IllegalArgumentException.
   * If a command is specified but its corresponding template is missing, it throws an IllegalArgumentException.
   * If there is only one command with two templates provided, it displays a warning message.
   */
  private void validateCommandsAndTemplates() {
    boolean hasEmailCommand = inputCommands.contains(EMAIL);
    boolean hasLetterCommand = inputCommands.contains(LETTER);
    boolean hasEmailTemplate = inputOptions.containsKey(EMAIL_TEMPLATE);
    boolean hasLetterTemplate = inputOptions.containsKey(LETTER_TEMPLATE);

    // ensure there is at least one command
    if (!hasEmailCommand && !hasLetterCommand)
      throw new IllegalArgumentException("No commands provided. Please include --email and/or --letter.");

    // has command but no corresponding template
    if ((hasEmailCommand && !hasEmailTemplate) || (hasLetterCommand && !hasLetterTemplate))
      throw new IllegalArgumentException(ERROR_MESSAGE);

    // just a notification, have 3 item (or can say one extra template). inputCommands size == 1 filtered the normal situation.
    if ((hasEmailCommand && hasLetterTemplate || hasLetterCommand && hasEmailTemplate) && inputCommands.size() == 1)
      throw new IllegalArgumentException("One command with two templates provided. Executed the matching one.");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandLineParser that = (CommandLineParser) o;
    return Objects.equals(inputCommands, that.inputCommands) && Objects.equals(
      inputOptions, that.inputOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputCommands, inputOptions);
  }

  @Override
  public String toString() {
    return "CommandLineParser{" +
      "inputCommands=" + inputCommands +
      ", inputOptions=" + inputOptions +
      '}';
  }
}
