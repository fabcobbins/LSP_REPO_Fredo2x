# Question 2 — Template Method Pattern Design Explanation

**Course:** Large-Scale Programming  
**Author:** Michael Cobbins  
**Date:** April 23, 2026

---

## Design Explanation

The Template Method pattern is implemented through the abstract `Report` class, which defines a fixed report generation workflow in the `generateReport()` method. This method is declared `final` to prevent subclasses from altering the sequence, enforcing that every report follows the same structure: `loadData()` → header output → `formatHeader()` → body output → `formatBody()` → footer output → `formatFooter()`. The four steps (`loadData`, `formatHeader`, `formatBody`, `formatFooter`) are declared `abstract` in `Report`, meaning each concrete subclass — `StudentReport` and `CourseReport` — must provide its own implementation while the overall structure remains fixed. The `Driver` class demonstrates polymorphism by storing both report types in a `List<Report>` and invoking `generateReport()` on each through the shared abstract type, with Java's runtime dispatch selecting the correct concrete behavior at each step.
