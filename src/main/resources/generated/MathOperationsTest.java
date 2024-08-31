import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathOperationsTest {

  @Test
  void testSquare() {
    double result = MathOperations.square(5);
    assertEquals(25, result);
  }

  @Test
  void testCube() {
    double result = MathOperations.cube(2);
    assertEquals(8, result);
  }

  @Test
  void testSqrt() {
    double result = MathOperations.sqrt(16);
    assertEquals(4, result);
  }

  @Test
  void testAbs() {
    double result = MathOperations.abs(-5);
    assertEquals(5, result);
  }

}
