import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created By Nika Doghonadze 1/13/2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("starting to read data: ");
        List<List<Double>> negativeData = getDataFromFile("negative_train.txt", 1);
        List<List<Double>> positiveData = getDataFromFile("positive_train.txt", 1);
        List<List<Double>> negativeTest = getDataFromFile("negative_test.txt", 1);
        List<List<Double>> positiveTest = getDataFromFile("Positive_test.txt", 1);

        List<Entry> dt = new ArrayList<>();
        for (List<Double> aNegativeData : negativeData) {
            dt.add(new Entry(aNegativeData, false));
        }
        for (List<Double> aPositiveData : positiveData){
            dt.add(new Entry(aPositiveData, true));
        }

        List<Entry> testDt = new ArrayList<>();
        for (List<Double> aNegativeData : negativeTest) testDt.add(new Entry(aNegativeData, false));
        for (List<Double> aPositiveData : positiveTest) testDt.add(new Entry(aPositiveData, true));

        Classifier perceptron = new DualPerceptron();
        Classifier myra = new Myra();
        Classifier RBFKernelizedPerceptron = new KernelizedPerceptron(new RBFKernel());
        Classifier SquareKernelizedPerceptron = new KernelizedPerceptron(new SquareKernel());
        Classifier PolinomialKernelizedPerceptron = new KernelizedPerceptron(new PolynomialKernel());
        Classifier KNNClassifier = new KNNClassifier(9, new ScalarDifference());

        shuffle(dt);

        testClassifier(perceptron, dt, testDt, "Perceptron");
        testClassifier(myra, dt, testDt, "Myra");
        testClassifier(RBFKernelizedPerceptron, dt, testDt, "Kernelized Perceptron with RBF Kernel");
        testClassifier(SquareKernelizedPerceptron, dt, testDt, "Kernelized Perceptron with Square Kernel");
//        testClassifier(PolinomialKernelizedPerceptron, dt, testDt, "Kernelized Perceptron with Polynomial Kernel");
        testClassifier(KNNClassifier, dt, testDt, "9NN Classifier");
//        for (int i=3; i<20; i+=2) {
//            Classifier knnClassifier = new KNNClassifier(i, new ScalarDifference());
//            testClassifier(knnClassifier, dt, testDt, i+"NN Classifier: ");
//        }

    }

    private static void testClassifier(Classifier classifier, List<Entry> dt, List<Entry> testDt, String name) {
        long t = System.currentTimeMillis();
        classifier.trainOnData(dt);
        classifier.testOnData(testDt);
        System.out.println(name + ": ");
        classifier.printResults();
        System.out.println("time: " + (System.currentTimeMillis() - t)/1000 + "s");
        System.out.println();
    }

    private static void shuffle(List<Entry> dt) {
        for (int i=0; i<dt.size()/2; i+=2) {
            Entry entry1 = dt.get(i);
            Entry entry2 = dt.get(dt.size() - i - 1);
            dt.set(dt.size()- i - 1, entry1);
            dt.set(i, entry2);
        }
    }


    private static List<List<Double>> getDataFromFile(String fileName, int n) {
        System.out.println("Reading data from " + fileName);
        List<List<Double>> res = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        int numFeatures = 7;
        while (sc.hasNext()) {
            List<Double> list = new ArrayList<>();
            for (int i=0; i<numFeatures; i++) {
                double d = sc.nextDouble();
                list.add(d);
            }
            for (int i=0; i<n; i++){
                List<Double> arr = new ArrayList<>();
                arr.addAll(list);
                res.add(arr);
            }
        }
        sc.close();
        return res;
    }
}
