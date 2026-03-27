package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday promotions.
 * Applies a 15% discount to the original price.
 *
 * @author Marcus Cobb
 */
public class HolidayPricingStrategy implements PricingStrategy {

    /**
     * Calculates the final price after applying a 15% holiday discount.
     *
     * @param price the original price
     * @return the price after a 15% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}
