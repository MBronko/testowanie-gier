package zad_4_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import zadania.Triangle;


public class TestTriangle {

    @ParameterizedTest
    @CsvSource({
            "5,4,2,0,-2,3,ROWNORAMIENNY",
            "6,-4,-2,-4,2,10,ROWNORAMIENNY",
            "8,2,11,13,2,6,ROWNORAMIENNY",
            "-2,3,-5,-4,2,-1,ROWNORAMIENNY",
            "1,5,2,5,4,6,ROZNOBOCZNY",
            "5,17,12,12,8,20,ROZNOBOCZNY",
            "1,1,1,4,1,5,INVALID",
    })
    void testTriangles(double x1, double y1, double x2, double y2, double x3, double y3, String typeString) {
        Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3);
        Triangle.TYPE type = Triangle.TYPE.valueOf(typeString);

        Assertions.assertEquals(triangle.getType(), type);
    }
}
