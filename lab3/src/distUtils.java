import static java.lang.Math.pow;

public class distUtils {
    public static double euclidianDistance (double[] m1,double[] m2)
    {
        double sum= 0;
        for(int i = 0 ; i < 2;i++)
            sum += pow((m1[i]-m2[i]),2);
        return Math.sqrt(sum);
    }
    public static double mahalanobisDistance (double[] m1,double[] m2, int nrCol)
    {
        double max = 0.0;
        for(int i = 0; i < 2; i++)
            max =  pow((m1[i]-m2[i]),nrCol);
        return Math.pow(max,(double)1/nrCol);
    }
    public static double cebisevDistance (double[]m1,double[] m2){
        double nrMax = 0.0;
        for(int i=0;i < 2;i++)
            if(Math.abs(m1[i] - m2[i])>nrMax)
                nrMax= Math.abs(m1[i] - m2[i]);
        return nrMax;
    }
    public static double cityBlockDistance (double[]m1,double[]m2){
        double sum = 0.0;
        for(int i=0;i<m1.length;i++) {
            sum += Math.abs(m1[i] - m2[i]);
        }
        return sum;
    }
}
