package MVC.Service.ServiceImplenments.Log;

import MVC.Model.Data;
import MVC.Service.InterfaceService.File.ParseFile;
import MVC.Service.InterfaceService.Log.ConsoleLoggerServer;
import MVC.Service.LazySingleton.ID.IDManager;
import MVC.Service.ServiceImplenments.File.ParseFileImplementation;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleLoggerServerImplementation implements ConsoleLoggerServer {

    private ParseFile parseFile;

    public ConsoleLoggerServerImplementation() {
        this.parseFile = new ParseFileImplementation();
    }

    @Override
    public void save() throws FileNotFoundException {

        FileOutputStream logFile = new FileOutputStream(Data.getFilePath(), true);

        PrintStream consoleOut = System.out;

        PrintStream fileOut = new PrintStream(logFile);

        File file = new File(Data.getFilePath());

        PrintStream combinedOut = new PrintStream(new OutputStream() {
            private StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {

                char c = (char) b;

                buffer.append(c);

                if (c == '\n') {
                    String line = buffer.toString();

                    consoleOut.print(line);

                    if (!isSystemMessageConnected(line) &&
                            !isSystemMessageHistory(line) &&
                            !isSystemMessageRequestHistory(line)) {

                        fileOut.print(line);

                    }

                    buffer.setLength(0);
                }
            }
        });

        System.setOut(combinedOut);
    }

    private static boolean isSystemMessageConnected(String message) {
        return message.contains("New client connected");
    }

    private static boolean isSystemMessageHistory(String message) {
        return message.contains("Old message");
    }

    private static boolean isSystemMessageRequestHistory(String message) {
        return message.contains("- request history data");
    }
}


class kakaka {

    private int getLastIDFromFile(File file) {
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

    public static void main(String[] args) {
        kakaka k = new kakaka();
        File file = new File(Data.getFilePath());
        int a = k.getLastIDFromFile(file);
        System.out.println(a);

    }
}