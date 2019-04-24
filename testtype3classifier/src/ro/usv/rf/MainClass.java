package ro.usv.rf;

import java.util.*;

public class MainClass {
    public static void main(String[] args){
        try {
            String[][] learningSet = FileUtils.readLearningSetFromFile("date.txt");

            // Get all clases
            Map<String, Integer> classes = distinctClassType(learningSet);
            double[][] wMat = getWMatrix(learningSet, classes);

            double[] feature = {4, 5};
            getThreeClasifier(learningSet, feature);

            System.out.println("Map size: " + classes.size());

        } catch(MyException e){
            e.printStackTrace();
        } finally{
            System.out.println("Executed!!!");
        }
    }

    private static double[][] getWMatrix(String[][] learningSet, Map<String, Integer> classes) {
        // Whithout the last element, which is the class name
        int nr_of_columns = learningSet[0].length - 1;
        int nr_of_classes = classes.size();

        double[][] w = new double[nr_of_classes+1][nr_of_columns+1]; // One column extra for the -1/2
        // One line extra because we are starting from 1 not from 0
        for ( double[] doubles : w ) {
            Arrays.fill(doubles, 0);
        }

        for ( String[] strings : learningSet ) {
            for ( int j = 0; j < nr_of_columns; j++ ) {
                Double value_to_be_added = Double.valueOf(strings[j]) / Double.valueOf(classes.get(strings[2]));
                String class_number = strings[2];
                w[Integer.parseInt(String.valueOf(class_number))][j] += value_to_be_added;
            }
        }

        for (int i = 1; i < w.length; i++)
        {
            for (int j =0; j < w[0].length-1; j++)
            {
                w[i][nr_of_columns] -= 1/2.0*(Math.pow(w[i][j], 2));
            }
        }
        return w;
    }

    private static Map<String,Integer> distinctClassType(String[][] learningSet) {
        Map<String, Integer> noDup = new HashMap <>();
        int valTmp=1;
        for ( String[] strings : learningSet ) {
            valTmp = 1;
            if ( noDup.containsKey(strings[2]) )
                noDup.put(strings[2], valTmp + 1);
            else
                noDup.put(strings[2], valTmp);
        }
        return noDup;
    }
    private static double getThreeClasifier(String[][] w, double[] feature)
    {
        Double min = Double.MAX_VALUE;
        int class_number = 0;
        System.out.println(w.length);
        for(int i=0; i < w.length; i++)
        {
            Double result = (double) 0;
            for(int j = 1; j < w[0].length; j++)
            {
                Double current_element = null;
                if(j >= feature.length)
                {
                    current_element = (double) 1;
                }
                else
                {
                    current_element = feature[j];
                }

                result += Double.valueOf(w[i][j])* current_element;
            }
            if(result <= min)
            {
                result = min;
                class_number = i+1; // classes start from 1
            }
        }
        return class_number;
    }

    public static double[][] getWArray(HashMap<Double, Integer> class_counter, double[][] learningSet) {

        double[][] w_array = new double[class_counter.size() + 1][class_counter.size()];

        for ( double[] doubles : w_array ) {
            Arrays.fill(doubles, 0);
        }

        for (int i = 0; i < learningSet.length; i++) {
            for (int j = 0; j < learningSet[0].length - 1; j++) {
                System.out.println("I= " + i + ", J= " + j);
                w_array[Double.valueOf(learningSet[i][learningSet[0].length - 1]).intValue()][j] += 1.0
                        * learningSet[i][j]
                        / class_counter.get(learningSet[i][learningSet[0].length - 1]);
            }
        }
        for (int i = 1; i < w_array.length; i++) {
            for (int j = 0; j < w_array[j].length - 1; j++) {
                w_array[i][w_array[j].length - 1] -= 0.5 * (Math.pow(w_array[i][j], 2));
            }
        }

        return w_array;
    }

    public static double get_class(double[][] w_matrix) {

        double[] x = { 4, 5, 1 };
        double MAX_value = Double.MIN_VALUE;
        double class_num = 0;
        for (int i = 1; i < w_matrix.length; i++) {
            double psi = 0;
            for (int j = 0; j < w_matrix[0].length; j++) {
                psi += w_matrix[i][j] * x[j];
            }
            if (MAX_value < psi) {
                MAX_value = psi;
                class_num = i;
            }
        }
        System.out.println("Clasa este: " + class_num);
        return 0;
    }
}
