package org.howard.edu.lsp.midterm.question2;

/*
  Using overloaded methods named 'area' keeps the API simple and readable; the compiler picks the right method
  based on parameter types and arity. I could have used circleArea/rectangleArea/etc., but overloading groups
  one concept ("area") under a single clear name and reduces mental overhead.

  AI usage:
  I used an AI assistant (chatgpt 4) to double-check the signatures, the exception requirement, and the exact expected output strings..
*/

public class Main {
    public static void main(String[] args) {
        // Required exact outputs
        System.out.println("Circle radius 3.0 \u2192 area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 \u2192 area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 \u2192 area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 \u2192 area = " + AreaCalculator.area(4));

        // Exception demo (must throw and be caught)
        try {
            AreaCalculator.area(0.0); // invalid radius -> should throw
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
