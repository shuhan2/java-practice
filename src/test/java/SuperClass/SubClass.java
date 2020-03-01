package SuperClass;

public class SubClass extends SuperClass{
  private String subField = getSubField();

  private String getSubField() {
    System.out.println("SubClass Field");
    return "subField";
  }

  public SubClass() {
    System.out.println("I’m SubClass class");
  }

  static {
    System.out.println("static SubClass");
  }

  public static void main(String[] args) {
    new SubClass();
  }

}
