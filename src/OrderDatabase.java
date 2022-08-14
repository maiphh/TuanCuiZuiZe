import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class OrderDatabase {
    private static ArrayList<Order> orders = new ArrayList<>();
    private static boolean loaded = false;
    private static String header;
    private static String header2;

    static void deleteOrder() {
        // The latest order
        orders.remove(orders.size() - 1);
    }
    static Order createOrder(String userID) {
        loadOrderDatabase();
        Order newOrder = new Order(userID);
        orders.add(newOrder);
        return newOrder;
    }

    static void addNewOrder(Order newOrder) {
        loadOrderDatabase();
        newOrder.updateID();
        orders.add(newOrder);
        System.out.println("New order add successfully");
    }

    static void loadOrderDatabase() {}


    static void updateOrderDatabase() {}

    static void seeOrderDetail(String uid) {}

    // FOR ADMIN only
    static void displayAllOrders() {}
    
    static void displayAllOrderByCustomerId() {}

    static void updateOrderStatus() {}
}
