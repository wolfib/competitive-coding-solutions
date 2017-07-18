// Codeforces Round 424
// Bamboo Partition
// Problem statement:
// http://codeforces.com/problemset/problem/830/C

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskF solver = new TaskF();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskF {
        int n;
        long[] a;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            long k = in.nextLong();
            a = in.readLongArray(n);

            for (int i = 0; i < n; i++) k += a[i];

            // sum(ceil(ai / d)) * d <= k
            // d <= k / floor(k / sum (ceil(ai / d))
            // there are only about 2 * sqrt(k) possible values for the value of floor(k/x), try all of them
            Set<Long> candidates = new HashSet<>();
            for (long i = 1; i <= Math.sqrt(k); i++) {
                candidates.add(i);
                candidates.add(k / i);
            }

            long result = 0;
            for (Long cand : candidates) {
                if (check(cand) <= k) result = Math.max(result, cand);
            }

            out.println(result);
        }

        long check(long d) {
            long result = 0;
            for (int i = 0; i < n; i++) {
                result += ((a[i] + d - 1) / d) * d;
            }
            return result;
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

