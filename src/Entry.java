import java.util.List;

/**
 * Created By Nika Doghonadze 1/18/2016.
 */
public class Entry {
    public final List<Double> data;
    public final boolean classValue;

    public Entry(List<Double> features, boolean classValue) {
        this.data = features;
        this.classValue = classValue;
    }

    @Override
    public String toString() {
        return data + " : " + classValue;
    }
}
