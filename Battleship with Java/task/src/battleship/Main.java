package battleship;

public class Main {

    static final char FOG_OF_WAR = '~';
    static final char CELL_WITH_SHIP = '0';
    static final char HIT = 'X';
    static final char MISS = 'M';

    static final int FILES_FIELD = 10;
    static final int COLUMNS_FIELD = 10;

    static final int HEADER = 1;

    public static void main(String[] args) {
        char [][] battleField = CreateEmptyField();

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
