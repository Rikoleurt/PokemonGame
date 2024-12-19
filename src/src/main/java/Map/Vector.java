package Map;

public class Vector {

    private double dx;
    private double dy;

    public Vector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double dx() {
        return dx;
    }

    public double dy(){
        return dy;
    }

    public Vector plus(Vector v) { // Summing vectors means to sum their coordinates.
        dx += v.dx;
        dy += v.dy;
        return new Vector(dx,dy);
    }
    public Vector times(double factor) { // Multiplying vectors means that will multiply its coordinates with a constant (factor)
        dx *= factor;
        dy *= factor;
        return new Vector(dx,dy);
    }
}
