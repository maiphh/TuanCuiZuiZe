import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin {
    private static final String userName = "admin";
    private static final String password = "admin";

    public static boolean loginAdmin() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String pass = sc.nextLine();


        if (userName.equals(username) && password.equals(pass)) {
            System.out.println("Log in Success");
            System.out.println("-".repeat(20));
            System.out.println("Hello Admin.");
            return true;

            }


        System.out.println("Wrong username or pass");
        return false;
    }
}
