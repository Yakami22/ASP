import enstabretagne.base.time.LogicalDateTime;
import main.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void openHour() {
        Utils utils = new Utils();
        LogicalDateTime date1 = new LogicalDateTime("01/01/2023 06:59:00");
        LogicalDateTime date2 = new LogicalDateTime("01/01/2023 07:00:00");
        LogicalDateTime date3 = new LogicalDateTime("01/01/2023 21:10:00");
        LogicalDateTime date4 = new LogicalDateTime("01/01/2023 21:11:00");
        LogicalDateTime date5 = new LogicalDateTime("01/01/2023 21:30:00");
        LogicalDateTime date6 = new LogicalDateTime("01/01/2023 21:31:00");

        assertFalse(utils.openHour(date1, 1));
        assertTrue(utils.openHour(date2, 1));
        assertTrue(utils.openHour(date3, 1));
        assertFalse(utils.openHour(date4, 1));
        assertFalse(utils.openHour(date1, 0));
        assertTrue(utils.openHour(date2, 0));
        assertTrue(utils.openHour(date5, 0));
        assertFalse(utils.openHour(date6, 0));
    }

    @Test
    void rushHour() {
        Utils utils = new Utils();
        LogicalDateTime date1 = new LogicalDateTime("01/01/2023 06:59:00");
        LogicalDateTime date2 = new LogicalDateTime("01/01/2023 07:00:00");
        LogicalDateTime date3 = new LogicalDateTime("01/01/2023 09:59:00");
        LogicalDateTime date4 = new LogicalDateTime("01/01/2023 10:00:00");
        LogicalDateTime date5 = new LogicalDateTime("01/01/2023 16:59:00");
        LogicalDateTime date6 = new LogicalDateTime("01/01/2023 17:00:00");
        LogicalDateTime date7 = new LogicalDateTime("01/01/2023 18:59:00");
        LogicalDateTime date8 = new LogicalDateTime("01/01/2023 19:00:00");

        assertFalse(utils.rushHour(date1));
        assertTrue(utils.rushHour(date2));
        assertTrue(utils.rushHour(date3));
        assertFalse(utils.rushHour(date4));
        assertFalse(utils.rushHour(date5));
        assertTrue(utils.rushHour(date6));
        assertTrue(utils.rushHour(date7));
        assertFalse(utils.rushHour(date8));
    }

    @Test
    void rescheduleToNextMorning() {
        Utils utils = new Utils();
        LogicalDateTime today = new LogicalDateTime("01/01/2023 10:00:00");
        LogicalDateTime tomorrow = new LogicalDateTime("02/01/2023 07:00:00");

        assertEquals(tomorrow.toString(), utils.rescheduleToNextMorning(today).toString());
    }
}