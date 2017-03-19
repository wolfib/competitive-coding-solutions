// Codeforces Round 404
// Anton and Classes
// Problem statement:
// http://codeforces.com/problemset/problem/785/B

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
            int[] a1 = new int[n];
            int[] b1 = new int[n];
            int maxa1 = 0;
            int minb1 = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                a1[i] = in.nextInt();
                maxa1 = Math.max(maxa1, a1[i]);
                b1[i] = in.nextInt();
                minb1 = Math.min(minb1, b1[i]);
            }
            int m = in.nextInt();
            int[] a2 = new int[m];
            int[] b2 = new int[m];
            int maxa2 = 0;
            int minb2 = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                a2[i] = in.nextInt();
                maxa2 = Math.max(maxa2, a2[i]);
                b2[i] = in.nextInt();
                minb2 = Math.min(minb2, b2[i]);
            }
            int res = Math.max(0, maxa2 - minb1);
            res = Math.max(res, maxa1 - minb2);

            out.println(res);
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

