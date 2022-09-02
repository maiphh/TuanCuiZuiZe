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
        ProductDatabase.loadProductDatabase();
        loginMenu();
    }

    public static void loginMenu() throws IOException {
        String input;
        while(true) {
        
            System.out.println("-".repeat(20));
            System.out.println(
                    "[1] Login As Guest.\n[2] Login As Member\n[3] Login As Admin\n[4] Register an account\n[0] Exit\nEnter a number: ");
            input = sc.nextLine();
            if (input.equals("0")) break;
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
                    // else
                    //     loginMenu();
                    // break;

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

    public static void guestMenu() {
        System.out.println("-".repeat(20));
        System.out.printf("[1] View all products.\n[2]\n[3] Sort product by price \nEnter a number: ");
        String input;
        while (true) {
            input = sc.nextLine();
            switch (input) {
                case "1":
                    break;
                case "2":

                    break;
                case "3":
                    ProductDatabase.displayByPrice();
                    break;

                case "4":

                    break;

                case "0":
                    break;

                default:
                    System.out.println("Invalid input. Please try again!");
                    guestMenu();
                    break;

            }
        }

    }

    public static void memberMenu() throws FileNotFoundException {
        System.out.println("[1] View all products.\n[2] View info\n[3] Start an order \nEnter a number: ");
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("0")) break;
            switch (input) {
                case "1":
                    break;
                case "2":
                    System.out.println(Member.displayInfo());
                    break;
                case "3":
                    placeOrder();
                    return;

                case "4":

                    break;
                case "5":
                default:
                    System.out.println("Invalid input. Please try again!");
                    guestMenu();
                    break;

            }
        }

    }

    public static void adminMenu() throws IOException {
        System.out.println("[1] View all products.\n[2]\nEnter a number: ");
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("0")) break;
            switch (input) {
                case "1":
                    break;
                case "2":

                    break;
                case "3":
                    Admin.addProduct();
                    adminMenu();
                    break;

                case "4":
                    Admin.getInfoByCid();
                    adminMenu();
                    break;

                case "0":
                    break;

                default:
                    System.out.println("Invalid input. Please try again!");
                    guestMenu();
                    break;

            }
        }

    }

    static void placeOrder() {
        if (currentOrder != null) {
            System.out.println("You already place your order");
            return;
        }
        // currentOrder = OrderDatabase.createOrder(currentMember.getID());
        currentOrder = new Order(currentMember.getCustomerId());
        System.out.println("Order succesfully place");
        return;
    }
}
