import java.sql.SQLOutput;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;

		try {
			learningSet = FileUtils.readLearningSetFromFile("D:\\Facultate\\CALCULATOARE-IV\\SEMESTRUL II\\RF\\lab3\\lab3\\src\\in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;

			for(int count =1; count<numberOfPatterns; count++)
			{
				System.out.println("----------------------------- " + count + "----------------------------------");
				double euclidianDist = distUtils.euclidianDistance(learningSet[0],learningSet[count]);
				System.out.println(" EUCLIDIAN : " + count + " is " + euclidianDist);
				double mahalanoisDist = distUtils.mahalanobisDistance(learningSet[0],learningSet[count],numberOfFeatures);
				System.out.println(" MAHALONOBIS : " + count + " is " + mahalanoisDist);
				double cebisevDist = distUtils.cebisevDistance(learningSet[0],learningSet[count]);
				System.out.println(" CEBISEV: " + count + " is " + cebisevDist);
				double cityBlockDist = distUtils.cityBlockDistance(learningSet[0], learningSet[count]);
				System.out.println(" CITY BLOCK: " + count + " is " + cityBlockDist);
			}


			//System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
