package SuperClass;

public class SuperClass {
  private String superField = getField();

  private String getField() {
    System.out.println("SuperClass Field ");
    return "field";
  }

  public SuperClass() {}

  {
    System.out.println("I’m SuperClass class");
  }

  static {
    System.out.println("static SuperClass");
  }

}
