// Codechef SnackDown 2017 Pre-elimination Round A
// A Temple of Snakes
// Problem statement:
// https://www.codechef.com/SNCKPA17/problems/SNTEMPLE

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
        TempleOfSnakes solver = new TempleOfSnakes();
        solver.solve(1, in, out);
        out.close();
    }

    static class TempleOfSnakes {
        int[] a;
        int n;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int t = in.nextInt();
            for (int i = 0; i < t; i++) {
                solveTemple(in, out);
            }
        }

        void solveTemple(InputReader in, PrintWriter out) {
            n = in.nextInt();
            a = in.readIntArray(n);

            int left = 1;
            int right = n;
            while (left < right) {
                int mid = left + (right - left + 1) / 2; // round up(!) to avoid being stuck in 2-element case
                if (!canFitTemple(mid)) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }

            //System.out.println("biggest temple has height " + left);
            long blocksNeeded = (long) left * left;

            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += a[i];
            }
            out.println(sum - blocksNeeded);
        }

        boolean canFitTemple(int h) {
            if (h * 2 - 1 > n) return false;
            int count = 0;
            for (int i = 0; i < n; i++) {
                count++;
                if (count == 2 * h - 1) {
                    return true;
                }
                if (count <= h) {
                    if (a[i] < count) {
                        count = a[i];
                    }
                } else {
                    if (a[i] < 2 * h - count) {
                        count = a[i];
                    }
                }
            }
            return false;
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

