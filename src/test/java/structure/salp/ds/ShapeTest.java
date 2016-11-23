package structure.salp.ds;

import org.junit.Test;

import java.util.Arrays;

import static structure.salp.ds.Shape.__;

/**
 * Created by Tommy Ettinger on 7/14/2016.
 */
public class ShapeTest {
    public void printArray(int[] arr)
    {
        System.out.println(Arrays.toString(arr));
    }
    @Test
    public void testSimple()
    {
        Shape s = new Shape(3, 2, 4, 3);
        printArray(s.getDimensions());
        printArray(s.multipleAt(2, __, 1, __));
        Shape s2 = new Shape(4, 4, 4);
        System.out.println(s2.at(1, 1, 1));
        printArray(s2.multipleAt(1, 1, 1));
        System.out.println(s2.at(2, 2, 2));
        printArray(s2.multipleAt(2, 2, 2));
    }
}
