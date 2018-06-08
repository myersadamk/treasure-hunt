package exigentech.codility.practice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

final class SolutionTest {

  @Test
  public void oneSetBit() {
    assertThat(new Solution().solution(3), is(0));
  }

  @Test
  public void trailingZeroes() {
    assertThat(new Solution().solution(328), is(2));
  }

  @Test
  public void twoGaps() {
      assertThat(new Solution().solution(529), is(4));
  }

  @Test
  public void noGaps() {
    assertThat(new Solution().solution(15), is(0));
  }

  @Test
  public void medium() {
    assertThat(new Solution().solution(20), is(1));
  }

  @Test
  public void large() {
    assertThat(new Solution().solution(6291457), is(20));
  }
}