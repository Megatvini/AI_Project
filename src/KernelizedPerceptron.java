import java.util.*;

/**
 * Created By Nika Doghonadze 1/17/2016.
 */
public class KernelizedPerceptron extends Classifier {
    private Kernel kernel;
    private List<Entry> alphas;

    public KernelizedPerceptron(Kernel kernel) {
        this.kernel = kernel;
        alphas = new ArrayList<>();
    }

    @Override
    public void trainOnData(Collection<Entry> trainingData) {
        int numIterations = 5;
        for (int i = 0; i < numIterations; i++) {
            for (Entry entry : trainingData) {
                boolean value = entry.classValue;
                List<Double> features = extractFeatures(entry.data);
                boolean res = classifyEntry(features);
                if (res != value) {
                    if (res) {
                        alphas.add(new Entry(features, false));
                    } else {
                        alphas.add(new Entry(features, true));
                    }
                }
            }
        }
    }

    @Override
    public boolean classifyEntry(List<Double> entryFeatures) {
        double score = 0;
        for (Entry entry : alphas) {
            int alpha = entry.classValue ? 1 : -1;
            score += alpha * kernel.kernelFunction(entry.data, entryFeatures);
        }
        return score > 0;
    }
}
