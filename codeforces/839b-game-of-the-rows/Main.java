// Codeforces Round 428
// Game of the Rows
// Problem statement:
// http://codeforces.com/problemset/problem/839/B

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
            int rows = in.nextInt();
            int groups = in.nextInt();
            int[] a = in.readIntArray(groups);

            int quartsLeft = rows;
            int pairsLeft = 2 * rows;

            for (int i = 0; i < groups; i++) {
                int x = Math.min(quartsLeft, a[i] / 4);
                quartsLeft -= x;
                a[i] -= 4 * x;
            }

            int singlesLeft = quartsLeft;
            pairsLeft += quartsLeft;

            for (int i = 0; i < groups; i++) {
                int x = Math.min(pairsLeft, a[i] / 2);
                pairsLeft -= x;
                a[i] -= 2 * x;
            }

            singlesLeft += pairsLeft;
            int sum = 0;
            for (int i = 0; i < groups; i++) {
                sum += a[i];
            }
            if (sum > singlesLeft) {
                out.println("NO");
            } else {
                out.println("YES");
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
                    //tok = new StringTokenizer(in.readLine(), ", \t\n\r\f"); //adds commas as delimeter
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

