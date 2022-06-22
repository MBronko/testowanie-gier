package zad_5_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import zadania.Roman;

public class TestRomanToInt {
    @Test
    public void CharacterRepeatedFourTimes() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Roman.romanToInt("XIIII"));
    }

    @Test
    public void IllegalCharacter() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Roman.romanToInt("MDAXI"));
    }

    @Test
    public void MultipleSubtractions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Roman.romanToInt("IIX"));
    }

    @ParameterizedTest
    @CsvSource({
            "CDXXV,425",
            "CCXIII,213",
            "LXXI,71",
            "DCXCIV,694",
            "DCCCLXVI,866"
    })
    public void RomanToIntParametrized(String val, int expected) {
        Assertions.assertEquals(Roman.romanToInt(val), expected);
    }
}
