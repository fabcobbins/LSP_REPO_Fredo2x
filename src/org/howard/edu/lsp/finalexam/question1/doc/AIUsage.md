# AI Usage Report — Final Exam Question 1

**Course:** Large-Scale Programming  
**Assignment:** Final Exam — Question 1 (Concurrency and OO Design)  
**Author:** Michael Cobbins  
**Date:** April 23, 2026

---

## AI Tools Used

| Tool | Purpose |
|------|---------|
| Claude (claude-sonnet-4-6) | Answer generation and file creation |

---

## Prompts Used

1. "Identify the shared resources and concurrency problems in the given RequestManager code. Answer in the required format."
2. "Evaluate each of the three fixes (Fix A, B, C) — is each one correct or incorrect, and why?"
3. "Based on Riel's heuristics, should getNextId() be public? Explain."
4. "Describe how ReentrantLock could be used as an alternative to synchronized to make addRequest() thread-safe. Include a code snippet."

---

## How AI Helped

Claude was used to draft structured answers for each part of the question based on the provided code and requirements. It identified the race conditions in `nextId` and `requests`, evaluated the three synchronization fixes with clear reasoning, and produced a `ReentrantLock` code snippet demonstrating the alternative approach. Each answer was reviewed for correctness and alignment with lecture material before submission.

---

## Reflection

Working through the fix evaluations — particularly understanding why Fix A is insufficient despite protecting the counter — reinforced how race conditions often span multiple operations, not just a single line. Synchronization must cover the full critical section, not just the most visible piece of shared state.
