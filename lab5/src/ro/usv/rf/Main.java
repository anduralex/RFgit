package ro.usv.rf;

import java.io.*;
import java.util.*;

import static ro.usv.rf.FileUtils.readFirstNNFile;

public class Main {
    public static void main(String[] args) throws IOException {
        try {

            List<String> learningSet = FileUtils.readLearningSetFromFile("data.csv");
            nnRules(learningSet);
            String[] Set1 = readFirstNNFile("file1.txt");
            String[] Set2 = readFirstNNFile("file2.txt");
            String[] Set3 = readFirstNNFile("file3.txt");
            System.out.println("FIRST SET");
            valuesSet(Set1);
            System.out.println("SECOND SET");
            valuesSet(Set2);
            System.out.println("THIRD SET");
            valuesSet(Set3);


        } finally {
        System.out.println("Finished learning set operations");
        }
    }

    private static void nnRules(List<String> learningSet){

         TreeMap<Double, String> firstTmap =  new TreeMap<Double, String>();
         TreeMap<Double, String> secondTmap =  new TreeMap<Double, String>();
         TreeMap<Double, String> thirdTmap =  new TreeMap<Double, String>();
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


                Map<Double,String> temporary1 = sortByKeysOne(firstTmap);
                Map<Double,String> temporary2 = sortByKeysTwo(secondTmap);
                Map<Double,String> temporary3 = sortByKeysThree(thirdTmap);






                //Write sorted values in a file
                try{
                    File file=new File("file1.txt");
                    FileOutputStream fos=new FileOutputStream(file);
                    PrintWriter pw=new PrintWriter(fos);

                    for(Map.Entry<Double,String> m :temporary1.entrySet()){
                        pw.println(m.getValue());
                    }
                    pw.flush();
                    pw.close();
                    fos.close();
                }catch(Exception e){
                    System.out.println("Can't write in file1!!");
                }
                try{

                    File file=new File("file2.txt");
                    FileOutputStream fos=new FileOutputStream(file);
                    PrintWriter pw=new PrintWriter(fos);

                    for(Map.Entry<Double,String> m :temporary2.entrySet()){
                        pw.println(m.getValue());
                    }
                    pw.flush();
                    pw.close();
                    fos.close();
                }catch(Exception e){
                    System.out.println("Can't write in file2!!");
                }
                try{

                    File file=new File("file3.txt");
                    FileOutputStream fos=new FileOutputStream(file);
                    PrintWriter pw=new PrintWriter(fos);

                    for(Map.Entry<Double,String> m :temporary3.entrySet()){
                        pw.println(m.getValue());
                    }
                    pw.flush();
                    pw.close();
                    fos.close();
                }catch(Exception e) {
                    System.out.println("Can't write in file3!!");
                }

            }
        }
    }

    private static <K extends Comparable, V> TreeMap <K,V> sortByKeysOne(TreeMap<K, V> firstCity) {
        return new TreeMap<>(firstCity);
    }
    private static <K extends Comparable, V> TreeMap <K,V> sortByKeysTwo(TreeMap<K, V> secondCity) {
        return new TreeMap<>(secondCity);
    }
    private static <K extends Comparable, V> TreeMap <K,V> sortByKeysThree(TreeMap<K, V> thirdCity) {
        return new TreeMap<>(thirdCity);
    }
    private static void valuesSet(String[] Set){
        int[] knn_cases = new int[]{9,11,17,31};
        //map for every case
        Map<String,Integer> nrOfRepsCase1 = new HashMap<String,Integer>();
        Map<String,Integer> nrOfRepsCase2 = new HashMap<String,Integer>();
        Map<String,Integer> nrOfRepsCase3 = new HashMap<String,Integer>();
        Map<String,Integer> nrOfRepsCase4 = new HashMap<String,Integer>();
        int i=1;
        for(String str : Set){

            if(nrOfRepsCase1.containsKey(str) && i<=9) {
                nrOfRepsCase1.put(str,nrOfRepsCase1.get(str) + 1);
            }
            else {
                nrOfRepsCase1.put(str, 1);

            }

            if(nrOfRepsCase2.containsKey(str) && i<=11) {
                nrOfRepsCase2.put(str,nrOfRepsCase2.get(str) + 1);
            }
            else {
                nrOfRepsCase2.put(str, 1);
            }

            if(nrOfRepsCase3.containsKey(str) && i<=17) {
                nrOfRepsCase3.put(str,nrOfRepsCase3.get(str) + 1);
            }
            else {
                nrOfRepsCase3.put(str, 1);
            }

            if(nrOfRepsCase4.containsKey(str) && i<=31) {
                nrOfRepsCase4.put(str,nrOfRepsCase4.get(str) + 1);
            }
            else {
                nrOfRepsCase4.put(str, 1);
            }

            i++;
        }

        for (Map.Entry<String, Integer> entry : nrOfRepsCase4.entrySet()) {
            System.out.println(entry.getValue() + "  " + entry.getKey());

        }

        int max=0;
        String finalValMax= null;

        for (Map.Entry<String, Integer> entry : nrOfRepsCase1.entrySet()) {
            if(entry.getValue()>max) {
                finalValMax = entry.getKey();
                max = entry.getValue();
            }
        }
        System.out.println(" k =  " + knn_cases[0] + " has class: " + finalValMax);
        finalValMax= null;
        max=0;

        for (Map.Entry<String, Integer> entry : nrOfRepsCase2.entrySet()) {
            if(entry.getValue()>max) {
                finalValMax = entry.getKey();
                max = entry.getValue();
            }
        }
        System.out.println(" k =  " + knn_cases[1] + " has class: " + finalValMax);
        finalValMax= null;
        max=0;

        for (Map.Entry<String, Integer> entry : nrOfRepsCase3.entrySet()) {
            if(entry.getValue()>max) {
                finalValMax = entry.getKey();
                max = entry.getValue();
            }
        }
        System.out.println(" k =  " + knn_cases[2] + " has class: " + finalValMax);
        finalValMax = null;
        max=0;

        for (Map.Entry<String, Integer> entry : nrOfRepsCase4.entrySet()) {
            if(entry.getValue()>max) {
                finalValMax = entry.getKey();
                max = entry.getValue();
            }
        }
        System.out.println(" k =  " + knn_cases[3] + " has class: " + finalValMax);
    }

}
