package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ETLPipeline {

  private static final String INPUT_PATH = "data/products.csv";
  private static final String OUTPUT_PATH = "data/transformed_products.csv";

  public static void main(String[] args) {
    Path input = Paths.get(INPUT_PATH);
    Path output = Paths.get(OUTPUT_PATH);

    if (!Files.exists(input)) {
      System.out.println("Error: Missing input file at " + input.toString());
      return;
    }

    int rowsRead = 0;
    int rowsTransformed = 0;
    int rowsSkipped = 0;

    try {
      if (output.getParent() != null) {
        Files.createDirectories(output.getParent());
      }

      try (BufferedReader reader = Files.newBufferedReader(input);
           BufferedWriter writer = Files.newBufferedWriter(output)) {

        String header = reader.readLine();
        if (header == null) {
          writer.write("ProductID,Name,Price,Category,PriceRange");
          writer.newLine();
          printSummary(rowsRead, rowsTransformed, rowsSkipped, output);
          return;
        }

        writer.write("ProductID,Name,Price,Category,PriceRange");
        writer.newLine();

        String line;
        while ((line = reader.readLine()) != null) {
          rowsRead++;

          if (line.trim().isEmpty()) {
            rowsSkipped++;
            continue;
          }

          String[] parts = line.split(",", -1);
          if (parts.length != 4) {
            rowsSkipped++;
            continue;
          }

          String productIdStr = parts[0].trim();
          String name = parts[1].trim();
          String priceStr = parts[2].trim();
          String category = parts[3].trim();

          int productId;
          BigDecimal price;

          try {
            productId = Integer.parseInt(productIdStr);
          } catch (NumberFormatException e) {
            rowsSkipped++;
            continue;
          }

          try {
            price = new BigDecimal(priceStr);
          } catch (NumberFormatException e) {
            rowsSkipped++;
            continue;
          }

          boolean originalElectronics = category.equals("Electronics");

          String transformedName = name.toUpperCase();

          BigDecimal transformedPrice = price;
          if (originalElectronics) {
            transformedPrice = transformedPrice.multiply(new BigDecimal("0.9"));
          }

          BigDecimal finalRoundedPrice = transformedPrice.setScale(2, RoundingMode.HALF_UP);

          String transformedCategory = category;
          if (originalElectronics && finalRoundedPrice.compareTo(new BigDecimal("500.00")) > 0) {
            transformedCategory = "Premium Electronics";
          }

          String priceRange = getPriceRange(finalRoundedPrice);

          writer.write(productId + "," 
              + transformedName + "," 
              + finalRoundedPrice.toPlainString() + "," 
              + transformedCategory + "," 
              + priceRange);
          writer.newLine();

          rowsTransformed++;
        }
      }

      printSummary(rowsRead, rowsTransformed, rowsSkipped, output);

    } catch (IOException e) {
      System.out.println("Error: Unable to process files. " + e.getMessage());
    }
  }

  private static String getPriceRange(BigDecimal price) {
    if (price.compareTo(new BigDecimal("10.00")) <= 0) {
      return "Low";
    }
    if (price.compareTo(new BigDecimal("100.00")) <= 0) {
      return "Medium";
    }
    if (price.compareTo(new BigDecimal("500.00")) <= 0) {
      return "High";
    }
    return "Premium";
  }

  private static void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, Path output) {
    System.out.println("Rows read: " + rowsRead);
    System.out.println("Rows transformed: " + rowsTransformed);
    System.out.println("Rows skipped: " + rowsSkipped);
    System.out.println("Output written to: " + output.toString());
  }
}
