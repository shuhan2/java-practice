package reflection;

import generics.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionTest {
  @Test
  void should_be_able_to_get_class_object() {
    Employee employee = new Employee();
    Class<? extends Employee> employeeClass = employee.getClass();

    // TODO: please modify the following code to pass the test
    // <--start
    final Class<? extends Employee> expected = null;
    // --end-->

    assertEquals(expected, employeeClass);
  }

  @Test
  void should_be_able_to_get_full_qualified_name() {
    Employee employee = new Employee();
    Class<? extends Employee> employeeClass = employee.getClass();

    // TODO: please modify the following code to pass the test
    // <--start
    final String expected = null;
    // --end-->

    assertEquals(expected, employeeClass.getName());
  }

  @Test
  void should_be_able_to_instantiate_types_at_runtime() throws Exception {
    Class<?> theClass = Class.forName("com.cultivation.javaBasic.util.Employee");

    // TODO: please created an instance described by `theClass`
    // <--start
    Employee instance = null;
    // --end-->

    assertEquals("Employee", instance.getTitle());
  }

  @SuppressWarnings({"ConstantConditions", "unused"})
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
    Object employee = new Employee();

    // TODO: please get the value of `getTitle` method using reflection. No casting to Employee is allowed.
    // <--start
    Object result = null;
    // --end-->

    assertEquals("Employee", result);
  }

  @Test
  void should_be_able_to_get_the_item_class_of_the_array() {
    Object employees = new Employee[0];

    // TODO: please get the class of array item `employees`
    // <--start
    Class<?> itemClass = employees.getClass();
    // --end-->

    assertEquals(Employee.class, itemClass);
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
