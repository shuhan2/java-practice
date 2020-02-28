package exception;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExceptionTest {

  @Test
  void should_use_the_try_pattern() {
    ClosableStateReference closableStateReference = new ClosableStateReference();
    try (MyClosableType closable = new MyClosableType(closableStateReference))
    {
      assertFalse(closable.isClosed());
    }

    // TODO: please modify the following code to pass the test
    final Optional<Boolean> expected = Optional.empty();

    assertEquals(expected.get(), closableStateReference.isClosed());
  }

  @Test
  void should_stop_closing_if_exception_occurred_beforehand() throws Exception {
    ArrayList<String> logger = new ArrayList<>();

    try {
      try (AutoCloseable withoutThrow = new ClosableWithoutException(logger);
          AutoCloseable withThrow = new ClosableWithException(logger)) {
      }
    } catch (Exception e) {
      // It is okay!
    }

    // TODO: please modify the following code to pass the test
    // <--start
    final String[] expected = null;
    // --end-->

    assertArrayEquals(
        expected,
        logger.toArray());
  }

}
