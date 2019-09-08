package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a Command which prompts a printing of the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Prompts Ui to print the list of tasks.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @param printer Printer which generates a response after this command executes.
     */
    public void execute(TaskList tasks, Storage storage, Printer printer) {
        printer.generateListResponse(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
