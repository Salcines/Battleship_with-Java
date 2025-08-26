public class Main {

    final static String SEARCH_BY = "STAR";
    public static void main(String[] args) {

        int counter = 0;

        for (Secret element : Secret.values())
        {
            if (element.name().startsWith(SEARCH_BY))
            {
                counter++;
            }
        }

        // write your code here
        System.out.println(counter);


    }
}

/* sample enum for inspiration
enum Secret {
    STAR, CRASH, START, // ...
}*/
