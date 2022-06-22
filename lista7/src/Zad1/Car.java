package Zad1;

import java.util.HashMap;

public class Car {
    private final int id;
    private final HashMap<String, Long> yearMileage;

    public Car(int id, HashMap<String, Long> yearMileage) {
        this.id = id;
        this.yearMileage = yearMileage;
    }

    public int getId() {
        return id;
    }

    public HashMap<String, Long> getYearMileage() {
        return yearMileage;
    }
}
