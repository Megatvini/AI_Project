import java.util.List;

/**
 * Created By Nika Doghonadze 1/17/2016.
 */
public class RBFKernel implements Kernel {
    @Override
    public double kernelFunction(List<Double> one, List<Double> two) {
        List<Double> minus = Utils.vectorMinus(one, two);
        double p = Utils.vectorProduct(minus, minus);
        return Math.exp(-p);
    }
}
