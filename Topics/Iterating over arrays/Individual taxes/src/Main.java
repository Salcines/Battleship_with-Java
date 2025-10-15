import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int companyNumber = input.nextInt();
        int[] income = new int[companyNumber];
        for (int i = 0; i < companyNumber; i++) {
            income[i] = input.nextInt();
        }

        double[] taxes = new double[companyNumber];
        for (int i = 0; i < companyNumber; i++) {
           taxes[i] = income[i] * (input.nextInt() / 100.0);
        }

        double maxTax = taxes[0];
        int index = 0;

        for (int i = 1; i < taxes.length; i++) {
            if (taxes[i] > maxTax) {
                maxTax = taxes[i];
                index = i;
            }
        }

        System.out.println(index + 1);
    }
}