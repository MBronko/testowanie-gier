import Zad1.Car;
import Zad1.CarDAO;
import Zad1.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarDAOTest {
    private CarRepository carRepository;
    private CarDAO carDAO;


    @BeforeEach
    void init() {
        carRepository = mock(CarRepository.class);
        carDAO = new CarDAO();
        carDAO.setCarRepository(carRepository);
    }

    @Test
    void testFindMileageBetweenYears() {
        HashMap<String, Long> mileage = new HashMap<>();
        mileage.put("1999", 2000L);
        mileage.put("2000", 1000L);
        mileage.put("2001", 3000L);
        mileage.put("2003", 6000L);
        mileage.put("2004", 2750L);
        Car mockedCar = new Car(1, mileage);
        when(carRepository.getCarById(1)).thenReturn(mockedCar);

        Long res = carDAO.findMileageBetweenYears(1, "1999", "2004");

        Assertions.assertEquals(res, 14750L);

        verify(carRepository).getCarById(1);
    }

    @Test
    void testInvalidCarId() {
        when(carRepository.getCarById(1)).thenReturn(null);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            carDAO.findMileageBetweenYears(1, "1999", "2004");
        });

        verify(carRepository).getCarById(1);
    }
}
