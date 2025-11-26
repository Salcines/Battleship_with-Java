package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    //region constant variables

    static final int FILES_FIELD = 10;
    static final int COLUMNS_FIELD = 10;
    static final Ships[][] SHIPS_GRID = new Ships[FILES_FIELD] [COLUMNS_FIELD];
    static final List<Ships> SUNK_SHIPS = new ArrayList<>();
    //endregion

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Cell[][] battlefield = createEmptyField();
        Cell[][] preparationfield = createEmptyField();

        printBattleField(preparationfield);

        for (Ships ship : Ships.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells):%n%n",
                    ship.getName(), ship.getLength());
            placeShips(preparationfield, input, ship);
            SUNK_SHIPS.add(ship);
        }

        beginGame(preparationfield, battlefield, input);
    }

    private static void beginGame(Cell[][] preparationfield,
                                  Cell[][] battlefield, Scanner input) {
        System.out.println("The game starts!\n");
        //printBattleField(preparationfield);
        List<Ships> sunkShips;
        takeShot(preparationfield, battlefield, input);
    }

    private static void takeShot(Cell[][] preparationfield,
                                 Cell[][] battlefield, Scanner input) {
        //Cell[][] battlefield = createEmptyField();
        System.out.println("\nTake a shot:\n");
        printBattleField(battlefield);
        do {
            String coordinate = input.next().toUpperCase();
            int row = coordinate.charAt(0) - 'A';
            int column = Integer.parseInt
                    (coordinate.substring(1)) - 1;

            // region Check if the player has already shot here (code comment)
            if (battlefield[row][column] == Cell.HIT ||
                    battlefield[row][column] == Cell.MISS) {
                System.out.println("You have already shot here!");
                continue;

            }
            //endregion

            if (preparationfield[row][column] == Cell.SHIP) {
                Ships ship = SHIPS_GRID[row][column];
                battlefield[row][column] = Cell.HIT;
                ship.hit();

                printBattleField(battlefield);

                if (ship.isSunk()) {
                    if (isAllShipsSunk()) {
                        System.out.println("You sank the last ship. You won. ");
                        System.out.println("Congratulations!");
                    } else {
                        System.out.println("You sank a ship. Specify a new target:");
                    }
                } else {
                    System.out.println("You hit a ship. Try again:");
                }
            } else {
                battlefield[row][column] = Cell.MISS;
                printBattleField(battlefield);
                System.out.println("You missed. Try again:");
            }

        } while (!isAllShipsSunk());
    }

    private static boolean isAllShipsSunk() {
        for (Ships ship : SUNK_SHIPS) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    //region place the ships. Final method
    private static void placeShips(Cell[][] preparationfield, Scanner input, Ships ship) {
        boolean validCoordinates = false;

        while (!validCoordinates) {
            String beginCoordinate = input.next().toUpperCase();
            String endCoordinate = input.next().toUpperCase();

            int rowInitial = beginCoordinate.charAt(0) -'A';
            int columnInitial = Integer.parseInt
                    (beginCoordinate.substring(1)) - 1;
            int rowFinal = endCoordinate.charAt(0) - 'A';
            int columnFinal = Integer.parseInt
                    (endCoordinate.substring(1)) - 1;

            //Basic validation
            if (isOutBounds (rowInitial, columnInitial)
                    || isOutBounds(rowFinal, columnFinal)) {
                System.out.println("Error!");
                continue;
                
            }

            boolean sameRow = rowInitial == rowFinal;
            boolean sameColumn = columnInitial == columnFinal;

            if (!(sameRow || sameColumn)) {
                System.out.println("Error! Wrong ship location! Try again:\n");
                continue;
            }

            //Length validation
            int length = sameRow ?
                    Math.abs(columnFinal - columnInitial) + 1 :
                    Math.abs(rowFinal - rowInitial) + 1;

            if (length != ship.getLength()) {
                System.out.printf("\nError! Wrong length of the %s! Try again:\n\n",
                        ship.getName());
                continue;
            }

            //Normalize the coordinates
            int startColumn = Math.min(columnInitial, columnFinal);
            int lastColumn = Math.max(columnInitial, columnFinal);
            int startRow = Math.min(rowInitial, rowFinal);
            int lastRow = Math.max(rowInitial, rowFinal);

            if (isToCloseOrOverlap
                    (preparationfield,
                            startRow, lastRow,
                            startColumn, lastColumn)) {
                System.out.print
                        ("\nError! You placed it too close to another one. Try again:\n\n");
                continue;
            }

            if (sameRow) {
                for (int col = startColumn; col <= lastColumn; col++) {
                    preparationfield[startRow][col] = Cell.SHIP;
                    SHIPS_GRID[startRow][col] = ship;
                }
            } else {
                for (int row = startRow; row <= lastRow; row++) {
                    preparationfield[row][startColumn] = Cell.SHIP;
                    SHIPS_GRID[row][startColumn] = ship;
                }
            }
            validCoordinates = true;
        }

        printBattleField(preparationfield);
    }

    private static boolean isToCloseOrOverlap(Cell[][] preparationfield,
                                              int startRow, int lastRow,
                                              int startColumn, int lastColumn) {
        int initialRow = Math.max(0, startRow - 1);
        int finalRow = Math.min(FILES_FIELD - 1, lastRow + 1);
        int initialColumn = Math.max(0, startColumn - 1);
        int finalColumn = Math.min(COLUMNS_FIELD - 1, lastColumn + 1);

        for (int row = initialRow; row <= finalRow; row++) {
            for (int col = initialColumn; col <= finalColumn; col++) {
                if (preparationfield[row][col] == Cell.SHIP) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isOutBounds(int row, int coliumn) {
        return (row < 0 || row >= FILES_FIELD ||
                coliumn < 0 || coliumn >= COLUMNS_FIELD);
    }

    //endregion

    //region Creates empty battlefield. Final method
    private static Cell[][] createEmptyField() {
//BUG: Don't override SHIPS_GRID
        Cell[][] battlefield = new Cell[FILES_FIELD][COLUMNS_FIELD];

        for (Cell[] row : battlefield)
            /*for (int j = 0; j < COLUMNS_FIELD; j++) {
                row[j] = Cell.FOG;
            }*/
            Arrays.fill(row, Cell.FOG);

        for (Ships[] row : SHIPS_GRID)
            /*
            for (int j = 0; j < COLUMNS_FIELD; j++) {
                row[j] = null;
            }*/
            Arrays.fill(row, null);

        return battlefield;
    }
    //endregion

    //region print battlefield. Final method
    private static void printBattleField(Cell[][] battlefield) {
        System.out.print(" ");

        for (int i = 1; i <= COLUMNS_FIELD; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        for (int i = 0; i < FILES_FIELD; i++) {
            char letter = (char) ('A' + i);
            System.out.printf("%s ", letter);

            for (int j = 0; j < COLUMNS_FIELD; j++) {
                System.out.printf("%c ", battlefield[i][j].getSymbol());
            }
            System.out.println();
        }
        System.out.println();
    }
    //endregion
}