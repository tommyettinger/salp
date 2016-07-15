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
        printArray(s.at(2, __, 1, __));
        Shape s2 = new Shape(4, 4, 4);
        printArray(s2.at(1, 1, 1));
        printArray(s2.at(2, 2, 2));
    }
}
