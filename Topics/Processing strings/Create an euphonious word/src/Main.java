import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        String word = input.nextLine();
        input.close();

        int extraLetter = countInsertions(word);

        System.out.println(extraLetter);
    }

    private static int countInsertions(String word) {
        int count = 0;
        int currentTypeCount = 0;
        boolean lastTypeVowel = true;

        for (char c : word.toCharArray()) {
            boolean isCurrentVowel = isVowel(c);
            if (isCurrentVowel == lastTypeVowel) {
                currentTypeCount++;
                if (currentTypeCount == 3) {
                    count++;
                    currentTypeCount = 1; // Reset after insertion
                }
            } else {
                lastTypeVowel = isCurrentVowel;
                currentTypeCount = 1;
            }
        }
        return count;
    }

    private static boolean isVowel(char c) {
        return "aeiouy".indexOf(Character.toLowerCase(c)) != -1;
    }
}