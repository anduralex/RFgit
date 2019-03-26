package ro.usv.rf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            double[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
            FileUtils.writeLearningSetToFile("out.csv",
                    normalizeLearningSet(learningSet));

        } finally {
            System.out.println("Finished learning set operations");

        }
    }
    private static double[][] normalizeLearningSet(double[][] learningSet) throws IOException {
        //write in a csv file
        BufferedWriter br = new BufferedWriter(new FileWriter("out.csv"));
        StringBuilder sb = new StringBuilder();

        double[][] normalizedLearningSet = new double[learningSet.length][];

        // Normalizata: xij = (xij-xjmin)/(xjmax-xjmin)
        for(int i=0;i<learningSet.length;i++){
            for(int j=0;j<learningSet[1].length;j++){
                sb.append((learningSet[i][j] - jmin(learningSet,i))/(jmax(learningSet,i)-jmin(learningSet,i)));
                sb.append(",");
            }
            sb.append("\n");
        }
        br.write(sb.toString());
        br.close();

        return normalizedLearningSet;
    }

    private static double jmin(double[][] learningSet, int column) {
        double min=0;
        for(int j=0;j<learningSet[1].length;j++){
                if(learningSet[column][j]< min)
                    min=learningSet[column][j];
            }
        return min;
    }

    private static double jmax(double[][] learningSet,int column) {
        double max=0;
        for(int j=0;j<learningSet[1].length;j++){
                if(learningSet[column][j] > max)
                    max = learningSet[column][j];
            }
        return max;
    }
}
