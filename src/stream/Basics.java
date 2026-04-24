package stream;

interface MathFunction {
    double run(double a, double b);
}

public class Basics {
    public static void printResult(MathFunction mf) {
        System.out.println(mf.run(10, 5));
    }
    public static void main(String[] args) {
        MathFunction add = new Addition();
        System.out.println(add.run(5, 3));

        MathFunction sub = new MathFunction() {
            @Override
            public double run(double a, double b) {
                return a - b;
            }
        };
    }
}

class Addition implements MathFunction {
    @Override
    public double run(double a, double b) {
        return a + b;
    }
}
