import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        Scanner input = new Scanner(System.in);
        String sample = input.nextLine().toLowerCase(Locale.getDefault());
        sample = sample.isEmpty() ? ALPHABET : sample;

        System.out.println(ALPHABET.contains(sample));
    }
}