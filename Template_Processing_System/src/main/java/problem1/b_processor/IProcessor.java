package problem1.b_processor;

/**
 * Interface for processors that process input data and return a result of type T.
 *
 * @param <T> the type of the result
 */
public interface IProcessor<T> {

  /**
   * Processes the input data and returns the result.
   *
   * @return the result of processing the input data
   */
  T process();
}
