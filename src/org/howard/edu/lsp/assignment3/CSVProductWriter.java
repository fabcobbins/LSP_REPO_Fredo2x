package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writes products to a CSV file in the output format.
 *
 * <p>This class demonstrates <strong>encapsulation</strong> by hiding the
 * complexity of file I/O operations and CSV formatting behind a simple
 * interface. Clients provide Product objects and do not need to know about
 * BufferedWriters, CSV formatting, or file creation details.
 * </p>
 *
 * <p>The writer automatically creates parent directories if needed and writes
 * the CSV header row upon construction. Products are written with 5 fields:
 * ProductID, Name, Price, Category, and PriceRange.
 * </p>
 *
 * <p>This class implements AutoCloseable to ensure proper resource cleanup
 * when used in try-with-resources blocks.
 * </p>
 *
 * @author Michael Fabian Cobbins
 * @version 1.0
 */
public class CSVProductWriter implements AutoCloseable {

  private final BufferedWriter writer;

  /**
   * Constructs a new CSVProductWriter for the specified output file.
   *
   * <p>This constructor creates any necessary parent directories, opens the
   * file for writing, and immediately writes the CSV header row. After
   * construction, the writer is ready to write product data rows.
   * </p>
   *
   * @param outputPath The path to the CSV file to write
   * @throws IOException If the file cannot be created or written
   */
  public CSVProductWriter(Path outputPath) throws IOException {
    // Create parent directories if they don't exist
    if (outputPath.getParent() != null) {
      Files.createDirectories(outputPath.getParent());
    }

    // Open the file for writing
    this.writer = Files.newBufferedWriter(outputPath);

    // Write the header row
    writer.write("ProductID,Name,Price,Category,PriceRange");
    writer.newLine();
  }

  /**
   * Writes a product to the CSV file.
   *
   * <p>The product is formatted as a CSV line with 5 fields:
   * ProductID, Name, Price, Category, PriceRange. The price is written
   * using toPlainString() to avoid scientific notation for large or small
   * values.
   * </p>
   *
   * <p>This matches the output format from Assignment 2 (lines 104-109).
   * </p>
   *
   * @param product The product to write to the CSV file
   * @throws IOException If an I/O error occurs while writing
   */
  public void write(Product product) throws IOException {
    writer.write(product.getProductId() + ","
        + product.getName() + ","
        + product.getPrice().toPlainString() + ","
        + product.getCategory() + ","
        + product.getPriceRange());
    writer.newLine();
  }

  /**
   * Closes the underlying BufferedWriter and releases system resources.
   *
   * <p>This method flushes any buffered data to disk before closing the file.
   * </p>
   *
   * @throws IOException If an I/O error occurs while closing the writer
   */
  @Override
  public void close() throws IOException {
    writer.close();
  }
}
