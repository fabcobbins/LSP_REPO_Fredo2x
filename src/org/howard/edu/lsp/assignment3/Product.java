package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product in the ETL pipeline with all its attributes.
 *
 * <p>This class demonstrates <strong>encapsulation</strong> by hiding internal
 * product data behind private fields and providing controlled access through
 * public getter and setter methods. The originalCategory field is immutable
 * after construction to preserve the product's initial category for
 * transformation logic.
 * </p>
 *
 * @author Michael Fabian Cobbins
 * @version 1.0
 */
public class Product {

  private int productId;
  private String name;
  private BigDecimal price;
  private String category;
  private final String originalCategory;
  private String priceRange;

  /**
   * Constructs a new Product with the specified attributes.
   *
   * <p>The originalCategory is set to the provided category value and
   * remains immutable throughout the product's lifecycle. This is necessary
   * for transformation logic that depends on the initial category value.
   * </p>
   *
   * @param productId The unique identifier for this product
   * @param name The product name
   * @param price The product price (must not be null)
   * @param category The product category
   */
  public Product(int productId, String name, BigDecimal price, String category) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.category = category;
    this.originalCategory = category;
    this.priceRange = null;
  }

  /**
   * Returns the product ID.
   *
   * @return The unique product identifier
   */
  public int getProductId() {
    return productId;
  }

  /**
   * Returns the product name.
   *
   * @return The product name (may be transformed to uppercase)
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the product name.
   *
   * @param name The new product name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the product price.
   *
   * @return The product price (may be discounted and rounded)
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * Sets the product price.
   *
   * @param price The new product price
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * Returns the current product category.
   *
   * @return The product category (may be transformed)
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets the product category.
   *
   * @param category The new product category
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Returns the original product category from construction time.
   *
   * <p>This value is immutable and always reflects the category value
   * provided to the constructor. It is used by transformation logic that
   * needs to check the original category (e.g., applying discounts to
   * products that were originally "Electronics" before any transformations).
   * </p>
   *
   * @return The original, unmodified product category
   */
  public String getOriginalCategory() {
    return originalCategory;
  }

  /**
   * Returns the calculated price range.
   *
   * @return The price range (Low, Medium, High, or Premium), or null if not yet calculated
   */
  public String getPriceRange() {
    return priceRange;
  }

  /**
   * Sets the calculated price range.
   *
   * @param priceRange The price range category (Low, Medium, High, or Premium)
   */
  public void setPriceRange(String priceRange) {
    this.priceRange = priceRange;
  }
}
