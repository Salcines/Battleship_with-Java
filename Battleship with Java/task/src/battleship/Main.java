package battleship;

import java.util.Scanner;

enum Ships {
    AIRCRAFT(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    private final int length;
    private final String name;
    private int hits;
    private boolean sunk;

    Ships(int length, String name) {
        this.length = length;
        this.name = name;
        this.hits = 0;
    }

    public void hit () {
        this.hits++;
    }

    public boolean isSunk() {
        return hits >= length;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

}

enum Cell {
    FOG('~'),
    HIT('X'),
    MISS('M'),
    SHIP('O');

    private final char symbol;

    Cell(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}

public class Main {

    //region final variables
    static final char FOG_OF_WAR = '~';
    static final char CELL_WITH_SHIP = 'O';
    static final char HIT = 'X';
    static final char MISS = 'M';

    static final int FILES_FIELD = 10;
    static final int COLUMNS_FIELD = 10;

    static final int HEADER = 1;

    static final char ADJUSTASCII = 64;
    //endregion

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Cell[][] battleField = createEmptyField();

        printBattleField(battleField);

        //placeShips(battleField, input);

        //beginGame(battleField, input);
    }

   /* //region place the ships. Final method

    private static void placeShips(char[][] battleField, Scanner input) {
        for (Ships ship : Ships.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells):%n%n",
                    ship.getName(), ship.getLength());

            checkPosition(battleField, input, ship);

            //TODO: Check the overlapping for two or more ships

            printBattleField(battleField);
        }

    }
    //endregion

    //region take a shoot. The game ends after first shot.
    private static void beginGame(char[][] battleField, Scanner input) {

        boolean notValid = true;
        char row;
        int column;
        Cell[][] playerField = createEmptyField();

        System.out.printf("%nThe game starts!%n%n");
        printBattleField(playerField);

        System.out.printf("%nTake a shot!%n%n");

        do {
            String coordinate = input.next().toUpperCase();
            row = coordinate.charAt(0);
            column = Integer.parseInt(coordinate.substring(1));

            if (isInvalidCoordinate(row, column)) {
                System.out.printf
                        ("%nError! You entered the wrong coordinates! Try again:%n%n");
                continue;
            }
            notValid = false;
        } while (notValid);

        if (battleField[row - ADJUSTASCII][column] == CELL_WITH_SHIP) {
            battleField[row - ADJUSTASCII][column] = HIT;
            playerField[row - ADJUSTASCII][column] = HIT;

            printBattleField(playerField);

            System.out.printf("%nYou hit a ship!%n%n");
            printBattleField(battleField);
        } else {
            battleField[row - ADJUSTASCII][column] = MISS;
            playerField[row - ADJUSTASCII][column] = MISS;
            printBattleField(playerField);

            System.out.printf("%nYou missed!%n%n");
            printBattleField(battleField);
        }
    }
//endregion

    //region chcek positions (valid input, lenght and position)
    private static void checkPosition(char[][] battleField, Scanner input, Ships ship) {

        boolean notValid = true;
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
                continue;
            }

            if (isAdjacentShip(startRow, startCol, battleField) ||
                    isAdjacentShip(endRow, endCol, battleField)) {
                System.out.printf
                        ("%nError! You placed it too close to another one. Try again:%n\n");
                continue;
            }

            sameRow = startRow == endRow;
            sameCol = startCol == endCol;

            if (!(sameRow || sameCol)) {
                System.out.printf("%nError! Wrong ship location! Try again:%n%n");
                continue;
            }

            int length = sameRow ? Math.abs(startCol - endCol) + 1 :
                    Math.abs(startRow - endRow) + 1;
            if (length != ship.getLength()) {
                System.out.printf("%nError! Wrong length of the %s! Try again:%n%n",
                        ship.getName());
                continue;
            }

            notValid = false;
        } while (notValid);

        if (sameRow) {
            int firstPosition = Math.min(startCol, endCol);
            int lastPosition = Math.max(startCol, endCol);

            for (int col = firstPosition; col <= lastPosition; col++) {
                battleField[startRow - ADJUSTASCII][col] = CELL_WITH_SHIP;
            }
        } else {
            char firstPosition = (char) Math.min(startRow, endRow);
            char lastPosition = (char) Math.max(startRow, endRow);

            for (char row = firstPosition; row <= lastPosition; row++) {
                battleField[row - ADJUSTASCII][startCol] = CELL_WITH_SHIP;
            }
        }
    }
    //endregion

    //Two validation methods.
    // region Adjacent and in bounds coordinates
    private static boolean isAdjacentShip(char row,
                                          int column,
                                          char[][] battleField) {

        int actualRow = row - ADJUSTASCII;

        return (actualRow > 0 && battleField[actualRow - 1][column] == CELL_WITH_SHIP) ||
                (actualRow < battleField.length - 1 && battleField[actualRow + 1][column] == CELL_WITH_SHIP)
                || (column > 0 && battleField[actualRow][column - 1] == CELL_WITH_SHIP)
                || (column < COLUMNS_FIELD && battleField[actualRow][column + 1] == CELL_WITH_SHIP);
    }

    private static boolean isInvalidCoordinate(char row, int column) {
        return row < 'A' || row > 'J' || column < 1 || column > COLUMNS_FIELD;
    }
    //endregion*/

    //region Creates empty battlefield. Final method
    private static Cell[][] createEmptyField() {

        Cell[][] battleField = new Cell[FILES_FIELD][COLUMNS_FIELD];

        for (Cell[] row : battleField)
            for (int j = 0; j < COLUMNS_FIELD; j++) {
                row[j] = Cell.FOG;
            }
        return battleField;
    }
    //endregion

    //region print battlefield. Final method
    private static void printBattleField(Cell[][] battleField) {
        System.out.print(" ");

        for (int i = 1; i <= COLUMNS_FIELD; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        for (int i = 0; i < FILES_FIELD; i++) {
            char letter = (char) ('A' + i);
            System.out.printf("%s ", letter);

            for (int j = 0; j < COLUMNS_FIELD; j++) {
                System.out.printf("%c ", battleField[i][j].getSymbol());
            }
            System.out.println();
        }
    }
    //endregion
}