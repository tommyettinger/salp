package structure.salp.ds;

/**
 * Created by Tommy Ettinger on 7/15/2016.
 */
public interface NearIndexer<I> {
    int[] near(Metric<I> metric, double tolerance, I index);
}
