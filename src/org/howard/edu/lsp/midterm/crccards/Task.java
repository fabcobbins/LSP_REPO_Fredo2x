package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a single task in the Task Management System.
 * Stores task information including ID, description, and current status.
 *
 * @author Marcus Cobb
 */
public class Task {

    private String taskId;
    private String description;
    private String status;

    /**
     * Constructs a new Task with the given ID and description.
     * The default status is set to "OPEN".
     *
     * @param taskId      the unique identifier for this task
     * @param description a brief description of the task
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }

    /**
     * Returns the task's unique identifier.
     *
     * @return the task ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the task's description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task's current status.
     *
     * @return the current status (OPEN, IN_PROGRESS, COMPLETE, or UNKNOWN)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the task's status. Valid values are "OPEN", "IN_PROGRESS", and "COMPLETE".
     * If an invalid value is provided, the status is set to "UNKNOWN".
     * Status comparisons are case-sensitive.
     *
     * @param status the new status to assign to this task
     */
    public void setStatus(String status) {
        if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns a string representation of the task in the format:
     * taskId description [status]
     *
     * @return formatted string representing this task
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}
