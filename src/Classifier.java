import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created By Nika Doghonadze 1/12/2016.
 */
public abstract class Classifier {
    private int nTriesNegative = 0;
    private int nSuccsNegative = 0;

    private int nTriesPositive = 0;
    private int nSuccsPositive = 0;

    protected final int numFeatures;

    public Classifier() {
        numFeatures = 7;
    }

    void testOnData(Collection<Entry> testData) {
        for (Entry entry : testData) {
            boolean value = entry.classValue;
            List<Double> features = extractFeatures(entry.data);
            boolean res = classifyEntry(features);
            if (value)
                nTriesPositive++;
            else
                nTriesNegative++;
            if (res == value) {
                if (value)
                    nSuccsPositive++;
                else
                    nSuccsNegative++;
            }
        }
    }

    public void printResults() {
        System.out.println("Success on Test Data: ");
        System.out.println("Positives test done ---- tries: " + nTriesPositive + ", errors: " + (nTriesPositive - nSuccsPositive) +
                " " + (double) nSuccsPositive/nTriesPositive);
        System.out.println("Negatives test done ---- tries: " + nTriesNegative + ", errors: " + (nTriesNegative - nSuccsNegative) +
                " " + (double) nSuccsNegative/nTriesNegative);
        System.out.println("Average success rate " + (getNegativeAccuracy() + getPositiveAccuracy())/2);
        System.out.println("Total Success rate " + ((double) nSuccsPositive + nSuccsNegative)/(nTriesNegative+nTriesPositive));
    }

    public List<Double> extractFeatures(List<Double> entry) {
        List<Double> res = new ArrayList<>();
        res.addAll(entry);
//
//        res.remove(res.size()-1);
//        res.remove(res.size()-1);
//
//        res.set(res.size()-2, res.get(5)/1.0);
//        res.set(res.size()-1, res.get(6)/10.0);

//        res.add(entry.get(0) > 0 ? 1.0 : -1.0);
//        res.add(entry.get(1) > 0 ? 1.0 : -1.0);
//        res.add(entry.get(2) > 0 ? 1.0 : -1.0);
//        res.add(entry.get(3) > 0 ? 1.0 : -1.0);
//        res.add(entry.get(4) > 0 ? 1.0 : -1.0);
//
//        for (int i=0; i<30; i++) {
//            if (entry.get(5) == i) {
//                res.add(1.0);
//            } else {
//                res.add(0.0);
//            }
//        }
//
//
//        for (int i=0; i<180; i++) {
//            if (entry.get(6) == i) {
//                res.add(1.0);
//            } else {
//                res.add(0.0);
//            }
//        }
//
//
//        Double sixth = entry.get(5);
//        for (int i=0; i<4; i++) {
//            if (sixth > i*10 && sixth < (i+1)*10) {
//                res.add(1.0);
//            } else {
//                res.add(0.0);
//            }
//        }
//
//
//        Double seventh = entry.get(6);
//        for (int i=0; i<19; i++) {
//            if (seventh > i*10 && seventh < (i+1)*10) {
//                res.add(1.0);
//            } else {
//                res.add(0.0);
//            }
//        }

        if (res.size() != numFeatures) {
            throw new RuntimeException("wrong number of features: " + res.size());
        }
        return res;
    }

    public double getPositiveAccuracy() {
        if (nTriesPositive == 0)
            throw new UnsupportedOperationException("Do training first");
        return (double) nSuccsPositive/nTriesPositive;
    }

    public double getNegativeAccuracy() {
        if (nTriesNegative == 0)
            throw new UnsupportedOperationException("Do training first");
        return (double) nSuccsNegative/nTriesNegative;
    }


    public double getTestAccuracy() {
        if (nTriesNegative + nTriesPositive == 0)
            throw new UnsupportedOperationException("Do Training first");
        return (nSuccsNegative + nSuccsPositive)/(double) (nTriesPositive + nTriesNegative);
    }


    public abstract void trainOnData(Collection<Entry> trainingData);
    public abstract boolean classifyEntry(List<Double> entry);
}
