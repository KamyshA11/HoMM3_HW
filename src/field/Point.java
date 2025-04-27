package field;

public class Point {
    int x;
    int y;

    Point() {
        this.x = 0;
        this.y = 0;
    }

    Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean equals(Point other) {
        return ((this.x == other.x) && (this.y == other.y));
    }

    public void show() {
        System.out.print("X: ");
        System.out.print(x);
        System.out.print(" Y: ");
        System.out.print(y);
        System.out.println();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
