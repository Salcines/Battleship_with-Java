import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String date = new Scanner(System.in).nextLine();
        StringBuilder dateConvert = new StringBuilder(10);

        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);

        dateConvert.append(month).append('/').append(day).append('/').append(year);

        System.out.println(dateConvert);

    }
}