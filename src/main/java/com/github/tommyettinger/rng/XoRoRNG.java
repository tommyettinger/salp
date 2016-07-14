/*  Written in 2016 by David Blackman and Sebastiano Vigna (vigna@acm.org)

To the extent possible under law, the author has dedicated all copyright
and related and neighboring rights to this software to the public domain
worldwide. This software is distributed without any warranty.

See <http://creativecommons.org/publicdomain/zero/1.0/>. */
package com.github.tommyettinger.rng;

import com.github.tommyettinger.util.StringKit;


/**
 * A port of Blackman and Vigna's xoroshiro 128+ generator; should be very fast and produce high-quality output.
 * Testing shows it is within 5% the speed of LightRNG, sometimes faster and sometimes slower, and has a larger period.
 * It's called XoRo because it involves Xor as well as Rotate operations on the 128-bit pseudo-random state.
 * <br>
 * Machines without access to efficient bitwise rotation (such as all desktop JREs, and some JDKs, run with the
 * {@code -client} flag or that default to the client VM, which includes practically all 32-bit Windows JREs) may
 * benefit from using XorRNG over XoRoRNG. LightRNG should continue to be very fast, but has a significantly shorter
 * period (the amount of random numbers it will go through before repeating), at {@code pow(2, 64)} as opposed to
 * XorRNG and XoRoRNG's {@code pow(2, 128)}, but LightRNG also allows the current RNG state to be retrieved and altered
 * with {@code getState()} and {@code setState()}. For most cases, you should decide between LightRNG and XoRoRNG based
 * on your needs for period length and state manipulation (LightRNG is also used internally by all StatefulRNG objects).
 * <br>
 * Original version at http://xoroshiro.di.unimi.it/xoroshiro128plus.c
 * Written in 2016 by David Blackman and Sebastiano Vigna (vigna@acm.org)
 *
 * @author Sebastiano Vigna
 * @author David Blackman
 * @author Tommy Ettinger
 */
public class XoRoRNG implements RandomnessSource {

    private static final long DOUBLE_MASK = (1L << 53) - 1;
    private static final double NORM_53 = 1. / (1L << 53);
    private static final long FLOAT_MASK = (1L << 24) - 1;
    private static final double NORM_24 = 1. / (1L << 24);

    private static final long serialVersionUID = 1018744536171610261L;

    private long state0, state1;

    /**
     * Creates a new generator seeded using Math.random.
     */
    public XoRoRNG() {
        this((long) (Math.random() * Long.MAX_VALUE));
    }

    public XoRoRNG(final long seed) {
        setSeed(seed);
    }

    @Override
    public int next(int bits) {
        return (int) (nextLong() & (1L << bits) - 1);
    }

    @Override
    public long nextLong() {
        final long s0 = state0;
        long s1 = state1;
        final long result = s0 + s1;

        s1 ^= s0;
        state0 = Long.rotateLeft(s0, 55) ^ s1 ^ (s1 << 14); // a, b
        state1 = Long.rotateLeft(s1, 36); // c

        return result;
    }

    /**
     * Produces a copy of this RandomnessSource that, if next() and/or nextLong() are called on this object and the
     * copy, both will generate the same sequence of random numbers from the point copy() was called. This just need to
     * copy the state so it isn't shared, usually, and produce a new value with the same exact state.
     *
     * @return a copy of this RandomnessSource
     */
    @Override
    public RandomnessSource copy() {
        XoRoRNG next = new XoRoRNG(state0);
        next.state0 = state0;
        next.state1 = state1;
        return next;
    }


    /**
     * Can return any int, positive or negative, of any size permissible in a 32-bit signed integer.
     *
     * @return any int, all 32 bits are random
     */
    public int nextInt() {
        return (int) nextLong();
    }

    /**
     * Exclusive on the upper bound.  The lower bound is 0.
     *
     * @param bound the upper bound; should be positive
     * @return a random int less than n and at least equal to 0
     */
    public int nextInt(final int bound) {
        if (bound <= 0) return 0;
        int threshold = (0x7fffffff - bound + 1) % bound;
        for (; ; ) {
            int bits = (int) (nextLong() & 0x7fffffff);
            if (bits >= threshold)
                return bits % bound;
        }
    }

    /**
     * Inclusive lower, exclusive upper.
     *
     * @param lower the lower bound, inclusive, can be positive or negative
     * @param upper the upper bound, exclusive, should be positive, must be greater than lower
     * @return a random int at least equal to lower and less than upper
     */
    public int nextInt(final int lower, final int upper) {
        if (upper - lower <= 0) throw new IllegalArgumentException("Upper bound must be greater than lower bound");
        return lower + nextInt(upper - lower);
    }

    /**
     * Exclusive on the upper bound. The lower bound is 0.
     *
     * @param bound the upper bound; should be positive
     * @return a random long less than n
     */
    public long nextLong(final long bound) {
        if (bound <= 0) return 0;
        long threshold = (0x7fffffffffffffffL - bound + 1) % bound;
        for (; ; ) {
            long bits = nextLong() & 0x7fffffffffffffffL;
            if (bits >= threshold)
                return bits % bound;
        }
    }

    public double nextDouble() {
        return (nextLong() & DOUBLE_MASK) * NORM_53;
    }

    public float nextFloat() {
        return (float) ((nextLong() & FLOAT_MASK) * NORM_24);
    }

    public boolean nextBoolean() {
        return (nextLong() & 1) != 0L;
    }

    public void nextBytes(final byte[] bytes) {
        int i = bytes.length, n = 0;
        while (i != 0) {
            n = Math.min(i, 8);
            for (long bits = nextLong(); n-- != 0; bits >>= 8) {
                bytes[--i] = (byte) bits;
            }
        }
    }

    /**
     * Sets the seed of this generator. Passing this 0 will just set it to -1
     * instead. Not the same as the exact state-setting method implementations
     * of StatefulRandomness have, {@code setState()}; this is used to generate
     * 128 bits of state from a 64-bit (non-zero) seed.
     *
     * @param seed the number to use as the seed
     */
    public void setSeed(final long seed) {

        long state = seed + 0x9E3779B97F4A7C15L,
                z = state;
        z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
        z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
        state0 = z ^ (z >>> 31);
        state = seed + 0x9E3779B97F4A7C15L;
        z = state;
        z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
        z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
        state1 = z ^ (z >>> 31);
    }

    @Override
    public String toString() {
        return "XoRoRNG with state hash 0x" + StringKit.hexHash(state0, state1) + 'L';
    }
}
