package com.github.tommyettinger.util;

import java.io.Serializable;

/**
 * Simple hashing functions that we can rely on staying the same cross-platform.
 * These use the Fowler/Noll/Vo Hash (FNV-1a) algorithm, which is public domain.
 * The hashes this returns are always 0 when given null to hash. Arrays with identical
 * elements of identical types will hash identically. Arrays with identical numerical
 * values but different types will hash differently. There are faster hashes out there,
 * but many of them are intended to run on very modern desktop processors (not, say,
 * Android phone processors, and they don't need to worry about uncertain performance
 * on GWT regarding 64-bit math). We probably don't need to hash arrays or Strings so
 * often that this (still very high-performance!) hash would be a bottleneck.
 * <br>
 * Created by Tommy Ettinger on 1/16/2016.
 * @author Glenn Fowler
 * @author Phong Vo
 * @author Landon Curt Noll
 * @author Tommy Ettinger
 */
public class CrossHash {
    public static int hash(boolean[] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length, o = 0;
        for (int i = 0; i < len; i++) {
            o |= (data[i]) ? (1 << (i % 8)) : 0;
            if(i % 8 == 7 || i == len - 1) {
                h ^= o;
                h *= 16777619;
                o = 0;
            }
        }
        return h;
    }
    public static int hash(byte[] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= data[i];
            h *= 16777619;
        }
        return h;
    }
    public static int hash(char[] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= data[i] & 0xff;
            h *= 16777619;
            h ^= data[i] >>> 8;
            h *= 16777619;
        }
        return h;
    }
    public static int hash(short[] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= data[i] & 0xff;
            h *= 16777619;
            h ^= data[i] >>> 8;
            h *= 16777619;
        }
        return h;
    }
    public static int hash(int[] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= data[i] & 0xff;
            h *= 16777619;
            h ^= (data[i] >>> 8) & 0xff;
            h *= 16777619;
            h ^= (data[i] >>> 16) & 0xff;
            h *= 16777619;
            h ^= data[i] >>> 24;
            h *= 16777619;
        }
        return h;
    }
    public static int hash(long[] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= (int)(data[i] & 0xff);
            h *= 16777619;
            h ^= (int)((data[i] >>> 8) & 0xff);
            h *= 16777619;
            h ^= (int)((data[i] >>> 16) & 0xff);
            h *= 16777619;
            h ^= (int)((data[i] >>> 24) & 0xff);
            h *= 16777619;
            h ^= (int)((data[i] >>> 32) & 0xff);
            h *= 16777619;
            h ^= (int)((data[i] >>> 40) & 0xff);
            h *= 16777619;
            h ^= (int)((data[i] >>> 48) & 0xff);
            h *= 16777619;
            h ^= (int)(data[i] >>> 56);
            h *= 16777619;
        }
        return h;
    }

    public static int hash(float[] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length, t;
        for (int i = 0; i < len; i++) {
            t = Float.floatToIntBits(data[i]);
            h ^= t & 0xff;
            h *= 16777619;
            h ^= (t >>> 8) & 0xff;
            h *= 16777619;
            h ^= (t >>> 16) & 0xff;
            h *= 16777619;
            h ^= t >>> 24;
            h *= 16777619;
        }
        return h;
    }
    public static int hash(double[] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length;
        long t;
        for (int i = 0; i < len; i++) {
            t = Double.doubleToLongBits(data[i]);
            h ^= (int)(t & 0xff);
            h *= 16777619;
            h ^= (int)((t >>> 8) & 0xff);
            h *= 16777619;
            h ^= (int)((t >>> 16) & 0xff);
            h *= 16777619;
            h ^= (int)((t >>> 24) & 0xff);
            h *= 16777619;
            h ^= (int)((t >>> 32) & 0xff);
            h *= 16777619;
            h ^= (int)((t >>> 40) & 0xff);
            h *= 16777619;
            h ^= (int)((t >>> 48) & 0xff);
            h *= 16777619;
            h ^= (int)(t >>> 56);
            h *= 16777619;
        }
        return h;
    }
    public static int hash(String s)
    {
        if(s == null)
            return 0;
        return hash(s.toCharArray());
    }

    public static int hash(String[] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length, t;
        for (int i = 0; i < len; i++) {
            t = hash(data[i]);
            h ^= t & 0xff;
            h *= 16777619;
            h ^= (t >>> 8) & 0xff;
            h *= 16777619;
            h ^= (t >>> 16) & 0xff;
            h *= 16777619;
            h ^= t >>> 24;
            h *= 16777619;
        }
        return h;
    }

    public static int hash(char[][] data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length, t;
        for (int i = 0; i < len; i++) {
            t = hash(data[i]);
            h ^= t & 0xff;
            h *= 16777619;
            h ^= (t >>> 8) & 0xff;
            h *= 16777619;
            h ^= (t >>> 16) & 0xff;
            h *= 16777619;
            h ^= t >>> 24;
            h *= 16777619;
        }
        return h;
    }

    public static int hash(String[]... data)
    {
        if(data == null)
            return 0;
        int h = -2128831035, len = data.length, t;
        for (int i = 0; i < len; i++) {
            t = hash(data[i]);
            h ^= t & 0xff;
            h *= 16777619;
            h ^= (t >>> 8) & 0xff;
            h *= 16777619;
            h ^= (t >>> 16) & 0xff;
            h *= 16777619;
            h ^= t >>> 24;
            h *= 16777619;
        }
        return h;
    }
    public static long hash64(boolean[] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length, o = 0;
        for (int i = 0; i < len; i++) {
            o |= (data[i]) ? (1 << (i % 8)) : 0;
            if(i % 8 == 7 || i == len - 1) {
                h ^= o;
                h *= 1099511628211L;
                o = 0;
            }
        }
        return h;
    }
    public static long hash64(byte[] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= data[i];
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(char[] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= data[i] & 0xff;
            h *= 1099511628211L;
            h ^= data[i] >>> 8;
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(short[] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= data[i] & 0xff;
            h *= 1099511628211L;
            h ^= data[i] >>> 8;
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(int[] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= data[i] & 0xff;
            h *= 1099511628211L;
            h ^= (data[i] >>> 8) & 0xff;
            h *= 1099511628211L;
            h ^= (data[i] >>> 16) & 0xff;
            h *= 1099511628211L;
            h ^= data[i] >>> 24;
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(long[] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length;
        for (int i = 0; i < len; i++) {
            h ^= (data[i] & 0xff);
            h *= 1099511628211L;
            h ^= ((data[i] >>> 8) & 0xff);
            h *= 1099511628211L;
            h ^= ((data[i] >>> 16) & 0xff);
            h *= 1099511628211L;
            h ^= ((data[i] >>> 24) & 0xff);
            h *= 1099511628211L;
            h ^= ((data[i] >>> 32) & 0xff);
            h *= 1099511628211L;
            h ^= ((data[i] >>> 40) & 0xff);
            h *= 1099511628211L;
            h ^= ((data[i] >>> 48) & 0xff);
            h *= 1099511628211L;
            h ^= (data[i] >>> 56);
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(float[] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length, t;
        for (int i = 0; i < len; i++) {
            t = Float.floatToIntBits(data[i]);
            h ^= t & 0xff;
            h *= 1099511628211L;
            h ^= (t >>> 8) & 0xff;
            h *= 1099511628211L;
            h ^= (t >>> 16) & 0xff;
            h *= 1099511628211L;
            h ^= t >>> 24;
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(double[] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length, t;
        for (int i = 0; i < len; i++) {
            t = Double.doubleToLongBits(data[i]);
            h ^= (t & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 8) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 16) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 24) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 32) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 40) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 48) & 0xff);
            h *= 1099511628211L;
            h ^= (t >>> 56);
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(String s)
    {
        if(s == null)
            return 0;
        return hash64(s.toCharArray());
    }
    public static long hash64(String[] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length, t;
        for (int i = 0; i < len; i++) {
            t = hash64(data[i]);
            h ^= (t & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 8) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 16) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 24) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 32) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 40) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 48) & 0xff);
            h *= 1099511628211L;
            h ^= (t >>> 56);
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(char[][] data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length, t;
        for (int i = 0; i < len; i++) {
            t = hash64(data[i]);
            h ^= (t & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 8) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 16) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 24) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 32) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 40) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 48) & 0xff);
            h *= 1099511628211L;
            h ^= (t >>> 56);
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(Iterable<String> data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, t;
        for (String datum : data) {
            t = hash64(datum);
            h ^= (t & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 8) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 16) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 24) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 32) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 40) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 48) & 0xff);
            h *= 1099511628211L;
            h ^= (t >>> 56);
            h *= 1099511628211L;
        }
        return h;
    }
    public static long hash64(String[]... data)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length, t;
        for (int i = 0; i < len; i++) {
            t = hash64(data[i]);
            h ^= (t & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 8) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 16) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 24) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 32) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 40) & 0xff);
            h *= 1099511628211L;
            h ^= ((t >>> 48) & 0xff);
            h *= 1099511628211L;
            h ^= (t >>> 56);
            h *= 1099511628211L;
        }
        return h;
    }

    /**
     * Hashes only a subsection of the given data, starting at start (inclusive) and ending before end (exclusive).
     * @param data the char array to hash
     * @param start the start of the section to hash (inclusive)
     * @param end the end of the section to hash (exclusive)
     * @return
     */
    public static long hash64(char[] data, int start, int end)
    {
        if(data == null)
            return 0;
        long h = -3750763034362895579L, len = data.length;
        start %= len;
        end %= len;
        if(end <= start || start < 0 || end <= 0)
            return 0;
        for (int i = start; i < end; i++) {
            h ^= data[i] & 0xff;
            h *= 1099511628211L;
            h ^= data[i] >>> 8;
            h *= 1099511628211L;
        }
        return h;
    }

    public interface IHasher extends Serializable
    {
        int hash(Object data);
    }
    private static class BooleanHasher implements IHasher {
        BooleanHasher(){};
        public int hash(Object data)
        {
            return (data instanceof boolean[]) ? CrossHash.hash((boolean[]) data) : data.hashCode();
        }
    }
    public static final IHasher booleanHasher = new BooleanHasher();

    private static class ByteHasher implements IHasher {
        ByteHasher(){};
        public int hash(Object data)
        {
            return (data instanceof byte[]) ? CrossHash.hash((byte[]) data) : data.hashCode();
        }
    }
    public static final IHasher byteHasher = new ByteHasher();
    private static class ShortHasher implements IHasher {
        ShortHasher(){};
        public int hash(Object data)
        {
            return (data instanceof short[]) ? CrossHash.hash((short[]) data) : data.hashCode();
        }
    }
    public static final IHasher shortHasher = new ShortHasher();
    private static class CharHasher implements IHasher {
        CharHasher(){};
        public int hash(Object data)
        {
            return (data instanceof char[]) ? CrossHash.hash((char[]) data) : data.hashCode();
        }
    }
    public static final IHasher charHasher = new CharHasher();
    private static class IntHasher implements IHasher {
        IntHasher(){};
        public int hash(Object data)
        {
            return (data instanceof int[]) ? CrossHash.hash((int[]) data) : data.hashCode();
        }
    }
    public static final IHasher intHasher = new IntHasher();
    private static class LongHasher implements IHasher {
        LongHasher(){};
        public int hash(Object data)
        {
            return (data instanceof long[]) ? CrossHash.hash((long[]) data) : data.hashCode();
        }
    }
    public static final IHasher longHasher = new LongHasher();
    private static class FloatHasher implements IHasher {
        FloatHasher(){};
        public int hash(Object data)
        {
            return (data instanceof float[]) ? CrossHash.hash((float[]) data) : data.hashCode();
        }
    }
    public static final IHasher floatHasher = new FloatHasher();
    private static class DoubleHasher implements IHasher {
        DoubleHasher(){};
        public int hash(Object data)
        {
            return (data instanceof double[]) ? CrossHash.hash((double[]) data) : data.hashCode();
        }
    }
    public static final IHasher doubleHasher = new DoubleHasher();
    private static class Char2DHasher implements IHasher {
        Char2DHasher(){};
        public int hash(Object data)
        {
            return (data instanceof char[][]) ? CrossHash.hash((char[][]) data) : data.hashCode();
        }
    }
    public static final IHasher char2DHasher = new Char2DHasher();
    private static class StringHasher implements IHasher {
        StringHasher(){};
        public int hash(Object data)
        {
            return (data instanceof String) ? CrossHash.hash((String) data) : data.hashCode();
        }
    }
    public static final IHasher stringHasher = new StringHasher();
    private static class StringArrayHasher implements IHasher {
        StringArrayHasher(){};
        public int hash(Object data)
        {
            return (data instanceof String[]) ? CrossHash.hash((String[]) data) : data.hashCode();
        }
    }
    public static final IHasher stringArrayHasher = new StringArrayHasher();
    public static class DefaultHasher implements IHasher {
        public DefaultHasher(){};
        public int hash(Object data)
        {
            return data.hashCode();
        }
    }

}
