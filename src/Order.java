import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private HashMap<Product, Integer> orderInfo = new HashMap<>();
    private String ID;
    private String userID;
    private String orderDate;
    private String status = "PROCESS";
    private double bill;
    private static int count = 1;

    public Order(String[] orderData){
        this.ID = orderData[0];
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
        count++;
    }


    public HashMap<Product, Integer> getInfo() {
        return this.orderInfo;
    }

    public void setInfo(HashMap<Product, Integer> info) {
        this.orderInfo = info;
    }

    public void updateStatus() {
        this.status = "PAID";
    }
    public void updateID() {
        this.ID = "o" + count;
    }

    // public static Order createOrder() throws Exception {
    //     Scanner sc = new Scanner(System.in);
    //     Order order = new Order();
    //     HashMap<Product, Integer> orderInfo = new HashMap<>();
    //     HashMap<String, Integer> orderInfoForUser = new HashMap<>();
    //     System.out.println("Press q to stop your order");
    //     while (true) {
    //         Product p = ProductDatabase.getProduct();
    //         if (p == null) {
    //             break;
    //         }
    //         System.out.println("Enter the quantity");
    //         String input = sc.nextLine();
    //         if (input.equals("q")) {
    //             break;
    //         }
    //         int qty = Integer.parseInt(input);
    //         orderInfo.put(p, qty);
    //         orderInfoForUser.put(p.getName(), qty);
    //         System.out.println(orderInfoForUser);

    //     }
    //     order.setInfo(orderInfo);
    //     return order;

    // }

    public long calculatePrice() {
        long total = 0;
        for (Product product : this.orderInfo.keySet()) {
            total += product.getPrice() * orderInfo.get(product);
        }
        return (long) (total * (1 - Member.currentUser.getDiscount()));
    }

}