package Zad1;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class CarDAO {
    CarRepository carRepository = new CarRepository();

    public void setCarRepository(CarRepository newCarRepository) {
        carRepository = newCarRepository;
    }

    public long findMileageBetweenYears(int id, String startYear, String endYear) {
        Car car = carRepository.getCarById(id);
        if (car == null) {
            throw new NoSuchElementException("There is no car with id " + id);
        }

        int startYearInt = Integer.parseInt(startYear);
        int endYearInt = Integer.parseInt(endYear);

        HashMap<String, Long> yearMileage = car.getYearMileage();
        long result = 0L;
        for (int i = startYearInt; i <= endYearInt; i++) {
            String year = Integer.toString(i);
            Long mileage = yearMileage.get(year);
            if (mileage != null) {
                result += mileage;
            }
        }

        return result;
    }
}
