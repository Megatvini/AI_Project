import java.util.List;

/**
 * Created By Nika Doghonadze 1/21/2016.
 */
public class ScalarDifference implements Difference {
    @Override
    public double diff(List<Double> one, List<Double> two) {
        List<Double> minus = Utils.vectorMinus(one, two);
        return Utils.vectorProduct(minus, minus);
    }
}
