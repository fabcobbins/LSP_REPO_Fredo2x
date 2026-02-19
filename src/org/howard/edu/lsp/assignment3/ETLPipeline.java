package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Coordinates the Extract-Transform-Load (ETL) pipeline for product data.
 *
 * <p>This class demonstrates <strong>composition</strong> by using multiple
 * collaborating objects (readers, writers, transformers, statistics), and
 * <strong>polymorphism</strong> by maintaining a list of ProductTransformer
 * objects that are treated uniformly through their common base class while
 * each implements different transformation logic.
 * </p>
 *
 * <p>The pipeline performs three main operations:
 * <ol>
 *   <li><strong>Extract:</strong> Read products from the input CSV file</li>
 *   <li><strong>Transform:</strong> Apply a series of transformations to each product</li>
 *   <li><strong>Load:</strong> Write transformed products to the output CSV file</li>
 * </ol>
 * </p>
 *
 * <p>This is the main entry point for the program. It maintains the same
 * functionality as Assignment 2 while demonstrating object-oriented design
 * principles through proper decomposition and responsibility assignment.
 * </p>
 *
 * @author Michael Fabian Cobbins
 * @version 1.0
 */
public class ETLPipeline {

  private static final String INPUT_PATH = "data/products.csv";
  private static final String OUTPUT_PATH = "data/transformed_products.csv";

  private List<ProductTransformer> transformers;
  private ETLStatistics statistics;

  /**
   * Executes the ETL pipeline for the given input and output files.
   *
   * <p>This method orchestrates the entire ETL process:
   * <ol>
   *   <li>Initializes statistics tracking</li>
   *   <li>Creates the transformation pipeline (demonstrating polymorphism)</li>
   *   <li>Opens input and output files</li>
   *   <li>Reads each product from the input</li>
   *   <li>Applies all transformations in sequence</li>
   *   <li>Writes the transformed product to the output</li>
   *   <li>Prints execution statistics</li>
   * </ol>
   * </p>
   *
   * <p>The transformer list demonstrates <strong>polymorphism</strong> - each
   * transformer is stored as a ProductTransformer reference but executes its
   * own specific implementation of the transform method. The transformers are
   * applied in a specific order: name transformation, then price transformation,
   * then category transformation. This order is critical for correct results.
   * </p>
   *
   * @param inputPath The path to the input CSV file
   * @param outputPath The path to the output CSV file
   * @throws IOException If an I/O error occurs during processing
   */
  public void run(Path inputPath, Path outputPath) throws IOException {
    // Initialize statistics
    statistics = new ETLStatistics();

    // Create transformation pipeline (demonstrates polymorphism!)
    // The order matters: Name -> Price -> Category
    transformers = new ArrayList<>();
    transformers.add(new NameTransformer());
    transformers.add(new PriceTransformer());
    transformers.add(new CategoryTransformer());

    // Process the ETL pipeline using try-with-resources for automatic cleanup
    try (CSVProductReader reader = new CSVProductReader(inputPath, statistics);
         CSVProductWriter writer = new CSVProductWriter(outputPath)) {

      // Read and process each product
      Product product;
      while ((product = reader.readNext()) != null) {
        // Apply all transformations polymorphically
        // Each transformer is called through the ProductTransformer interface,
        // but executes its own specific implementation
        for (ProductTransformer transformer : transformers) {
          transformer.transform(product);
        }

        // Write the transformed product
        writer.write(product);
        statistics.incrementRowsTransformed();
      }
    }

    // Print execution summary
    statistics.printSummary(outputPath);
  }

  /**
   * Main entry point for the ETL pipeline program.
   *
   * <p>This method validates the input file exists, creates the pipeline
   * instance, and executes the ETL process. Any I/O errors are caught and
   * reported to the user.
   * </p>
   *
   * <p>The input and output file paths are hardcoded to match Assignment 2's
   * behavior: data/products.csv and data/transformed_products.csv.
   * </p>
   *
   * @param args Command-line arguments (not used)
   */
  public static void main(String[] args) {
    Path inputPath = Paths.get(INPUT_PATH);
    Path outputPath = Paths.get(OUTPUT_PATH);

    // Validate input file exists
    if (!Files.exists(inputPath)) {
      System.out.println("Error: Missing input file at " + inputPath.toString());
      return;
    }

    // Create and run the pipeline
    ETLPipeline pipeline = new ETLPipeline();
    try {
      pipeline.run(inputPath, outputPath);
    } catch (IOException e) {
      System.out.println("Error: Unable to process files. " + e.getMessage());
    }
  }
}
