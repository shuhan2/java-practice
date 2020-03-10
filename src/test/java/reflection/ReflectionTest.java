package reflection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionTest {
  @Test
  void should_be_able_to_get_class_object() {
    EmployeeR employee = new EmployeeR();
    Class<? extends EmployeeR> employeeClass = employee.getClass();

    // TODO: please modify the following code to pass the test
    // <--start
    final Class<? extends EmployeeR> expected = null;
    // --end-->

    assertEquals(expected, employeeClass);
  }

  @Test
  void should_be_able_to_get_full_qualified_name() {
    EmployeeR employee = new EmployeeR();
    Class<? extends EmployeeR> employeeClass = employee.getClass();

    // TODO: please modify the following code to pass the test
    // <--start
    final String expected = null;
    // --end-->

    assertEquals(expected, employeeClass.getName());
  }

  @Test
  void should_be_able_to_instantiate_types_at_runtime() throws Exception {
    Class<?> theClass = Class.forName("reflection.EmployeeR");

    // TODO: please created an instance described by `theClass`
    // <--start
    EmployeeR instance = null;
    // --end-->

    assertEquals("EmployeeTitle", instance.getTitle());
  }

  @Test
  void should_be_able_to_print_all_static_methods_of_double() {
    Class<Double> doubleClass = Double.class;

    // TODO: please get all public static declared methods of Double. Sorted in an ascending order
    // <--start
    String[] publicStaticMethods = null;
    // --end-->

    final String[] expected = {
        "compare", "doubleToLongBits", "doubleToRawLongBits", "hashCode",
        "isFinite", "isInfinite", "isNaN", "longBitsToDouble", "max",
        "min", "parseDouble", "sum", "toHexString", "toString", "valueOf",
        "valueOf"
    };

    assertArrayEquals(expected, publicStaticMethods);
  }

  @Test
  void should_be_able_to_evaluate_object_field_values_at_runtime() {
    Object employee = new EmployeeR();

    // TODO: please get the value of `getTitle` method using reflection. No casting to EmployeeR is allowed.
    // <--start
    Object result = null;
    // --end-->

    assertEquals("EmployeeTitle", result);
  }

  @Test
  void should_be_able_to_get_the_item_class_of_the_array() {
    Object employees = new EmployeeR[0];

    // TODO: please get the class of array item `employees`
    // <--start
    Class<?> itemClass = null;
    // --end-->

    assertEquals(EmployeeR.class, itemClass);
  }

  @Test
  void should_be_able_to_get_the_methods_who_contains_MyAnnotation_annotation() {
    Class<MethodWithAnnotation> theClass = MethodWithAnnotation.class;

    // TODO: please get the methods who contains MyAnnotation annotation.
    // <--start
    String[] methodsContainsAnnotations = null;
    // --end-->

    assertArrayEquals(new String[] {"theMethod"}, methodsContainsAnnotations);
  }

}
