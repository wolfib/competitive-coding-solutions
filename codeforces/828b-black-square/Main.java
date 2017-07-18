// Codeforces Round 423
// Black Square
// Problem statement:
// http://codeforces.com/problemset/problem/828/B

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
            int cols = in.nextInt();
            int blackCount = 0;
            int top = 999;
            int bottom = -1;
            int left = 999;
            int right = -1;
            for (int i = 0; i < rows; i++) {
                String s = in.next();
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == 'B') {
                        blackCount++;
                        top = Math.min(top, i);
                        bottom = Math.max(bottom, i);
                        left = Math.min(left, j);
                        right = Math.max(right, j);
                    }
                }
            }
            if (blackCount == 0) {
                out.println(1);
                return;
            }
            if ((bottom - top + 1 > cols) || (right - left + 1 > rows)) {
                out.println(-1);
                return;
            }
            int side = Math.max(bottom - top + 1, right - left + 1);
            int result = side * side - blackCount;
            out.println(result);
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

