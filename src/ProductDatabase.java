import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class ProductDatabase {
    private static ArrayList<Product> products = new ArrayList<>();
    private static boolean dataLoaded = false;

    // private ProductDatabase() {
    //     loadProductDatabase();
    // }

    static void loadProductDatabase() {}

    static void updateProductDatabase() {}

    static void displayAll() {
        loadProductDatabase();
        for (Product product:products) {
            product.displayGeneralInfo();
        }
    }

    static void displayByCategory() {}
    //
    static void displaySpecific() {}

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
                return(int) (p1.getPrice() - p2.getPrice());
            }
        });
        for (Product i: sortPrice) {
            i.displayGeneralInfo();
        }
    }

    // For admin
    // addProduct
    static void addProduct() {}
    
    
}
