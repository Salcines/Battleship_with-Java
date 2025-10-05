import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word = input.next();
        int index = input.nextInt();
        input.close();

        String output = ((index >= word.length()) || (index <= 0)) ? word :
                (word.substring(index) + word.substring(0, index));

        System.out.println(output);
    }
}