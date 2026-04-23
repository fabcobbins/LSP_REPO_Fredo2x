# Question 5 — Arthur Riel's OO Design Heuristics

**Course:** Large-Scale Programming  
**Author:** Michael Cobbins  
**Date:** April 23, 2026

---

## Heuristic 1:

**Name:** All data should be hidden within its class.

**Explanation:**
This heuristic enforces encapsulation by requiring that fields be declared private and accessed only through controlled public methods. In lecture, this was illustrated by showing that exposing raw fields directly allows external code to modify internal state without going through the class's own logic — breaking invariants and making bugs harder to trace. When data is hidden, the internal representation can change without affecting any client that depends on the class, which directly improves maintainability. It also improves readability because the public interface communicates what a class *does*, not how it stores data internally.

---

## Heuristic 2:

**Name:** Minimize the number of messages in the public interface of a class.

**Explanation:**
This heuristic states that a class should expose only the methods that external clients genuinely need, and keep implementation-detail methods private. In lecture, this was discussed in the context of separating internal helper methods from the public contract of a class — a helper that only exists to support other methods in the same class has no reason to be public. Keeping the public interface small reduces the surface area that other classes can depend on, which makes refactoring safer and reduces unintended coupling. It also improves readability because clients reading a class's API see a focused, purposeful set of operations rather than a mix of public and internal methods.

---

## Heuristic 3:

**Name:** Inheritance should only be used to model an is-a relationship.

**Explanation:**
This heuristic warns against using inheritance purely for code reuse when no true generalization exists between the classes. In lecture, this was explained by contrasting is-a inheritance (a `StudentReport` is a `Report`) with has-a composition (a `PriceCalculator` has a `PricingStrategy`). When inheritance is used correctly, polymorphism works as intended — a `List<Report>` can hold any subtype and call `generateReport()` without knowing the concrete type. When inheritance is misused, subclasses are forced to inherit behavior they don't logically own, making the hierarchy confusing and fragile. Keeping inheritance tied to genuine is-a relationships makes class hierarchies easier to read and extend correctly.
