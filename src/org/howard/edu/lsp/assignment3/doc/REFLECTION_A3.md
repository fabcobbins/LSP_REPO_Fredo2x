# Assignment 3 Reflection: Object-Oriented Redesign

**Author:** Michael Fabian Cobbins
**Date:** February 18, 2026

---

## Overview

This document reflects on the redesign of Assignment 2's monolithic ETL pipeline into an object-oriented architecture for Assignment 3. The goal was to apply object-oriented decomposition principles while maintaining identical functionality.

---

## Architecture Comparison: Assignment 2 vs Assignment 3

### Assignment 2: Monolithic Design

**Structure:**
- Single class: `ETLPipeline.java` (142 lines)
- All logic in the `main()` method (~120 lines)
- Two helper methods: `getPriceRange()` and `printSummary()`

**Problems:**
1. **Violation of Single Responsibility Principle:** The `main()` method handles everything:
   - File I/O (opening, reading, writing)
   - CSV parsing and formatting
   - Data validation
   - Business logic (transformations)
   - Statistics tracking
   - Error handling

2. **No Separation of Concerns:** Extract, Transform, and Load operations are intermingled in a single loop.

3. **Low Reusability:** Cannot reuse parsing logic, transformations, or statistics tracking in other contexts.

4. **Difficult to Test:** All logic is static and tightly coupled, making unit testing nearly impossible.

5. **Hard to Extend:** Adding a new transformation requires modifying the main method's procedural code.

### Assignment 3: Object-Oriented Design

**Structure:**
- 9 classes with clear responsibilities
- Proper separation of concerns
- Use of inheritance, polymorphism, and encapsulation

**Classes:**
1. **Product** - Domain model (encapsulation)
2. **ETLStatistics** - Statistics tracking (encapsulation)
3. **ProductTransformer** - Abstract base class (inheritance)
4. **NameTransformer** - Concrete transformer (inheritance + polymorphism)
5. **PriceTransformer** - Concrete transformer (inheritance + polymorphism)
6. **CategoryTransformer** - Concrete transformer (inheritance + polymorphism)
7. **CSVProductReader** - Input handling (encapsulation)
8. **CSVProductWriter** - Output handling (encapsulation)
9. **ETLPipeline** - Orchestration (composition + polymorphism)

**Benefits:**
1. **Single Responsibility:** Each class has one clear purpose.
2. **Separation of Concerns:** Extract, Transform, and Load are distinct components.
3. **High Reusability:** Transformers, readers, and writers can be reused independently.
4. **Testable:** Each class can be unit tested in isolation.
5. **Extensible:** New transformers can be added by extending `ProductTransformer` without modifying existing code.

---

## Object-Oriented Principles Demonstrated

### 1. Encapsulation

**Definition:** Hiding internal implementation details and providing controlled access through public interfaces.

**Examples:**

**Product Class:**
- **What:** Private fields (`productId`, `name`, `price`, `category`, `originalCategory`, `priceRange`) with public getters/setters.
- **Why:** Protects data integrity by controlling how product attributes are accessed and modified. The `originalCategory` field is immutable (final), ensuring it can never be changed after construction.
- **Benefit:** Could add validation in setters (e.g., price must be positive) without affecting client code.

**ETLStatistics Class:**
- **What:** Private counters (`rowsRead`, `rowsTransformed`, `rowsSkipped`) with increment methods.
- **Why:** Hides the counter implementation. Could switch to thread-safe atomic integers without changing the public interface.
- **Benefit:** Prevents direct manipulation of counters, ensuring they can only increase (no accidental decrements).

**CSVProductReader Class:**
- **What:** Hides `BufferedReader` and CSV parsing complexity behind a simple `readNext()` interface.
- **Why:** Clients work with `Product` objects, not raw CSV strings or file handles.
- **Benefit:** Could switch to a different file format (JSON, XML) by creating a new reader class implementing the same interface pattern.

**CSVProductWriter Class:**
- **What:** Hides `BufferedWriter` and CSV formatting logic behind a simple `write(Product)` interface.
- **Why:** Clients provide `Product` objects without knowing about CSV syntax or file I/O.
- **Benefit:** Could easily add JSON output by creating a `JSONProductWriter` with the same interface.

### 2. Inheritance

**Definition:** Creating a base class that child classes extend, inheriting shared behavior and structure.

**Example:**

**ProductTransformer Hierarchy:**
```
ProductTransformer (abstract base class)
    ├── NameTransformer (concrete)
    ├── PriceTransformer (concrete)
    └── CategoryTransformer (concrete)
```

