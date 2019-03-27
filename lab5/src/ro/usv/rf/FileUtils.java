package ro.usv.rf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static String[] readFirstNNFile(String fileName) throws IOException{
        Scanner sc = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<String>();
        int counter = 0;
        while (sc.hasNextLine() && counter < 31) {
            lines.add(sc.nextLine());
            counter++;
        }

        String[] arr = lines.toArray(new String[0]);
        return arr;
    }
}
