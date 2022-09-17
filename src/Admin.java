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
        System.out.println("-Adding new product-");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String name = sc.nextLine();
        System.out.println("Enter product description: ");
        String des = sc.nextLine();
        System.out.println("Enter product category: ");
        String category = sc.nextLine();
        System.out.println("Enter product price: ");
        String price = sc.nextLine();
        System.out.println("Enter product quantity: ");
        String quantity = sc.nextLine();

        Product newProduct = new Product(name, des, category, price, quantity);
        BufferedWriter out = new BufferedWriter(new FileWriter("Product.csv", true));
        out.write(newProduct.toString() + "\n");
        out.close();
        System.out.println("Added successfully!");

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
