package MVC.Service.ServiceImplenments.File;

import MVC.Service.InterfaceService.File.ParseFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseFileImplementation implements ParseFile {
    @Override
    public int getBiggestID(File file) {
        int lastID = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String lastLine = null;

            while ((line = br.readLine()) != null) {
                lastLine = line;
            }

            if (lastLine != null) {
                Pattern pattern = Pattern.compile("^\\d+");
                Matcher matcher = pattern.matcher(lastLine);

                if (matcher.find()) {
                    lastID = Integer.parseInt(matcher.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastID;
    }
}
