package quiz.posMachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PosMachine {

  private static final String separator = "------------------------------------------------------------";
  private final String line = System.lineSeparator();
  HashMap<String, Product> products;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public void readDataSource(Reader reader) throws IOException {

    products = Arrays.stream(objectMapper.readValue(reader, Product[].class))
        .collect(HashMap::new, (map, item) -> map.put(item.getId(), item), HashMap::putAll);
  }

  public String printReceipt(String barcodeContent) throws IOException {

    if (products == null) {
      throw new IllegalStateException();
    }
    String[] ids = readIds(barcodeContent);
    return generateReceipt(ids);
  }


  private String generateReceipt(String[] ids) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Receipts").append(line).append(separator).append(line);
    Map<Product, Long> countMap = Arrays.stream(ids)
        .map(id -> products.get(id))
        .collect(Collectors.groupingBy(product -> product, Collectors.counting()));
    Long sum = countMap.keySet().stream()
        .sorted(Comparator.comparing(Product::getId))
        .map(product ->
             {
               String productLine = String.format(
                   "%-32s%-11s%s",
                   product.getName(),
                   product.getPrice().toString(),
                   countMap.get(product));
               stringBuilder.append(productLine).append(line);
               return countMap.get(product) * product.getPrice();
             }).reduce(0L, Long::sum);

    stringBuilder.append(separator).append(line).append("Price: ").append(sum).append(line);
    return stringBuilder.toString();
  }

  private String[] readIds(String codeContent) throws IOException {
    if (codeContent == null) {
      return new String[0];
    }
    return objectMapper.readValue(codeContent, String[].class);
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