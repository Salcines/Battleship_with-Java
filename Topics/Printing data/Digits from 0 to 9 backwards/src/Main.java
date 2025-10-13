
class Main {
    public static void main(String[] args) {
        int[] digits = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};



        for (int i = digits.length - 1; i >= 0; i--) {
            System.out.print(digits[i]);
            if (i != 0) System.out.print(" ");
        }
    }
}