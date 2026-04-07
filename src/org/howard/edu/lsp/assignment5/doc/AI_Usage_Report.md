# AI Usage Report — Assignment 5: IntegerSet

## AI Tools Used

**Tool:** Claude (Anthropic) via Claude Code CLI
**Date:** April 6, 2026

### How It Was Used

- Used Claude to generate the initial skeleton of `IntegerSet.java` based on the method signatures and behavioral definitions provided in the assignment specification.
- Reviewed and verified each generated method against the expected behavior described in the spec (union, intersect, diff, complement, equals, toString, etc.).
- Confirmed that all set operations return new IntegerSet objects without modifying the originals.
- Verified the `toString()` output format (ascending sorted order, bracket notation) matched the required format.
- Used Claude to generate `IntegerSetException` for the `largest()` and `smallest()` edge cases.

### External References

- [Java ArrayList Documentation](https://docs.oracle.com/en/java/docs/books/tutorial/collections/implementations/list.html)
- [Java Collections.sort()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#sort-java.util.List-)
