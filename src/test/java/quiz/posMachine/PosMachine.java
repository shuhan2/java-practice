package quiz.posMachine;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PosMachine {
  public void readDataSource(Reader reader) throws IOException {
    // TODO: please implement the following method to pass the test
    // <--start
    throw new NotImplementedException();
    // --end-->
  }

  public String printReceipt(String barcodeContent) throws IOException {
    // TODO: please implement the following method to pass the test
    // <--start
    throw new NotImplementedException();
    // --end-->
  }
}

class Product {

  private String id;
  private String name;
  private Integer price;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    Product other = (Product) obj;

    return Objects.equals(id, other.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}