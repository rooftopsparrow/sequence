/**
 * Integer sequence generators.
 *
 * <p>
 * A sequence is defined by its initial values, and a depth.
 * The initial values represent the first values returned by the sequence.
 * The depth is how many previous items are added together to calculate the
 * next item in the sequence, after all initial values have been exhausted.
 *
 * <p>
 * Examples:
 *
 * <p>
 * Sequence({4, 9, 12}, 0) produces [4, 9, 12, 0, 0, 0, 0, ...]<br>
 * Sequence({1}, 1) produces [1, 1, 1, 1, 1, 1, ...]<br>
 * Sequence({3, 8}, 1) produces [3, 8, 8, 8, 8, 8, 8, ...]<br>
 * Sequence({2, 4}, 2) produces [2, 4, 6, 10, 16, 26, 42, 68, ...]<br>
 * Sequence({2, 4, 6, 8}, 3) produces [2, 4, 6, 8, 18, 32, 58, 108, ...]
 *
 * <p>
 * The behavior of sequences for which the depth is greater than the number of
 * the initial values is unspecified.
 *
 * @version 1.0.1
 * @author Jonathan Nicholson
 */
public class Sequence {

  /**
   * The initial values of the sequence.
   */
  private Integer[] initial;
  /**
   * The buffer is of length determined by `depth`
   * which holds the values for the next sum.
   */
  private Integer[] buffer;
  /**
   * The count of how many times `next()` was called.
   */
  private Integer count = 0;

  /**
   * Constructs an arbitrary sequence.
   */
  public Sequence() {
    this(new Integer[] { 1 }, 1);
  }

  /**
   * Constructs a specified sequence.
   * @param init the initial values of the sequence
   * @param depth the number of historical values used in calculation
   */
  public Sequence(final Integer[] init, Integer depth) {
    // If given a depth of less than zero,
    // lets just force it to be zero
    if (depth < 0) {
      depth = 0;
    }
    // If given a larger depth than the initial size,
    // just set it to the correct size
    if (depth > init.length) {
      depth = init.length;
    }
    this.initial = init;
    this.buffer = new Integer[depth];
    // Fill the buffer with initial data at the specified depth.
    int offset = initial.length - depth;
    for (int b = 0; b < buffer.length; b++) {
      buffer[b] = initial[b + offset];
    }
  }

  /**
   * Used to shift all numbers in the buffer to the left. This method shifts
   * off the head and pushes newNumber to the tail.
   * @param newNumber the new tail value
   */
  private void rotateBuffer(Integer newNumber) {
    // No buffer length -> pretend we did something
    if (this.buffer.length == 0) {
      return;
    }
    int end = this.buffer.length - 1;
    for (int i = 0; i < end; i++) {
      this.buffer[i] = this.buffer[i + 1];
    }
    this.buffer[end] = newNumber;
    return;
  }

  /**
   * Accesses the next number in the sequence.
   * @return the next number in the sequence
   */
  public Integer next() {
    // Are we still in consuming the initial state?
    if (this.count < this.initial.length) {
      return this.initial[this.count++];
    }
    // calculate the sum
    Integer sum = 0;
    for (Integer num : this.buffer) {
      sum += num;
    }
    // rotate the buffer
    this.rotateBuffer(sum);
    // move the sequence forward
    this.count++;
    return sum;
  }

}
