package duke;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.ToDoCommand;
import exception.DukeException;
import exception.DukeInvalidTaskDescriptionException;
import exception.DukeInvalidTaskTimeException;
import exception.DukeMissingNumberedTaskException;

class Parser {

    /**
     * Returns a Command that is converted from user's input.
     * @param input a string that contains the user input.
     * @return a Command for the main logic in Duke to execute.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    static Command parse(String input) throws DukeException {
        String[] inputSplitBySpace = input.split(" ");
        switch (inputSplitBySpace[0]) {
        case "bye":
            return parseExit(input);
        case "deadline":
            return parseDeadline(input);
        case "delete" :
            return parseDelete(input);
        case "done":
            return parseDone(input);
        case "event":
            return parseEvent(input);
        case "find":
            return parseFind(input);
        case "list":
            return parseList(input);
        case "todo":
            return parseTodo(input);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseDeadline(String input) throws DukeException {
        String[] inputSplitBySpace = input.split("/by");
        String taskDesc = inputSplitBySpace[0].substring(8).trim();
        if (taskDesc.equals("")) {
            throw new DukeInvalidTaskDescriptionException("Deadline");
        } else if (inputSplitBySpace.length < 2) {
            throw new DukeInvalidTaskTimeException("deadline");
        }
        return new DeadlineCommand(taskDesc, inputSplitBySpace[1].trim());
    }

    private static Command parseDelete(String input) throws DukeException {
        String[] inputSplitBySpace = input.split(" ");
        if (inputSplitBySpace.length <= 1) {
            throw new DukeMissingNumberedTaskException("delete");
        }
        try {
            int index = Integer.parseInt(inputSplitBySpace[1]);
            return new DeleteCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new DukeMissingNumberedTaskException("delete");
        }
    }

    private static Command parseDone(String input) throws DukeException {
        String[] inputSplitBySpace = input.split(" ");
        if (inputSplitBySpace.length <= 1) {
            throw new DukeMissingNumberedTaskException("do");
        }
        try {
            int index = Integer.parseInt(inputSplitBySpace[1]);
            return new DoneCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new DukeMissingNumberedTaskException("do");
        }
    }

    private static Command parseEvent(String input) throws DukeException {
        String[] inputSplitBySpace = input.split("/at");
        String taskDesc = inputSplitBySpace[0].substring(5).trim();
        if (taskDesc.equals("")) {
            throw new DukeInvalidTaskDescriptionException("Event");
        } else if (inputSplitBySpace.length < 2) {
            throw new DukeInvalidTaskTimeException("event");
        }
        return new EventCommand(taskDesc, inputSplitBySpace[1].trim());
    }

    private static Command parseExit(String input) {
        assert input.equals("bye");
        return new ExitCommand();
    }

    private static Command parseFind(String input) throws DukeException {
        String[] inputSplitBySpace = input.split(" ");
        if (inputSplitBySpace.length <= 1) {
            throw new DukeException("Give me a keyword to find.");
        }
        return new FindCommand(input.substring(4).trim());
    }

    private static Command parseList(String input) {
        assert input.equals("list");
        return new ListCommand();
    }

    private static Command parseTodo(String input) throws DukeException {
        String[] inputSplitBySpace = input.split(" ");
        if (inputSplitBySpace.length <= 1) {
            throw new DukeInvalidTaskDescriptionException("ToDo");
        }
        return new ToDoCommand(inputSplitBySpace[1]);
    }
}