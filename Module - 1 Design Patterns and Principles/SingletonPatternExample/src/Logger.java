public class Logger {

    // Single instance of Logger
    private static Logger instance = new Logger();

    // Private constructor prevents object creation
    private Logger() {

    }

    // Returns the single instance
    public static Logger getInstance() {
        return instance;
    }

    // Logging method
    public void log(String message) {
        System.out.println(message);
    }
}