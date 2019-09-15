package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;
import exception.DukeException;

/**
 * Represents a Command that Parser returns to the main logic in Duke.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage, Printer printer) throws DukeException;

    public abstract boolean isExit();

}
