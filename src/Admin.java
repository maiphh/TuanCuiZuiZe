import java.io.*;
import java.util.Scanner;

public class Admin {
    private static final String userName = "admin";
    private static final String password = "admin";

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
            return true;

        }


        System.out.println("Wrong username or pass");
        return false;
    }

    public static void addProduct() throws IOException {
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

    }

    public static void getInfoByCid() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer ID: ");
        String cid = sc.nextLine();

        File f = new File("Order.csv");
        Scanner file = new Scanner(f);
        while (file.hasNextLine()) {
            String fileLine = file.nextLine();
            String[] fileLineArray = fileLine.split(",");
            if (fileLine != "") {
                if (fileLineArray[1].equals(cid)) {
                    System.out.println(fileLine);
                }

            }
        }
    }

    public void changeStatus(){

    }
}
