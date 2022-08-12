import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ProductDatabase {
    private static ArrayList<Product> products = new ArrayList<>();

    // private static File file = new File("../Product.csv");
    private static File file = new File("Product.csv");
    private static boolean loaded = false;
    private static String header = "";

    // private ProductDatabase() {
    // loadProductDatabase();
    // }

    static Product getProduct() {
        Scanner sc = new Scanner(System.in);
        Product result = null;
        // System.out.println(products);
        System.out.println("Enter product name");
        String productName = sc.nextLine();
        if (productName.equals("q")) {
            return null;
        } else {
            for (Product product : products) {
                if (product.getName().equals(productName)) {
                    result = product;
                }
            }
            return result;
        }

    }

    static void updateProductDatabase() {
        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(file.getPath(), false));

            out.write(header + "\n");
            for (Product product : products) {
                out.write(product.toString() + "\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void loadProductDatabase() {
        if (loaded)
            return;
        try {

            Scanner sc = new Scanner(file);
            header = sc.nextLine();
            while (sc.hasNextLine()) {
                String[] productData = sc.nextLine().split(",");

                Product newProduct = new Product(productData);
                products.add(newProduct);
            }
            loaded = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void displayAll() {
        loadProductDatabase();
        for (Product product : products) {
            product.displayGeneralInfo();
        }
    }

    static void displayByCategory() {
    }

    //
    static void displaySpecific() {
    }

    static void displayByPrice() {
        loadProductDatabase();
        int numsProduct = products.size();
        Product[] sortPrice = new Product[numsProduct];

        for (int i = 0; i < numsProduct; i++) {
            sortPrice[i] = products.get(i);
        }
        // Sort product base on price
        Arrays.sort(sortPrice, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return (int) (p1.getPrice() - p2.getPrice());
            }
        });
        for (Product i : sortPrice) {
            i.displayGeneralInfo();
        }
    }

    // For admin
    // addProduct
    static void addProduct() {
    }

}
