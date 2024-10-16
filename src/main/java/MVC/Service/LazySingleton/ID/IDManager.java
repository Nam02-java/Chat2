package MVC.Service.LazySingleton.ID;



public class IDManager {
    private static IDManager instance;
    private int maxReceivedId;

    public IDManager() {
    }

    public static IDManager getInstance() {
        if (instance == null) {
            instance = new IDManager();
        }
        return instance;
    }

    public void updateMaxReceivedId(int id) {
        this.maxReceivedId = id;
    }

    public int getMaxReceivedId() {
        return maxReceivedId;
    }
}
