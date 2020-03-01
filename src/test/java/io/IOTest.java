package io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpioneer.jupiter.TempDirectory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TempDirectory.class)
public class IOTest {

  @Test
  void should_read_write_file_from_file_stream(@TempDirectory.TempDir Path dir) throws Exception {
    final String message = "Hello world!" + System.lineSeparator();
    Path filePath = dir.resolve("sample.txt");

    writeAllText(message, filePath, StandardCharsets.UTF_8);
    assertEquals(message, readAllText(filePath, StandardCharsets.UTF_8));
  }

  private static void writeAllText(String message, Path filePath, Charset charset) throws IOException {
    try (PrintWriter printWriter = new PrintWriter(filePath.toString(), charset.name())) {
      printWriter.append(message);
    }

  }

  private static String readAllText(Path path, Charset charset) throws IOException {

    StringBuilder builder = new StringBuilder();
    try (Stream<String> list = Files.lines(path, charset)) {
      list.forEach(string -> builder.append(string).append(System.lineSeparator()));
    }
    return builder.toString();
  }

  @Test
  void should_be_able_to_write_and_read_binary_data_to_file(@TempDirectory.TempDir Path dir) throws Exception {
    Path filePath = dir.resolve("sample.bin");

    final int firstValue = 2018;
    final double pi = 3.14;

    try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(filePath.toString()))) {
      writer.writeInt(firstValue);
      writer.writeDouble(pi);
    }

    int actualFirstValue;
    double actualPi;

    try (DataInputStream reader = new DataInputStream(new FileInputStream(filePath.toString()))) {
      actualFirstValue = reader.readInt();
      actualPi = reader.readDouble();
    }

    assertEquals(firstValue, actualFirstValue);
    assertEquals(pi, actualPi);
  }

}
