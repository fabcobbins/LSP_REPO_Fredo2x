# Design Evaluation: OrderProcessor

## Overview

The `OrderProcessor` class contains several significant object-oriented design violations. Each issue is described below with an explanation of why it leads to poor structure, maintainability, or extensibility.

---

## Issue 1: Poor Encapsulation (Public Fields)

All four fields — `customerName`, `email`, `item`, and `price` — are declared `public`. This means any external class can read or modify order data directly, with no validation or control. This violates the fundamental OO principle that a class should hide its internal data and expose it only through controlled accessors. Riel's heuristics warn against this: data should not be publicly accessible, as it couples other classes directly to internal representation and makes future changes risky.

---

## Issue 2: Single Responsibility Principle Violation (God Method)

The `processOrder()` method performs at least five distinct responsibilities:

1. Calculating tax and totals
2. Printing a receipt to the console
3. Writing order data to a file
4. Sending a confirmation email
5. Logging the timestamp

A single method should do one thing. When a method handles this many concerns, any change to one behavior (e.g., switching from file storage to a database) risks breaking unrelated behavior (e.g., email sending). This is a classic "God Method" — it knows too much and does too much.

---

## Issue 3: God Class

The `OrderProcessor` class itself takes on too many roles. It is simultaneously a data holder, a tax engine, a file writer, an email sender, a discount engine, and a logger. Riel's heuristics state that a class should represent a single abstraction. When a class accumulates too many responsibilities, it becomes difficult to test, reuse, or extend any single piece of its behavior.

---

## Issue 4: Logic Ordering Bug (Discount Applied Too Late)

The discount is calculated **after** the receipt has already been printed and the order has been saved to the file. This means the customer sees the wrong total on their receipt, and the wrong amount is persisted to disk. This is a direct consequence of cramming all logic into one method — there is no clean way to enforce correct ordering when everything is tangled together.

---

## Issue 5: No Separation of Concerns

Business logic (tax calculation, discount), persistence (file writing), presentation (console printing), and communication (email) are all mixed together. This makes the class impossible to unit test in isolation — you cannot test the tax calculation without triggering a file write, for example. A well-designed system would separate these layers so each can be developed, tested, and swapped independently.

---

## Summary

| Issue | Impact |
|---|---|
| Public fields | No data protection; external classes tightly coupled to internals |
| God Method | Hard to maintain, test, or modify one behavior without affecting others |
| God Class | Violates single abstraction per class; impossible to reuse parts |
| Logic order bug | Incorrect receipt and file output due to misplaced discount logic |
| No separation of concerns | Cannot test or replace any single concern independently |
