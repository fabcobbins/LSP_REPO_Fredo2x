# CRC Card Delegation Explanation

TaskManager collaborates with Task because its core responsibilities — storing tasks, finding tasks by ID, and returning tasks by status — all require direct interaction with Task objects. TaskManager cannot fulfill these responsibilities without accessing Task's data and behavior.

Task, on the other hand, has no collaborators because its sole responsibilities are to store its own information, update its own status, and provide its own details. These are entirely self-contained operations that do not require Task to know about or interact with any other class, including TaskManager.

This one-directional dependency reflects the principle that a lower-level data class (Task) should not need to be aware of the higher-level manager class (TaskManager) that uses it, keeping responsibilities clean and the design loosely coupled.
