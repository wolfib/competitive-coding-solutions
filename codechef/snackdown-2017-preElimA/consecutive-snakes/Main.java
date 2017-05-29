// Codechef SnackDown 2017 Pre-elimination Round A
// Consecutive Snakes
// Problem statement:
// https://www.codechef.com/SNCKPA17/problems/CONSESNK

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
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        ConsecutiveSnakes solver = new ConsecutiveSnakes();
        solver.solve(1, in, out);
        out.close();
    }

    static class ConsecutiveSnakes {
        int n;
        long len;
        int a;
        int b;
        int[] s;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int t = in.nextInt();
            for (int i = 0; i < t; i++) {
                out.println(solveConsecutive(in, out));
            }
        }

        long solveConsecutive(InputReader in, PrintWriter out) {
            n = in.nextInt();
            len = in.nextInt();
            a = in.nextInt();
            b = in.nextInt();
            s = in.readIntArray(n);
            Arrays.sort(s);

            if (getCost(a) <= getCost(a + 1)) {
                return getCost(a);
            }
            int hi = b - n * (int) len;
            if (getCost(hi) <= getCost(hi - 1)) {
                return getCost(hi);
            }

            // instead of ternary search we can use binary search
            // because we are in the integer domain (not continuous domain)
            int lo = a - 1;
            while (hi - lo > 1) {
                int mid = (hi + lo) >> 1;
                if (getCost(mid) <= getCost(mid + 1))
                    hi = mid;
                else
                    lo = mid;
            }
            return getCost(lo + 1);
        }

        long getCost(long pos) {
            long result = 0;
            for (int i = 0; i < n; i++) {
                result += Math.abs((long) s[i] - pos);
                pos += len;
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

