package zad_1_testy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import zadania.StringFilter;


public class TestStringFilter {
    @Test
    public void listIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> StringFilter.filterStrings(null, "test"));
    }

    @Test
    public void valueIsNull() {
        String[] list = {"test1", "test2"};
        Assertions.assertThrows(IllegalArgumentException.class, () -> StringFilter.filterStrings(list, null));
    }

    @Test
    public void emptyList() {
        String[] list = {};
        String[] expectedResult = {};
        String value = "test1";

        String[] result = StringFilter.filterStrings(list, value);
        Assertions.assertArrayEquals(result, expectedResult);
    }

    @Test
    public void nullInList() {
        String[] list = {"test1", null, "test2", "test3"};
        String[] expectedResult = {"test1", null, "test2"};
        String value = "test3";

        String[] result = StringFilter.filterStrings(list, value);
        Assertions.assertArrayEquals(result, expectedResult);
    }

    @Test
    public void removeZero() {
        String[] list = {"test1", "test2"};
        String[] expectedResult = {"test1", "test2"};
        String value = "test3";

        String[] result = StringFilter.filterStrings(list, value);
        Assertions.assertArrayEquals(result, expectedResult);
    }

    @Test
    public void removeOnce() {
        String[] list = {"test1", "test2", "test3"};
        String[] expectedResult = {"test1", "test3"};
        String value = "test2";

        String[] result = StringFilter.filterStrings(list, value);
        Assertions.assertArrayEquals(result, expectedResult);
    }

    @Test
    public void removeMultiple() {
        String[] list = {"test1", "test2", "test3", "test1"};
        String[] expectedResult = {"test2", "test3"};
        String value = "test1";

        String[] result = StringFilter.filterStrings(list, value);
        Assertions.assertArrayEquals(result, expectedResult);
    }

    @Test
    public void removeAll() {
        String[] list = {"test1", "test1", "test1"};
        String[] expectedResult = {};
        String value = "test1";

        String[] result = StringFilter.filterStrings(list, value);
        Assertions.assertArrayEquals(result, expectedResult);
    }
}
