package work3.Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller = new Controller();

    @Test
    void getPlayerScore1() {

    assertEquals(controller.getPlayerScore(-1), 2);
    }
}