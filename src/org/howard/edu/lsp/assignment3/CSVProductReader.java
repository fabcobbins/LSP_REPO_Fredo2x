package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Reads and parses products from a CSV file.
 *
 * <p>This class demonstrates <strong>encapsulation</strong> by hiding the
 * complexity of file I/O operations and CSV parsing behind a simple interface.
 * Clients interact only with Product objects, without needing to know about
 * BufferedReaders, line splitting, or validation logic.
 * </p>
 *
 * <p>The reader validates each row and tracks statistics for rows read and
 * skipped. Invalid rows (empty lines, wrong field count, unparseable data)
 * are silently skipped, allowing the ETL pipeline to continue processing
 * valid data.
 * </p>
 *
 * <p>This class implements AutoCloseable to ensure proper resource cleanup
 * when used in try-with-resources blocks.
 * </p>
 *
 * @author Michael Fabian Cobbins
 * @version 1.0
 */
public class CSVProductReader implements AutoCloseable {

  private final BufferedReader reader;
  private final ETLStatistics statistics;

  /**
   * Constructs a new CSVProductReader for the specified input file.
   *
   * <p>This constructor opens the file and reads (and discards) the header row.
   * After construction, the reader is positioned to read the first data row.
   * </p>
   *
   * @param inputPath The path to the CSV file to read
   * @param statistics The statistics tracker for recording rows read and skipped
   * @throws IOException If the file cannot be opened or read
   */
  public CSVProductReader(Path inputPath, ETLStatistics statistics) throws IOException {
    this.reader = Files.newBufferedReader(inputPath);
    this.statistics = statistics;

    // Read and discard the header row
    reader.readLine();
  }

  /**
   * Reads and parses the next valid product from the CSV file.
   *
   * <p>This method reads lines from the CSV file until it finds a valid product
   * or reaches the end of the file. Invalid rows are skipped and counted in
   * the statistics. A row is considered invalid if:
   * <ul>
   *   <li>It is empty or contains only whitespace</li>
   *   <li>It does not have exactly 4 fields</li>
   *   <li>The ProductID cannot be parsed as an integer</li>
   *   <li>The Price cannot be parsed as a decimal number</li>
   * </ul>
   * </p>
   *
   * <p>For each line read (including empty lines), the rowsRead counter is
   * incremented. For each invalid row, the rowsSkipped counter is incremented.
   * </p>
   *
   * @return A valid Product object, or null if the end of file is reached
   * @throws IOException If an I/O error occurs while reading the file
   */
  public Product readNext() throws IOException {
    String line;

    while ((line = reader.readLine()) != null) {
      // Increment rows read for every line (including empty lines)
      statistics.incrementRowsRead();

      // Skip empty lines
      if (line.trim().isEmpty()) {
        statistics.incrementRowsSkipped();
        continue;
      }

      // Split line into fields (limit -1 to preserve empty trailing fields)
      String[] parts = line.split(",", -1);

      // Validate field count (must be exactly 4)
      if (parts.length != 4) {
        statistics.incrementRowsSkipped();
        continue;
      }

      // Trim all parts
      String productIdStr = parts[0].trim();
      String name = parts[1].trim();
      String priceStr = parts[2].trim();
      String category = parts[3].trim();

      // Validate and parse ProductID
      int productId;
      try {
        productId = Integer.parseInt(productIdStr);
      } catch (NumberFormatException e) {
        statistics.incrementRowsSkipped();
        continue;
      }

      // Validate and parse Price
      BigDecimal price;
      try {
        price = new BigDecimal(priceStr);
      } catch (NumberFormatException e) {
        statistics.incrementRowsSkipped();
        continue;
      }

      // All validations passed - create and return Product
      return new Product(productId, name, price, category);
    }

    // End of file reached
    return null;
  }

  /**
   * Closes the underlying BufferedReader and releases system resources.
   *
   * @throws IOException If an I/O error occurs while closing the reader
   */
  @Override
  public void close() throws IOException {
    reader.close();
  }
}
