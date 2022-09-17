import java.util.HashMap;
import java.util.HashSet;

public class AccessMapping {
    private static HashMap<Class<?>, HashSet<Class<?>>> map = new HashMap<>();

    AccessMapping() {
        HashSet<Class<?>> managerSet = new HashSet<>();
        HashSet<Class<?>> personalizedSet = new HashSet<>();
        HashSet<Class<?>> publicSet = new HashSet<>();
        managerSet.add(Admin.class);
        personalizedSet.add(Member.class);
        personalizedSet.addAll(managerSet);
        publicSet.add(Guest.class);
        publicSet.addAll(personalizedSet);

        map.put(Manager.class, managerSet);
        map.put(Personalized.class, personalizedSet);
        map.put(Public.class, publicSet);
        System.out.println(map);
    }

    public static HashMap<Class<?>, HashSet<Class<?>>> getAccessMap() {
        return map;
    }

}
