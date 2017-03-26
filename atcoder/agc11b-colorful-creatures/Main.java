// AtCoder Grand Contest 11
// Colorful Creatures
// Problem statement:
// http://agc011.contest.atcoder.jp/tasks/agc011_b


import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
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
        ColorfulCreatures solver = new ColorfulCreatures();
        solver.solve(1, in, out);
        out.close();
    }

    static class ColorfulCreatures {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = in.readIntArray(n);
            Arrays.sort(a); //sort creatures by size

            int res = 0;
            long sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += a[i]; // sum of all creatures up to a[i]
                // if sum is less than half of next creature, the small creatures can never be the last ones left
                // and we reset counter
                if (2 * sum >= a[i + 1]) {
                    res++;
                } else {
                    res = 0;
                }
            }
            res++;
            out.println(res);
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
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

