package org.howard.edu.lsp.assignment3;

import java.nio.file.Path;

/**
 * Tracks and reports statistics for the ETL pipeline execution.
 *
 * <p>This class demonstrates <strong>encapsulation</strong> by hiding the
 * internal counter implementations behind private fields and providing
 * controlled access through increment methods and getters. This design
 * allows the internal representation to change (e.g., to thread-safe
 * counters) without affecting client code.
 * </p>
 *
 * @author Michael Fabian Cobbins
 * @version 1.0
 */
public class ETLStatistics {

  private int rowsRead;
  private int rowsTransformed;
  private int rowsSkipped;

  /**
   * Constructs a new ETLStatistics object with all counters initialized to zero.
   */
  public ETLStatistics() {
    this.rowsRead = 0;
    this.rowsTransformed = 0;
    this.rowsSkipped = 0;
  }

  /**
   * Increments the count of rows read from the input file.
   *
   * <p>This should be called for every non-empty line read from the input,
   * regardless of whether the row is valid or skipped.
   * </p>
   */
  public void incrementRowsRead() {
    rowsRead++;
  }

  /**
   * Increments the count of rows successfully transformed and written.
   *
   * <p>This should be called only after a product has been successfully
   * transformed and written to the output file.
   * </p>
   */
  public void incrementRowsTransformed() {
    rowsTransformed++;
  }

  /**
   * Increments the count of rows skipped due to validation errors.
   *
   * <p>This should be called when a row cannot be processed due to
   * missing fields, invalid data types, or empty lines.
   * </p>
   */
  public void incrementRowsSkipped() {
    rowsSkipped++;
  }

  /**
   * Returns the total number of rows read from the input file.
   *
   * @return The count of non-empty rows read
   */
  public int getRowsRead() {
    return rowsRead;
  }

  /**
   * Returns the number of rows successfully transformed and written.
   *
   * @return The count of successfully processed rows
   */
  public int getRowsTransformed() {
    return rowsTransformed;
  }

  /**
   * Returns the number of rows skipped due to validation errors.
   *
   * @return The count of skipped rows
   */
  public int getRowsSkipped() {
    return rowsSkipped;
  }

  /**
   * Prints a summary of the ETL pipeline execution statistics to the console.
   *
   * <p>This method outputs the number of rows read, transformed, and skipped,
   * along with the path to the output file. The format matches the original
   * Assignment 2 implementation.
   * </p>
   *
   * @param outputPath The path to the output file
   */
  public void printSummary(Path outputPath) {
    System.out.println("Rows read: " + rowsRead);
    System.out.println("Rows transformed: " + rowsTransformed);
    System.out.println("Rows skipped: " + rowsSkipped);
    System.out.println("Output written to: " + outputPath.toString());
  }
}
