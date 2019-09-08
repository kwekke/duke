package duke;

import task.Task;

public class Printer {

    private String response;
    private static final String ADDED_MSG =  "Got it. I've added this task: \n";
    private static final String BYE_MSG =    "Bye. Hope to see you again soon!\n";
    private static final String DELETE_MSG = "Noted. I've removed this task: \n";
    private static final String DONE_MSG =   "Nice! I've marked this task as done: \n";
    private static final String FIND_MSG =   "%s task%s found with the \"%s\" keyword.\n";

    public String generateResponse() {
        return response;
    }

    private String printTaskListSize(TaskList tasks) {
        return "Now you have " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? " " : "s ") + "in the list.";
    }

    /**
     * Generates Duke's welcome message.
     * @param tasks List of tasks currently saved.
     */
    public void generateWelcomeMessage(TaskList tasks) {
        response = "hi im duke \n"
                + printTaskListSize(tasks) + "\n"
                + tasks.toString() + "\n"
                + "What can i do for you?\n";
    }

    /**
     * Generates a response from DeadlineCommand.
     * @param tasks List of tasks currently saved.
     * @param newDeadlineTask The newly added DeadlineTask.
     */
    public void generateDeadlineResponse(TaskList tasks, Task newDeadlineTask) {
        response = ADDED_MSG
                + newDeadlineTask + "\n"
                + printTaskListSize(tasks);
    }

    public void generateEmptyTaskListResponse(String commandWord) {
        response = "You have no tasks to " + commandWord;
    }

    /**
     * Generates a response from DeleteCommand.
     * @param tasks List of tasks currently saved.
     * @param deletedTask The newly deleted task.
     */
    public void generateDeleteResponse(TaskList tasks, Task deletedTask) {
        response = DELETE_MSG
                + deletedTask + "\n"
                + printTaskListSize(tasks);
    }

    public void generateDoneResponse(Task doneTask) {
        response = DONE_MSG
                + doneTask;
    }

    /**
     * Generates a response from EventCommand.
     * @param tasks List of tasks currently saved.
     * @param newEventTask The newly added EventTask.
     */
    public void generateEventResponse(TaskList tasks, Task newEventTask) {
        response = ADDED_MSG
                + newEventTask + "\n"
                + printTaskListSize(tasks);
    }

    public void generateExitResponse() {
        response = BYE_MSG;
    }

    /**
     * Generates a response from FindCommand.
     * @param tasksFound List of tasks found with the given keyword.
     * @param keyword Keyword used to find tasks in the saved list of tasks.
     */
    public void generateFindResponse(TaskList tasksFound, String keyword) {
        if (tasksFound.getSize() == 0) {
            response = String.format(FIND_MSG, "No", "s", keyword);
        } else {
            boolean hasMultipleTasksFound = tasksFound.getSize() > 1;
            response = String.format(FIND_MSG, tasksFound.getSize(), hasMultipleTasksFound ? "s" : "", keyword)
                    + tasksFound.toString();
        }
    }

    public void generateListResponse(TaskList tasks) {
        response = printTaskListSize(tasks) + "\n"
                + tasks.toString();
    }

    /**
     * Generates a response from ToDoCommand.
     * @param tasks Lists of tasks currently saved.
     * @param newToDoTask The newly added ToDoTask.
     */
    public void generateToDoResponse(TaskList tasks, Task newToDoTask) {
        response = ADDED_MSG
                + newToDoTask + "\n"
                + printTaskListSize(tasks);
    }

    /**
     * Generates an exception message with Duke's taunt.
     * @param message the exception message to be displayed.
     * @return an exception message to be displayed in MainWindow.
     */
    public String generateExceptionMessage(String message) {
        return message + "\n"
                + "\n "
                + "are you even trying? lul";
    }
}
