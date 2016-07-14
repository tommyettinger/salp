package com.github.tommyettinger.util;

import java.util.Iterator;

/**
 * Static methods useful to be GWT-compatible, and also methods useful for filling gaps in Java's support for Iterable
 * types. There's a replacement for a Math method that isn't available on GWT, and a quick way to get the first element
 * in an Iterable.
 *
 * @author smelC
 * @author Tommy Ettinger
 */
public class Compatibility {

    /**
     * A replacement for Math.IEEEremainder, just because Math.IEEEremainder isn't GWT-compatible.
     * Gets the remainder of op / d, which can be negative if any parameter is negative.
     *
     * @param op the operand/dividend
     * @param d  the divisor
     * @return The remainder of {@code op / d}, as a double; can be negative
     */
    public static double IEEEremainder(double op, double d) {
        final double div = Math.round(op / d);
        return op - (div * d);
    }

    /**
     * Stupidly simple convenience method that produces a range from 0 to end, not including end, as an int array.
     * @param end the exclusive upper bound on the range
     * @return the range of ints as an int array
     */
    public static int[] range(int end)
    {
        if(end <= 0)
            return new int[0];
        int[] r = new int[end];
        for (int i = 0; i < end; i++) {
            r[i] = i;
        }
        return r;
    }

    /**
     * Stupidly simple convenience method that produces a range from start to end, not including end, as an int array.
     * @param start the inclusive lower bound on the range
     * @param end the exclusive upper bound on the range
     * @return the range of ints as an int array
     */
    public static int[] range(int start, int end)
    {
        if(end - start <= 0)
            return new int[0];
        int[] r = new int[end - start];
        for (int i = start; i < end; i++) {
            r[i] = i;
        }
        return r;
    }

    /**
     * Gets the first item in an Iterable of T, or null if it is empty. Meant for collections like LinkedHashSet, which
     * can promise a stable first element but don't provide a way to access it.
     *
     * @param collection an Iterable of T; if collection is null or empty this returns null
     * @param <T>        any object type
     * @return the first element in collection, or null if it is empty or null itself
     */
    public static <T> T first(Iterable<T> collection) {
        if (collection == null)
            return null;
        Iterator<T> it = collection.iterator();
        if (it.hasNext())
            return it.next();
        return null;
    }

}
