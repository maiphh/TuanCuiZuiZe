import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProductDatabase extends Database implements Public {
    private static ArrayList<Product> products = new ArrayList<>();

    // private static File file = new File("../Product.csv");
    private static File file = new File("Product.csv");
    private static boolean loaded = false;
    private static String header = "";
    private static int count = 0;

    // private ProductDatabase() {
    // loadProductDatabase();
    // }
    public Product getProduct() {

        if (this.checkCompatibility()) {

            Scanner sc = new Scanner(System.in);
            Product result = null;

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
        } else {
            System.out.println("You don't have access to this functionality");
            return null;
        }

    }

    public ProductDatabase() {
        loadProductDatabase();
    }

    // public int getCount() {
    // int result = 0;
    // // loadProductDatabase();
    // try {
    // // File newFile = new File("Product.csv");

    // Scanner sc = new Scanner(file);
    // header = sc.nextLine();
    // // System.out.println("start");
    // while (!sc.nextLine().equals(null)) {
    // // System.out.println("between");
    // result++;
    // if (!sc.hasNext())
    // break;
    // }
    // // System.out.println("end");

    // } catch (Exception e) {
    // // System.out.println(e.getMessage());
    // System.out.println("weee");
    // }
    // count = result;
    // return count;

    // }

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

    public Product selectedProduct() {
        loadProductDatabase();
        Scanner input = new Scanner(System.in);
        Product currentProduct = null;
        // Should add a while loop to prevent wrong input
        System.out.println("Enter the product id: ");
        String id = input.nextLine();
        int index;
        String pIDPatter = "^p[0-9]+$";
        // must enter a real product
        while (true) {
            if (!id.matches(pIDPatter)) {
                System.out.println("Product ID format incorrect");
                break;
            }
            if (id.equals("0"))
                break;
            index = Integer.parseInt(id.substring(1));
            if (index <= products.size() && index > 0) {
                return products.get(index - 1);
            }
            System.out.println("We don't have that ID");
            break;
        }

        return currentProduct;

    }
    // public int getCount() {
    // loadProductDatabase();
    // return count;
    // }

    static void loadProductDatabase() {
        // System.out.println("loading...");
        if (loaded) {

            return;
        }
        try {

            Scanner sc = new Scanner(file);
            header = sc.nextLine();
            while (sc.hasNextLine()) {
                String[] productData = sc.nextLine().split(",");
                System.out.println(Arrays.toString(productData));

                Product newProduct = new Product(productData);
                products.add(newProduct);
                // count++;
            }
            loaded = true;
            System.out.println(products);
            sc.close();
        } catch (Exception e) {
            System.out.println("wee");
            System.out.println(e.getMessage());
        }
    }

    public void displayAll() {

        if (!this.checkCompatibility()) {
            return;
        }
        loadProductDatabase();
        TableGenerator.printTable(file);
        // TableGenerator.printTable(products, header);
    }

    public void displayByCategory() {
        loadProductDatabase();
        if (!this.checkCompatibility()) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter category: ");
        String input = sc.nextLine();
        boolean has = false;
        ArrayList<Product> results = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCategory().equals(input) && products.get(i).getQuantity() > 0) {
                results.add(products.get(i));
                has = true;
            }
        }
        if(has) {
            TableGenerator.printTable(results, header);
        }
        else System.out.println("There is no "+input+" category!");
    }

    //

    public void displayByPrice() {

        if (!this.checkCompatibility()) {
            return;
        }
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

    static void updateQuantity(HashMap<String, Integer> productQuantity) {
        for (String i : productQuantity.keySet()) {
            int index = Integer.parseInt(i.substring(1)) - 1;
            products.get(index).setQuantity(productQuantity.get(i));
        }
    }

    public void displaySpecific() {

        if (!this.checkCompatibility()) {
            return;
        }
        // consider doing index search to be more efficient later
        loadProductDatabase();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the product id: ");
        String id = input.nextLine();
        // input.close();
        for (Product product : products) {
            if (product.getID().equals(id)) {
                product.displayDetailInfo();
                return;
            }
        }
        System.out.println("Product with that id doesn't exist");

    }

    // For admin
    // addProduct
    public void addProduct() {

        if (!this.checkCompatibility()) {
            return;
        }
        loadProductDatabase();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter product name:");
        String name = input.nextLine();
        System.out.println("Enter product description:");
        String description = input.nextLine();
        System.out.println("Enter product category:");
        String category = input.nextLine();

        System.out.println("Enter product quantity:");
        String quantity = input.nextLine();
        String pattern = "[0-9]+";
        if (!(Pattern.compile(pattern).matcher(quantity).matches())) {
            System.out.println("Invalid input. Please try again!");
            return;
        }

        System.out.println("Enter product price:");
        String price = input.nextLine();

        if (!(Pattern.compile(pattern).matcher(price).matches())) {
            System.out.println("Invalid input. Please try again!");
            return;
        }

        products.add(new Product(name, description, category, price, quantity));
        // Category.addCategory(category);
        System.out.println("Product added successfully");
        updateProductDatabase();
    }

    public void updatePrice() {

        if (!this.checkCompatibility()) {
            return;
        }
        loadProductDatabase();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the wanted update price product");
        String upPriceProduct = input.nextLine();
        String doublePattern = "^[1-9][0-9]+\\.?[0-9]+$";
        // Add validation to the input later (MUST ADD);
        for (Product i : products) {
            if (i.getID().equals(upPriceProduct)) {
                System.out.println("Enter the wanted Price");
                String price = input.nextLine();
                if (!price.matches(doublePattern)) {
                    System.out.println("You have to enter a double");
                    return;
                }
                double newPrice = Double.parseDouble(price);

                i.setPrice(newPrice);
                System.out.println("Price updated successfully");
            }
        }
        System.out.println("We don't have that product");
        // int wantedIndex = Integer.parseInt(upPriceProduct.substring(1)) - 1;
        // Product wantedProduct = products.get(wantedIndex);
        // System.out.println("Enter wanted price: ");
        // double newPrice = input.nextDouble();
        // wantedProduct.setPrice(newPrice);
        // input.close();
        updateProductDatabase();
    }

    @Override
    public ArrayList<Product> getList() {
        return products;
    }

    @Override
    public String getHeader() {
        return header;
    }

}
