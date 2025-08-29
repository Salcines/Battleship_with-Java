package battleship;

import java.util.Scanner;

public class Main {

    static final char FOG_OF_WAR = '~';
    static final char CELL_WITH_SHIP = '0';
    static final char HIT = 'X';
    static final char MISS = 'M';

    static final int FILES_FIELD = 10;
    static final int COLUMNS_FIELD = 10;

    static final int HEADER = 1;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        char [][] battleField = CreateEmptyField();

        placeShips(battleField, input);
    }

    private static void placeShips(char[][] battleField, Scanner input) {
        boolean isConsecutive = false;
        int lenght = 0;
        StringBuilder progression = new StringBuilder();

        System.out.println("Enter the coordinates of the ship:");
        String begin = input.next();
        String end = input.next();

        if ((begin.charAt(0) == end.charAt(0)) || (begin.charAt(1) == end.charAt(1))) {
            isConsecutive = true;
        }

        if (
               isConsecutive
            && (Character.getNumericValue(begin.charAt(1)) > 0)
                        && (Character.getNumericValue(end.charAt(1)) < 11)
        ) {
                lenght = (Math.abs(Integer.parseInt(begin.substring(1)) -
                    Integer.parseInt(end.substring(1)))) + 1;
        } else {
            System.out.println("Error!");
            return;
        }

        int firstColumn = Character.getNumericValue(begin.charAt(1));
        int lastColumn = Character.getNumericValue(end.charAt(1));

        if (firstColumn < lastColumn) {
            for (int i = firstColumn; i <= lastColumn; i++) {
                progression.append
                        (begin.charAt(0)).
                        append(i).
                        append(" ");
            }
        } else {
            for (int i = firstColumn; i >= lastColumn; i--) {
                progression.append
                        (end.charAt(0)).
                        append(i).
                        append(" ");
            }
        }

        progression.deleteCharAt(progression.length() - 1);

        System.out.printf("Length: %d\n", lenght);
        System.out.printf("Parts: %s%n", progression);
    }

    private static char[][] CreateEmptyField() {
        char column = 64;
        char [][] battleField = new char[FILES_FIELD + HEADER][COLUMNS_FIELD + HEADER + 1];
        battleField[0][0] = ' ';

        for (int i = 1; i < battleField.length; i++ ) {
            battleField[0][i] = Character.forDigit(i, 10);
            if (i == 10) {
                int number = 0;
                battleField[0][i] = Character.forDigit(number + 1, 10);
                battleField[0][i + 1] = Character.forDigit(number, 10);
                break;
            }
        }
        for (int i = 1; i < battleField.length; i++ ) {
            battleField[i][0] = (char) (i + column);
        }

        for (int i = 1; i < battleField.length; i++) {
            for  (int j = 1; j < battleField[i].length - 1; j++) {
                battleField[i][j] = FOG_OF_WAR;
            }
        }

        PrintBattleField(battleField);

        return battleField;
    }

    private static void PrintBattleField(char[][] battleField) {
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField[i].length - 1; j++) {
                System.out.print(battleField[i][j]);
                if (i == 0 && j == 10) {
                    System.out.print(battleField[i][j + 1]);
                    continue;
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
