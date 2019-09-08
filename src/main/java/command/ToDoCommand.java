package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import task.Task;
import task.ToDoTask;

/**
 * Represents a Command which adds an ToDoTask to the TaskList.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Creates a ToDoCommand with a given description.
     * @param description Task description.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a ToDoTask to the TaskList.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @param printer Printer which generates a response after this command executes.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    public void execute(TaskList tasks, Storage storage, Printer printer) {
        Task task = new ToDoTask(description);
        tasks.addTask(task);
        storage.save(tasks);
        printer.generateToDoResponse(tasks, task);
    }

    public boolean isExit() {
        return false;
    }

}
