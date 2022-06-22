package zad_5_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import zadania.Roman;

public class TestIntToRoman {
    @Test
    public void intUnder1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Roman.intToRoman(0));
    }

    @Test
    public void intOver3999() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Roman.intToRoman(4000));
    }

    @ParameterizedTest
    @CsvSource({
            "125,CXXV",
            "561,DLXI",
            "634,DCXXXIV",
            "761,DCCLXI",
            "63,LXIII"
    })
    public void IntToRomanParametrized(int val, String expected) {
        Assertions.assertEquals(Roman.intToRoman(val), expected);
    }
}
