@SuppressWarnings("All")
class Util {
    public static String capitalize(String str) {
        System.out.println("Before: " + str);
        //It's a bad joke
        if (str == null || str.isBlank()) {
            System.out.println("After: " + str);
            return str;
        }


        String word = str;
        if (str.length() == 1) {
            word = str.toUpperCase();
        } else {
            word = Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
        }

        System.out.println("After: " + word);

        return word;
    }
}