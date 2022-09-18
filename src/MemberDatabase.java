import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberDatabase extends Database implements Manager {
    private static ArrayList<Member> list = new ArrayList<>();
    private static File file = new File("Customer.csv");
    private static boolean loaded = false;
    private static String header = "";
    private static int count = 0;

    MemberDatabase() {

    }

    static void updateMemberDatabase() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Customer.csv", false));
            out.write(header + "\n");
            for (Member member : list) {
                out.write(member.toString() + "\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public int getCount() {
        int result = 0;
        try {

            Scanner sc = new Scanner(file);
            header = sc.nextLine();
            // System.out.println("start");
            while (!sc.nextLine().equals(null)) {
                // System.out.println("between");
                result++;
                if (!sc.hasNext())
                    break;
            }
            // System.out.println("end");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        count = result;
        return count;

    }

    public void load() {
        if (loaded)
            return;
        try {

            Scanner sc = new Scanner(file);
            header = sc.nextLine();
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");

                Member newMember = new Member(data);
                // newMember.checkUpgrade(newMember);
                list.add(newMember);
                count++;
            }
            loaded = true;
            // lastIndex = list.size();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Member> extractToCache() {
        User currentUser = User.currentUser;
        ArrayList<Member> result = new ArrayList<>();
        for (Member member : list) {
            if (member.getCustomerId().equals(((Member) currentUser).getCustomerId())) {
                result.add(member);
            }
        }
        return result;

    }

    public void displayAll() {
        load();
        if (!this.checkCompatibility()) {
            return;
        }
        TableGenerator.printTable(list, header);
    }

    @Override
    public ArrayList<Member> getList() {
        load();
        return list;
    }

    @Override
    public String getHeader() {

        return header;
    }

    @Override
    public void loadFromCache() {
        ArrayList<Member> cacheList = (new PersonalMemberDatabase()).getList();
        Member cacheMember = cacheList.get(0);
        for (Member member : list) {
            if (cacheMember.getCustomerId().equals(member.getCustomerId())) {
                member = cacheMember;
            }

        }

    }

}
