package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 * Applies a 20% discount to the original price.
 *
 * @author Marcus Cobb
 */
public class VIPPricingStrategy implements PricingStrategy {

    /**
     * Calculates the final price after applying a 20% VIP discount.
     *
     * @param price the original price
     * @return the price after a 20% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}
