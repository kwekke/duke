package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import exception.DukeInvalidTaskIndexException;

/**
 * Represents a Command which marks a task from the TaskList as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Creates a DoneCommand with a given index to mark a task from the TaskList as done.
     * @param index Task index to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task from the TaskList as done.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @param printer Printer which generates a response after this command executes.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    public void execute(TaskList tasks, Storage storage, Printer printer) throws DukeException {
        if (tasks.getSize() == 0) {
            printer.generateEmptyTaskListResponse("do");
        } else if (index < 0 || index >= tasks.getSize()) {
            throw new DukeInvalidTaskIndexException("do", tasks.getSize());
        } else {
            tasks.doneTask(index);
            storage.save(tasks);
            printer.generateDoneResponse(tasks.getTask(index));
        }
    }

    public boolean isExit() {
        return false;
    }
}