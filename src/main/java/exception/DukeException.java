package exception;

public class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }

    public String toString() {
        return this.getMessage();
    }
}
