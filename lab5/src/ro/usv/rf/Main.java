package ro.usv.rf;
import ro.usv.rf.FileUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            List<String> learningSet = FileUtils.readLearningSetFromFile("data.csv");
            //print(learningSet);
            nnRules(learningSet);
        } finally {
        System.out.println("Finished learning set operations");

        }
    }

    public static void print(List<String> learningSet) {
        for(int i=1;i<learningSet.size();i++) {

            System.out.println(learningSet.get(i));
        }
    }

    public static void nnRules(List<String> learningSet){
//        TreeMap<Double, String>;

        //  Distance = sqrt((x2−x1)2+(y2−y1)2)
        for(int i=0;i<learningSet.size();i++) {
            String[] latitude = learningSet.get(i).split(",");
            String[] longitude = learningSet.get(i).split(",");
            System.out.println(latitude[i]+" "+longitude[i]);

        }

    }

}
