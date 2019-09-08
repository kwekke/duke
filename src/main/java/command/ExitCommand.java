package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a Command which signals the end of the program.
 */
public class ExitCommand extends Command {

    /**
     * Prompts Ui to print a concluding message.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @param printer Printer which generates a response after this command executes.
     */
    public void execute(TaskList tasks, Storage storage, Printer printer) {
        printer.generateExitResponse();
    }

    public boolean isExit() {
        return true;
    }
}

