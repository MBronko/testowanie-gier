package zad_5_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import zadania.Roman;

public class TestRoman {
    @Test
    public void testRoman() {
        for (int i = 1; i < 4000; i++) {
            Assertions.assertEquals(Roman.romanToInt(Roman.intToRoman(i)), i);
        }
    }
}
