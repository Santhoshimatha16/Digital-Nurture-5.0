public class Main {

    public static void main(String[] args) {

        // Get first Logger object
        Logger logger1 = Logger.getInstance();

        // Get second Logger object
        Logger logger2 = Logger.getInstance();

        // Use both objects
        logger1.log("First message");
        logger2.log("Second message");

        // Check whether both references point to the same object
        if (logger1 == logger2) {
            System.out.println("Only one Logger instance exists.");
        } else {
            System.out.println("Multiple Logger instances exist.");
        }
    }
}