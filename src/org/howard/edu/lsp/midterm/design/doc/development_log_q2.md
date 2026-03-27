# Development Log - Question 2

## External Resources Used

### AI Tool: Claude (claude.ai)

The following is the complete conversation transcript used during the completion of this question.

---

**Me:**
I have a design evaluation question. There's a class called OrderProcessor that has public fields and a processOrder() method that does like 5 different things. What OO design problems does this have?

**Claude:**
There are a few major issues here. First, having public fields is a clear encapsulation violation — any class can modify the data directly with no validation. This is one of Riel's core heuristics: data should be hidden behind accessors. Second, the processOrder() method is doing way too much — calculating tax, printing a receipt, writing to a file, sending an email, applying a discount, and logging. That's a God Method. The class itself is also a God Class since it holds all of these responsibilities at once.

---

**Me:**
There's also a bug right? The discount is applied after the receipt is already printed and the order is saved to file.

**Claude:**
Yes, exactly. The discount is calculated after the file write and console print, so both the receipt and the saved record show the wrong total. This is a direct consequence of packing everything into one method — there's no clean way to enforce execution order when all concerns are tangled together.

---

**Me:**
Okay, now I need to redesign this using CRC cards. How would you split it up?

**Claude:**
I'd break it into these classes: an `Order` class that just holds data with private fields and getters, a `TaxCalculator` that handles tax and discount logic, a `ReceiptPrinter` for console output, an `OrderRepository` for file persistence, a `NotificationService` for email and logging, and finally an `OrderProcessor` that only coordinates — it calls each collaborator in the right order and delegates all real work.

---

**Me:**
Why does Order have no collaborators?

**Claude:**
Because Order is a pure data class. Its only job is to store and expose order information. It doesn't need to interact with any other class to fulfill that responsibility — other classes come to it, not the other way around.

---

**Me:**
Makes sense. And the OrderProcessor in the redesign — does it still do all the work?

**Claude:**
No, that's the key difference. In the redesign, OrderProcessor is just a coordinator. It knows the correct sequence of operations and delegates each step to the right collaborator. It doesn't calculate taxes, write files, or send emails itself. This keeps it thin and easy to modify if the workflow changes.
