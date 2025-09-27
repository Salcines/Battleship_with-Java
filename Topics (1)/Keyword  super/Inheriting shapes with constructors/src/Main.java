import java.util.Scanner;

// Define the base class Shape
class Shape {
    private final String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

// Define the derived class Rectangle
class Rectangle extends Shape {
    private final int width;
    private final int height;
    public Rectangle(String name, int width, int height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // TODO: Read the shape's name, width, and height from the user input
        String name = scanner.nextLine();
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        Rectangle rectangle = new Rectangle(name, width, height);

        System.out.println("Name: "+ rectangle.getName());
        System.out.println("Width: " + rectangle.getWidth());
        System.out.println("Height: " + rectangle.getHeight());
    }
}