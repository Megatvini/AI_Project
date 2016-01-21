import java.util.ArrayList;
import java.util.List;

/**
 * Created By Nika Doghonadze 1/21/2016.
 */
public class SpecialDifference implements Difference {
    @Override
    public double diff(List<Double> one, List<Double> two) {
        List<Double> minus = Utils.vectorMinus(one, two);
        List<Double> m = new ArrayList<>();
        minus.forEach(x -> m.add(Math.pow(x, 2)));
        return 100 - Utils.vectorProduct(one, two);
    }
}
