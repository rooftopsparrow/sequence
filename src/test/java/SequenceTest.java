import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Tests for Sequence.
 *
 * <p>Example tests for constructor
 * and next() methods.
 *
 * @author  Dr. Jody Paul
 * @version 2.0.2
 */
public class SequenceTest {

  @Test
  public void arbitrarySequence() {
    Sequence sequence = new Sequence();
    assertThat(sequence.next(), is(1));
    assertThat(sequence.next(), is(1));
    for (int i = 0; i < 100; i++) {
      assertThat(sequence.next(), is(1));
    }
  }

  @Test
  public void zeroSequences() {
    Sequence sequence0 = new Sequence(new Integer[] {}, 0);
    for (int i = 0; i < 100; i++) {
      assertThat(sequence0.next(), is(0));
    }
    sequence0 = new Sequence(new Integer[] { 0, 0, 0 }, 0);
    for (int i = 0; i < 100; i++) {
      assertThat(sequence0.next(), is(0));
    }
    sequence0 = new Sequence(new Integer[] { 42, -3, 86 }, 0);
    assertThat(sequence0.next(), is(42));
    assertThat(sequence0.next(), is(-3));
    assertThat(sequence0.next(), is(86));
    for (int i = 0; i < 100; i++) {
      assertThat(sequence0.next(), is(0));
    }
  }

  @Test
  public void oneSequences() {
    Sequence sequence1 = new Sequence(new Integer[] { 42 }, 1);
    for (int i = 0; i < 100; i++) {
      assertThat(sequence1.next(), is(42));
    }
    sequence1 = new Sequence(new Integer[] { 42, -3, 86 }, 1);
    assertThat(sequence1.next(), is(42));
    assertThat(sequence1.next(), is(-3));
    for (int i = 0; i < 100; i++) {
      assertThat(sequence1.next(), is(86));
    }
  }

  @Test
  public void twoSequences() {
    Sequence sequence2 = new Sequence(new Integer[] { 1, 1 }, 2);
    assertThat(sequence2.next(), is(1));
    assertThat(sequence2.next(), is(1));
    for (int i = 1, j = 1, k = 1, t = 0; i < 100; i++) {
      t = j + k;
      j = k;
      k = t;
      assertThat(sequence2.next(), is(k));
    }
    sequence2 = new Sequence(new Integer[] { 42, -3, 0 }, 2);
    assertThat(sequence2.next(), is(42));
    assertThat(sequence2.next(), is(-3));
    assertThat(sequence2.next(), is(0));
    assertThat(sequence2.next(), is(-3));
    for (int i = 1, j = 0, k = -3, t = 0; i < 100; i++) {
      t = j + k;
      j = k;
      k = t;
      assertThat(sequence2.next(), is(k));
    }
  }

  @Test
  public void threeSequences() {
    Sequence sequence3 = new Sequence(new Integer[] { 0, 1, 2 }, 3);
    assertThat(sequence3.next(), is(0));
    assertThat(sequence3.next(), is(1));
    assertThat(sequence3.next(), is(2));
    for (int i = 1, j = 0, k = 1, m = 2, t = 0; i < 100; i++) {
      t = j + k + m;
      j = k;
      k = m;
      m = t;
      assertThat(sequence3.next(), is(m));
    }
    sequence3 = new Sequence(new Integer[] { 42, -3, 0 }, 3);
    assertThat(sequence3.next(), is(42));
    assertThat(sequence3.next(), is(-3));
    assertThat(sequence3.next(), is(0));
    assertThat(sequence3.next(), is(39));
    assertThat(sequence3.next(), is(36));
    assertThat(sequence3.next(), is(75));
    assertThat(sequence3.next(), is(150));
    assertThat(sequence3.next(), is(261));
  }

  @Test
  public void largerDepthThanInit() {
    Sequence seq = new Sequence(new Integer[] {0, 1}, 4);
    assertThat(seq.next(), is(0));
    assertThat(seq.next(), is(1));
    assertThat(seq.next(), is(1));
    assertThat(seq.next(), is(2));
  }

  @Test
  public void firstExample() {
    Sequence seq = new Sequence(new Integer[] { 4, 9, 12 }, 0);
    assertThat(seq.next(), is(4));
    assertThat(seq.next(), is(9));
    assertThat(seq.next(), is(12));
    assertThat(seq.next(), is(0));
    assertThat(seq.next(), is(0));
    assertThat(seq.next(), is(0));
    assertThat(seq.next(), is(0));
  }

  @Test
  public void secondExample() {
    Sequence seq = new Sequence(new Integer[] { 1 }, 1);
    assertThat(seq.next(), is(1));
    assertThat(seq.next(), is(1));
    assertThat(seq.next(), is(1));
    assertThat(seq.next(), is(1));
    assertThat(seq.next(), is(1));
    assertThat(seq.next(), is(1));
  }

  @Test
  public void thirdExample() {
    Sequence seq = new Sequence(new Integer[] { 3, 8 }, 1);
    assertThat(seq.next(), is(3));
    assertThat(seq.next(), is(8));
    assertThat(seq.next(), is(8));
    assertThat(seq.next(), is(8));
    assertThat(seq.next(), is(8));
    assertThat(seq.next(), is(8));
  }

  @Test
  public void fourthExample() {
    Sequence seq = new Sequence(new Integer[] { 2, 4 }, 2);
    assertThat(seq.next(), is(2));
    assertThat(seq.next(), is(4));
    assertThat(seq.next(), is(6));
    assertThat(seq.next(), is(10));
    assertThat(seq.next(), is(16));
    assertThat(seq.next(), is(26));
    assertThat(seq.next(), is(42));
    assertThat(seq.next(), is(68));
  }

  @Test
  public void fifthExample() {
    Sequence seq = new Sequence(new Integer[] { 2, 4, 6, 8 }, 3);
    assertThat(seq.next(), is(2));
    assertThat(seq.next(), is(4));
    assertThat(seq.next(), is(6));
    assertThat(seq.next(), is(8));
    assertThat(seq.next(), is(18));
    assertThat(seq.next(), is(32));
    assertThat(seq.next(), is(58));
    assertThat(seq.next(), is(108));
  }

  @Test
  public void negativeDepth() {
    Sequence seq = new Sequence(new Integer[] { 2, 3 }, -2);
    assertThat(seq.next(), is(2));
    assertThat(seq.next(), is(3));
    assertThat(seq.next(), is(0));
  }
}
