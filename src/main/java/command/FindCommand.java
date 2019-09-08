package command;

import duke.Printer;
import duke.Storage;
import duke.TaskList;
import task.Task;

/**
 * Represents a Command which prompts a printing of a list of tasks with a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand which prompts the printing of a list of tasks with a given keyword.
     * @param keyword a string representing the keyword to be searched within the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prompts Ui to print a list of tasks which contain a keyword.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @param printer Printer which generates a response after this command executes.
     */
    public void execute(TaskList tasks, Storage storage, Printer printer) {
        TaskList tasksFound = new TaskList();
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(keyword)) {
                tasksFound.addTask(task);
            }
        }
        printer.generateFindResponse(tasksFound, keyword);
    }

    public boolean isExit() {
        return false;
    }
}