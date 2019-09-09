package duke;


import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.FileCheckOutCommand;
import command.FileCopyCommand;
import command.FileDeleteCommand;
import command.FileListCommand;
import command.FileRenameCommand;
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
        switch (inputSplitBySpace[0].toLowerCase()) {
        case "b":
        case "bb":
        case "bye":
            return parseBye(input);
        case "deadline":
            return parseDeadline(input);
        case "delete" :
            return parseDelete(input);
        case "done":
            return parseDone(input);
        case "event":
            return parseEvent(input);
        case "file":
            return parseFile(input);
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

    private static Command parseBye(String input) {
        assert input.equals("b") || input.equals("bb") || input.equals("bye");
        return new ExitCommand();
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

    private static Command parseFile(String input) throws DukeException {
        String[] inputSplitBySpace = input.split(" ");
        switch (inputSplitBySpace[1].toLowerCase()) {
        case "checkout":
            return parseFileCheckOut(input);
        case "copy":
            return parseFileCopy(input);
        case "delete":
            return parseFileDelete(input);
        case "list":
            return parseFileList();
        case "rename":
            return parseFileRename(input);
        default:
            throw new DukeException("Invalid file command.");
        }
    }

    private static Command parseFileCheckOut(String input) throws DukeException {
        String[] inputSplitBySpace = input.split(" ");
        if (inputSplitBySpace.length <= 2) {
            throw new DukeException("Give a file name to check out.");
        }
        return new FileCheckOutCommand(input.substring(14).trim());
    }

    private static Command parseFileCopy(String input) throws DukeException {
        String[] inputSplitBySpace = input.split(" ");
        if (inputSplitBySpace.length <= 2) {
            throw new DukeException("Give a file name to copy.");
        }
        return new FileCopyCommand(input.substring(10).trim());
    }

    private static Command parseFileDelete(String input) throws DukeException {
        String[] inputSplitBySpace = input.split(" ");
        if (inputSplitBySpace.length <= 2) {
            throw new DukeException("Give me a file name to delete.");
        }
        return new FileDeleteCommand(input.substring(12).trim());
    }

    private static Command parseFileList() {
        return new FileListCommand();
    }

    private static Command parseFileRename(String input) throws DukeException {
        String[] inputSplitByCommandKeyword = input.split("/to");
        String oldFileName = inputSplitByCommandKeyword[0].substring(12).trim();
        if (oldFileName.equals("")) {
            throw new DukeException("Give me a file to rename.");
        }
        if (inputSplitByCommandKeyword.length < 2) {
            throw new DukeException("Did you remember to use \"/to\"?");
        }
        String newFileName = inputSplitByCommandKeyword[1].trim();
        return new FileRenameCommand(oldFileName, newFileName);
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
        return new ToDoCommand(input.substring(5).trim());
    }
}