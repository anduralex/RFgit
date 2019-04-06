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

//            Map<Double,String> allData = new HashMap<Double, String>();
            TreeMap<Double, String> allData =  new TreeMap<Double, String>();


            for(int i =0; i < numberOfPatterns; i++){
                allData.put(Double.valueOf(String.valueOf(learningSet[i][0])),String.valueOf(learningSet[i][1]));
            }
            List<Double> grades = Arrays.asList(3.80,5.75,6.25, 7.25, 8.5);
            for (Map.Entry<Double,String> entry : allData.entrySet()) {
                for(Double g: grades){
                String value = entry.getValue();
                Double key = entry.getKey();
                System.out.println(key+" "+value);
                double euclidDist = Math.floor(Math.sqrt(Math.pow((key - g), 2)) * 100) / 100;
            }
            }

        } catch (USVInputFileCustomException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Finished learning set operations");
        }
    }

}
