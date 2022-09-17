import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberDatabase extends Database implements Manager {
    private static ArrayList<Member> list = new ArrayList<>();
    private static File file = new File("TuanCuiZuiZe/Customer.csv");
    private static boolean loaded = false;
    private static String header = "";

    public void load() {
        if (loaded)
            return;
        try {

            Scanner sc = new Scanner(file);
            header = sc.nextLine();
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");

                Member newMember = new Member(data);
                list.add(newMember);
            }
            loaded = true;

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
        if (!this.checkCompatibility()) {
            return;
        }
        TableGenerator.printTable(list, header);
    }

    @Override
    public ArrayList<Member> getList() {
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
