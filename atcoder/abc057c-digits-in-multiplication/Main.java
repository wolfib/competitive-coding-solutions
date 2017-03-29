// AtCoder Beginner Contest 057
// Digits in Multiplication
// Problem statement:
// http://abc057.contest.atcoder.jp/tasks/abc057_c

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
        DigitsInMultiplication solver = new DigitsInMultiplication();
        solver.solve(1, in, out);
        out.close();
    }

    static class DigitsInMultiplication {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            long n = in.nextLong();
            long i = (long) Math.floor(Math.sqrt(n));
            while (n % i > 0) i--;
            long j = n / i;
            out.println((long) Math.floor(Math.log10(j)) + 1);
        }

    }

    static class InputReader {
        private static BufferedReader in;
        private static StringTokenizer tok;

        public InputReader(InputStream in) {
            this.in = new BufferedReader(new InputStreamReader(in));
        }

        public long nextLong() {
            return Long.parseLong(next());
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
