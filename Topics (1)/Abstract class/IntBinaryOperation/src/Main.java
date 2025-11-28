import java.util.Scanner;

abstract class IntBinaryOperation {

    protected int firstArg;
    protected int secondArg;

    public IntBinaryOperation(int firstArg, int secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

    public abstract int perform();
}

class Addition extends IntBinaryOperation {
    public Addition(int firstArg, int secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    public int perform() {
        return firstArg + secondArg;
    }
}

class Multiplication extends IntBinaryOperation {
    public Multiplication(int firstArg, int secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    public int perform() {
        return firstArg * secondArg;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter two numbers separated by space: ");

        int firstArg = scanner.nextInt();
        int secondArg = scanner.nextInt();
        scanner.close();

        IntBinaryOperation addition = new Addition(firstArg, secondArg);
        System.out.printf("The result of %d + %d = %d\n\n",
                firstArg, secondArg, addition.perform());

        IntBinaryOperation multiplication = new Multiplication(firstArg, secondArg);
        System.out.printf("The result of %d * %d = %d",
                firstArg, secondArg, multiplication.perform());
    }
}