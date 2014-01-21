package rasterizer;

public class Vec3 {
    public double x, y, z;

    public Vec3() {
        this(0.0, 0.0, 0.0);
    }

    public Vec3(double xyz) {
        this(xyz, xyz, xyz);
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}