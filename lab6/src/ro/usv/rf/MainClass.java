package ro.usv.rf;

import java.util.*;

public class MainClass {


    public static void main(String[] args) {
        String[][] learningSet;
        try {
            learningSet = FileUtils.readLearningSetFromFile("in.txt");
            int numberOfPatterns = learningSet.length;
            int numberOfFeatures = learningSet[0].length;
            System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));

            TreeMap<Double, String> initialMap =  new TreeMap<Double, String>();
            //read all data and save it into initialMap
            saveIntoTreemap(initialMap,numberOfPatterns,learningSet);

            double[] grades = {3.80,5.75,6.25, 7.25, 8.5};
            int[] knnCases ={1,3,5,7,9,13,17};

            //calculate euclidian for  initialMap with all Grades and save it in a map
            int i=0;
            while(i<grades.length){
                euclidianCalc(initialMap,grades[i],knnCases);
                i++;
            }

        } catch (USVInputFileCustomException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Finished learning set operations");
        }
    }

    private static void euclidianCalc(TreeMap <Double, String> initialMap, double grades,int[] knn_casses) {
        TreeMap <Double, String> euclidianResult = new TreeMap <Double, String>();
        for (Map.Entry <Double, String> entry : initialMap.entrySet()) {
                String value = entry.getValue();
                Double key = entry.getKey();
                euclidianResult.put((Math.floor(Math.sqrt(Math.pow((key - grades), 2)) * 100) / 100), value);
        }
        printResult(euclidianResult, grades, knn_casses);

    }
    private static void printResult(TreeMap<Double,String>euclidianResult,double grades,int[] knn_casses){
        System.out.println("---------------Set for Grades: " + grades + "-----------------");
        for (int knn_cass : knn_casses) {
            //map for every case
            Map <String, Integer> countWithmap = new HashMap <>();
            List <String> listTmpWithValues = new ArrayList <>();
            //convert map-Values into a String[]
            String[] arrValuesFromMap = new String[euclidianResult.values().size()];
            euclidianResult.values().toArray(arrValuesFromMap);

            //for every knn case we add in a list tmp value
            for (int j = 0; j<knn_cass;j++) {
                listTmpWithValues.add(arrValuesFromMap[j]);
            }

            //iau doar knn_casses
            List <String> al2 = new ArrayList <>(listTmpWithValues.subList(0, knn_cass));
            for (String temp : al2) {
                Integer count = countWithmap.get(temp);
                countWithmap.put(temp, (count == null) ? 1 : count + 1);
            }
            System.out.println("K: " + knn_cass + " apartine de clasa: " +
                    Collections.max(countWithmap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
        }
    }

    private static TreeMap<Double, String> saveIntoTreemap(TreeMap<Double, String> allData, int numberOfPatterns, String[][] learningSet){
        for(int i =0; i < numberOfPatterns; i++){
            allData.put(Double.valueOf(String.valueOf(learningSet[i][0])),String.valueOf(learningSet[i][1]));
        }
        return allData;
    }

}
