import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(2)
    void loginTest() {
        System.out.println("Login Test");
    }

    @Test
    @Order(1)
    void registerTest() {
        System.out.println("Register Test");
    }

    @Test
    @Order(3)
    void logoutTest() {
        System.out.println("Logout Test");
    }
}