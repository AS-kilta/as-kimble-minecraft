package com.asdvek.MinecraftASKimble.math;

import java.lang.Math;

/**
 * A minimal 3 dimensional double vector implementation.
 * Arithmetic is implemented in static methods returning new objects rather than modifying existing ones with side effects.
 */

public class Vec3 {
    // coordinate values
    private double x;
    private double y;
    private double z;

    // getters
    public double x() { return this.x; }
    public double y() { return this.y; }
    public double z() { return this.z; }

    // setters
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setZ(double z) { this.z = z; }
    public void set(double x, double y, double z) { this.x = x; this.y = y; this.z = z; }

    // properties
    public double length() { return Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z); }

    /* constructors */
    // initialise to zero vector by default
    public Vec3() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    // initialise all components to a scalar
    public Vec3(double s) {
        this.x = s;
        this.y = s;
        this.z = s;
    }

    // initialize xyz components to given values
    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // copy values from pre-existing vector
    public Vec3(Vec3 v) {
        this.x = v.x();
        this.y = v.y();
        this.z = v.z();
    }

    // add v2 to v1 and return result as a new Vec3 object
    public static Vec3 add(Vec3 v1, Vec3 v2) {
        return new Vec3(v1.x()+v2.x(), v1.y()+v2.y(), v1.z()+v2.z());
    }

    // subtract v2 from v1 and return result as a new Vec3 object
    public static Vec3 sub(Vec3 v1, Vec3 v2) {
        return new Vec3(v1.x()-v2.x(), v1.y()-v2.y(), v1.z()-v2.z());
    }

    // multiply vector by a scalar and return result as a new Vec3 object
    public static Vec3 mult(Vec3 v, double s) {
        return new Vec3(s*v.x(), s*v.y(), s*v.z());
    }

    // carry out cross product v1 x v2 and return result as a new Vec3 object
    public static Vec3 cross(Vec3 v1, Vec3 v2) {
        return new Vec3(
        v1.y()*v2.z() - v1.z()*v2.y(),
        v1.z()*v2.x() - v1.x()*v2.z(),
        v1.x()*v2.y() - v1.y()*v2.x()
        );
    }

    // carry out cross product v1 x v2 and return result as a new Vec3 object
    public static double distance(Vec3 v1, Vec3 v2) {
        double dx = v2.x() - v1.x();
        double dy = v2.y() - v1.y();
        double dz = v2.z() - v1.z();
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
}
