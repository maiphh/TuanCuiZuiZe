import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public Main() {
    }

    public static void main(String[] args) throws IOException {
        ProductDatabase.loadProductDatabase();
        loginMenu();
    }

    public static void loginMenu() throws IOException {
        System.out.println("-".repeat(20));
        System.out.println(
                "[1] Login As Guest.\n[2] Login As Member\n[3] Login As Admin\n[4] Register an account\n[0] Exit\nEnter a number: ");
        String input = sc.nextLine();
        switch (input) {
            case "1":
                guestMenu();
                break;
            case "2":
                if (Member.loginMember()) {
                    memberMenu();
                } else
                    loginMenu();

                break;
            case "3":
                if (Admin.loginAdmin()) {
                    adminMenu();
                } else
                    loginMenu();
                break;

            case "4":
                Member mem = Member.createMember();
                loginMenu();
                break;

            default:
                System.out.println("Invalid input. Please try again!");
                loginMenu();
                break;

        }
    }

    public static void guestMenu() {
        System.out.println("-".repeat(20));
        System.out.printf("[1] View all products.\n[2]\n[3] Sort product by price \nEnter a number: ");
        String input = sc.nextLine();
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

    public static void memberMenu() throws FileNotFoundException {
        System.out.println("[1] View all products.\n[2] View info\n[3] Start an order \nEnter a number: ");
        String input = sc.nextLine();
        switch (input) {
            case "1":
                break;
            case "2":
                System.out.println(Member.displayInfo());

                break;
            case "3":
                try {
                    Order.createOrder();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            case "4":

                break;

            default:
                System.out.println("Invalid input. Please try again!");
                guestMenu();
                break;

        }
    }

    public static void adminMenu() {
        System.out.println("[1] View all products.\n[2]\nEnter a number: ");
        String input = sc.nextLine();
        switch (input) {
            case "1":
                break;
            case "2":

                break;
            case "3":

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
