// Educational Codeforces Round 23
// Really Big Numbers
// Problem statement:
// http://codeforces.com/problemset/problem/817/C

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
            long s = in.nextLong();

            if (n - digitSum(n) < s) {
                out.println(0);
                return;
            }

            long left = 0;
            long right = n;
            while (left < right) {
                long mid = left + (right - left) / 2;
                if (mid - digitSum(mid) >= s) { // if condition(mid) == true
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            out.println(n - left + 1);
        }

        long digitSum(long a) {
            long result = 0;
            while (a > 0) {
                result += a % 10;
                a /= 10;
            }
            return result;
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

