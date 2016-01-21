import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created By Nika Doghonadze 1/12/2016.
 */
public class DualPerceptron extends Classifier {
    protected List<Double> weights;
    protected int numIterations;

    public DualPerceptron() {
        super();
        numIterations = 3;
        weights = new ArrayList<>();
        for (int i=0; i<numFeatures; i++) weights.add(0.0);
    }

    @Override
    public void trainOnData(Collection<Entry> trainingData) {
        for (int i=0; i<numIterations; i++) {
            for (Entry entry : trainingData) {
                boolean value = entry.classValue;
                List<Double> features = extractFeatures(entry.data);
                boolean res = classifyEntry(features);
                if (res != value) {
                    if (res) {
                        weights = updateWeights(weights, features, -1);
                    } else {
                        weights = updateWeights(weights, features, 1);
                    }
                }
            }
        }
    }

    private List<Double> updateWeights(List<Double> weights, List<Double> features, int sign) {
        List<Double> newWeights = new ArrayList<>();
        for (int i=0; i<weights.size(); i++) {
            newWeights.add(weights.get(i) + sign * features.get(i));
        }
        return newWeights;
    }

    @Override
    public boolean classifyEntry(List<Double> features) {
        Double score = Utils.vectorProduct(weights, features);
        return score > 0;
    }
}
