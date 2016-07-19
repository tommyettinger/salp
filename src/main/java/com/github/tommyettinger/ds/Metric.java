package com.github.tommyettinger.ds;

/**
 * Created by Tommy Ettinger on 7/18/2016.
 */
public interface Metric<T> {
    double distance(T left, T right);

    Metric<int[]> chebyshev = new Chebyshev();
    class Chebyshev implements Metric<int[]>
    {
        @Override
        public double distance(int[] left, int[] right) {
            if(left == null || right == null || left.length != right.length)
                return Double.MIN_VALUE;
            double distance = 0;
            for (int d = 0; d < left.length; d++) {
                distance = Math.max(distance, Math.abs(left[d] - right[d]));
            }
            return distance;
        }
    }

    Metric<int[]> manhattan = new Manhattan();
    class Manhattan implements Metric<int[]>
    {
        @Override
        public double distance(int[] left, int[] right) {
            if(left == null || right == null || left.length != right.length)
                return Double.MIN_VALUE;
            double distance = 0;
            for (int d = 0; d < left.length; d++) {
                distance += Math.abs(left[d] - right[d]);
            }
            return distance;
        }
    }

    Metric<int[]> euclidean = new Euclidean();
    class Euclidean implements Metric<int[]>
    {
        @Override
        public double distance(int[] left, int[] right) {
            if(left == null || right == null || left.length != right.length)
                return Double.MIN_VALUE;
            double distance = 0;
            for (int d = 0; d < left.length; d++) {
                distance += (left[d] - right[d]) * (left[d] - right[d]);
            }
            return Math.sqrt(distance);
        }
    }
}
