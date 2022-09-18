import java.util.ArrayList;
import java.util.Scanner;

public class PersonalOrderDatabase extends Database implements Personalized {
    private static Manager manager = new OrderDatabase();
    private static ArrayList<Order> list = new ArrayList<>();
    private static String header = ((Database) manager).getHeader();
    private static boolean loaded = false;

    public void load() {
        if (loaded) {
            return;
        }
        list = (ArrayList<Order>) manager.extractToCache();
        loaded = true;
    }

    public void viewOrderByID() {
        load();
        if (!this.checkCompatibility()) {
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Input your order ID");
        String oID = input.nextLine();
        // System.out.println(list);
        for (Order order : list) {
            // System.out.println(order.getID());
            if (order.getID().equals(oID)) {
                order.displayInfo();
                return;
            }
        }
        System.out.println("You don't have that order");
    }

    @Override
    public void clear() {
        list = null;
        loaded = false;
    }

    @Override
    public ArrayList<Order> getList() {
        return list;
    }

    @Override
    public String getHeader() {
        return header;
    }

}
