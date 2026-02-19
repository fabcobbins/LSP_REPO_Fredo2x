# AI Assistant Usage for Assignment 3

**Author:** Michael Fabian Cobbins
**Date:** February 18, 2026
**AI Tool:** Claude Sonnet 4.5 (via Claude Code CLI)

---

## Overview

This document explains how I used an AI assistant as a tool during the Assignment 3 redesign. The AI helped with analysis and brainstorming, but I made all critical design decisions, rejected several suggestions that didn't fit the requirements, and adapted others to match the assignment's scope.

---

## Phase 1: Understanding the Problem

### My Initial Request

I started by asking the AI to analyze my existing Assignment 2 code to understand its structure before I began redesigning. I wanted to identify the specific areas that needed object-oriented decomposition.

**What the AI Found:**
- Assignment 2 is a single monolithic class (~142 lines)
- All ETL logic (Extract, Transform, Load) is mixed together in one main() method
- No separation of concerns or use of OO principles

**My Analysis:**
After reviewing the AI's summary, I recognized several problems:
- Violation of Single Responsibility Principle
- No encapsulation (all logic is static and procedural)
- No inheritance or polymorphism opportunities being used
- Difficult to test individual components

This confirmed my understanding that the code needed significant restructuring to demonstrate proper OO design.

---

## Phase 2: Clarifying Requirements

### Questioning the Approach

When the AI started planning, I caught an assumption it was making and challenged it directly:

**My Question:**
"Does it say to change assignment 2 anywhere in the instructions? or was that a personal preference?"

**Why I Asked This:**
I wanted to make sure I understood the requirements correctly - should I modify Assignment 2 in place or create a new Assignment 3? This distinction was important for maintaining my git history and assignment organization.

**The Clarification:**
The AI confirmed that the instructions say "start with your Assignment 2 solution" and "redesign your solution," which meant creating a new assignment3 package while preserving assignment2 intact.

**My Decision:**
I decided to create a completely new `org.howard.edu.lsp.assignment3` package, keeping Assignment 2 as a reference. This approach:
- Preserves my original work
- Makes it easy to compare monolithic vs. OO versions
- Follows the package naming convention from Assignments 1 and 2

---

## Phase 3: Designing the Object-Oriented Structure

### My Design Process

I provided the AI with the full assignment requirements and asked it to propose a class structure. I wanted to see how it would decompose the monolithic code while demonstrating the required OO principles.

**AI's Initial Proposal:**
The AI suggested a 9-class structure with clear separation of concerns.

**My Evaluation:**
I analyzed the proposed structure against the assignment requirements:

✅ **Accepted Design Elements:**
1. **Product class** - Good encapsulation example with private fields and controlled access
2. **Transformer inheritance hierarchy** - Excellent way to demonstrate inheritance and polymorphism
3. **Reader/Writer separation** - Clean separation of I/O concerns
4. **originalCategory solution** - Clever way to handle the transformation logic correctly

❌ **Rejected AI Suggestions:**
1. **Price validation in setters** - AI wanted to add `if (price <= 0) throw exception`. I rejected this because Assignment 2 doesn't validate, and the assignment requires identical behavior.
2. **Reader/Writer interfaces** - AI suggested creating interfaces. I rejected this as over-engineering since there's only one implementation of each.
3. **Dependency injection** - AI wanted constructor injection for transformers. I rejected this to keep it simple and match Assignment 2's hardcoded approach.

**My Final Design Decision:**
I decided on 9 classes that clearly demonstrate the required OO principles without over-complicating:
- Product, ETLStatistics (encapsulation)
- ProductTransformer + 3 subclasses (inheritance + polymorphism)
- CSVProductReader, CSVProductWriter (encapsulation)
- ETLPipeline (composition + polymorphism)

---

## Phase 4: Implementation and Testing

### Building the Classes

I used the AI to help implement each class with proper Javadocs. For each class, I:
1. Reviewed the AI-generated code for correctness
2. Ensured Javadocs clearly stated which OO principle was demonstrated
3. Verified the implementation matched Assignment 2's behavior

**Key Implementation Decisions I Made:**
- Used `BigDecimal` for price handling (not double) to match Assignment 2's precision
- Implemented `AutoCloseable` for reader/writer to use try-with-resources
- Kept transformation order explicit: Name → Price → Category
- Made `originalCategory` field final to prevent modification

### Finding and Fixing a Bug

When I first tested the code, the statistics didn't match Assignment 2:
- Mine: 11 rows read, 5 skipped
- Assignment 2: 12 rows read, 5 skipped

**My Debugging Process:**
I compared my CSVProductReader logic against Assignment 2's code and found the issue:
- Assignment 2 increments `rowsRead` for EVERY line (including empty ones)
- My code was incrementing `rowsRead` only for non-empty lines

**The Fix:**
I moved the `statistics.incrementRowsRead()` call to BEFORE the empty line check, matching Assignment 2's logic exactly. This taught me to pay careful attention to the order of operations when matching existing behavior.

