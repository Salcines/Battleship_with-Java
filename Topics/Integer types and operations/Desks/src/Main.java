import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float firstGroup = scanner.nextFloat();
        float secondGroup = scanner.nextFloat();
        float thirdGroup = scanner.nextFloat();

        System.out.printf("%d", (int) Math.ceil(firstGroup / 2) +
                (int) Math.ceil(secondGroup / 2) +
                (int) Math.ceil(thirdGroup / 2));



    }
}