import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    private static Order currentOrder;
    private static Member currentMember;

    public Main() {
    }

    public static void main(String[] args) throws IOException {

        AccessMapping accessMap = new AccessMapping();
        MemberDatabase md = new MemberDatabase();
        // System.out.println(md.getCount());
        // System.out.println(md.getList());
        // System.out.println(md.getCount());
        // System.out.println((new MemberDatabase()).getList());
        // ProductDatabase.loadProductDatabase();
        loginMenu();
    }

    public static void loginMenu() throws IOException {
        String input;
        while (true) {

            System.out.println("-".repeat(20));
            System.out.println(
                    "[1] Login As Guest\n[2] Login As Member\n[3] Login As Admin\n[4] Register an account\n[0] Exit\nEnter a number: ");
            input = sc.nextLine();
            if (input.equals("0"))
                break;
            switch (input) {
                case "1":

                    guestMenu();
                    break;
                case "2":
                    if (Member.loginMember()) {
                        memberMenu();
                    }
                    break;
                case "3":
                    if (Admin.loginAdmin()) {
                        adminMenu();
                    }
                    break;

                case "4":
                    currentMember = Member.createMember();
                    // loginMenu();
                    break;

                default:
                    System.out.println("Invalid input. Please try again!");
                    // loginMenu();
                    break;

            }
        }
    }

    public static void guestMenu() throws IOException {
        //
        Guest guest = new Guest();
        User.currentUser = guest;
        String input;
        while (true) {
            System.out.println("-".repeat(20));
            System.out.printf(
                    "[1] View all products\n[2] Search Product by category \n[3] Sort product by price \n[4] Register an account \n[0] Exit \nEnter a number: ");
            input = sc.nextLine();
            if (input.equals("0"))
                break;
            switch (input) {
                case "1":
                    // View All Products
                    guest.viewAllProducts();
                    break;
                case "2":
                    // Search Product by category
                    guest.searchProductByCategory();
                    break;
                case "3":
                    guest.displayByPrice();
                    break;

                case "4":
                    currentMember = Member.createMember();
                    loginMenu();
                    break;

                default:
                    System.out.println("Invalid input. Please try again!");
                    break;

            }
        }

    }

    public static void memberMenu() throws IOException {
        User.currentUser = Member.currentUser;
        // System.out.println((new PersonalMemberDatabase()).getList());
        Member member = (Member) User.currentUser;
        String input;
        while (true) {
            System.out.println(
                    "[1] View all products\n[2] Search Product by category \n[3] Sort product by price\n[4] Start an order \n[5] View information of order by Order ID\n[6] View Personal Info\n[7] Add product to order\n[8] Pay up order\n[0]Exit\nEnter a number: ");
            input = sc.nextLine();
            // if (currentOrder != null) {
            //     currentOrder.displayAllInfo();
            // }
            if (input.equals("0"))
                break;
            switch (input) {
                case "1":
                    // View All Product
                    // checked
                    member.viewAllProducts();
                    break;

                case "2":
                    // Search Product by category
                    // checked

                    member.searchProductByCategory();
                    break;
                case "3":
                    // checked

                    member.displayByPrice();
                    break;

                case "4":
                    placeOrder();
                    break;

                case "5":
                    // View Information of Order by Order ID
                    member.viewOrderByID();

                case "6":
                    // System.out.println(member.displayInfo());
                    // checked
                    member.displayInfo();
                    break;

                case "7":
                    // add product to order
                    addProductToOrder();
                    break;
                case "8":
                    // pay up order
                    payUpOrder();
                    break;
                default:
                    System.out.println("Invalid input. Please try again!");
                    break;

            }
        }

    }

    public static void adminMenu() throws IOException {
        Admin admin = new Admin();
        User.currentUser = admin;
        String input;
        while (true) {
            System.out.println(
                    "[1] View all products\n[2] View all Members\n[3] View all Orders\n[4] Add new products\n[5] Update price\n[6] Get information of Order by Customer ID\n[7] Change status of Order\n[0] Exit\nEnter a number: ");
            input = sc.nextLine();
            if (input.equals("0"))
                break;
            switch (input) {
                case "1":
                    // view All products
                    admin.viewAllProducts();
                    break;
                case "2":
                    // view All members
                    admin.viewAllMembers();
                    break;
                case "3":
                    // view All orders
                    admin.viewAllOrders();
                    break;
                case "4":
                    admin.addProduct();
                    break;

                case "5":
                    admin.updatePrice();
                    break;

                case "6":
                    admin.getOrderInfoByCid();
                    break;

                case "7":
                    admin.changeOrderStatus();
                    break;

                default:
                    System.out.println("Invalid input. Please try again!");
                    break;

            }
        }

    }

    static void placeOrder() {
        if (Member.currentUser.equals(null)) {
            System.out.println("You have not signed in!!!");
            return;
        }
        if (currentOrder != null) {
            System.out.println("You already place your order");
            return;
        }
        // currentOrder = OrderDatabase.createOrder(currentMember.getID());
        currentOrder = new Order(Member.currentUser.getCustomerId());
        System.out.println("Order succesfully started");
        return;
    }
    static void addProductToOrder() {
        if (currentOrder == null) {
            System.out.println("You don't have an order yet");
            return;
        }
        Product currentProduct = (new ProductDatabase()).selectedProduct();
        if (currentProduct == null) return;
        currentProduct.displayGeneralInfo();
        String numPattern = "[0-9]+";
        int userInput;

        if (currentProduct.getQuantity() == 0) {
            System.out.println("Sorry, we don't have that kind of product");
            return;
        }
        // Scanner input = new Scanner(System.in);

        System.out.println("Enter quantity");

        while (true) {
            String checkInput = sc.nextLine();

            if (!checkInput.matches(numPattern)) {
                System.out.println("Please enter a non-negative number: ");
                continue;
            }
            userInput = Integer.parseInt(checkInput);
            if (userInput == 0) {
                System.out.println("Hope you can find something else");
                return;
            }
            if (userInput <= currentProduct.getQuantity()) break;
            System.out.println("We don't have enough for your demand, please input again:");
        }
        System.out.println("Item succesfully added");
        currentOrder.addProduct(currentProduct, userInput);
        currentProduct = null;

    }
    static void payUpOrder() {
        if (currentOrder == null) {
            System.out.println("You don't have an order");
            return;
        }
        if (currentOrder.getSize() == 0) {
            System.out.println("You'r order is empty");
            return;
        }
        System.out.printf("Your total bill is %.2f \n", currentOrder.getBill());
        // store the total bill OR discount bill ?
        // no discount yet
        System.out.printf("Your total bill after getting a discount: %.2f \n", currentOrder.getBill());
        System.out.println("Do you REALLY want to pay now ? Y/N");
        // Scanner input = new Scanner(System.in);
        String userInput = sc.nextLine();
        if (userInput.equals("y")) {
            Member.currentUser.updateSpending(currentOrder.getBill());
            MemberDatabase.updateMemberDatabase();
            ProductDatabase.updateQuantity(currentOrder.boughtQuantity());
            ProductDatabase.updateProductDatabase();
            OrderDatabase.addNewOrder(currentOrder);
            OrderDatabase.updateOrderDatabase();
            System.out.println("Thank you for shopping with us =))");
            // Add some update
            return;
        }

    }
}
