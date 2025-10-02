import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int[] elements = new int[size];
        for(int i = 0; i < size; i++) {
            elements[i] = input.nextInt();
        }

        int product = 1;
        int lastElement = 1;

        for (int element : elements) {
            if (element * lastElement > product) {
                product = element * lastElement;
            }
            lastElement = element;
        }

        System.out.println(product);
    }
}