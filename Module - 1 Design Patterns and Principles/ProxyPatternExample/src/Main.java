public class Main {

    public static void main(String[] args) {

        Image image =
                new ProxyImage("profile.jpg");

        System.out.println(
                "First Display:");

        image.display();

        System.out.println(
                "\nSecond Display:");

        image.display();
    }
}