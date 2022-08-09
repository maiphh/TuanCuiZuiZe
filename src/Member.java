import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Member {
    private int customerId ;
    private String userName;
    private String password;
    private String fullName;
    private int phoneNumber;
    private int spending;
    private static int count = 0;

    private static String currentUser = null;

    public Member(){

    }

    public Member(String userName, String password, String fullName, int phoneNumber) throws IOException{
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.spending = 0;
        count++;
        customerId = count;


        File f = new File("Customer.csv");
        Scanner file = new Scanner(f);
        while (file.hasNextLine()) {
            String fileLine = file.nextLine();
            String[] fileLineArray = fileLine.split(",");
            if(fileLine!="") {
                if (fileLineArray[1].equals(userName)) {
                    System.out.println("User Name already exist!");
                    return;
                }
            }


        }

        System.out.println("Create successfully!");
        BufferedWriter out = new BufferedWriter(new FileWriter("Customer.csv", true));
        out.write(this.toString() +"\n");
        out.close();
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
        try {
            int phoneNum = sc.nextInt();
            sc.nextLine();
            return new Member(username,pass,fullName,phoneNum);
        }
        catch (Exception e) {
            System.out.println("Invalid input");
            createMember();
        }

        return null;
    }

    public static boolean loginMember() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String pass = sc.nextLine();

        File f = new File("Customer.csv");
        Scanner file = new Scanner(f);
        while (file.hasNextLine()) {
            String fileLine = file.nextLine();
            String[] fileLineArray = fileLine.split(",");
            if(fileLine!="") {
                if (fileLineArray[1].equals(username) && fileLineArray[2].equals(pass)) {
                    System.out.println("Log in Success");
                    System.out.println("-".repeat(20));
                    System.out.println("Hello "+username+".");
                    currentUser = username;
                    return true;
                }
            }

        }
        System.out.println("Wrong username or pass");
        return false;
    }

    public static String displayInfo() throws FileNotFoundException {
        File f = new File("Customer.csv");
        Scanner file = new Scanner(f);
        while (file.hasNextLine()) {
            String fileLine = file.nextLine();
            String[] fileLineArray = fileLine.split(",");
            if (fileLine != "") {
                if (fileLineArray[1].equals(currentUser)) {
                    return "Member{" +
                            "customerId=" + fileLineArray[0] +
                            ", userName='" + fileLineArray[1] + '\'' +
                            ", password='" + fileLineArray[2] + '\'' +
                            ", fullName='" + fileLineArray[3] + '\'' +
                            ", phoneNumber=" + fileLineArray[4] +
                            ", spending=" + fileLineArray[5] +
                            '}';
                }
            }
        }
        return null;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void register(String userName, String password) {

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return customerId+","+userName+","+password+","+fullName+","+phoneNumber+","+spending;
    }
}
