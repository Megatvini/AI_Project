import java.util.ArrayList;
import java.util.List;

/**
 * Created By Nika Doghonadze 1/12/2016.
 */
public class Utils {
    public static double vectorProduct(List<Double> one, List<Double> two) {
        checkSameSizes(one, two);
        double res = 0;
        for (int i=0; i<one.size(); i++) {
            res += one.get(i) * two.get(i);
        }
        return res;
    }

    private static void checkSameSizes(List<Double> one, List<Double> two) {
        if (one.size() != two.size())
            throw new RuntimeException("vector operations must be done on same size vectors");
    }

    public static List<Double> vectorMinus(List<Double> one, List<Double> two) {
        checkSameSizes(one, two);
        List<Double> res = new ArrayList<>();
        for (int i=0; i<one.size(); i++) {
            res.add(one.get(i) - two.get(i));
        }
        return res;
    }

    public static List<Double> constantTimes(List<Double> vec, int c) {
        List<Double> res = new ArrayList<>();
        for (Double aVector : vec) res.add(aVector * c);
        return res;
    }

    public static double sumOfVector(List<Double> v) {
        double res = 0;
        for (Double d: v) {
            res += d;
        }
        return res;
    }

    public static void norm(List<Double> v) {
        double sum = sumOfVector(v);
        for (int i=0; i<v.size(); i++) {
            v.set(i, v.get(i)/sum);
        }
    }
}
