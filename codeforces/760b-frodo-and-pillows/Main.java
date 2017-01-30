// Codeforces 8VC Venture Cup 2017 - Final Round Div. 2 Edition
// Frodo and Pillows
// Problem statement:
// http://codeforces.com/problemset/problem/760/B

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
        int n;
        int m;
        int k;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();

            // binary search
            int left = 1;
            int right = m;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (pillowsNeeded(mid) > m) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            out.println(right);
        }

        public long pillowsNeeded(long x) {
            long result = x;
            result += pillowsSingleSide(x - 1, k - 1);
            result += pillowsSingleSide(x - 1, n - k);
            return result;
        }

        public long pillowsSingleSide(long h, long w) {
            if (w >= h) {
                // if number of pillows for the hobbit right next to frodo <= number of hobbits
                return (h + 1) * h / 2 + (w - h);
            } else {
                // if number of pillows for the hobbit right next to frodo > number of hobbits
                // which means hobbit on the other end needs more than one pillow
                return (h + h - w + 1) * w / 2;
            }
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
}

