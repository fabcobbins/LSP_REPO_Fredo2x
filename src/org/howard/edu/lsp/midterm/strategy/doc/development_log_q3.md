# Development Log - Question 3

## External Resources Used

### AI Tool: Claude (claude.ai)

The following is the complete conversation transcript used during the completion of this question.

---

**Me:**
I have a PriceCalculator class that uses a chain of if statements to apply different discounts based on customer type. What's wrong with this design?

**Claude:**
The main issue is that it violates the Open/Closed Principle. Any time you need to add a new customer type, you have to modify the existing method. That's risky because you can accidentally break the logic for types that were already working. The method also just keeps growing — 4 types now, but what happens at 10 or 20? It becomes unreadable and untestable.

---

**Me:**
What design pattern would fix this?

**Claude:**
The Strategy Pattern. You define a `PricingStrategy` interface with a `calculatePrice(double price)` method, then create one concrete class per customer type — `RegularPricingStrategy`, `MemberPricingStrategy`, `VIPPricingStrategy`, `HolidayPricingStrategy`. The `PriceCalculator` becomes a context class that holds a reference to a strategy and delegates to it.

---

**Me:**
So PriceCalculator wouldn't have any if statements anymore?

**Claude:**
Exactly. The refactored `PriceCalculator` just calls `strategy.calculatePrice(price)`. The if logic is gone entirely. Each strategy class handles its own discount rule independently. To add a new customer type, you just add a new class — you never touch `PriceCalculator` again.

---

**Me:**
How should the Driver demonstrate this?

**Claude:**
Create a single `PriceCalculator` and use `setStrategy()` to swap in each strategy one by one, then call `calculatePrice(100.0)` and print the result. That demonstrates the runtime flexibility of the pattern — the same calculator object produces different results depending on which strategy is active.

---

**Me:**
What should go in the design evaluation?

**Claude:**
Focus on four things: the Open/Closed Principle violation (you have to modify the method to add types), the scalability problem (the method grows forever), the lack of runtime flexibility (behavior is locked in the if chain), and the difficulty of testing each discount rule in isolation. Then explain how the Strategy Pattern solves all four.
