package MVC.Service.ServiceImplenments.Log;

import MVC.Model.Data;
import MVC.Service.InterfaceService.Log.ReadLogServer;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

import java.util.ArrayList;
import java.util.List;


public class ReadLogServerImplementation implements ReadLogServer {

    private List<String> list;

    public ReadLogServerImplementation() {
        this.list = new ArrayList<>();
    }

    @Override
    public List<String> read(Data data) {
        list.clear();
        int linesToRead = 5;
        int startLine = data.getStartLine();
        int linesRead = 0;
        try (RandomAccessFile raf = new RandomAccessFile(Data.getFilePath(), "r")) {
            long fileLength = raf.length();
            long pointer = fileLength - 1;
            StringBuilder sb = new StringBuilder();
            boolean isLineEmpty = true;

            while (pointer >= 0 && linesRead < startLine + linesToRead) {
                raf.seek(pointer);
                char c = (char) raf.read();

                if (c == '\n' || pointer == 0) {
                    if (sb.length() > 0 || pointer == 0) {
                        if (pointer == 0 && c != '\n') {
                            sb.append(c);
                        }

                        if (linesRead >= startLine && linesRead < startLine + linesToRead) {
                            list.add(sb.reverse().toString().trim());
                        }

                        sb.setLength(0);
                        linesRead++;
                        isLineEmpty = true;
                    }
                } else {
                    sb.append(c);
                    isLineEmpty = false;
                }
                pointer--;
            }

            if (sb.length() > 0 && linesRead >= startLine && linesRead < startLine + linesToRead) {
                list.add(sb.reverse().toString().trim());
            }

            Collections.reverse(list);

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.setStartLine(startLine + linesToRead);

        return list;
    }
}

