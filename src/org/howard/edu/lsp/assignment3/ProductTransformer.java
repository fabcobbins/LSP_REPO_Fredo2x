package org.howard.edu.lsp.assignment3;

/**
 * Abstract base class for all product transformations in the ETL pipeline.
 *
 * <p>This class demonstrates <strong>inheritance</strong> by serving as a base
 * class that concrete transformers extend, and <strong>polymorphism</strong> by
 * defining an abstract transform method that each subclass must implement
 * differently. This design allows the ETL pipeline to treat all transformers
 * uniformly through a common interface while each transformer implements its
 * own specific transformation logic.
 * </p>
 *
 * <p>Subclasses must implement the {@link #transform(Product)} method to define
 * their specific transformation behavior. Examples include transforming names
 * to uppercase, applying price discounts, or renaming categories.
 * </p>
 *
 * @author Michael Fabian Cobbins
 * @version 1.0
 */
public abstract class ProductTransformer {

  /**
   * Transforms the given product according to the specific transformation rules
   * implemented by the concrete subclass.
   *
   * <p>This method is called by the ETL pipeline for each product. The
   * transformation is performed in-place, modifying the product's attributes
   * directly rather than returning a new product object.
   * </p>
   *
   * @param product The product to transform (must not be null)
   */
  public abstract void transform(Product product);
}
