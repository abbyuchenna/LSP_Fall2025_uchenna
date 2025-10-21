package org.howard.edu.lsp.midterm.question2;

/**
 * overloaded area calculation
 * all theee methods throw IllegalArgumentException if any dimension is <= 0.
 */
public final class AreaCalculator {

    // Circle area: πr²
    public static double area(double radius) {
        validate(radius);
        return Math.PI * radius * radius;
    }

    // Rectangle area: width * height
    public static double area(double width, double height) {
        validate(width);
        validate(height);
        return width * height;
    }

    // Triangle area (base & height): 1/2 * base * height
    public static double area(int base, int height) {
        validate(base);
        validate(height);
        return 0.5 * base * height;
    }

    // Square area: side^2
    public static double area(int side) {
        validate(side);
        return (double) side * side;
    }

    // ---- helpers ----
    private static void validate(double v) {
        if (v <= 0) throw new IllegalArgumentException("Dimension must be > 0: " + v);
    }
    private static void validate(int v) {
        if (v <= 0) throw new IllegalArgumentException("Dimension must be > 0: " + v);
    }
}
