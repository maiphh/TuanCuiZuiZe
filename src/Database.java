import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

abstract public class Database {
    public abstract ArrayList<?> getList();

    public abstract String getHeader();

    public boolean checkCompatibility() {
        boolean result = false;
        HashMap<Class<?>, HashSet<Class<?>>> map = AccessMapping.getAccessMap();
        Class<?> userClass = User.currentUser.getClass();
        // System.out.println(this.getClass());
        for (Class<?> class1 : map.keySet()) {
            if (class1.isAssignableFrom(this.getClass())) {

                if (map.get(class1).contains(userClass)) {
                    // System.out.println(class1.getName());
                    // System.out.println("pass first check");
                    result = true;
                }

            }

        }

        return result;
    }

}
