# Development Log

## External Resources Used

### AI Tool: Claude (claude.ai)

The following is the complete conversation transcript used during the development of this solution.

---

**Me:**
Hey, I have a Java assignment. I need to implement a Task Management System from CRC cards. There's a Task class and a TaskManager class. Can you help me understand what a CRC card is first?

**Claude:**
Sure! A CRC card stands for Class-Responsibility-Collaborator. It's a design tool used in object-oriented programming to identify the responsibilities a class should have and which other classes it needs to work with. In your case, the Task class is responsible for storing task information and updating status, and the TaskManager is responsible for managing a collection of Task objects.

---

**Me:**
Okay, so the Task class needs fields for taskId, description, and status. The default status should be "OPEN". Can you help me write the constructor?

**Claude:**
Here's a constructor for your Task class:

```java
public Task(String taskId, String description) {
    this.taskId = taskId;
    this.description = description;
    this.status = "OPEN";
}
```

The status is hardcoded to "OPEN" by default since that's the requirement.

---

**Me:**
Now I need to write the setStatus method. It should only allow OPEN, IN_PROGRESS, and COMPLETE. Anything else should set status to UNKNOWN.

**Claude:**
You can use a simple if-else with equals() for case-sensitive comparison:

```java
public void setStatus(String status) {
    if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
        this.status = status;
    } else {
        this.status = "UNKNOWN";
    }
}
```

This is case-sensitive because equals() is case-sensitive in Java.

---

**Me:**
Good. Now the toString should produce output like: T1 Write report [OPEN]. How do I format that?

**Claude:**
Simple string concatenation:

```java
@Override
public String toString() {
    return taskId + " " + description + " [" + status + "]";
}
```

---

**Me:**
Now for TaskManager. I need to store tasks, prevent duplicate IDs, find by ID, and return tasks by status. What data structure should I use?

**Claude:**
A `LinkedHashMap<String, Task>` is a great choice. It maps taskId (String) to Task objects, so lookups by ID are O(1). It also prevents duplicate keys by design, and LinkedHashMap maintains insertion order which ensures consistent iteration order when filtering by status.

---

**Me:**
How do I throw an exception if someone tries to add a duplicate task ID?

**Claude:**
Check with `containsKey()` before putting:

```java
public void addTask(Task task) {
    if (tasks.containsKey(task.getTaskId())) {
        throw new IllegalArgumentException("Task with ID already exists.");
    }
    tasks.put(task.getTaskId(), task);
}
```

---

**Me:**
And for getTasksByStatus, it needs to return a List<Task> of all tasks matching the given status.

**Claude:**
Iterate over the map's values and collect matches:

```java
public List<Task> getTasksByStatus(String status) {
    List<Task> result = new ArrayList<>();
    for (Task task : tasks.values()) {
        if (task.getStatus().equals(status)) {
            result.add(task);
        }
    }
    return result;
}
```

---

**Me:**
And findTask should return null if the task isn't found?

**Claude:**
Yes, `HashMap.get()` already returns null if the key doesn't exist, so this is straightforward:

```java
public Task findTask(String taskId) {
    return tasks.get(taskId);
}
```

---

**Me:**
Perfect. Thanks for walking me through it step by step.

**Claude:**
Happy to help! Your implementation should now match all the requirements. Make sure to add Javadoc comments to all public methods and classes before submitting.
