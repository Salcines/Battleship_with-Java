class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        StringBuilder alphabet = new StringBuilder();

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            alphabet.append(letter).append(" ");
        }

        return alphabet.deleteCharAt(alphabet.length() - 1);
    }
}