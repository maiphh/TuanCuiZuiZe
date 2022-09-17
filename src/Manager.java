import java.util.ArrayList;

public interface Manager {
    public ArrayList<?> extractToCache();

    public void loadFromCache();
}
