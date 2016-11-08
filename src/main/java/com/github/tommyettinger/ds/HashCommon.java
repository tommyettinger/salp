package com.github.tommyettinger.ds;

/**
 * Created by Tommy Ettinger on 7/19/2016.
 */
class HashCommon {

    private HashCommon() {
    }

    /**
     * This reference is used to fill keys and values of removed entries (if
     * they are objects). <code>null</code> cannot be used as it would confuse the
     * search algorithm in the presence of an actual <code>null</code> key.
     */
    public static final Object REMOVED = new Object();

    /**
     * 2<sup>32</sup> &middot; &phi;, &phi; = (&#x221A;5 &minus; 1)/2.
     */
    private static final int INT_PHI = 0x9E3779B9;
    /**
     * The reciprocal of {@link #INT_PHI} modulo 2<sup>32</sup>.
     */
    private static final int INV_INT_PHI = 0x144cbc89;
    /**
     * 2<sup>64</sup> &middot; &phi;, &phi; = (&#x221A;5 &minus; 1)/2.
     */
    private static final long LONG_PHI = 0x9E3779B97F4A7C15L;
    /**
     * The reciprocal of {@link #LONG_PHI} modulo 2<sup>64</sup>.
     */
    private static final long INV_LONG_PHI = 0xf1de83e19937733dL;

    /**
     * Avalanches the bits of an integer by applying the finalisation step of MurmurHash3.
     * <br>
     * <br>This method implements the finalisation step of Austin Appleby's <a href="http://code.google.com/p/smhasher/">MurmurHash3</a>.
     * Its purpose is to avalanche the bits of the argument to within 0.25% bias.
     *
     * @param x an integer.
     * @return a hash value with good avalanching properties.
     */
    static int murmurHash3(int x) {
        x ^= x >>> 16;
        x *= 0x85ebca6b;
        x ^= x >>> 13;
        x *= 0xc2b2ae35;
        x ^= x >>> 16;
        return x;
    }


    /**
     * Avalanches the bits of a long integer by applying the finalisation step of MurmurHash3.
     * <br>
     * <br>This method implements the finalisation step of Austin Appleby's <a href="http://code.google.com/p/smhasher/">MurmurHash3</a>.
     * Its purpose is to avalanche the bits of the argument to within 0.25% bias.
     *
     * @param x a long integer.
     * @return a hash value with good avalanching properties.
     */
    static long murmurHash3(long x) {
        x ^= x >>> 33;
        x *= 0xff51afd7ed558ccdL;
        x ^= x >>> 33;
        x *= 0xc4ceb9fe1a85ec53L;
        x ^= x >>> 33;
        return x;
    }

    /**
     * Quickly mixes the bits of an integer.
     * <br>This method mixes the bits of the argument by multiplying by the golden ratio and
     * xorshifting the result. It is borrowed from <a href="https://github.com/OpenHFT/Koloboke">Koloboke</a>, and
     * it has slightly worse behaviour than {@link #murmurHash3(int)} (in open-addressing hash tables the average number of probes
     * is slightly larger), but it's much faster.
     *
     * @param x an integer.
     * @return a hash value obtained by mixing the bits of {@code x}.
     * @see #invMix(int)
     */
    static int mix(final int x) {
        final int h = x * INT_PHI;
        return h ^ (h >>> 16);
    }

    /**
     * The inverse of {@link #mix(int)}. This method is mainly useful to create unit tests.
     *
     * @param x an integer.
     * @return a value that passed through {@link #mix(int)} would give {@code x}.
     */
    static int invMix(final int x) {
        return (x ^ x >>> 16) * INV_INT_PHI;
    }

    /**
     * Quickly mixes the bits of a long integer.
     * <br>This method mixes the bits of the argument by multiplying by the golden ratio and
     * xorshifting twice the result. It is borrowed from <a href="https://github.com/OpenHFT/Koloboke">Koloboke</a>, and
     * it has slightly worse behaviour than {@link #murmurHash3(long)} (in open-addressing hash tables the average number of probes
     * is slightly larger), but it's much faster.
     *
     * @param x a long integer.
     * @return a hash value obtained by mixing the bits of {@code x}.
     */
    static long mix(final long x) {
        long h = x * LONG_PHI;
        h ^= h >>> 32;
        return h ^ (h >>> 16);
    }

    /**
     * The inverse of {@link #mix(long)}. This method is mainly useful to create unit tests.
     *
     * @param x a long integer.
     * @return a value that passed through {@link #mix(long)} would give {@code x}.
     */
    static long invMix(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        return (x ^ x >>> 32) * INV_LONG_PHI;
    }

    /**
     * Return the least power of two greater than or equal to the specified value.
     * <br>Note that this function will return 1 when the argument is 0.
     *
     * @param x an integer smaller than or equal to 2<sup>30</sup>.
     * @return the least power of two greater than or equal to the specified value.
     */
    static int nextPowerOfTwo(int x) {
        if (x == 0) return 1;
        x--;
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        return (x | x >> 16) + 1;
    }

    /**
     * Return the least power of two greater than or equal to the specified value.
     * <br>Note that this function will return 1 when the argument is 0.
     *
     * @param x a long integer smaller than or equal to 2<sup>62</sup>.
     * @return the least power of two greater than or equal to the specified value.
     */
    static long nextPowerOfTwo(long x) {
        if (x == 0) return 1;
        x--;
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        return (x | x >> 32) + 1;
    }
}