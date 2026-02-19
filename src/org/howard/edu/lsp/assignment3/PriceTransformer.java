package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Transforms product prices by applying discounts and calculating price ranges.
 *
 * <p>This class demonstrates <strong>inheritance</strong> by extending the
 * ProductTransformer abstract base class, and <strong>polymorphism</strong>
 * by providing a specific implementation of the transform method.
 * </p>
 *
 * <p>The transformation applies a 10% discount to products originally in the
 * "Electronics" category, rounds all prices to 2 decimal places, and calculates
 * a price range category (Low, Medium, High, or Premium) based on the final price.
 * </p>
 *
 * @author Michael Fabian Cobbins
 * @version 1.0
 */
public class PriceTransformer extends ProductTransformer {

  /**
   * Transforms the product price by applying discounts, rounding, and calculating
   * the price range.
   *
   * <p>This transformation performs three operations:
   * <ol>
   *   <li>If the product's original category is "Electronics", applies a 10%
   *       discount by multiplying the price by 0.9</li>
   *   <li>Rounds the price to 2 decimal places using HALF_UP rounding mode</li>
   *   <li>Calculates and sets the price range based on the final rounded price</li>
   * </ol>
   * </p>
   *
   * <p>This matches the transformation logic from Assignment 2 (lines 90-95, 102).
   * </p>
   *
   * @param product The product whose price will be transformed
   */
  @Override
  public void transform(Product product) {
    BigDecimal transformedPrice = product.getPrice();

    // Apply 10% discount if original category is Electronics
    if (product.getOriginalCategory().equals("Electronics")) {
      transformedPrice = transformedPrice.multiply(new BigDecimal("0.9"));
    }

    // Round to 2 decimal places with HALF_UP rounding
    BigDecimal finalRoundedPrice = transformedPrice.setScale(2, RoundingMode.HALF_UP);
    product.setPrice(finalRoundedPrice);

    // Calculate and set price range based on final price
    String priceRange = calculatePriceRange(finalRoundedPrice);
    product.setPriceRange(priceRange);
  }

  /**
   * Calculates the price range category based on the product price.
   *
   * <p>Price ranges are determined as follows:
   * <ul>
   *   <li>Low: price ≤ $10.00</li>
   *   <li>Medium: $10.00 < price ≤ $100.00</li>
   *   <li>High: $100.00 < price ≤ $500.00</li>
   *   <li>Premium: price > $500.00</li>
   * </ul>
   * </p>
   *
   * <p>This matches the logic from Assignment 2 (lines 122-133).
   * </p>
   *
   * @param price The product price to categorize
   * @return The price range category (Low, Medium, High, or Premium)
   */
  private String calculatePriceRange(BigDecimal price) {
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
}