- **What:** `ProductTransformer` defines an abstract `transform(Product)` method that all subclasses must implement.
- **Why:** Establishes a common contract for all transformations while allowing each transformer to implement its own specific logic.
- **Benefit:** New transformers can be added by extending the base class. For example, adding a `DescriptionTransformer` would require only creating a new class—no changes to existing code.

### 3. Polymorphism

**Definition:** Treating objects of different types uniformly through a common interface, with each object executing its own specific behavior.

**Example:**

**Transformer Pipeline in ETLPipeline:**
```java
List<ProductTransformer> transformers = new ArrayList<>();
transformers.add(new NameTransformer());
transformers.add(new PriceTransformer());
transformers.add(new CategoryTransformer());

for (ProductTransformer transformer : transformers) {
    transformer.transform(product);  // Polymorphic call!
}
```

- **What:** The `ETLPipeline` maintains a `List<ProductTransformer>` and calls `transform()` on each element without knowing which concrete class it is.
- **Why:** Each transformer executes its own implementation of `transform()` at runtime.
- **Benefit:** Transformers can be added, removed, or reordered without changing the loop logic. The pipeline treats all transformers uniformly through their common base type.

### 4. Composition

**Definition:** Building complex functionality by combining simpler, independent objects.

**Example:**

**ETLPipeline Class:**
- **What:** Uses `CSVProductReader`, `CSVProductWriter`, `List<ProductTransformer>`, and `ETLStatistics` to coordinate the ETL process.
- **Why:** Rather than implementing all functionality itself, the pipeline delegates to specialized components.
- **Benefit:** Each component can be developed, tested, and maintained independently. Changes to the reader don't affect the transformers or writer.

---

## Critical Design Decisions and Tradeoffs

### Decision 1: Store `originalCategory` in Product

**Problem:** The category transformation checks if the ORIGINAL category was "Electronics" (before any modifications) but needs the FINAL price (after discount).

**Solution:** Added an immutable `originalCategory` field to the `Product` class that's set in the constructor and never modified.

**Tradeoff:**
- ✅ **Pro:** Simple, clear, and matches Assignment 2's logic exactly (line 86: `boolean originalElectronics = category.equals("Electronics");`)
- ✅ **Pro:** Makes the transformation logic straightforward and bug-free.
- ❌ **Con:** Adds an extra field to the `Product` class that's only used by one transformer.

**Alternative Considered:** Pass original category as a parameter to transformers. Rejected because it would break the uniform `transform(Product)` interface.

### Decision 2: Transformation Order

**Problem:** Transformations must execute in a specific order because CategoryTransformer depends on the final discounted price from PriceTransformer.

**Solution:** Document the order requirement clearly and use an ordered list in `ETLPipeline`:
```java
transformers.add(new NameTransformer());      // 1. Transform name
transformers.add(new PriceTransformer());     // 2. Discount and round price
transformers.add(new CategoryTransformer());  // 3. Rename category if needed
```

**Tradeoff:**
- ✅ **Pro:** Simple and explicit. The order is clear in the code.
- ✅ **Pro:** Each transformer remains independent (no inter-transformer dependencies).
- ❌ **Con:** The order requirement is implicit—reordering transformers could break functionality.

**Alternative Considered:** Add priority levels or dependencies to transformers. Rejected as over-engineering for this assignment's scope.

### Decision 3: In-Place Transformation

**Problem:** Should transformers modify the product in-place or return a new transformed product?

**Solution:** In-place modification (transformers call setters on the product object).

**Tradeoff:**
- ✅ **Pro:** Matches Assignment 2's approach (all transformations modify the same variables).
- ✅ **Pro:** More memory efficient (no copying of objects).
- ✅ **Pro:** Simpler to implement and understand.
- ❌ **Con:** Makes products mutable, which could lead to bugs in concurrent scenarios.

**Alternative Considered:** Immutable products with transformers returning new instances. Rejected because it adds complexity without clear benefit for this assignment.

### Decision 4: Exception Handling Strategy

**Problem:** How should invalid rows be handled?

**Solution:** "Graceful degradation" - skip invalid rows, track them in statistics, continue processing.

**Tradeoff:**
- ✅ **Pro:** Matches Assignment 2's behavior exactly.
- ✅ **Pro:** Allows partial success—valid rows are processed even if some rows fail.
- ✅ **Pro:** Simple to implement (return null from `readNext()` for invalid rows).
- ❌ **Con:** Silent failures—no logging of which specific rows failed or why.

