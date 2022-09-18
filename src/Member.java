import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Member extends User {
    private String customerId;
    private String userName;
    private String password;
    private String fullName;
    private String phoneNumber;
    private double spending;
    private double discount = 0;
    // static MemberDatabase md = new MemberDatabase();
    // private static int count = md.getCount();
    private static int count = (new MemberDatabase()).getCount();
    private static boolean loggedIn = false;
    public static Member currentUser = null;

    public Member() {

    }

    public Member(String userName, String password, String fullName, String phoneNumber) throws IOException {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.spending = 0;
        count++;
        customerId = "u" + count;

        File f = new File("Customer.csv");
        Scanner file = new Scanner(f);
        while (file.hasNextLine()) {
            String fileLine = file.nextLine();
            String[] fileLineArray = fileLine.split(",");
            if (fileLine != "") {
                if (fileLineArray[1].equals(userName)) {
                    System.out.println("User Name already exists!");
                    count--;
                    return;
                }
            }

        }
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        if(!(Pattern.compile(pattern).matcher(phoneNumber).matches())){
            System.out.println("Phone number has to have 10 digits!");
            count--;
            return;
        }


        System.out.println("Create successfully!");
        BufferedWriter out = new BufferedWriter(new FileWriter("Customer.csv", true));
        out.write(this.toString() + "\n");
        out.close();
    }

    public Member(String[] data) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        // Field[] f = this.getClass().getDeclaredFields();

        // for (int i = 0; i < data.length; i++) {
        // if (!(f[i].getType().equals(((Object) data[i]).getClass()))) {

        // Object wrapped = f[i].get(this);
        // Class<?> c = wrapped.getClass();

        // Method m = (c.getMethod("valueOf", ((Object) data[i]).getClass()));
        // Object j = m.invoke(null, data[i]);
        // f[i].set(this, j);
        // } else {
        // f[i].set(this, data[i]);
        // }
        // }
        this.customerId = data[0];
        this.userName = data[1];
        this.password = data[2];
        this.fullName = data[3];
        this.phoneNumber = data[4];
        this.spending = Double.parseDouble(data[5]);

    }

    public static Member createMember() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String pass = sc.nextLine();
        System.out.println("Full Name: ");
        String fullName = sc.nextLine();
        System.out.println("Phone number: ");

        String phoneNum = sc.nextLine();
        return new Member(username, pass, fullName, phoneNum);


    }

    public static boolean loginMember() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String pass = sc.nextLine();
        for (Member member : (new MemberDatabase()).getList()) {
            if (member.getUserName().equals(username) && member.getPassword().equals(pass)) {
                currentUser = member;
                System.out.println("Log in Success");
                System.out.println("-".repeat(20));
                System.out.println("Hello " + username + ".");
                loggedIn = true;
                return loggedIn;
            }
        }
        System.out.println("Wrong username or pass");
        return false;
    }

    public void displayInfo() throws FileNotFoundException {
        // File f = new File("Customer.csv");
        // Scanner file = new Scanner(f);
        // while (file.hasNextLine()) {
        // String fileLine = file.nextLine();
        // String[] fileLineArray = fileLine.split(",");
        // if (fileLine != "") {
        // if (fileLineArray[1].equals(currentUser.getUserName())) {
        // return "Member{" +
        // "customerId=" + fileLineArray[0] +
        // ", userName='" + fileLineArray[1] + '\'' +
        // ", password='" + fileLineArray[2] + '\'' +
        // ", fullName='" + fileLineArray[3] + '\'' +
        // ", phoneNumber=" + fileLineArray[4] +
        // ", spending=" + fileLineArray[5] +
        // '}';
        // }
        // }
        // }
        // return null;
        if (loggedIn) {
            (new PersonalMemberDatabase()).displayAll();
        }
    }

    public void viewAllProducts() {
        if (loggedIn) {
            (new ProductDatabase()).displayAll();
        }
    }

    public void searchProductByCategory() {
        if (loggedIn) {
            (new ProductDatabase()).displayByCategory();
            ;
        }
    }

    public void displayByPrice() throws FileNotFoundException {
        if (loggedIn) {
            (new ProductDatabase()).displayByPrice();
        }
    }

    public void viewOrderByID() {
        if (loggedIn) {
            (new PersonalOrderDatabase()).viewOrderByID();
        }
    }

    public void checkUpgrade(Member member) {
        if (this.spending > PlatinumMember.getSpendingThreshold()) {
            PlatinumMember platinumMember = new PlatinumMember(member);
            Member.currentUser = platinumMember;
            User.currentUser = platinumMember;

        } else if (this.spending > GoldMember.getSpendingThreshold()) {
            GoldMember goldMember = new GoldMember(member);
            Member.currentUser = goldMember;
            User.currentUser = goldMember;
        } else if (this.spending > SilverMember.getSpendingThreshold()) {
            SilverMember silverMember = new SilverMember(member);
            Member.currentUser = silverMember;
            User.currentUser = silverMember;
        }
    }

    // public void buy(Order order) {
    // this.spending = (this.getSpending() + order.getBill());
    // this.checkUpgrade(this);
    // }

    public double getDiscount() {
        return this.discount;
    }

    public void setSpending(double spending) {
        this.spending = spending;
    }

    public double getSpending() {
        return this.spending;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void register(String userName, String password) {

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void updateSpending(double amount) {
        spending += amount;
    }

    @Override
    public String toString() {
        return customerId + "," + userName + "," + password + "," + fullName + "," + phoneNumber + "," + spending;
    }
}
