package priv.mfurmane.szlachtownica.worldgen;

/** Wspólny szum wartościowy (value noise) + fBm. Deterministyczny z ziarna. */
final class Noise {

    private Noise() {
    }

    /** Wielooktawowy fBm w [0,1]. */
    static double fbm(double x, double y, int octaves, long seed) {
        double sum = 0, amp = 1, total = 0, freq = 1;
        for (int o = 0; o < octaves; o++) {
            sum += amp * valueNoise2D(x * freq, y * freq, seed + o * 1013L);
            total += amp;
            amp *= 0.5;
            freq *= 2.0;
        }
        return sum / total;
    }

    static double valueNoise2D(double x, double y, long seed) {
        int x0 = (int) Math.floor(x), y0 = (int) Math.floor(y);
        double tx = fade(x - x0), ty = fade(y - y0);
        double v00 = hash(x0, y0, seed), v10 = hash(x0 + 1, y0, seed);
        double v01 = hash(x0, y0 + 1, seed), v11 = hash(x0 + 1, y0 + 1, seed);
        return lerp(lerp(v00, v10, tx), lerp(v01, v11, tx), ty);
    }

    static double hash(int x, int y, long seed) {
        long h = x * 0x1f1f1f1fL ^ (y * 0x8da6b343L) ^ (seed * 0xca5a5a5aL);
        h ^= (h >>> 13);
        h *= 0x9E3779B97F4A7C15L;
        h ^= (h >>> 7);
        return (h & 0xffffffffL) / (double) 0xffffffffL;
    }

    static double fade(double t) {
        return t * t * (3 - 2 * t);
    }

    static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }
}
