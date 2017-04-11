// Codeforces Round 407
// Functions again
// Problem statement:
// http://codeforces.com/contest/788/problem/A

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            long[] a = in.readLongArray(n);
            long[] diff = new long[n - 1];

            for (int i = 0; i < n - 1; i++) {
                diff[i] = Math.abs(a[i] - a[i + 1]);
            }

            long sum = 0;
            long sum2 = 0;
            long maxSum = 0;
            for (int i = 0; i < n - 1; i++) {
                long current = diff[i];
                if (i % 2 == 1) {
                    current = -current;
                }
                sum += current;
                sum2 -= current;
                if (sum < 0) {
                    sum = 0;
                }
                if (sum2 < 0) {
                    sum2 = 0;
                }
                maxSum = Math.max(maxSum, sum);
                maxSum = Math.max(maxSum, sum2);
            }
            out.println(maxSum);
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

