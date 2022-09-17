import java.util.ArrayList;

public class PersonalMemberDatabase extends Database implements Personalized {
    private static Manager manager = new MemberDatabase();
    private static ArrayList<Member> list = new ArrayList<>();
    private static String header = ((Database) manager).getHeader();
    private static boolean loaded = false;

    public void load() {
        if (loaded) {
            return;
        }
        list = (ArrayList<Member>) manager.extractToCache();
        loaded = true;
    }

    public void displayAll() {
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

}
