import java.util.*;

public class Main {
    static final String BOX1SMALLER = "Box 1 < Box 2";
    static final String BOX2SMALLER = "Box 1 > Box 2";
    static final String NOBOXESENTRY = "Incompatible";
    static final int DIMENSIONS = 3;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int [] box1 = new int[DIMENSIONS];
        int [] box2 = new int[DIMENSIONS];

        for (int i = 0; i < DIMENSIONS; i++) {
            box1[i] = input.nextInt();
        }

        for (int i = 0; i < DIMENSIONS; i++) {
            box2[i] = input.nextInt();
        }

        input.close();

        Arrays.sort(box1);
        Arrays.sort(box2);

        if (box1[0] > box2[0] && box1[1] > box2[1] && box1[2] > box2[2]) {
            System.out.println(BOX2SMALLER);
        } else if (box1[0] < box2[0] && box1[1] < box2[1] && box1[2] < box2[2]) {
            System.out.println(BOX1SMALLER);
        } else {
            System.out.println(NOBOXESENTRY);
        }
    }
}