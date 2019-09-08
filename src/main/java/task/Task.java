package task;

/**
 * Represents a task that the user can add into the TaskList.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task Constructor used by classes extending Task.
     * @param description Task description.
     */
    public Task(String description) {
        assert !description.equals("");
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}