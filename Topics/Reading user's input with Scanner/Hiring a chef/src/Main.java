import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();
        String preferences = sc.nextLine();
        int experience = sc.nextInt();

        sc.close();
        System.out.printf
                ("The form for %s is completed. " +
                                "We will contact you if we need a chef who cooks %s " +
                                "dishes and has %d years of experience."
                , name, preferences, experience);
    }
}