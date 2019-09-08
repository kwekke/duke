package exception;

public class DukeInvalidTaskIndexException extends DukeException {

    public DukeInvalidTaskIndexException(String commandWord, int maxSize) {
        super("Invalid numbered task to " + commandWord + ". Insert a number from 1 to " + maxSize + ".");
    }

}
