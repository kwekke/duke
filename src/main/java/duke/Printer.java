package duke;

import task.Task;

import java.util.ArrayList;
import java.util.Collections;

public class Printer {

    private String response;
    private static final String ADDED_MSG = "Got it. I've added this task: \n";
    private static final String BYE_MSG = "Bye. Hope to see you again soon!\n";
    private static final String DELETE_MSG = "Noted. I've removed this task: \n";
    private static final String DONE_MSG = "Nice! I've marked this task as done: \n";
    private static final String DUKE_TAUNT = "are you even trying? lul\n";
    private static final String FILE_CHECKOUT_MESSAGE = "Checking out %s.txt!\n";
    private static final String FILE_COPY_MESSAGE = "You have copied %s.txt.\nCurrently checking out its copy. \n";
    private static final String FILE_CURRENT_MESSAGE = "You are currently on %s.\n";
    private static final String FILE_LIST_MESSAGE = "You have the following files in this folder.\n";
    private static final String FILE_LIST_REMINDER_MESSAGE = "Use file names only (exclude .txt in commands).\n";
    private static final String FILE_RENAME_MESSAGE = "You have renamed %s to %s.\n";
    private static final String FIND_MESSAGE = "%s task%s found with the \"%s\" keyword.\n";


    public String generateResponse() {
        return response;
    }

    private String printTaskListSize(TaskList tasks) {
        return "Now you have " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? " " : "s ") + "in the list.\n";
    }

    /**
     * Generates Duke's welcome message.
     * @param tasks List of tasks currently saved.
     */
    public void generateWelcomeMessage(TaskList tasks) {
        response = "hi im duke \n"
                + printTaskListSize(tasks)
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
                + "    " + newDeadlineTask + "\n"
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
                + "    " + deletedTask + "\n"
                + printTaskListSize(tasks);
    }

    public void generateDoneResponse(Task doneTask) {
        response = DONE_MSG
                + "    " + doneTask;
    }

    /**
     * Generates a response from EventCommand.
     * @param tasks List of tasks currently saved.
     * @param newEventTask The newly added EventTask.
     */
    public void generateEventResponse(TaskList tasks, Task newEventTask) {
        response = ADDED_MSG
                + "    " + newEventTask + "\n"
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
            response = String.format(FIND_MESSAGE, "No", "s", keyword);
        } else {
            boolean hasMultipleTasksFound = tasksFound.getSize() > 1;
            response = String.format(FIND_MESSAGE, tasksFound.getSize(), hasMultipleTasksFound ? "s" : "", keyword)
                    + tasksFound.toString();
        }
    }

    public void generateListResponse(TaskList tasks) {
        response = printTaskListSize(tasks)
                + tasks.toString();
    }

    /**
     * Generates a response from ToDoCommand.
     * @param tasks lists of tasks currently saved
     * @param newToDoTask the newly added ToDoTask
     */
    public void generateToDoResponse(TaskList tasks, Task newToDoTask) {
        response = ADDED_MSG
                + "    " + newToDoTask + "\n"
                + printTaskListSize(tasks);
    }

    /**
     * Generates an exception message with Duke's taunt.
     * @param exceptionMessage the exception message to be displayed
     * @return an exception message to be displayed in MainWindow
     */
    String generateExceptionMessage(String exceptionMessage) {
        return exceptionMessage + "\n"
                + "\n "
                + DUKE_TAUNT;
    }

    /**
     * Generates a response form FileCheckOutCommand.
     * @param newTaskList the list of tasks in the checked out file
     * @param fileName the name of the checked out file
     */
    public void generateFileCheckOutMessage(TaskList newTaskList, String fileName) {
        response = String.format(FILE_CHECKOUT_MESSAGE, fileName)
                + printTaskListSize(newTaskList)
                + newTaskList.toString();
    }

    /**
     * Generates a response from FileListCommand.
     * @param listOfFileNames the list of file names in the current directory
     * @param currentFilePath the name of the current file path Storage is using
     */
    public void generateFileListMessage(ArrayList<String> listOfFileNames, String currentFilePath) {
        Collections.sort(listOfFileNames);
        response = FILE_LIST_MESSAGE;
        for (int i = 0; i < listOfFileNames.size(); i++) {
            response += (i + 1) + ". " + listOfFileNames.get(i) + "\n";
        }
        String currentFileName = currentFilePath.substring(7);
        response += String.format(FILE_CURRENT_MESSAGE, currentFileName)
                + FILE_LIST_REMINDER_MESSAGE;
    }

    public void generateFileDeleteMessage(String fileName) {
        response = fileName + " deleted.";
    }

    /**
     * Generates a response from FileCopyCommand.
     * @param copiedTaskList the current task list.
     * @param fileName the current file name.
     */
    public void generateFileCopyMessage(TaskList copiedTaskList, String fileName) {
        response = String.format(FILE_COPY_MESSAGE, fileName)
                + printTaskListSize(copiedTaskList)
                + copiedTaskList.toString();
    }

    public void generateFileRenameMessage(String oldFileName, String newFileName) {
        response = String.format(FILE_RENAME_MESSAGE, oldFileName, newFileName)
                + String.format(FILE_CURRENT_MESSAGE, newFileName);
    }
}
