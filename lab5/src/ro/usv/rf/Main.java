package ro.usv.rf;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static ro.usv.rf.FileUtils.readFirstNNFile;

public class Main {
    public static void main(String[] args) throws IOException {
        try {

            List<String> learningSet = FileUtils.readLearningSetFromFile("data.csv");
            nnRules(learningSet);

        } finally {
        System.out.println("Finished learning set operations");
        }
    }

    private static void nnRules(List<String> learningSet){

         TreeMap<Double, String> firstTmap =  new TreeMap<>();
         TreeMap<Double, String> secondTmap =  new TreeMap<>();
         TreeMap<Double, String> thirdTmap =  new TreeMap<>();
        int[] knn_cases = new int[]{9,11,17,31};
        // cross every line of string
        for(int i=1;i<learningSet.size();i++) {
            String[] rows = learningSet.get(i).split("\n");
            //take the values from every column except CITY column
            for (int columnsLoop = 0; columnsLoop < rows.length; columnsLoop++) {

                String[] column = rows[columnsLoop].split(",");
                double latitude = Double.parseDouble(column[0]);
                double longitude = Double.parseDouble(column[1]);
                String city = (column[3]);
                //  Euclidian Distance = sqrt((x2−x1)^2+(y2−y1)^2)
                double euclidDistOne = Math.floor(Math.sqrt(Math.pow((latitude - 25.89), 2) + Math.pow((longitude- 47.56), 2)) * 100) / 100;
                double euclidDistTwo = Math.floor(Math.sqrt(Math.pow((latitude - 24), 2) + Math.pow((longitude - 45.15), 2)) * 100) / 100;
                double euclidDistThree = Math.floor(Math.sqrt(Math.pow((latitude - 25.33), 2) + Math.pow((longitude - 45.44), 2)) * 100) / 100;

                // Save all Distance in 3 different TREEMAP
                firstTmap.put(euclidDistOne, city);
                secondTmap.put(euclidDistTwo, city);
                thirdTmap.put(euclidDistThree, city);
            }
        }

        String[] values1 = firstTmap.values().toArray(new String[0]);
        String[] values2 = secondTmap.values().toArray(new String[0]);
        String[] values3 = thirdTmap.values().toArray(new String[0]);

        System.out.println("------------Set1----------------");
        for(int i=0;i<knn_cases.length;i++) {
            valuesSet(values1,knn_cases[i]);
        }
        System.out.println("------------Set2----------------");
        for(int i=0;i<knn_cases.length;i++) {
            valuesSet(values2,knn_cases[i]);
        }
        System.out.println("------------Set3----------------");
        for(int i=0;i<knn_cases.length;i++) {
            valuesSet(values3, knn_cases[i]);
        }
    }

    private static void valuesSet(String[] allValues,int knn_casses){
        //map for every case
        Map<String, Integer> countWithmap = new HashMap<>();
        //am lista
        List<String> listTmp = new ArrayList<>();
        for(int i=0;i<knn_casses;i++)
            listTmp.add(allValues[i]);
        //iau doar knn_casses
        List<String> al2 = new ArrayList<>(listTmp.subList(0, knn_casses));

        for (String temp : al2) {
            Integer count = countWithmap.get(temp);
            countWithmap.put(temp, (count == null) ? 1 : count + 1);
        }
        System.out.println("Knn: " + knn_casses + " apartine de clasa: " +
                Collections.max(countWithmap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
    }
}
