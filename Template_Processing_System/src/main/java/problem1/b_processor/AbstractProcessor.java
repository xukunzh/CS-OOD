package problem1.b_processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * An abstract class representing a processor for handling file input and converting it to a specified type.
 * This class provides common functionality for processing input files.
 *
 * @param <T> the type of data to be processed and returned
 */

public abstract class AbstractProcessor<T> implements IProcessor<T> {

  protected String filePath;

  /**
   * Constructs a AbstractProcessor object with the specified file path.
   *
   * @param filePath the path to the input file
   */
  protected AbstractProcessor(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Processes the input file and returns the result.
   *
   * @return the processed result
   */
  @Override
  public T process() {
    try (BufferedReader inputFile = new BufferedReader(new FileReader(filePath))) {
      return this.convert(inputFile);
    } catch (FileNotFoundException fnfe) {
      System.err.println("File not found: " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.err.println("Error reading file: " + ioe.getMessage());
      ioe.printStackTrace();
    }
    return null;
  }

  /**
   * Converts the content of the input file into the desired type.
   *
   * @param inputFile the BufferedReader object for reading the input file
   * @return the converted result
   * @throws IOException if an I/O error occurs
   */
  protected abstract T convert(BufferedReader inputFile) throws IOException;
}
