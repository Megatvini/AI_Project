import java.util.*;

/**
 * Created By Nika Doghonadze 1/20/2016.
 */
public class KNNClassifier extends Classifier {
    private List<Entry> entries;
    private final int k;
    private Difference difference;

    public KNNClassifier(int k, Difference difference) {
        this.k = k;
        this.difference = difference;
    }

    @Override
    public void trainOnData(Collection<Entry> trainingData) {
        entries = new ArrayList<>();
        entries.addAll(trainingData);
    }

    @Override
    public boolean classifyEntry(List<Double> entry) {
        sortEntries(entry);
        List<Entry> subl = entries.subList(0, k);

        int t = 0;
        int f = 0;
        for (Entry aSubl : subl) {
            if (aSubl.classValue) {
                t++;
            } else {
                f++;
            }
        }

        return t >= f;
    }

    @Override
    public List<Double> extractFeatures(List<Double> entry) {
        List<Double> res = new ArrayList<>();
        res.addAll(entry);

        res.set(5, res.get(5)/10.0);
        res.set(6, res.get(6)/100.0);

        Utils.norm(res);

        if (res.size() != numFeatures) {
            throw new RuntimeException("wrong number of features: " + res.size());
        }
        return res;
    }

    private void sortEntries(List<Double> entry) {
        Collections.sort(entries, (o1, o2) -> {
            List<Double> features1 = extractFeatures(o1.data);
            List<Double> features2 = extractFeatures(o2.data);

            double d1 = difference.diff(features1, entry);
            double d2 = difference.diff(features2, entry);

            if (d1 < d2) {
                return -1;
            } else if (d1 == d2) {
                return 0;
            } else {
                return 1;
            }
        });
    }
}
