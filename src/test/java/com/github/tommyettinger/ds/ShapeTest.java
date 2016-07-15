package com.github.tommyettinger.ds;

import org.junit.Test;

import java.util.Arrays;

import static com.github.tommyettinger.ds.Shape.__;

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
        printArray(s.partialAt(2, __, 1, __));
    }
}
