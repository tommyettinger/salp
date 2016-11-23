package structure.salp.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Static methods for various frequently-used operations on 1D and 2D arrays. Has methods for copying, inserting, and
 * filling 2D arrays of primitive types (char, int, double, and boolean). Has a few mehods for creating ranges of ints
 * or chars easily as 1D arrays. Also contains certain methods for working with orderings, which can be naturally used
 * with {@link structure.salp.ds.OrderedMap}, {@link structure.salp.ds.OrderedSet}, {@link structure.salp.ds.K2},
 * and similar ordered collections plus ArrayList using {@link #reorder(ArrayList, int...)} in this class.
 * Created by Tommy Ettinger on 11/17/2016.
 */
public class ArrayTools {

    private static final char[] letters = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'À', 'Á',
            'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý',
            'Þ', 'ß', 'à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', 'ø', 'ù',
            'ú', 'û', 'ü', 'ý', 'þ', 'ÿ', 'Ā', 'ā', 'Ă', 'ă', 'Ą', 'ą', 'Ć', 'ć', 'Ĉ', 'ĉ', 'Ċ', 'ċ', 'Č', 'č', 'Ď', 'ď', 'Đ', 'đ', 'Ē', 'ē', 'Ĕ',
            'ĕ', 'Ė', 'ė', 'Ę', 'ę', 'Ě', 'ě', 'Ĝ', 'ĝ', 'Ğ', 'ğ', 'Ġ', 'ġ', 'Ģ', 'ģ', 'Ĥ', 'ĥ', 'Ħ', 'ħ', 'Ĩ', 'ĩ', 'Ī', 'ī', 'Ĭ', 'ĭ', 'Į', 'į',
            'İ', 'ı', 'Ĵ', 'ĵ', 'Ķ', 'ķ', 'ĸ', 'Ĺ', 'ĺ', 'Ļ', 'ļ', 'Ľ', 'ľ', 'Ŀ', 'ŀ', 'Ł', 'ł', 'Ń', 'ń', 'Ņ', 'ņ', 'Ň', 'ň', 'ŉ', 'Ō', 'ō', 'Ŏ',
            'ŏ', 'Ő', 'ő', 'Œ', 'œ', 'Ŕ', 'ŕ', 'Ŗ', 'ŗ', 'Ř', 'ř', 'Ś', 'ś', 'Ŝ', 'ŝ', 'Ş', 'ş', 'Š', 'š', 'Ţ', 'ţ', 'Ť', 'ť', 'Ŧ', 'ŧ', 'Ũ', 'ũ',
            'Ū', 'ū', 'Ŭ', 'ŭ', 'Ů', 'ů', 'Ű', 'ű', 'Ų', 'ų', 'Ŵ', 'ŵ', 'Ŷ', 'ŷ', 'Ÿ', 'Ź', 'ź', 'Ż', 'ż', 'Ž', 'ž', 'Ǿ', 'ǿ', 'Ș', 'ș', 'Ț', 'ț',
            'Γ', 'Δ', 'Θ', 'Λ', 'Ξ', 'Π', 'Σ', 'Φ', 'Ψ', 'Ω', 'α', 'β', 'γ'};
    private static final char[] empty = new char[0];

    /**
     * Stupidly simple convenience method that produces a range from 0 to end, not including end, as an int array.
     *
     * @param end the exclusive upper bound on the range
     * @return the range of ints as an int array
     */
    public static int[] range(int end) {
        if (end <= 0)
            return new int[0];
        int[] r = new int[end];
        for (int i = 0; i < end; i++) {
            r[i] = i;
        }
        return r;
    }

    /**
     * Stupidly simple convenience method that produces a range from start to end, not including end, as an int array.
     *
     * @param start the inclusive lower bound on the range
     * @param end   the exclusive upper bound on the range
     * @return the range of ints as an int array
     */
    public static int[] range(int start, int end) {
        if (end - start <= 0)
            return new int[0];
        int[] r = new int[end - start];
        for (int i = 0, n = start; n < end; i++, n++) {
            r[i] = n;
        }
        return r;
    }

    /**
     * Stupidly simple convenience method that produces a char range from start to end, including end, as a char array.
     *
     * @param start the inclusive lower bound on the range, such as 'a'
     * @param end   the inclusive upper bound on the range, such as 'z'
     * @return the range of chars as a char array
     */
    public static char[] charSpan(char start, char end) {
        if (end - start <= 0)
            return empty;
        if (end == 0xffff) {

            char[] r = new char[0x10000 - start];
            for (char i = 0, n = start; n < end; i++, n++) {
                r[i] = n;
            }
            r[0xffff - start] = 0xffff;
            return r;
        }
        char[] r = new char[end - start + 1];
        for (char i = 0, n = start; n <= end; i++, n++) {
            r[i] = n;
        }
        return r;
    }

    /**
     * Stupidly simple convenience method that produces a char array containing only letters that can be reasonably
     * displayed (with SquidLib's default text display assets, at least). The letters are copied from a single source
     * of 256 chars; if you need more chars or you don't need pure letters, you can use {@link #charSpan(char, char)}.
     *
     * @param charCount the number of letters to return in an array; the maximum this will produce is 256
     * @return the range of letters as a char array
     */
    public static char[] letterSpan(int charCount) {
        if (charCount <= 0)
            return empty;
        char[] r = new char[Math.min(charCount, 256)];
        System.arraycopy(letters, 0, r, 0, r.length);
        return r;
    }

    /**
     * Gets a copy of the 2D char array, source, that has the same data but shares no references with source.
     *
     * @param source a 2D char array
     * @return a copy of source, or null if source is null
     */
    public static char[][] copy(char[][] source) {
        if (source == null)
            return null;
        if (source.length < 1)
            return new char[0][0];
        char[][] target = new char[source.length][source[0].length];
        for (int i = 0; i < source.length && i < target.length; i++) {
            System.arraycopy(source[i], 0, target[i], 0, source[i].length);
        }
        return target;
    }

    /**
     * Gets a copy of the 2D double array, source, that has the same data but shares no references with source.
     *
     * @param source a 2D double array
     * @return a copy of source, or null if source is null
     */
    public static double[][] copy(double[][] source) {
        if (source == null)
            return null;
        if (source.length < 1)
            return new double[0][0];
        double[][] target = new double[source.length][source[0].length];
        for (int i = 0; i < source.length && i < target.length; i++) {
            System.arraycopy(source[i], 0, target[i], 0, source[i].length);
        }
        return target;
    }

    /**
     * Gets a copy of the 2D int array, source, that has the same data but shares no references with source.
     *
     * @param source a 2D int array
     * @return a copy of source, or null if source is null
     */
    public static int[][] copy(int[][] source) {
        if (source == null)
            return null;
        if (source.length < 1)
            return new int[0][0];
        int[][] target = new int[source.length][source[0].length];
        for (int i = 0; i < source.length && i < target.length; i++) {
            System.arraycopy(source[i], 0, target[i], 0, source[i].length);
        }
        return target;
    }

    /**
     * Gets a copy of the 2D boolean array, source, that has the same data but shares no references with source.
     *
     * @param source a 2D boolean array
     * @return a copy of source, or null if source is null
     */
    public static boolean[][] copy(boolean[][] source) {
        if (source == null)
            return null;
        if (source.length < 1)
            return new boolean[0][0];
        boolean[][] target = new boolean[source.length][source[0].length];
        for (int i = 0; i < source.length && i < target.length; i++) {
            System.arraycopy(source[i], 0, target[i], 0, source[i].length);
        }
        return target;
    }

    /**
     * Inserts as much of source into target at the given x,y position as target can hold or source can supply.
     * Modifies target in-place and also returns target for chaining.
     * Used primarily to place a smaller array into a different position in a larger array, often freshly allocated.
     *
     * @param source a 2D char array that will be copied and inserted into target
     * @param target a 2D char array that will be modified by receiving as much of source as it can hold
     * @param x      the x position in target to receive the items from the first cell in source
     * @param y      the y position in target to receive the items from the first cell in source
     * @return a modified copy of target with source inserted into it at the given position
     */
    public static char[][] insert(char[][] source, char[][] target, int x, int y) {
        if (source == null || target == null)
            return target;
        if (source.length < 1 || source[0].length < 1)
            return copy(target);
        for (int i = 0; i < source.length && x + i < target.length; i++) {
            System.arraycopy(source[i], 0, target[x + i], y, Math.min(source[i].length, target[x + i].length - y));
        }
        return target;
    }

    /**
     * Inserts as much of source into target at the given x,y position as target can hold or source can supply.
     * Modifies target in-place and also returns target for chaining.
     * Used primarily to place a smaller array into a different position in a larger array, often freshly allocated.
     *
     * @param source a 2D double array that will be copied and inserted into target
     * @param target a 2D double array that will be modified by receiving as much of source as it can hold
     * @param x      the x position in target to receive the items from the first cell in source
     * @param y      the y position in target to receive the items from the first cell in source
     * @return a modified copy of target with source inserted into it at the given position
     */
    public static double[][] insert(double[][] source, double[][] target, int x, int y) {
        if (source == null || target == null)
            return target;
        if (source.length < 1 || source[0].length < 1)
            return copy(target);
        for (int i = 0; i < source.length && x + i < target.length; i++) {
            System.arraycopy(source[i], 0, target[x + i], y, Math.min(source[i].length, target[x + i].length - y));
        }
        return target;
    }

    /**
     * Inserts as much of source into target at the given x,y position as target can hold or source can supply.
     * Modifies target in-place and also returns target for chaining.
     * Used primarily to place a smaller array into a different position in a larger array, often freshly allocated.
     *
     * @param source a 2D int array that will be copied and inserted into target
     * @param target a 2D int array that will be modified by receiving as much of source as it can hold
     * @param x      the x position in target to receive the items from the first cell in source
     * @param y      the y position in target to receive the items from the first cell in source
     * @return a modified copy of target with source inserted into it at the given position
     */
    public static int[][] insert(int[][] source, int[][] target, int x, int y) {
        if (source == null || target == null)
            return target;
        if (source.length < 1 || source[0].length < 1)
            return copy(target);
        for (int i = 0; i < source.length && x + i < target.length; i++) {
            System.arraycopy(source[i], 0, target[x + i], y, Math.min(source[i].length, target[x + i].length - y));
        }
        return target;
    }

    /**
     * Inserts as much of source into target at the given x,y position as target can hold or source can supply.
     * Modifies target in-place and also returns target for chaining.
     * Used primarily to place a smaller array into a different position in a larger array, often freshly allocated.
     *
     * @param source a 2D boolean array that will be copied and inserted into target
     * @param target a 2D boolean array that will be modified by receiving as much of source as it can hold
     * @param x      the x position in target to receive the items from the first cell in source
     * @param y      the y position in target to receive the items from the first cell in source
     * @return a modified copy of target with source inserted into it at the given position
     */
    public static boolean[][] insert(boolean[][] source, boolean[][] target, int x, int y) {
        if (source == null || target == null)
            return target;
        if (source.length < 1 || source[0].length < 1)
            return copy(target);
        for (int i = 0; i < source.length && x + i < target.length; i++) {
            System.arraycopy(source[i], 0, target[x + i], y, Math.min(source[i].length, target[x + i].length - y));
        }
        return target;
    }

    /**
     * Creates a 2D array of the given width and height, filled with entirely with the value contents.
     * You may want to use {@link #fill(char[][], char)} to modify an existing 2D array instead.
     * @param contents the value to fill the array with
     * @param width    the desired width
     * @param height   the desired height
     * @return a freshly allocated 2D array of the requested dimensions, filled entirely with contents
     */
    public static char[][] fill(char contents, int width, int height) {
        char[][] next = new char[width][height];
        for (int x = 0; x < width; x++) {
            Arrays.fill(next[x], contents);
        }
        return next;
    }

    /**
     * Creates a 2D array of the given width and height, filled with entirely with the value contents.
     * You may want to use {@link #fill(double[][], double)} to modify an existing 2D array instead.
     * @param contents the value to fill the array with
     * @param width    the desired width
     * @param height   the desired height
     * @return a freshly allocated 2D array of the requested dimensions, filled entirely with contents
     */
    public static double[][] fill(double contents, int width, int height) {
        double[][] next = new double[width][height];
        for (int x = 0; x < width; x++) {
            Arrays.fill(next[x], contents);
        }
        return next;
    }

    /**
     * Creates a 2D array of the given width and height, filled with entirely with the value contents.
     * You may want to use {@link #fill(int[][], int)} to modify an existing 2D array instead.
     * @param contents the value to fill the array with
     * @param width    the desired width
     * @param height   the desired height
     * @return a freshly allocated 2D array of the requested dimensions, filled entirely with contents
     */
    public static int[][] fill(int contents, int width, int height) {
        int[][] next = new int[width][height];
        for (int x = 0; x < width; x++) {
            Arrays.fill(next[x], contents);
        }
        return next;
    }

    /**
     * Creates a 2D array of the given width and height, filled with entirely with the value contents.
     * You may want to use {@link #fill(boolean[][], boolean)} to modify an existing 2D array instead.
     * @param contents the value to fill the array with
     * @param width    the desired width
     * @param height   the desired height
     * @return a freshly allocated 2D array of the requested dimensions, filled entirely with contents
     */
    public static boolean[][] fill(boolean contents, int width, int height) {
        boolean[][] next = new boolean[width][height];
        if (contents) {
            for (int x = 0; x < width; x++) {
                Arrays.fill(next[x], true);
            }
        }
        return next;
    }
    /**
     * Fills {@code array2d} with {@code value}.
     * Not to be confused with {@link #fill(boolean, int, int)}, which makes a new 2D array.
     * @param array2d a 2D array that will be modified in-place
     * @param value the value to fill all of array2D with
     */
    public static void fill(boolean[][] array2d, boolean value) {
        final int width = array2d.length;
        final int height = width == 0 ? 0 : array2d[0].length;
        if(width > 0) {
            for (int i = 0; i < height; i++) {
                array2d[0][i] = value;
            }
        }
        for (int x = 1; x < width; x++) {
            System.arraycopy(array2d[0], 0, array2d[x], 0, height);
        }
    }

    /**
     * Fills {@code array2d} with {@code value}.
     * Not to be confused with {@link #fill(char, int, int)}, which makes a new 2D array.
     * @param array2d a 2D array that will be modified in-place
     * @param value the value to fill all of array2D with
     */
    public static void fill(char[][] array2d, char value) {
        final int width = array2d.length;
        final int height = width == 0 ? 0 : array2d[0].length;
        if(width > 0) {
            for (int i = 0; i < height; i++) {
                array2d[0][i] = value;
            }
        }
        for (int x = 1; x < width; x++) {
            System.arraycopy(array2d[0], 0, array2d[x], 0, height);
        }
    }

    /**
     * Fills {@code array2d} with {@code value}.
     * Not to be confused with {@link #fill(double, int, int)}, which makes a new 2D array.
     * @param array2d a 2D array that will be modified in-place
     * @param value the value to fill all of array2D with
     */
    public static void fill(double[][] array2d, double value) {
        final int width = array2d.length;
        final int height = width == 0 ? 0 : array2d[0].length;
        if(width > 0) {
            for (int i = 0; i < height; i++) {
                array2d[0][i] = value;
            }
        }
        for (int x = 1; x < width; x++) {
            System.arraycopy(array2d[0], 0, array2d[x], 0, height);
        }
    }

    /**
     * Fills {@code array2d} with {@code value}.
     * Not to be confused with {@link #fill(int, int, int)}, which makes a new 2D array.
     * @param array2d a 2D array that will be modified in-place
     * @param value the value to fill all of array2D with
     */
    public static void fill(int[][] array2d, int value) {
        final int width = array2d.length;
        final int height = width == 0 ? 0 : array2d[0].length;
        if(width > 0) {
            for (int i = 0; i < height; i++) {
                array2d[0][i] = value;
            }
        }
        for (int x = 1; x < width; x++) {
            System.arraycopy(array2d[0], 0, array2d[x], 0, height);
        }
    }

    /**
     * Rearranges an ArrayList to use the given ordering, returning a copy; random orderings can be produced with
     * {@link sarong.RNG#randomOrdering(int)} or
     * {@link sarong.RNG#randomOrdering(int, int[])}. These orderings will never repeat an earlier element,
     * and the returned ArrayList may be shorter than the original if {@code ordering} isn't as long as {@code list}.
     * Using a random ordering is like shuffling, but allows you to repeat the shuffle exactly on other collections of
     * the same size. A reordering can also be inverted with {@link #invertOrdering(int[])} or
     * {@link #invertOrdering(int[], int[])}, getting the change that will undo another ordering.
     *
     * @param list     an ArrayList that you want a reordered version of; will not be modified.
     * @param ordering an ordering, typically produced by one of RNG's randomOrdering methods.
     * @param <T>      any generic type
     * @return a modified copy of {@code list} with its ordering changed to match {@code ordering}.
     */
    public static <T> ArrayList<T> reorder(ArrayList<T> list, int... ordering) {
        int ol;
        if (list == null || ordering == null || (ol = Math.min(list.size(), ordering.length)) == 0)
            return list;
        ArrayList<T> alt = new ArrayList<T>(ol);
        for (int i = 0; i < ol; i++) {
            alt.add(list.get((ordering[i] % ol + ol) % ol));
        }
        return alt;
    }

    /**
     * Given an ordering such as one produced by {@link sarong.RNG#randomOrdering(int, int[])}, this finds
     * its inverse, able to reverse the reordering and vice versa.
     *
     * @param ordering the ordering to find the inverse for
     * @return the inverse of ordering
     */
    public static int[] invertOrdering(int[] ordering) {
        int ol = 0;
        if (ordering == null || (ol = ordering.length) == 0) return ordering;
        int[] next = new int[ol];
        for (int i = 0; i < ol; i++) {
            if (ordering[i] < 0 || ordering[i] >= ol) return next;
            next[ordering[i]] = i;
        }
        return next;
    }

    /**
     * Given an ordering such as one produced by {@link sarong.RNG#randomOrdering(int, int[])}, this finds
     * its inverse, able to reverse the reordering and vice versa. This overload doesn't allocate a new int
     * array, and instead relies on having an int array of the same size as ordering passed to it as an
     * additional argument.
     *
     * @param ordering the ordering to find the inverse for
     * @param dest     the int array to put the inverse reordering into; should have the same length as ordering
     * @return the inverse of ordering; will have the same value as dest
     */
    public static int[] invertOrdering(int[] ordering, int[] dest) {
        int ol = 0;
        if (ordering == null || dest == null || (ol = Math.min(ordering.length, dest.length)) == 0)
            return ordering;
        for (int i = 0; i < ol; i++) {
            if (ordering[i] < 0 || ordering[i] >= ol) return dest;
            dest[ordering[i]] = i;
        }
        return dest;
    }
}
