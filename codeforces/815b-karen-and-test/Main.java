// Codeforces Round 419
// Karen and Test
// Problem statement:
// http://codeforces.com/problemset/problem/815/B

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        int n;
        long[] a;
        final long MOD = 1000000007;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            a = in.readLongArray(n);
            if (n < 5) {
                long result = bruteForce(a);
                result = result % MOD;
                while (result < 0) result += MOD;
                out.println(result);
                return;
            }

            if (n % 2 == 1) {
                a = singleStep(a, true);
                n = a.length;
            }

            Combinatorics comb = new Combinatorics(1000000007);
            int m = n / 2;
            long first = 0;
            long second = 0;
            for (int i = 0; i < m; i++) {
                first += comb.modBinomialPreCalc(m - 1, i) * a[2 * i];
                first = first % MOD;
                second += comb.modBinomialPreCalc(m - 1, i) * a[2 * i + 1];
                second = second % MOD;
            }

            long result;
            if (n % 4 == 0) result = first - second;
            else result = first + second;
            result = result % MOD;
            while (result < 0) result += MOD;
            out.println(result);
        }

        long[] singleStep(long[] a, boolean startWithAddition) {
            int n = a.length;
            long[] b = new long[n - 1];
            for (int i = 0; i < n - 1; i++) {
                if (startWithAddition) b[i] = (a[i] + a[i + 1]) % MOD;
                else b[i] = (a[i] - a[i + 1]) % MOD;
                startWithAddition = !startWithAddition;
            }
            return b;
        }

        long bruteForce(long[] a) {
            boolean isAdd = true;
            int n = a.length;
            while (n > 1) {
                for (int i = 0; i < n - 1; i++) {
                    if (isAdd) a[i] = a[i] + a[i + 1];
                    else a[i] = a[i] - a[i + 1];
                    isAdd = !isAdd;
                }
                n--;
            }
            return a[0];
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

        public long modBinomialPreCalc(int n, int k) {
            if (k < 0 || k > n) return 0;
            if (k == 0 || k == n) return 1;
            //if (k > n - k) k = n - k;

            return fact[n] * invFact[k] % mod * invFact[n - k] % mod;
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
                    //tok = new StringTokenizer(in.readLine(), ", \t\n\r\f"); //adds commas as delimeter
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

