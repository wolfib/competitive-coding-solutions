// Educational Codeforces Round 20
// Maximal GCD
// Problem statement:
// http://codeforces.com/problemset/problem/803/C

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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            long n = in.nextLong();
            long k = in.nextLong();

            if ((k > 200000) || ((k * (k + 1)) / 2 > n)) {
                out.println(-1);
                return;
            }

            long gcd = 1;
            int wurzel = (int) Math.sqrt(n);
            long minSum = (k * (k + 1)) / 2;
            for (long i = 2; i <= wurzel; i++) {
                if (n % i == 0) {
                    if (n / i >= minSum) gcd = Math.max(gcd, i);
                    long x = n / i;
                    if (n / x >= minSum) gcd = Math.max(gcd, x);
                }
            }

            long sum = 0;
            for (int i = 1; i < k; i++) {
                sum += i * gcd;
                out.print((i * gcd) + " ");
            }
            out.println(n - sum);
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
                    //tok = new StringTokenizer(in.readLine(), ", \t\n\r\f"); //adds commas as delimeter
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

