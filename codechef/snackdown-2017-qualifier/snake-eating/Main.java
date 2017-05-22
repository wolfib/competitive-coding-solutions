// Codechef SnackDown 2017 Qualifier Round
// Snake Eating
// Problem statement:
// https://www.codechef.com/SNCKQL17/problems/SNAKEEAT

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
        SnakeEating solver = new SnakeEating();
        solver.solve(1, in, out);
        out.close();
    }

    static class SnakeEating {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                solveSnakeEating(in, out);
            }
        }

        void solveSnakeEating(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int q = in.nextInt();
            long[] snakes = in.readLongArray(n);
            Arrays.sort(snakes);
            long[] prefixSums = new long[n];
            prefixSums[0] = snakes[0];
            for (int i = 1; i < n; i++) {
                prefixSums[i] = prefixSums[i - 1] + snakes[i];
            }

            for (int i = 0; i < q; i++) {
                long k = in.nextInt();
                out.println(getNumberOfSnakes(snakes, prefixSums, k));
            }
        }

        int getNumberOfSnakes(long[] snakes, long[] prefixSums, long k) {
            int n = snakes.length;
            if (snakes[0] >= k) return n;
            //if (snakes[n - 1] < k) return 0;

            int firstSingle;
            if (snakes[n - 1] < k) {
                firstSingle = n;
            } else {
                firstSingle = binarySearch(snakes, 0, n - 1, k);
            }
            int firstMultiple = binarySearch2(prefixSums, 0, firstSingle, k, firstSingle);

            int result = n - firstMultiple;
            return result;
            //return n - firstSingle;
        }

        int binarySearch(long arr[], int left, int right, long target) {
            while (left < right) {
                int mid = (left + right) / 2;
                if (arr[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        int binarySearch2(long arr[], int left, int right, long k, int firstSingle) {
            while (left < right) {
                int mid = (left + right) / 2;
                //if (arr[mid] < max) {
                if (arr[firstSingle - 1] - (mid > 0 ? arr[mid - 1] : 0) + mid >= k * (firstSingle - mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public long[] readLongArray(int n) {
            long[] ar = new long[n];
            for (int i = 0; i < n; i++) {
                ar[i] = nextLong();
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

