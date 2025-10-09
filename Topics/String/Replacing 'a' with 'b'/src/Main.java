import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sample = scanner.nextLine();

        System.out.println(sample.replace("a","b"));
    }
}