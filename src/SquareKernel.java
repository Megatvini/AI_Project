import java.util.List;

/**
 * Created By Nika Doghonadze 1/17/2016.
 */
public class SquareKernel implements Kernel {
    @Override
    public double kernelFunction(List<Double> one, List<Double> two) {
        double p = Utils.vectorProduct(one, two);
        return (p+1)*(p+1);
    }
}
