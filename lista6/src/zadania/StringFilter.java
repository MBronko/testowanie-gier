package zadania;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringFilter {
    public static String[] filterStrings(String[] list, String value) throws IllegalArgumentException {
        if (list == null) {
            throw new IllegalArgumentException("Lista ma wartość null");
        }
        if (value == null) {
            throw new IllegalArgumentException("Napis ma wartość null");
        }

        List<String> result = new ArrayList<>();

        for (String element : list) {
            if (!Objects.equals(element, value)) {
                result.add(element);
            }
        }

        return result.toArray(new String[0]);
    }
}