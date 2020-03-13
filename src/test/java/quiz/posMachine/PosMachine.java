package quiz.posMachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PosMachine {

  private static final String separator = "------------------------------------------------------------";
  private final String line = System.lineSeparator();
  private final ObjectMapper objectMapper = new ObjectMapper();

  // Note: much blank space in test each line can be output by this way
  // String productLine = String.format(
  //                "%-32s%-11s%s",
  //                " ",
  //                " ",
  //                " ");

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