**Alternative Considered:** Log detailed error messages for each failed row. Rejected to maintain identical behavior with Assignment 2.

---

## Lessons Learned

### 1. Object-Oriented Decomposition

Breaking down a monolithic program into classes requires careful thought about:
- **Responsibilities:** What should each class be responsible for?
- **Boundaries:** Where do responsibilities begin and end?
- **Dependencies:** How do classes interact without tight coupling?

The key insight: Start with the data (Product), then build operations around it (transformers, readers, writers), and finally add coordination (pipeline).

### 2. Balance Between Design and Simplicity

While the object-oriented design is more complex than the monolithic version (9 classes vs. 1), it's also more maintainable, testable, and extensible. However, there's a risk of over-engineering:
- **Good:** Abstracting transformers with a base class enables polymorphism.
- **Bad:** Adding complex dependency injection, configuration frameworks, or design patterns not required by the assignment.

The lesson: Apply OO principles where they add value, not for their own sake.

### 3. Maintaining Behavioral Compatibility

Redesigning for better structure while maintaining identical behavior is challenging. Every decision (like when to increment `rowsRead`) must match the original exactly. This taught me to:
- Read the original code carefully before redesigning.
- Test thoroughly to ensure outputs match.
- Document critical behavior (like transformation order) explicitly.

---

## Use of AI Assistant in Redesign Process

### How I Used the AI Assistant

1. **Initial Analysis:** Asked the AI to analyze Assignment 2's structure and identify areas for object-oriented improvement.

2. **Brainstorming Design:** Used the AI to propose a class structure that demonstrates encapsulation, inheritance, and polymorphism.

3. **Critical Design Questions:** Asked about:
   - How to handle the original category problem
   - Whether to use in-place transformation or immutable products
   - How to demonstrate polymorphism naturally (not forced)

4. **Code Generation:** Used the AI to draft initial implementations of each class, which I then reviewed and refined.

5. **Documentation:** Leveraged the AI to help write Javadocs and this reflection document.

### What I Adapted from AI Suggestions

**Accepted:**
- The 9-class structure (Product, Statistics, Transformer hierarchy, Reader, Writer, Pipeline)
- The `originalCategory` solution for preserving initial category
- The use of `List<ProductTransformer>` to demonstrate polymorphism
- Implementing `AutoCloseable` for reader and writer classes

**Adapted:**
- **AI Suggestion:** Add validation in Product setters (e.g., price > 0).
  - **My Decision:** Kept simple setters to match Assignment 2's behavior, which doesn't validate.

- **AI Suggestion:** Use dependency injection for the transformer list.
  - **My Decision:** Hardcode transformers in `run()` method to keep it simple and match Assignment 2's hardcoded logic.

- **AI Suggestion:** Add logging for skipped rows.
  - **My Decision:** Maintain silent skipping to match Assignment 2's behavior exactly.

**Rejected:**
- **AI Suggestion:** Use a configuration file for discount rates and thresholds.
  - **My Reason:** Assignment 2 hardcodes these values, and the assignment goal is OO decomposition, not configurability.

- **AI Suggestion:** Create interfaces for Reader and Writer.
  - **My Reason:** Only one implementation of each exists, so interfaces add complexity without benefit (YAGNI principle).

### Reflection on AI Assistance

**Strengths:**
- Quickly proposed a comprehensive design structure
- Identified OO principles and where to apply them
- Generated well-documented starter code
- Helped brainstorm tradeoffs for design decisions

**Limitations:**
- Sometimes suggested over-engineering (interfaces, DI, configuration)
- Needed guidance to match Assignment 2's exact behavior
- Required human judgment to balance OO principles with simplicity

**Conclusion:** The AI was an excellent brainstorming partner and code generation assistant, but human oversight was essential to ensure the design met the assignment's requirements without over-complicating the solution.

---

## Conclusion

The redesign from Assignment 2 to Assignment 3 successfully demonstrates object-oriented decomposition while maintaining identical functionality. The resulting architecture is:
- **More maintainable:** Each class has a single responsibility.
- **More testable:** Components can be tested independently.
- **More extensible:** New transformers can be added without modifying existing code.

The process reinforced the value of OO principles (encapsulation, inheritance, polymorphism) in creating flexible, well-structured software. It also highlighted the importance of balancing good design with simplicity—applying principles where they add value without over-engineering.
