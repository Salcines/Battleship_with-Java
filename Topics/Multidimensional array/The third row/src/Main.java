class ArrayOperations {
    public static void printTheThirdRow(int[][] twoDimArray) {
        int rowToPrint = 2;

        for (int element : twoDimArray[rowToPrint]) {
            System.out.print(element + " ");
        }

    }
}