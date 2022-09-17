import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

// consider singleton since only need one order during the whole shopping trip
public class Order {
    private String oID;
    private String userID;
    private ArrayList<String[]> products = new ArrayList<>();
    private String orderDate;
    private String status = "PROCESS";
    private double bill;
    private static int count = 1;

    public Order(String[] orderData) {
        this.oID = orderData[0];
        this.userID = orderData[1];
        this.orderDate = orderData[2];
        this.status = orderData[3];
        this.bill = Double.parseDouble(orderData[4]);
        count++;
    }

    public Order(String userID) {
        // add date
        this.userID = userID;
        this.orderDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // this.oID = "o" + String.valueOf(count);
        // count++;
    }

    public void addProduct(Product product, int quantity) {

        // 4 is the number of header in orderitem.csv
        // Add product when creating new order
        String[] data = new String[5];
        // data[0] = this.oID;
        data[1] = product.getID();
        data[2] = product.getName();
        data[3] = String.valueOf(product.getPrice());
        data[4] = String.valueOf(quantity);
        this.bill += product.getPrice() * quantity;
        this.products.add(data);
    }

    public void addProduct(String[] archiveData) {
        // add product when loading data into order
        String[] data = new String[5];
        data[0] = archiveData[0];
        data[1] = archiveData[1];
        data[2] = archiveData[2];
        data[3] = archiveData[3];
        data[4] = archiveData[4];
        this.products.add(data);
    }

    public void deleteProduct() {
        // will not remove
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the product ID that you want to remove, press 0 to escape");
        String userInput = input.nextLine();
        String[] tempProduct;
        while (true) {
            if (userInput.equals("0"))
                return;
            for (int i = 0; i < products.size(); i++) {
                tempProduct = products.get(i);
                if (tempProduct[0].equals(userInput)) {
                    products.remove(i);
                    System.out.println("Your product have been removed");
                }
            }
            System.out.println("Product not found, please re enter:");
            userInput = input.nextLine();
        }

    }

    public String getID() {
        return oID;
    }

    public void setStatus(String status) {
        if (User.currentUser instanceof Admin) {
            this.status = status;
        }
    }

    public void updateID() {
        count++;
        this.oID = "o" + count;
        // update order ID to all product
        for (String[] i : products) {
            i[0] = this.oID;
        }

    }

    public String getUser() {
        return userID;
    }

    public Double getBill() {
        return this.bill;
    }

    public void displayInfo() {
        System.out.printf("%s,%s,%s,%.2f\n", userID, status, orderDate, bill);
    }

    public void displayAllInfo() {
        this.displayInfo();
        for (String[] i : this.products) {
            System.out.println(Arrays.toString(i));
        }
    }

    public void updateStatus() {
        this.status = "PAID";
    }

    public String toString() {
        String[] orderData = { this.oID, this.userID, this.orderDate, this.status, Double.toString(this.bill) };
        String data = String.join(",", orderData);
        return data;
    }

    public String[] productsToString() {
        String[] orderItemData = new String[products.size()];
        for (int i = 0; i < orderItemData.length; i++) {
            orderItemData[i] = String.join(",", products.get(i));
        }
        return orderItemData;

    }

    public HashMap<String, Integer> boughtQuantity() {
        HashMap<String, Integer> quantityPerProduct = new HashMap<>();
        for (String[] i : this.products) {
            System.out.println(i[0]);
            quantityPerProduct.put(i[1], Integer.parseInt(i[4]));
        }
        return quantityPerProduct;
    }

    public int getSize() {
        return products.size();
    }

    public ArrayList<String[]> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String[]> products) {
        this.products = products;
    }

}

// public static Order createOrder() throws Exception {
// Scanner sc = new Scanner(System.in);
// Order order = new Order();
// HashMap<Product, Integer> orderInfo = new HashMap<>();
// HashMap<String, Integer> orderInfoForUser = new HashMap<>();
// System.out.println("Press q to stop your order");
// while (true) {
// Product p = ProductDatabase.getProduct();
// if (p == null) {
// break;
// }
// System.out.println("Enter the quantity");
// String input = sc.nextLine();
// if (input.equals("q")) {
// break;
// }
// int qty = Integer.parseInt(input);
// orderInfo.put(p, qty);
// orderInfoForUser.put(p.getName(), qty);
// System.out.println(orderInfoForUser);

// }
// order.setInfo(orderInfo);
// return order;

// }

// public long calculatePrice() {
// long total = 0;
// for (Product product : this.orderInfo.keySet()) {
// total += product.getPrice() * orderInfo.get(product);
// }
// return (long) (total * (1 - Member.currentUser.getDiscount()));
// }
