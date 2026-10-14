package problem2;

public class Point {
    public final long x;
    public final long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        long dx = this.x - other.x;
        long dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
