import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter category: ");
        String input = sc.nextLine();
        ArrayList<Product> results = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCategory().equals(input) && products.get(i).getQuantity() > 0) {
                results.add(products.get(i));
            }
        }
        TableGenerator.printTable(results, header);
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

        List<Product> list = Arrays.asList(sortPrice);

        // for (Product i : sortPrice) {
        // i.displayGeneralInfo();
        // }
        TableGenerator.printTable(list, header);
    }

    // For admin
    // addProduct
    static void addProduct() {
    }

}
