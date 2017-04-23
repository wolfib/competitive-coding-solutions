// Educational Codeforces Round 19
// Array Queries
// Problem statement:
// http://codeforces.com/problemset/problem/797/E

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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        int[][] dp;
        int n;
        int[] a;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            a = in.read1BasedIntArray(n);

            int cutoff = (int) Math.floor(Math.sqrt(n));
            dp = new int[n + 1][cutoff];
            for (int i = 1; i < cutoff; i++) {
                precompute(i);
            }

            int q = in.nextInt();
            for (int i = 0; i < q; i++) {
                int p = in.nextInt();
                int k = in.nextInt();

                if (k >= cutoff) {
                    out.println(simulate(p, k));
                } else {
                    out.println(dp[p][k]);
                }

            }
        }

        void precompute(int k) {
            for (int i = n; i > 0; i--) {
                if (i + a[i] + k > n) {
                    dp[i][k] = 1;
                } else {
                    dp[i][k] = 1 + dp[i + a[i] + k][k];
                }
            }
        }

        int simulate(int p, int k) {
            int result = 0;
            while (p <= n) {
                p = p + a[p] + k;
                result++;
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

        public int[] read1BasedIntArray(int n) {
            int[] ar = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                ar[i] = nextInt();
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

