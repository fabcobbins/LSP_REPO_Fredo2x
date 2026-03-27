# Proposed Redesign: CRC Cards

The redesign breaks `OrderProcessor` into focused classes, each with a single clear responsibility. The `OrderProcessor` is retained as a coordinator only — it delegates all work to specialized collaborators.

---

## CRC Card 1

**Class:** `Order`

**Responsibilities:**
- Store order data (customer name, email, item, price)
- Provide controlled access to order fields via getters
- Represent a single order as a data object

**Collaborators:**
- None

---

## CRC Card 2

**Class:** `TaxCalculator`

**Responsibilities:**
- Calculate the tax amount for a given price
- Calculate the final total after tax and applicable discounts
- Apply discount rules (e.g., 10% off orders over $500)

**Collaborators:**
- `Order`

---

## CRC Card 3

**Class:** `ReceiptPrinter`

**Responsibilities:**
- Format and print a receipt to the console
- Display customer name, item, and final total

**Collaborators:**
- `Order`
- `TaxCalculator`

---

## CRC Card 4

**Class:** `OrderRepository`

**Responsibilities:**
- Persist order data to a file or other storage
- Write order records in the required format

**Collaborators:**
- `Order`

---

## CRC Card 5

**Class:** `NotificationService`

**Responsibilities:**
- Send a confirmation email to the customer
- Log order activity with a timestamp

**Collaborators:**
- `Order`

---

## CRC Card 6

**Class:** `OrderProcessor`

**Responsibilities:**
- Coordinate the full order processing workflow in the correct sequence
- Delegate tax calculation, receipt printing, persistence, and notification to appropriate collaborators

**Collaborators:**
- `Order`
- `TaxCalculator`
- `ReceiptPrinter`
- `OrderRepository`
- `NotificationService`

---

## Design Rationale

Each class now has a single responsibility. The discount and tax are computed by `TaxCalculator` before the receipt is printed or the order is saved, eliminating the logic ordering bug in the original design. All fields in `Order` are private with getters, enforcing proper encapsulation. Any collaborator can be swapped independently (e.g., replace `OrderRepository` with a database-backed version) without touching the others.