---

## Phase 5: Critical Evaluation of AI Suggestions

### AI Recommendations I Rejected

The AI made several suggestions that I carefully evaluated and rejected because they didn't fit the assignment requirements:

### 1. Price Validation

**AI's Suggestion:** Add validation to throw an exception if price is negative or zero.

**My Decision: REJECTED**
- **Why:** Assignment 2 doesn't do any validation, and the requirement is to maintain identical behavior.  Adding validation would change how the program handles data.
- **Lesson Learned:** Sometimes "best practices" aren't appropriate when you need to match existing behavior exactly.

### 2. Reader/Writer Interfaces

**AI's Suggestion:** Create `ProductReader` and `ProductWriter` interfaces that the CSV classes would implement.

**My Decision: REJECTED**
- **Why:** YAGNI principle - "You Ain't Gonna Need It." Since there's only one implementation of each, interfaces add unnecessary complexity. The assignment is about demonstrating basic OO concepts, not advanced design patterns.
- **Lesson Learned:** Don't over-engineer solutions. Interfaces are useful when you have multiple implementations, but they're overkill here.

### 3. Dependency Injection

**AI's Suggestion:** Pass transformers into the ETLPipeline constructor instead of creating them in the `run()` method.

**My Decision: REJECTED**
- **Why:** Assignment 2 hardcodes its logic. Keeping transformers hardcoded maintains consistency with the original approach while still demonstrating polymorphism where it counts (in the loop that applies transformations).
- **Lesson Learned:** Pick your battles - demonstrate OO principles where they add clear value, not everywhere possible.

### 4. Logging for Invalid Rows

**AI's Suggestion:** Print warnings to stderr for each skipped row with line numbers.

**My Decision: REJECTED**
- **Why:** Assignment 2 silently skips invalid rows. Changing this would produce different output and violate the "same behavior" requirement.
- **Lesson Learned:** When refactoring, maintain the original external behavior even if internal structure changes.

---

## Phase 6: Documentation

### Writing Javadocs

For documentation, I used the AI to draft initial Javadocs, then reviewed and edited each one. The AI helped me understand what should be included, but I made sure each Javadoc:
- Accurately described what the class/method does
- Explicitly stated which OO principle it demonstrates (required by the assignment)
- Used correct @param, @return, and @throws tags

**Example of my editing:** The AI's Javadocs were sometimes too verbose. I simplified them to be clear and concise while still meeting requirements.

### Creating the Reflection Document

The AI helped structure my reflection into logical sections (Architecture Comparison, OO Principles, Design Decisions), but I wrote the actual analysis based on my own understanding:
- I explained why I made each design choice
- I documented which AI suggestions I rejected and why
- I reflected on what I learned about OO decomposition
- I honestly assessed both the strengths and limitations of using AI as a development tool

---

## What I Learned

### How I Used the AI as a Tool

The AI was useful for:
1. **Quick Analysis** - Rapidly scanning my Assignment 2 code to identify problems
2. **Design Brainstorming** - Proposing class structures I could evaluate
3. **Implementation Assistance** - Generating boilerplate code and Javadocs
4. **Bug Catching** - Spotting the statistics counting error during testing

### Where I Had to Take Control

The AI had limitations that required my judgment:
1. **Over-Engineering** - AI suggested interfaces, DI, and other patterns that weren't needed for this assignment's scope
2. **Context Understanding** - AI didn't always recognize when "best practices" would violate the "same behavior" requirement
3. **Requirements Interpretation** - AI needed clarification about whether to modify Assignment 2 or create Assignment 3
4. **Simplicity Balance** - AI leaned toward more complex solutions when simpler ones were more appropriate

### My Critical Thinking Process

Throughout this assignment, I:
- **Questioned assumptions** - Asked "where does it say that?" when the AI made claims about requirements
- **Evaluated tradeoffs** - Weighed AI suggestions against assignment constraints
- **Rejected bad ideas** - Said no to 4+ AI suggestions that didn't fit
- **Made final decisions** - Chose which OO principles to apply and where
- **Debugged independently** - Identified and fixed the statistics bug by comparing code myself

### Key Insights About AI-Assisted Development

**What worked well:**
- Using AI for routine tasks (Javadocs, boilerplate code)
- Getting multiple design alternatives to consider
- Having a "second set of eyes" for bug detection

**What required human judgment:**
- Deciding which OO principles to apply where
- Balancing code quality with assignment requirements
- Knowing when to reject "best practices" for simpler solutions
- Understanding the difference between good OO design and over-engineering

### Conclusion

The AI was a valuable brainstorming and implementation assistant, but I remained in control of all major design decisions. I critically evaluated each suggestion, rejected several that didn't fit, adapted others, and made the final calls about architecture and implementation.

The result is a well-structured OO design that demonstrates encapsulation, inheritance, and polymorphism while maintaining the simplicity appropriate for a learning assignment. The AI accelerated the process, but my understanding of OO principles and critical thinking determined the final design.
