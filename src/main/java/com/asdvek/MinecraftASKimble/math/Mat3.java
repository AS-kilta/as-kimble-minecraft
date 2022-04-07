package com.asdvek.MinecraftASKimble.math;

/**
 * A minimal 3x3 matrix implementation.
 * Arithmetic is implemented in static methods returning new objects rather than modifying existing ones with side effects.
 */

public class Mat3 {
    // matrix columns
    private Vec3 c1;
    private Vec3 c2;
    private Vec3 c3;

    // getters
    public Vec3 c1() { return this.c1; }
    public Vec3 c2() { return this.c2; }
    public Vec3 c3() { return this.c3; }

    // setters
    public void setC1(Vec3 c1) { this.c1 = new Vec3(c1); }
    public void setC2(Vec3 c2) { this.c2 = new Vec3(c2); }
    public void setC3(Vec3 c3) { this.c3 = new Vec3(c3); }

    /* constructors */
    // initialise to zero vector by default
    public Mat3() {
        this.c1 = new Vec3(0.0);
        this.c2 = new Vec3(0.0);
        this.c3 = new Vec3(0.0);
    }

    // initialize xyz components to given values
    public Mat3(Vec3 c1, Vec3 c2, Vec3 c3) {
        this.c1 = new Vec3(c1);
        this.c2 = new Vec3(c2);
        this.c3 = new Vec3(c3);
    }

    // premultiply vector by a matrix
    // calculates a linear combination of the matrix columns with scalars from the vector
    public static Vec3 mult(Mat3 m, Vec3 v) {
        return Vec3.add(Vec3.add(Vec3.mult(m.c1(), v.x()), Vec3.mult(m.c2(), v.y())), Vec3.mult(m.c3(), v.z()));
    }

    // compute the transpose of the matrix
    public static Mat3 tran(Mat3 m) {
        return new Mat3(
                new Vec3(m.c1.x(), m.c2.x(), m.c3.x()),
                new Vec3(m.c1.y(), m.c2.y(), m.c3.y()),
                new Vec3(m.c1.z(), m.c2.z(), m.c3.z())
        );
    }

}
