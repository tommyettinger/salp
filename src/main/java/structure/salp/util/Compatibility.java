package structure.salp.util;

import java.util.ArrayList;
import java.util.Collections;

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
     * Stupidly simple convenience method that produces a range from start to end, not including end, as an int array.
     * @param start the inclusive lower bound on the range
     * @param end the exclusive upper bound on the range
     * @param step the distance between ints in the range (doesn't apply to indices in the returned array)
     * @return the range of ints as an int array
     */
    public static int[] range(int start, int end, int step)
    {
        if(end - start <= 0 || step < 1)
            return new int[0];
        int[] r = new int[(end - start + step - 1) / step];
        for (int i = start, j = 0; i < end; i += step) {
            r[j++] = i;
        }
        return r;
    }

    /**
     * Stupidly simple convenience method that produces a range from start to end, not including end, as an int array.
     * @param basis the array to insert into; will be modified
     * @param insertAt the first position to modify in basis
     * @param start the inclusive lower bound on the range
     * @param end the exclusive upper bound on the range
     * @param step the distance between ints in the range (doesn't apply to indices of basis)
     * @return the range of ints as an int array, equivalent to the new value of basis
     */
    public static int[] rangeInto(int[] basis, int insertAt, int start, int end, int step)
    {
        if(basis == null || end - start <= 0 || step < 1)
            return basis;
        int insertStop = (end - start + step - 1) / step + insertAt;
        for (int i = start; i < end && insertAt < insertStop; i += step) {
            basis[insertAt++] = i;
        }
        return basis;
    }

    /**
     * Makes an ArrayList of T given an array or vararg of T elements.
     * @param elements an array or vararg of T
     * @param <T> just about any non-primitive type
     * @return a newly-allocated ArrayList containing all of elements, in order
     */
    @SafeVarargs
    public static <T> ArrayList<T> makeList(T... elements) {
        if(elements == null) return null;
        ArrayList<T> list = new ArrayList<>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }
}
