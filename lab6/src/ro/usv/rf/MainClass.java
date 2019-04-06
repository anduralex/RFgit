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

            TreeMap<Double, String> allData =  new TreeMap<Double, String>();
            putDataIntoATreeMap(allData,numberOfPatterns,learningSet);


            double[] grades = {3.80,5.75,6.25, 7.25, 8.5};
            int[] knnCases ={1,3,5,7,9,13,17};
            TreeMap<Double, String> unu =  new TreeMap<Double, String>();
            TreeMap<Double, String> doi =  new TreeMap<Double, String>();
            TreeMap<Double, String> trei =  new TreeMap<Double, String>();
            TreeMap<Double, String> patru =  new TreeMap<Double, String>();
            TreeMap<Double, String> cinci =  new TreeMap<Double, String>();

            Map <Double,String> euclidianCalc = new HashMap<>();

            for(Double g: grades){
                 for (Map.Entry<Double,String> entry : allData.entrySet()) {
                    String value = entry.getValue();
                    Double key = entry.getKey();
                    if(g == 3.80)
                        unu.put((Math.floor(Math.sqrt(Math.pow((key - g), 2)) * 100) / 100),value);
                    else if (g == 5.75)
                        doi.put((Math.floor(Math.sqrt(Math.pow((key - g), 2)) * 100) / 100),value);
                    else if(g == 6.25)
                        trei.put((Math.floor(Math.sqrt(Math.pow((key - g), 2)) * 100) / 100),value);
                    else if(g == 7.25)
                        patru.put((Math.floor(Math.sqrt(Math.pow((key - g), 2)) * 100) / 100),value);
                    else
                        cinci.put((Math.floor(Math.sqrt(Math.pow((key - g), 2)) * 100) / 100),value);
                 }
            }
            for (Map.Entry<Double,String> entry : unu.entrySet())  {
                String value = entry.getValue();
                    Double key = entry.getKey();
                System.out.println(key+" "+ value);
            }


        } catch (USVInputFileCustomException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Finished learning set operations");
        }
    }

    private static TreeMap<Double, String> putDataIntoATreeMap (TreeMap<Double, String> allData, int numberOfPatterns,String[][] learningSet    ){
        for(int i =0; i < numberOfPatterns; i++){
            allData.put(Double.valueOf(String.valueOf(learningSet[i][0])),String.valueOf(learningSet[i][1]));
        }
        return allData;
    }

}
