// Educational Codeforces Round 19
// Odd Sum
// Problem statement:
// http://codeforces.com/problemset/problem/797/B

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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = in.readIntArray(n);
            int sum = 0;
            int minPosOdd = 9999999;
            int maxNegOdd = -9999999;
            for (int i = 0; i < n; i++) {
                if (a[i] > 0) sum += a[i];
                if (a[i] % 2 != 0) {
                    if ((a[i] > 0) && (a[i] < minPosOdd)) {
                        minPosOdd = a[i];
                    }
                    if ((a[i] < 0) && (a[i] > maxNegOdd)) {
                        maxNegOdd = a[i];
                    }
                }
            }
            if (sum % 2 == 1) {
                out.println(sum);
                return;
            }
            int corr = Math.min(-maxNegOdd, minPosOdd);
            out.println(sum - corr);
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

        public int[] readIntArray(int n) {
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) {
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

