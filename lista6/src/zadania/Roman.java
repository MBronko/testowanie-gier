package zadania;

public class Roman {
    private static final int[] int_values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] roman_values = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    static public int romanToInt(String roman) {
        int res = 0;

        String roman_buf = roman;
        for (int i = 0; i < int_values.length; i++) {
            int counter = 0;
            while (roman_buf.startsWith(roman_values[i])) {
                counter++;
                if (counter > 3) {
                    break;
                }

                res += int_values[i];
                roman_buf = roman_buf.substring(roman_values[i].length());
            }
        }

        if (!roman_buf.equals("")) {
            throw new IllegalArgumentException("\"" + roman + "\" is not a valid roman numeral");
        }

        return res;
    }

    static public String intToRoman(int num) {
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException("Number outside is lower than 1 or higher than 3999");
        }
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < int_values.length; i++) {
            while (num >= int_values[i]) {
                res.append(roman_values[i]);
                num -= int_values[i];
            }
        }

        return res.toString();
    }
}
