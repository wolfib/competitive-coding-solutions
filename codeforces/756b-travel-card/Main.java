// Codeforces 8VC Venture Cup 2017 - Final Round
// Travel Card
// Problem statement:
// http://codeforces.com/problemset/problem/756/B

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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] t = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                t[i] = in.nextInt();
            }
            t[0] = -999999;

            // dynamic programming
            // opt[i] contains the optimal amount spent after i'th trip
            // 3 cases per trip, depending on whether last ticket bought is
            // single, 90min or daily
            int[] opt = new int[n + 1];
            opt[1] = 20;
            out.println(20);
            int ninetyBack = 0;
            int dayBack = 0;
            for (int i = 2; i <= n; i++) {
                opt[i] = opt[i - 1] + 20;
                while (t[ninetyBack] <= t[i] - 90) ninetyBack++;
                while (t[dayBack] <= t[i] - 1440) dayBack++;
                opt[i] = Math.min(opt[i], opt[ninetyBack - 1] + 50);
                opt[i] = Math.min(opt[i], opt[dayBack - 1] + 120);
                out.println(opt[i] - opt[i - 1]);
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

