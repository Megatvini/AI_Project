import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created By Nika Doghonadze 1/13/2016.
 */
public class Myra extends DualPerceptron {
    protected double c;

    public Myra() {
        super();
        c = 0.005;
    }

    @Override
    public void trainOnData(Collection<Entry> trainingData) {
        for (int i=0; i<numIterations; i++) {
            for (Entry entry : trainingData) {
                boolean value = entry.classValue;
                List<Double> features = extractFeatures(entry.data);
                boolean res = super.classifyEntry(features);
                double gamma;
                if (res != value) {
                    if (res) {
                        gamma = getGamma(weights, Utils.constantTimes(weights, -1), features, c);
                        weights = updateWeights(weights, features, gamma, -1);
                    } else {
                        gamma = getGamma(Utils.constantTimes(weights, -1), weights, features, c);
                        weights = updateWeights(weights, features, gamma, +1);
                    }
                }
            }
        }
    }

    private double getGamma(List<Double> guessedWeights, List<Double> correctWeights, List<Double> features, double c) {
        double featureSquare = Utils.vectorProduct(features, features);
        List<Double> minusVector = Utils.vectorMinus(guessedWeights, correctWeights);
        double product = Utils.vectorProduct(minusVector, features);
        return Math.min(c, (product+1)/(2*featureSquare));
    }


    private List<Double> updateWeights(List<Double> weights, List<Double> features, double gamma,  int sign) {
        List<Double> newWeights = new ArrayList<>();
        for (int i=0; i<weights.size(); i++) {
            newWeights.add(weights.get(i) + sign * features.get(i) * gamma);
        }
        return newWeights;
    }
}