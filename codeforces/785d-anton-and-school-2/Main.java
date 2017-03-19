// Codeforces Round 404
// Anton and School - 2
// Problem statement:
// http://codeforces.com/problemset/problem/785/D

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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String s = in.next();
            int n = s.length();
            int[] openCounter = new int[n];
            int[] closeCounter = new int[n];

            int count = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '(') {
                    count++;
                }
                openCounter[i] = count;
            }

            count = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == ')') {
                    count++;
                }
                closeCounter[i] = count;
            }

            long result = 0;
            Combinatorics comb = new Combinatorics(1000000007, n + 1);
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == '(') {
                    long current = comb.modBinomialPreCalc(openCounter[i] + closeCounter[i + 1] - 1, openCounter[i]);
                    result += current;
                    result %= 1000000007;
                }
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
}

