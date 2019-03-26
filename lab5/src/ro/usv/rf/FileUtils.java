package ro.usv.rf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils
{
    public static List<String> readLearningSetFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
        List<String> lines = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
