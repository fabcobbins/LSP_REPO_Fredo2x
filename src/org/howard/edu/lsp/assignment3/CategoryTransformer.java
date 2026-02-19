package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Transforms product categories based on the original category and final price.
 *
 * <p>This class demonstrates <strong>inheritance</strong> by extending the
 * ProductTransformer abstract base class, and <strong>polymorphism</strong>
 * by providing a specific implementation of the transform method.
 * </p>
 *
 * <p>This transformer renames products that were originally in the "Electronics"
 * category to "Premium Electronics" if their final price (after discounts and
 * rounding) exceeds $500.00. It must run after the PriceTransformer to ensure
 * the price has been properly discounted and rounded.
 * </p>
 *
 * @author Michael Fabian Cobbins
 * @version 1.0
 */
public class CategoryTransformer extends ProductTransformer {

  /**
   * Transforms the product category to "Premium Electronics" for qualifying products.
   *
   * <p>A product qualifies for the "Premium Electronics" category if:
   * <ol>
   *   <li>Its original category (at construction time) was "Electronics"</li>
   *   <li>Its final price (after discounts and rounding) is greater than $500.00</li>
   * </ol>
   * </p>
   *
   * <p>This transformation must be applied after the PriceTransformer has
   * completed its work, as it depends on the final discounted and rounded price.
   * This matches the transformation logic from Assignment 2 (lines 97-100).
   * </p>
   *
   * @param product The product whose category may be transformed
   */
  @Override
  public void transform(Product product) {
    // Check if originally Electronics AND final price > $500.00
    if (product.getOriginalCategory().equals("Electronics") &&
        product.getPrice().compareTo(new BigDecimal("500.00")) > 0) {
      product.setCategory("Premium Electronics");
    }
  }
}
