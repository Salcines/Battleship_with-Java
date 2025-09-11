package battleship;

import java.util.Scanner;

enum Ships {
    AIRCRAFT(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    private final int position;
    private final String name;

    Ships(int position, String name) {
        this.position = position;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

}

public class Main {

    static final char FOG_OF_WAR = '~';
    static final char CELL_WITH_SHIP = 'O';
    static final char HIT = 'X';
    static final char MISS = 'M';

    static final int FILES_FIELD = 10;
    static final int COLUMNS_FIELD = 10;

    static final int HEADER = 1;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        char[][] battleField = CreateEmptyField();

        placeShips(battleField, input);
    }

    private static void placeShips(char[][] battleField, Scanner input) {
        for (Ships ship : Ships.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells):%n%n",
                    ship.getName(), ship.getPosition());

            checkPosition(battleField, input, ship);

            //TODO: Check the overlapping for two or more ships

            PrintBattleField(battleField);
        }

    }

    private static void checkPosition(char[][] battleField, Scanner input, Ships ship) {

        boolean notValid = true;
        char adjustAscii = 64;
        String begin;
        String end;
        char startRow;
        int startCol;
        char endRow;
        int endCol;
        boolean sameRow = false;
        boolean sameCol;

        do {
            begin = input.next().toUpperCase();
            end = input.next().toUpperCase();
            startRow = begin.charAt(0);
            startCol = Integer.parseInt(begin.substring(1));
            endRow = end.charAt(0);
            endCol = Integer.parseInt(end.substring(1));

        if (isInvalidCoordinate(startRow, startCol) || isInvalidCoordinate(endRow, endCol)) {
            System.out.println("Error!");
            return;
        }

            sameRow = startRow == endRow;
            sameCol = startCol == endCol;

            if (!(sameRow || sameCol)) {
                System.out.printf("%nError! Wrong ship location! Try again:%n");
                continue;
            }

            int length = sameRow ? Math.abs(startCol - endCol) + 1 :
                    Math.abs(startRow - endRow) + 1;
            if (length != ship.getPosition()) {
                System.out.printf("%nError! Wrong length of the %s! Try again:%n",
                        ship.getName());
                continue;
            }

            notValid = false;
        } while (notValid);

       if (sameRow) {
           int firstPosition = Math.min(startCol, endCol);
           int lastPosition = Math.max(startCol, endCol);

           for (int col = firstPosition; col <= lastPosition; col++) {
               progression.append(startRow).append(col).append(" ");
           }
       }  else {
           char firstPosition = (char) Math.min(startRow, endRow);
           char lastPosition = (char) Math.max(startRow, endRow);

            for (char row = firstPosition; row <= lastPosition; row++) {
                battleField[row - adjustAscii][startCol] = CELL_WITH_SHIP;
            }
        }
    }

    private static boolean isAdjacentShip(char row,
                                          int column,
                                          char[][] battleField) {

        int actualRow = row - 64;

        return (actualRow > 0 && battleField[actualRow - 1][column] == CELL_WITH_SHIP) ||
                (actualRow < battleField.length - 1 && battleField[actualRow + 1][column] == CELL_WITH_SHIP)
                || (column > 0 && battleField[actualRow][column - 1] == CELL_WITH_SHIP)
                || (column < COLUMNS_FIELD && battleField[actualRow][column + 1] == CELL_WITH_SHIP);
    }

    private static boolean isInvalidCoordinate(char row, int column) {
        return row < 'A' || row > 'J' || column < 1 || column > COLUMNS_FIELD;
    }

    private static char[][] CreateEmptyField() {
        char column = 64;
        char[][] battleField = new char[FILES_FIELD + HEADER][COLUMNS_FIELD + HEADER + 1];
        battleField[0][0] = ' ';

        for (int i = 1; i < battleField.length; i++) {
            battleField[0][i] = Character.forDigit(i, 10);
            if (i == 10) {
                // If the column is 10, we put a 1 in position 11 and 0 in position 12
                int number = 0;
                battleField[0][i] = Character.forDigit(number + 1, 10);
                battleField[0][i + 1] = Character.forDigit(number, 10);
                break;
            }
        }
        for (int i = 1; i < battleField.length; i++) {
            battleField[i][0] = (char) (i + column);
        }

        for (int i = 1; i < battleField.length; i++) {
            for (int j = 1; j < battleField[i].length - 1; j++) {
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
        System.out.println();
    }
}