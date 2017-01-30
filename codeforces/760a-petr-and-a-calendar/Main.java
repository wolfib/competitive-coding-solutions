// Codeforces 8VC Venture Cup 2017 - Final Round Div. 2 Edition
// Petr and a Calendar
// Problem statement:
// http://codeforces.com/problemset/problem/760/A

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
            int m = in.nextInt();
            int d = in.nextInt();

            // February (28 days)
            if (m == 2) {
                if (d == 1) {
                    out.println("4");
                    return;
                } else {
                    out.println("5");
                    return;
                }
            // months with 31 days
            } else if ((m == 1) || (m == 3) || (m == 5) || (m == 7) || (m == 8) || (m == 10) || (m == 12)) {
                if (d <= 5) {
                    out.println("5");
                    return;
                } else {
                    out.println("6");
                    return;
                }
            // months with 30 days
            } else {
                if (d <= 6) {
                    out.println("5");
                    return;
                } else {
                    out.println("6");
                    return;
                }
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

