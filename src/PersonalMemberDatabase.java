import java.util.ArrayList;

public class PersonalMemberDatabase extends Database implements Personalized {
    private static Manager manager = new MemberDatabase();
    private static ArrayList<Member> list = new ArrayList<>();
    private static String header = ((Database) manager).getHeader();

    public void load() {
        list = (ArrayList<Member>) manager.extractToCache();

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

}
