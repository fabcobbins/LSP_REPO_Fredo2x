# Question 1 — Concurrency and Object-Oriented Design

**Course:** Large-Scale Programming  
**Author:** Michael Cobbins  
**Date:** April 23, 2026

---

## Part 1: Shared Resources and Risk

**Shared Resource #1:** `nextId` — an integer counter accessed by all threads to generate unique request IDs. Since it is read and incremented as two separate operations, it is not atomically safe under concurrent access.

**Shared Resource #2:** `requests` — an `ArrayList<String>` that every thread writes to when adding a new request. `ArrayList` is not thread-safe and does not protect against concurrent modification.

**Concurrency Problem:** Race condition. When multiple threads execute `addRequest()` simultaneously, they can interleave at any point between reading `nextId` and writing to `requests`. This leads to two distinct failure modes: duplicate IDs being assigned to different requests, and corrupted or lost entries in the list due to unsynchronized concurrent writes.

**Why `addRequest()` is unsafe:** The method performs two logically related operations — generating a unique ID via `getNextId()` and appending to the shared list via `requests.add()` — but neither step is atomic, and there is no synchronization holding them together. Thread A can read `nextId = 1`, then Thread B can read `nextId = 1` before either increments it, resulting in both requests receiving ID 1. Additionally, two simultaneous calls to `ArrayList.add()` can corrupt internal array state, causing data loss or an `ArrayIndexOutOfBoundsException`.

---

## Part 2: Evaluate Fixes

**Fix A — `public synchronized int getNextId() { ... }`**  
**Incorrect.** Synchronizing `getNextId()` ensures that only one thread at a time can read and increment `nextId`, which eliminates duplicate IDs. However, it does not protect the `requests.add()` call inside `addRequest()`. Two threads can still execute `addRequest()` concurrently: one thread finishes `getNextId()` and releases the lock, then both threads call `requests.add()` at the same time, corrupting the `ArrayList`. The critical section is larger than just the counter — the fix only covers half of it.

**Fix B — `public synchronized void addRequest(String studentName) { ... }`**  
**Correct.** Synchronizing the entire `addRequest()` method makes both operations — ID generation and list insertion — execute as a single atomic unit. Only one thread can hold the lock at a time, so no two threads can interleave inside `addRequest()`. This eliminates both the duplicate ID race and the concurrent `ArrayList` modification in one lock. Since `getNextId()` is only ever called from within `addRequest()`, this lock covers the full critical section.

**Fix C — `public synchronized List<String> getRequests() { ... }`**  
**Incorrect.** Synchronizing `getRequests()` only protects the read path — it prevents a thread from reading the list while another thread is also reading it. It does nothing to prevent concurrent writes inside `addRequest()`. The original race condition on `nextId` and `requests.add()` is completely unaddressed. Write-side safety requires synchronization on the write operations, not the read.

---

## Part 3: Object-Oriented Design

**Answer:** No, `getNextId()` should **not** be public.

**Explanation:** According to Arthur Riel's object-oriented design heuristics, a class should minimize its public interface to only what external clients genuinely need. `getNextId()` is an internal mechanism — it manages the private counter used by `addRequest()` to sequence requests. It is an implementation detail, not a service the class offers to the outside world. Exposing it publicly breaks encapsulation: external code could call `getNextId()` directly, consuming an ID without creating a corresponding request and silently corrupting the sequence. The method should be declared `private` so that ID generation remains fully under the control of `RequestManager`.

---

## Part 4: Alternative Synchronization Approach

**Description:**  
The alternative approach is explicit locking using `ReentrantLock` from the `java.util.concurrent.locks` package. Rather than using the `synchronized` keyword, you declare a `ReentrantLock` field and manually acquire the lock before entering the critical section, then release it in a `finally` block to guarantee release even if an exception is thrown. This makes `addRequest()` thread-safe by ensuring that ID generation and list insertion execute as an uninterrupted unit — only one thread can hold the lock at a time, and all others block until it is released. `ReentrantLock` also offers additional capabilities over `synchronized`, such as timed lock attempts and interruptible waiting.

**Code Snippet:**

```java
import java.util.concurrent.locks.ReentrantLock;

private final ReentrantLock lock = new ReentrantLock();

public void addRequest(String studentName) {
    lock.lock();
    try {
        int id = getNextId();
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    } finally {
        lock.unlock();
    }
}
```
