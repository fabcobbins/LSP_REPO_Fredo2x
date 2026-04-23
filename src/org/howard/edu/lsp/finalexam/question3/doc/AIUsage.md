# AI Usage Report — Final Exam Question 3

**Course:** Large-Scale Programming  
**Assignment:** Final Exam — Question 3 (JUnit 5 Testing)  
**Author:** Michael Cobbins  
**Date:** April 23, 2026

---

## AI Tools Used

| Tool | Purpose |
|------|---------|
| Claude (claude-sonnet-4-6) | Test case generation and file scaffolding |

---

## Prompts Used

1. "Write a JUnit 5 test class for GradeCalculator. Include one test for average(), one for letterGrade(), one for isPassing(), two boundary-value tests, and two assertThrows() exception tests."
2. "Make sure boundary tests cover the exact threshold values at 90 and 60, and exception tests cover scores below 0 and above 100."

---

## How AI Helped

Claude generated the full `GradeCalculatorTest.java` file based on the given `GradeCalculator` implementation and the assignment's required test coverage. It identified the correct boundary values (90 for A/B cutoff, 60 for passing threshold) and structured the exception tests using `assertThrows` with lambda expressions. Each test was reviewed to confirm it maps to the correct method behavior and expected output.

---

## Reflection

Writing boundary-value tests made it clear that the grade thresholds are inclusive — `>= 90` means 90.0 should return `"A"`, not `"B"`. Testing exact boundaries is more valuable than testing values well inside a range, because off-by-one errors at the boundary are the most common source of subtle bugs in conditional logic.
