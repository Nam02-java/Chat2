package MVC.Service.LazySingleton.ID;

import MVC.Service.InterfaceService.File.ParseFile;
import MVC.Service.ServiceImplenments.File.ParseFileImplementation;

import java.io.File;

public class BiggestID {

    private ParseFile parseFile;

    private static BiggestID instance;
    private int biggestID;

    public BiggestID() {
        this.parseFile = new ParseFileImplementation();
    }

    public static synchronized BiggestID getInstance() {
        if (instance == null) {
            instance = new BiggestID();
        }
        return instance;
    }

    public int getBiggestID() {
        return biggestID;
    }

    public void setBiggestID(File file) {
        this.biggestID = parseFile.getBiggestID(file);
    }
}
