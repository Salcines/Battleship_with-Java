import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        scanner.close();
        double s = Math.PI * r * r;

        System.out.println(s);

    }
}