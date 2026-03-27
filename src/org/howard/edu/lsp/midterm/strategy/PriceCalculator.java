package org.howard.edu.lsp.midterm.strategy;

/**
 * Context class for the Strategy Pattern.
 * Delegates price calculation to a PricingStrategy implementation,
 * allowing the discount behavior to be swapped at runtime.
 *
 * @author Marcus Cobb
 */
public class PriceCalculator {

    private PricingStrategy strategy;

    /**
     * Constructs a PriceCalculator with the specified pricing strategy.
     *
     * @param strategy the pricing strategy to use for calculating prices
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sets a new pricing strategy, allowing the behavior to be changed at runtime.
     *
     * @param strategy the new pricing strategy to apply
     */
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price by delegating to the current pricing strategy.
     *
     * @param price the original price before discount
     * @return the final price after applying the strategy's discount
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}
