package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import exception.DukeInvalidTaskDateFormatException;
import task.DeadlineTask;
import task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Command which adds a DeadlineTask to the TaskList.
 */
public class DeadlineCommand extends Command {
    private String description;
    private Date time;

    /**
     * Creates a DeleteCommand with a given description and time.
     * @param description Task description.
     * @param time Task deadline date and time in the format of "dd/mm/yyyy hhmm".
     */
    public DeadlineCommand(String description, String time) throws DukeException {
        this.description = description;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.time = format.parse(time);
        } catch (ParseException e) {
            throw new DukeInvalidTaskDateFormatException(time, format.toPattern());
        }
    }

    /**
     * Adds a DeadlineTask to the TaskList.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @param printer Printer which generates a response after this command executes.
     */
    public void execute(TaskList tasks, Storage storage, Printer printer) {
        Task task = new DeadlineTask(description, time);
        tasks.addTask(task);
        storage.save(tasks);
        printer.generateDeadlineResponse(tasks, task);
    }

    public boolean isExit() {
        return false;
    }
}
