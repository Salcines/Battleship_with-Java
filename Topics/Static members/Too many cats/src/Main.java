class Cat {
    static int numberOfCats = 0;

    private int age;
    private String name;


    public Cat(String name, int age) {
        this.age = age;
        this.name = name;
        numberOfCats++;

        if (numberOfCats > 5) {
            System.out.println("You have too many cats");
        }
    }

    public static int getNumberOfCats() {
        return numberOfCats;
    }
}