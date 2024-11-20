package MVC.Service.LazySingleton.Status;

import MVC.Service.Enum.Status;

public class StatusManager {
    private static StatusManager instance;
    private Status currentStatus;

    private StatusManager() {
        this.currentStatus = Status.RELAX;
    }

    public static synchronized StatusManager getInstance() {
        if (instance == null) {
            instance = new StatusManager();
        }
        return instance;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status status) {
        if (status != null) {
            this.currentStatus = status;
        }
    }
}

