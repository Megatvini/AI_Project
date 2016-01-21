import java.util.List;

/**
 * Created By Nika Doghonadze 1/19/2016.
 */
public class PolynomialKernel implements Kernel{
    @Override
    public double kernelFunction(List<Double> one, List<Double> two) {
        double gamma = 0.29;
        double r = 0.8;
        double p = Utils.vectorProduct(one, two) * gamma + r;
        return Math.pow(p, 4);
    }
}
