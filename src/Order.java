import java.util.*;

public class Order {
    private HashMap<Product, Integer> orderInfo = new HashMap<>();
    private String ID;

    public HashMap<Product, Integer> getInfo() {
        return this.orderInfo;
    }

    public void setInfo(HashMap<Product, Integer> info) {
        this.orderInfo = info;
    }

    public static Order createOrder() throws Exception {
        Scanner sc = new Scanner(System.in);
        Order order = new Order();
        HashMap<Product, Integer> orderInfo = new HashMap<>();
        HashMap<String, Integer> orderInfoForUser = new HashMap<>();
        System.out.println("Press q to stop your order");
        while (true) {
            Product p = ProductDatabase.getProduct();
            if (p == null) {
                break;
            }
            System.out.println("Enter the quantity");
            String input = sc.nextLine();
            if (input.equals("q")) {
                break;
            }
            int qty = Integer.parseInt(input);
            orderInfo.put(p, qty);
            orderInfoForUser.put(p.getName(), qty);
            System.out.println(orderInfoForUser);

        }
        order.setInfo(orderInfo);
        return order;

    }

    public long calculatePrice() {
        long total = 0;
        for (Product product : this.orderInfo.keySet()) {
            total += product.getPrice() * orderInfo.get(product);
        }
        return (long) (total * (1 - Member.currentUser.getDiscount()));
    }

}