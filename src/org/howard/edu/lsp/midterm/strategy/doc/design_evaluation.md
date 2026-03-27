# Design Evaluation: PriceCalculator

## Current Design

The original `PriceCalculator` class uses a chain of `if` statements to determine the correct pricing logic based on a `String` customer type passed at runtime.

---

## Problem 1: Open/Closed Principle Violation

The most significant issue is that adding any new customer type requires modifying the existing `calculatePrice()` method. If the business wants to add a `STUDENT` or `EMPLOYEE` discount, a developer must open the method, add another `if` block, and risk introducing bugs into already-working conditions. A well-designed system should be open for extension but closed for modification.

---

## Problem 2: Scalability — The Method Grows Indefinitely

As the number of customer types grows, so does the method. A system with 10 or 20 customer types produces a method that is long, hard to read, and harder to test. Each new condition adds risk to every other condition, since they all live in the same place and share the same local variables.

---

## Problem 3: No Runtime Flexibility

The current design requires the caller to pass a string and relies on the method to branch internally. There is no way to assign a pricing behavior to an object and carry it around — the behavior is always locked inside the `if` chain. This makes it impossible to compose or inject pricing strategies from outside the class.

---

## Problem 4: Difficult to Test in Isolation

Because all four pricing rules live inside a single method, you cannot write a focused unit test for just the VIP discount without running through the same method that handles REGULAR, MEMBER, and HOLIDAY. Each strategy should be independently testable.

---

## Solution: Strategy Pattern

The Strategy Pattern addresses all of these problems by extracting each pricing behavior into its own class that implements a common interface. The `PriceCalculator` context class holds a reference to a `PricingStrategy` and delegates to it. Adding a new customer type means adding a new class — the existing code is never touched.
