package zadania;

public class Triangle {
    public static class Point {
        public double x, y;

        public Point(double X, double Y) {
            x = X;
            y = Y;
        }
    }

    Point p1, p2, p3;

    public enum TYPE {INVALID, ROWNORAMIENNY, ROWNOBOCZNY, ROZNOBOCZNY}

    public Triangle(Point P1, Point P2, Point P3) {
        p1 = P1;
        p2 = P2;
        p3 = P3;
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        p3 = new Point(x3, y3);
    }

    private double side_length(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public boolean isValid() {
        return (p1.x != p2.x || p2.x != p3.x) && (p1.y != p2.y || p2.y != p3.y);
    }

    public TYPE getType() {
        if (!isValid()) {
            return TYPE.INVALID;
        }

        double side1 = side_length(p1, p2);
        double side2 = side_length(p2, p3);
        double side3 = side_length(p1, p3);

        if (side1 == side2 && side2 == side3) {
            return TYPE.ROWNOBOCZNY;
        }
        if (side1 == side2 || side2 == side3 || side1 == side3) {
            return TYPE.ROWNORAMIENNY;
        }

        return TYPE.ROZNOBOCZNY;
    }
}
