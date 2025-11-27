import java.util.Scanner;
import java.util.Arrays;

class Publication {

    private String title;

    public Publication(String title) {
        this.title = title;
    }

    public final String getInfo() {
        return String.format("%s%s: %s", getType(), getDetails(), title);
    }

    public String getType() {
        return "Publication";
    }

    public String getDetails() {
        return "";
    }

}

class Newspaper extends Publication {

    private String source;

    public Newspaper(String title, String source) {
        super(title);
        this.source = source;
    }

    @Override
    public String getType() { return "Newspaper";}

    @Override
    public String getDetails() {
        return String.format(" (source - %s)", source);
    }

}

class Article extends Publication {

    private String author;

    public Article(String title, String author) {
        super(title);
        this.author = author;
    }

    @Override
    public String getType() { return "Article";}

    @Override
    public String getDetails() {
        return String.format(" (author - %s)", author);
    }
}

class Announcement extends Publication {

    private int daysToExpire;

    public Announcement(String title, int daysToExpire) {
        super(title);
        this.daysToExpire = daysToExpire;
    }

    @Override
    public String getType() { return "Announcement";}

    @Override
    public String getDetails() {
        return String.format(" (days to expire - %d)", daysToExpire);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] source = scanner.nextLine().split("; ");
        System.out.println(Arrays.toString(source));
        switch (source[0]) {
            case "Publication" -> {
                Publication p = new Publication(source[1]);
                System.out.println(p.getInfo());
            }
            case "Article" -> {
                Article a = new Article(source[1], source[2]);
                System.out.println(a.getInfo());
            }
            case "Newspaper" -> {
                Newspaper n = new Newspaper(source[1], source[2]);
                System.out.println(n.getInfo());
            }
            case "Announcement" -> {
                Announcement an = new Announcement(source[1], Integer.parseInt(source[2]));
                System.out.println(an.getInfo());
            }
        }

    }
}