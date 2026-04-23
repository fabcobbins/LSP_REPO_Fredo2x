# AI Usage Report — Final Exam Question 2

**Course:** Large-Scale Programming  
**Assignment:** Final Exam — Question 2 (Template Method Pattern)  
**Author:** Michael Cobbins  
**Date:** April 23, 2026

---

## AI Tools Used

| Tool | Purpose |
|------|---------|
| Claude (claude-sonnet-4-6) | Code generation and file scaffolding |

---

## Prompts Used

1. "Design a Template Method pattern in Java with an abstract Report class and concrete StudentReport and CourseReport subclasses. Match the required output format."
2. "Write the Driver class using List<Report> to demonstrate polymorphism by calling generateReport() on each."
3. "Write the design explanation for how Template Method is used, referencing specific methods. Keep it 3–5 sentences."

---

## How AI Helped

Claude generated the full class hierarchy for the Template Method implementation — the abstract `Report` base class with `generateReport()` as the final template method, both concrete subclasses, and the polymorphic `Driver`. Each file was scaffolded with Javadocs and reviewed to confirm the output matched the expected format exactly. The design explanation was drafted to directly reference the pattern mechanics and the role of each method.

---

## Reflection

Implementing Template Method made it clear that the `final` keyword on `generateReport()` is what actually enforces the pattern — without it, subclasses could override the entire workflow and break the invariant. The abstract method contracts force subclasses to fill in only the variable parts, keeping the structure rigid where it needs to be.
