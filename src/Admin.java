import java.io.*;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Scanner;

public class Admin extends User {
    private static final String userName = "admin";
    private static final String password = "admin";
    private static boolean loggedIn = false;

    public static boolean loginAdmin() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String pass = sc.nextLine();

        if (userName.equals(username) && password.equals(pass)) {
            System.out.println("Log in Success");
            System.out.println("-".repeat(20));
            System.out.println("Hello Admin.");
            loggedIn = true;
            return loggedIn;

        }

        System.out.println("Wrong username or pass");
        return false;
    }

    public void addProduct() throws IOException {
        if (loggedIn) {
            ((new ProductDatabase())).addProduct();
        }

    }

    public void updatePrice() {
        if (loggedIn) {
            (new ProductDatabase()).updatePrice();
        }

    }

    public void getOrderInfoByCid() throws FileNotFoundException {
        if (loggedIn) {
            (new OrderDatabase()).displayAllOrderByCustomerId();
        }
    }

    public void changeOrderStatus() {
        if (loggedIn) {
            (new OrderDatabase()).updateOrderStatus();
        }

    }

    public void viewAllProducts() {
        if (loggedIn) {
            (new ProductDatabase()).displayAll();
        }
    }

    public void viewAllMembers() {
        if (loggedIn) {
            (new MemberDatabase()).displayAll();
        }
    }

    public void viewAllOrders() {
        if (loggedIn) {
            (new OrderDatabase()).displayAllOrders();
        }
    }
}
