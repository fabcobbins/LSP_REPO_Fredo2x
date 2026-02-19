package org.howard.edu.lsp.assignment3;

/**
 * Transforms product names to uppercase.
 *
 * <p>This class demonstrates <strong>inheritance</strong> by extending the
 * ProductTransformer abstract base class, and <strong>polymorphism</strong>
 * by providing a specific implementation of the transform method. When used
 * in the ETL pipeline, this transformer can be referenced through the
 * ProductTransformer base class type, allowing for uniform treatment of
 * all transformers.
 * </p>
 *
 * @author Michael Fabian Cobbins
 * @version 1.0
 */
public class NameTransformer extends ProductTransformer {

  /**
   * Transforms the product name to uppercase.
   *
   * <p>This transformation modifies the product's name attribute in-place,
   * converting all characters to uppercase. This matches the transformation
   * performed in Assignment 2 at line 88.
   * </p>
   *
   * @param product The product whose name will be transformed to uppercase
   */
  @Override
  public void transform(Product product) {
    String uppercaseName = product.getName().toUpperCase();
    product.setName(uppercaseName);
  }
}
