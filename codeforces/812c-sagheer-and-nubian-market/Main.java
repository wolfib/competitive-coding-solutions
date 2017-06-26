// Codeforces Round 417
// Sagheer and Nubian Market
// Problem statement:
// http://codeforces.com/problemset/problem/812/C

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
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
        int n;
        long[] a;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            long s = in.nextInt();
            a = in.readLongArray(n);
            long sum = 0;
            long min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                sum += a[i] + (i + 1) * n;
                min = Math.min(min, a[i] + i + 1);
            }
            if (sum <= s) {
                out.println(n + " " + sum);
                return;
            }
            if (min > sum) {
                out.println("0 0");
                return;
            }

            int left = 1;
            int right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (cost(mid) > s) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            int anz = left - 1;
            out.println(anz + " " + cost(anz));
        }

        long cost(int k) {
            long[] b = new long[n];
            for (int i = 0; i < n; i++) {
                b[i] = a[i] + (long) k * (i + 1);
            }
            Arrays.sort(b);
            long result = 0;
            for (int i = 0; i < k; i++) {
                result += b[i];
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

