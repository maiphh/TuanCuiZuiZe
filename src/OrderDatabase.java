import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.HashMap;
import java.util.Scanner;

public class OrderDatabase extends Database implements Manager {
    private static ArrayList<Order> orders = new ArrayList<>();
    private static boolean loaded = false;
    private static String header;
    private static String header2;

    // static Order createOrder(String userID) {
    // loadOrderDatabase();
    // Order newOrder = new Order(userID);
    // orders.add(newOrder);
    // return newOrder;
    // }

    static void addNewOrder(Order newOrder) {
        loadOrderDatabase();
        newOrder.updateID();
        orders.add(newOrder);
        System.out.println("New order add successfully");
    }

    static void loadOrderDatabase() {
        // load from both order file and order-product file
        Order newOrder;
        String[] orderData;
        String[] orderItem = null;
        if (loaded)
            return;
        try {
            File f1 = new File("Order.csv");
            File f2 = new File("OrderItem.csv");
            Scanner file = new Scanner(f1);
            Scanner file2 = new Scanner(f2);
            header = file.nextLine();
            header2 = file2.nextLine();

            while (file.hasNextLine()) {
                orderData = file.nextLine().split(",");
                newOrder = new Order(orderData);
                if (orderItem != null) {
                    newOrder.addProduct(orderItem);
                }

                while (file2.hasNextLine()) {
                    orderItem = file2.nextLine().split(",");
                    if (!newOrder.getID().equals(orderItem[0]))
                        break;
                    newOrder.addProduct(orderItem);

                }
                orders.add(newOrder);

            }

            loaded = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    static void updateOrderDatabase() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Order.csv", false));
            out.write(header + "\n");
            for (Order order : orders) {
                out.write(order.toString() + "\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedWriter out2 = new BufferedWriter(new FileWriter("OrderItem.csv", false));
            out2.write(header2 + "\n");
            for (Order order : orders) {
                String[] orderItemData = order.productsToString();
                for (String i : orderItemData) {
                    out2.write(i + "\n");
                }
            }
            out2.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // static void seeOrderDetail(String uid) {
    // // May add an option for see all of a user's order
    // loadOrderDatabase();
    // Scanner input = new Scanner(System.in);
    // System.out.println("Input your order ID");
    // String oID = input.nextLine();
    // for (Order order : orders) {
    // if (order.getID().equals(oID) && order.getUser().equals(uid)) {
    // order.displayInfo();
    // return;
    // }
    // }
    // System.out.println("You don't have that order");
    // }

    // for admin only
    public String calculateRevenue() throws FileNotFoundException {
        if (this.checkCompatibility()) {
            double result = 0;
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Scanner sc = new Scanner(new File("Order.csv"));
            String s = sc.nextLine();
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                String date = data[2];
                if (today.equals(date)) {
                    result += Double.parseDouble(data[4]);
                }
            }
            return String.format("%.2f", result);
        }
        return null;

    }

    public void viewOrdersToday() throws FileNotFoundException {
        if (!this.checkCompatibility()) {
            return;
        }
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Scanner sc = new Scanner(new File("Order.csv"));
        String s = sc.nextLine();
        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");
            String date = data[2];
            if (today.equals(date)) {
                System.out.println(Arrays.asList(data));
                System.out.println("-".repeat(25));
            }
        }

    }

    public void displayAllOrders() {
        if (!this.checkCompatibility()) {
            return;
        }
        loadOrderDatabase();
        // for (Order order:orders) {
        // order.displayInfo();
        // }
        TableGenerator.printTable(new File("Order.csv"));
    }

    public void displayAllOrderByCustomerId() {
        if (!this.checkCompatibility()) {
            return;
        }
        loadOrderDatabase();
        int validID = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter customer ID");
        String mID = input.nextLine();
        for (Order order : orders) {
            if (order.getUser().equals(mID)) {
                order.displayInfo();
                validID++;
            }
        }
        if (validID == 0) {
            System.out.println("We don't have that user ID");
        }
        // input.close();
        // Add validation to the input later (MUST ADD);
    }

    public void updateOrderStatus() {
        if (!this.checkCompatibility()) {
            return;
        }
        loadOrderDatabase();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the wanted order");
        String oID = input.nextLine();
        // OID validation
        String oIDPattern = "^o[0-9]+$";
        if (!oID.matches(oIDPattern)) {
            System.out.println("Invalide order ID");
            return;
        }
        int wantedIndex = Integer.parseInt(oID.substring(1)) - 1;
        if (wantedIndex > orders.size() || wantedIndex <= 0) {
            System.out.println("We don't have that order");
            return;
        }
        Order wantedOrder = orders.get(wantedIndex);
        wantedOrder.updateStatus();
        System.out.println("Status update succesfully");
        updateOrderDatabase();
    }

    @Override
    public ArrayList<Order> extractToCache() {
        loadOrderDatabase();
        User currentUser = User.currentUser;
        ArrayList<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser().equals(((Member) currentUser).getCustomerId())) {
                result.add(order);
            }
        }
        return result;

    }

    @Override
    public void loadFromCache() {
        ArrayList<Order> cacheList = (new PersonalOrderDatabase()).getList();
        for (Order cacheOrder : cacheList) {
            for (Order order : orders) {
                if (cacheOrder.getID().equals(order.getID())) {
                    order = cacheOrder;
                }
            }
        }
    }

    @Override
    public ArrayList<Order> getList() {
        return orders;
    }

    @Override
    public String getHeader() {
        return header;
    }

}
