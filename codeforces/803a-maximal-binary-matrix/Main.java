// Educational Codeforces Round 20
// Maximal Binary Matrix
// Problem statement:
// http://codeforces.com/problemset/problem/803/A

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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();

            if (k > n * n) {
                out.println(-1);
                return;
            }

            int[][] mat = new int[n][n];

            for (int row = 0; row < n; row++) {
                if (k > 0) {
                    mat[row][row] = 1;
                    k--;
                }
                for (int col = row + 1; col < n; col++) {
                    if (k > 1) {
                        mat[row][col] = 1;
                        mat[col][row] = 1;
                        k -= 2;
                    }
                }
            }

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    out.print(mat[row][col] + " ");
                }
                out.println();
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
                    //tok = new StringTokenizer(in.readLine(), ", \t\n\r\f"); //adds commas as delimeter
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

