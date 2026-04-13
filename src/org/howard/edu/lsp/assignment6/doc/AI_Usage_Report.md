# AI Usage Report — Assignment 6

**Course:** Large-Scale Programming  
**Assignment:** Assignment 6 — JUnit Testing of IntegerSet  
**Author:** Michael Cobbins  
**Date:** April 13, 2026

---

## Tools Used

| Tool | Purpose |
|------|---------|
| Claude (claude-sonnet-4-6) | Test case generation and file scaffolding |

---

## How AI Was Used

### 1. Copying and Adapting IntegerSet.java

The `IntegerSet.java` and `IntegerSetException.java` from Assignment 5 were copied into the `assignment6` package. Claude handled the package declaration update (`assignment5` → `assignment6`) while leaving all method logic and Javadoc identical to the original submission.

**Prompt used (paraphrased):**  
> "Copy Assignment 5's IntegerSet into a new assignment6 package and update the package declaration."

No logic changes were made — this was a mechanical copy, not a redesign.

---

### 2. Generating the JUnit 5 Test Suite

Claude generated `IntegerSetTest.java` based on the assignment rubric. The following was provided to Claude:

- The full rubric listing all methods and their required edge cases
- The existing `IntegerSet.java` implementation for reference

**Prompt used (paraphrased):**  
> "Write a complete JUnit 5 test file covering every method in IntegerSet. Include both normal cases and the specific edge cases listed in the rubric. Use assertEquals, assertTrue, assertFalse, and assertThrows."

Claude produced the initial test structure covering all 14 methods. Each test was reviewed manually to confirm:

- The setup (`@BeforeEach`) correctly resets state between tests
- `assertThrows` is used correctly for `largest()` and `smallest()` on empty sets
- Edge cases map exactly to rubric requirements (e.g., `equals()` with different order, `diff()` on identical sets, `union()` with empty set)

---

## What I Reviewed and Verified

After generation, I personally checked:

1. **Test naming** — all `@DisplayName` annotations describe the scenario clearly
2. **Correctness of assertions** — verified expected values match actual `IntegerSet` behavior
3. **Edge case alignment** — confirmed every rubric-required edge case has a dedicated test
4. **No use of Driver.java** — test-only file, no main method
5. **Package declaration** — both source files correctly declare `package org.howard.edu.lsp.assignment6;`

---

## Reflection

Using AI for boilerplate test generation accelerated the scaffolding phase significantly. However, meaningful engagement came from verifying that each generated test was actually correct — for example, confirming that `diff()` on identical sets genuinely returns an empty set, or that `complement()` on disjoint sets returns all elements of the second set. AI-assisted code requires the same critical review as hand-written code; the value is in reducing repetition, not in replacing understanding.
