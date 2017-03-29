// AtCoder Beginner Contest 057
// Maximum Average Sets
// Problem statement:
// http://abc057.contest.atcoder.jp/tasks/abc057_d

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        MaximumAverageSets solver = new MaximumAverageSets();
        solver.solve(1, in, out);
        out.close();
    }

    static class MaximumAverageSets {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            long[] v = in.readLongArray(n);
            Arrays.sort(v);

            long sum = 0;
            for (int i = n - 1; i >= n - a; i--) {
                sum += v[i];
            }
            double av = (double) sum / a;
            out.format("%.6f\n", av);

            int selectMin = 1;
            while ((selectMin < a) && (v[n - a + selectMin] == v[n - a])) {
                selectMin++;
            }
            int total = selectMin;
            int idx = n - a;
            while ((idx > 0) && (v[idx - 1] == v[n - a])) {
                idx--;
                total++;
            }

            long result = 0;
            if (v[n - 1] == v[n - a]) {
                for (int i = selectMin; i <= Math.min(total, selectMin + b - a); i++) {
                    result += Combinatorics.choose(total, i).longValue();
                }
            } else {
                result += Combinatorics.choose(total, selectMin).longValue();
            }
            out.println(result);
        }

    }

    static class InputReader {
        private static BufferedReader in;
        private static StringTokenizer tok;

        public InputReader(InputStream in) {
            this.in = new BufferedReader(new InputStreamReader(in));
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public long[] readLongArray(int n) {
            long[] ar = new long[n];
            for (int i = 0; i < n; i++) {
                ar[i] = nextLong();
            }
            return ar;
        }

        public String next() {
            try {
                while (tok == null || !tok.hasMoreTokens()) {
                    tok = new StringTokenizer(in.readLine());
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }

    static class Combinatorics {
        long[] fact;
        long[] invFact;
        int max;
        int mod;

        public Combinatorics(int modul, int m) {
            //modul should be prime

            mod = modul;
            max = m;
            fact = new long[max];
            invFact = new long[max];
            fact[1] = 1;
            fact[0] = 1;
            invFact[1] = 1;
            invFact[0] = 1;
            for (int i = 2; i < max; i++) {
                fact[i] = fact[i - 1] * i % mod;
            }

            invFact[max - 1] = modInverse(fact[max - 1], mod);
            for (int i = max - 2; i >= 0; i--) {
                invFact[i] = ((i + 1) * invFact[i + 1]) % mod; //.. 1 / i! = (i + 1) * 1 / (i + 1)!
            }
        }

        public Combinatorics(int modul) {
            // modul should be prime

            this(modul, 3000000);
        }

        public static BigInteger choose(long n, long k) {
            if (k < 0 || k > n) return BigInteger.ZERO;
            if (k == 0 || k == n) return BigInteger.ONE;
            if (k > n - k) k = n - k;

            return choose(n - 1, k - 1).multiply(BigInteger.valueOf(n)).divide(BigInteger.valueOf(k));
        }

        public static long modPow(long a, long x, long p) {
            //calculates a^x mod p in logarithmic time.
            long res = 1;
            while (x > 0) {
                if (x % 2 != 0) {
                    res = (res * a) % p;
                }
                a = (a * a) % p;
                x /= 2;
            }
            return res;
        }

        public static long modInverse(long a, long p) {
            //calculates the modular multiplicative of a mod m.
            //(assuming p is prime).
            return modPow(a, p - 2, p);
        }

    }
}
