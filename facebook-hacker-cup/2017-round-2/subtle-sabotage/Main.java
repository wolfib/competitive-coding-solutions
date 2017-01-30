// Facebook Hacker Cup 2017 Round 2
// Subtle Sabotage
// Problem statement:
// https://www.facebook.com/hackercup/problem/371325719893664/
// Editorial:
// https://www.facebook.com/notes/facebook-hacker-cup/hacker-cup-2017-round-2-solutions/1607098535972708

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

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Wolfgang Beyer
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        SubtleSabotage solver = new SubtleSabotage();
        solver.solve(1, in, out);
        out.close();
    }

    static class SubtleSabotage {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) solveSab(i + 1, in, out);
        }

        public void solveSab(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();

            if (n > m) {
                int temp = n;
                n = m;
                m = temp;
            }

            if ((k >= n) || (2 * k + 3 > m)) {
                out.println("Case #" + testNumber + ": -1");
                return;
            }

            int result = (int) Math.ceil(((double) n) / k);
            if (result > 4) {
                if (k == 1) result = 5;
                else result = 4;
            }
            out.println("Case #" + testNumber + ": " + result);
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

