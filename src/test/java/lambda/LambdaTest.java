package lambda;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaTest {


  @Test
  public void should_apply_to_interface_with_single_abstract_method() {
    StringFunction lambda = () -> "Hello";

    // TODO: please modify the following code to pass the test
    // <--start
    final String expect = "Hello";
    // --end-->

    assertEquals(expect, lambda.getString());
  }

  @Test
  public void should_be_able_to_bind_to_instance_method() {
    // TODO: please bind lambda to instanceMethod(use method reference).
    // <--start
    StringFunction lambda = this::instanceMethod;
    // --end-->

    assertEquals("instanceMethod", lambda.getString());
  }

  @Test
  public void should_be_able_to_bind_to_static_method() {
    // TODO: please bind lambda to staticMethod(use method reference)
    // <--start
    StringFunction lambda = LambdaTest::staticMethod;
    // --end-->

    assertEquals("staticMethod", lambda.getString());
  }

  @Test
  public void should_bind_to_constructor() {
    // TODO: please bind lambda to constructor of List<Integer>(use method reference)
    // <--start
    GenericFunction<List<Integer>> lambda = new GenericFunction<List<Integer>>() {
      @Override
      public List<Integer> getValue() {
        return new ArrayList<Integer>();
      }
    };
    // --end-->

    List<Integer> value = lambda.getValue();

    assertEquals(0, value.size());
  }

  @Test
  public void should_capture_variable_in_a_closure() {
    int captured = 5;

    StringFunction lambda = () -> captured + " has been captured.";

    final String message = lambda.getString();

    // TODO: please modify the following code to pass the test
    // <--start
    final String expected = "5 has been captured.";
    // --end-->

    assertEquals(expected, message);
  }

  private static String staticMethod() {
    return "staticMethod";
  }

  private String instanceMethod() {
    return "instanceMethod";
  }

}
