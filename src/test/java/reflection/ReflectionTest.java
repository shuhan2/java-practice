package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionTest {
  @Test
  void should_be_able_to_get_class_object() {
    EmployeeR employeeR = new EmployeeR();
    Class<? extends EmployeeR> employeeClass = employeeR.getClass();

    final Class<? extends EmployeeR> expected = EmployeeR.class;

    assertEquals(expected, employeeClass);
  }

  @Test
  void should_be_able_to_get_full_qualified_name() {
    EmployeeR employeeR = new EmployeeR();
    Class<? extends EmployeeR> employeeClass = employeeR.getClass();

    final String expected = "reflection.EmployeeR";

    assertEquals(expected, employeeClass.getName());
  }

  @Test
  void should_be_able_to_instantiate_types_at_runtime() throws Exception {
    Class<?> theClass = Class.forName("reflection.EmployeeR");


    EmployeeR instance = (EmployeeR) theClass.newInstance();

    assertEquals("EmployeeTitle", instance.getTitle());
  }

  @Test
  void should_be_able_to_print_all_static_methods_of_double() {
    Class<Double> doubleClass = Double.class;

    String[] publicStaticMethods = Arrays.stream(doubleClass.getDeclaredMethods())
        .filter(m -> {
          int modifiers = m.getModifiers();
          return Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers); })
        .map(Method::getName)
        .sorted()
        .toArray(String[]::new);;

    final String[] expected = {
        "compare", "doubleToLongBits", "doubleToRawLongBits", "hashCode",
        "isFinite", "isInfinite", "isNaN", "longBitsToDouble", "max",
        "min", "parseDouble", "sum", "toHexString", "toString", "valueOf",
        "valueOf"
    };

    assertArrayEquals(expected, publicStaticMethods);
  }

  @Test
  void should_be_able_to_evaluate_object_field_values_at_runtime() throws Exception {
    Object employee = new EmployeeR();


    Class<?> theClass = employee.getClass();
    Object result = theClass.getMethod("getTitle").invoke(employee);

    assertEquals("EmployeeTitle", result);
  }

  @Test
  void should_be_able_to_get_the_item_class_of_the_array() {
    Object employees = new EmployeeR[0];

    Class<?> itemClass = employees.getClass().getComponentType();

    assertEquals(EmployeeR.class, itemClass);
  }

  @Test
  void should_be_able_to_get_the_methods_who_contains_MyAnnotation_annotation() {
    Class<MethodWithAnnotation> theClass = MethodWithAnnotation.class;

    String[] methodsContainsAnnotations = Arrays.stream(theClass.getMethods())
        .filter(a -> a.getAnnotation(MyAnnotation.class) != null)
        .map(Method::getName)
        .sorted()
        .toArray(String[]::new);

    assertArrayEquals(new String[] {"theMethod"}, methodsContainsAnnotations);
  }

}
