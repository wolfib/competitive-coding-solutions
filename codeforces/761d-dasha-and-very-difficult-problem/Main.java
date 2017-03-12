// Codeforces Round 394
// Dasha and Very Difficult Problem
// Problem statement:
// http://codeforces.com/problemset/problem/761/D

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
import java.util.Comparator;
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
            int n = in.nextInt();
            int left = in.nextInt();
            int right = in.nextInt();
            int[] a = in.readIntArray(n);
            int[] c = in.readIntArray(n);
            Triple[] t = new Triple[n];

            for (int i = 0; i < n; i++) {
                t[i] = new Triple(a[i], c[i], i);
            }

            Arrays.sort(t, Comparator.comparing(tr -> tr.c));

            int[] b = new int[n];
            b[t[0].i] = left;
            int currentDiff = left - t[0].a;
            for (int i = 1; i < n; i++) {
                currentDiff++;
                b[t[i].i] = Math.max(left, currentDiff + t[i].a);
                currentDiff = b[t[i].i] - t[i].a;
                if (b[t[i].i] > right) {
                    out.println(-1);
                    return;
                }
            }

            for (int i = 0; i < n; i++) {
                out.print(b[i] + " ");
            }

        }

        class Triple {
            int a;
            int c;
            int i;

            Triple(int aa, int cc, int index) {
                a = aa;
                c = cc;
                i = index;
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
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

