package com.github.tommyettinger.ds;

import com.github.tommyettinger.util.Compatibility;

/**
 * Created by Tommy Ettinger on 7/14/2016.
 */
public class Shape {
    protected final int[] dimensions;
    public final int rank, max;
    public static final double __ = Double.POSITIVE_INFINITY;
    public Shape()
    {
        rank = 1;
        max = 0;
        dimensions = new int[1];
    }
    public Shape(int... dims) {
        if (dims == null || dims.length <= 0) {
            rank = 1;
            max = 0;
            dimensions = new int[1];
        }
        else {
            rank = dims.length;
            dimensions = new int[rank];
            System.arraycopy(dims, 0, dimensions, 0, rank);
            int m = dims[0];
            for (int i = 1; i < rank; i++) {
                m *= dims[i];
            }
            max = m;
        }
    }
    public int at(int... coordinates)
    {
        if(coordinates == null || coordinates.length != rank)
        {
            return -1;
        }
        int idx = 0, mul = 1;
        for (int i = 0; i < rank; i++) {
            idx += coordinates[i] * mul;
            mul *= dimensions[i];
        }
        return idx;
    }
    public int[] partialAt(double... coordinates)
    {
        if(coordinates == null || coordinates.length <= 0)
        {
            return Compatibility.range(max);
        }
        int sz = 1, i = 0, start = 0, end = max, step = 1, mul = 1, fixed = 0;
        for (; i < rank && i < coordinates.length; i++) {
            if(coordinates[i] == __)
                sz *= dimensions[i];
            else
                fixed += coordinates[i] * mul;
            mul *= dimensions[i];
        }
        for (; i < rank; i++) {
            sz *= dimensions[i];
        }
        int[] indices = new int[sz];
        int[][] cart = new int[rank][];
        mul = 1;
        for (int j = 0; j < rank; j++) {
            if(j >= coordinates.length || coordinates[j] == __)
            {
                cart[j] = Compatibility.range(0, dimensions[j] * mul, mul);
            }
            else {
                cart[j] = null;
            }
            mul *= dimensions[j];
        }
        int ctr = 0;
        for (int j = 0; j < rank; j++) {
            if(cart[j] != null)
            {
                if(ctr == 0)
                {
                    ctr = cart[j].length;
                    System.arraycopy(cart[j], 0, indices, 0, ctr);
                }
                else
                {
                    for (int k = cart[j].length - 1; k >= 0; k--) {
                        for (int l = 0; l < ctr; l++) {
                            indices[ctr * k + l] = cart[j][k] + indices[l];
                        }
                    }
                    ctr *= cart[j].length;
                }
            }
        }
        for (int n = 0; n < sz; n++) {
            indices[n] += fixed;
        }
        return indices;
    }

    public int[] getDimensions() {
        int[] copy = new int[rank];
        System.arraycopy(dimensions, 0, copy, 0, rank);
        return copy;
    }
}
