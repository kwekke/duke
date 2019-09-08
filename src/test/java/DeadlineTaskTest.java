import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.DeadlineTask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTaskTest {

    @Test
    public void testToFileString() throws DukeException {
        assertEquals("D||0||deadlineTest||01/01/2000 2359",
                new DeadlineTask("deadlineTest", "01/01/2000 2359").toFileString());
    }

    @Test
    public void testInvalidTaskDateExceptionThrown() {
        try {
            new DeadlineTask("deadlineTest", "01/01");
            fail();
        } catch (Exception e) {
            assertEquals("01/01 is not a valid date. Use dd/MM/yyyy HHmm", e.getMessage());
        }
    }
}
