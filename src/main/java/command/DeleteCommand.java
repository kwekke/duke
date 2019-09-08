package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import exception.DukeInvalidTaskIndexException;
import task.Task;

/**
 * Represents a Command which deletes a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand with a given index to delete from the TaskList.
     * @param index Task index to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task from the TaskList.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @param printer Printer which generates a response after this command executes.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    public void execute(TaskList tasks, Storage storage, Printer printer) throws DukeException {
        if (tasks.getSize() == 0) {
            printer.generateEmptyTaskListResponse("delete");
        } else if (index < 0 || index >= tasks.getSize()) {
            throw new DukeInvalidTaskIndexException("delete", tasks.getSize());
        } else {
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.save(tasks);
            printer.generateDeleteResponse(tasks, task);
        }
    }

    public boolean isExit() {
        return false;
    }
}