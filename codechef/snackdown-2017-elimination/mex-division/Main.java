// Codechef SnackDown 2017 Elimination Round
// Mex division
// Problem statement:
// https://www.codechef.com/problems/MEXDIV

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
        MexDivision solver = new MexDivision();
        solver.solve(1, in, out);
        out.close();
    }

    static class MexDivision {
        int n;
        int[] a;
        int k;
        static final long MOD = 1000000007;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            k = in.nextInt();
            a = in.readIntArray(n);

            // edge case
            if (k > n) {
                long result = 1;
                for (int i = 1; i < n; i++) {
                    result *= 2;
                    result %= MOD;
                }
                out.println(result);
                return;
            }

            // dynamic programming solution
            // relevant subarray = array of minimal size from beginIndex to i which contains all values 0..k at least once
            int[] c = new int[k + 1]; // count of how often a value is within the relevant subarray

            int beginInvalid = 0; // index of start of relevant subarray
            int invalidMissing = k + 1; // count of how many values are still missing in relevant subarray
            long result[] = new long[n];

            for (int i = 0; i < n; i++) {
                if (i == 0) result[i] = 1;
                else result[i] = result[i - 1] * 2; // moving 1 step to the right doubles the amount of solutions
                // now we just have to substract all invalid solutions

                if (invalidMissing == 0) { // all values 0..k have already been encountered
                    if (a[i] <= k) {
                        c[a[i]]++;
                        // move beginInvalid to the right until relevan subarray is minimal again
                        while ((a[beginInvalid] > k) || (c[a[beginInvalid]] > 1)) {
                            if (a[beginInvalid] <= k) {
                                c[a[beginInvalid]]--;
                            }
                            result[i] -= result[beginInvalid];
                            beginInvalid++;
                        }

                    }
                } else { // not all values 0..k have been encountered
                    if (a[i] <= k) {
                        if (c[a[i]] == 0) invalidMissing--;
                        c[a[i]]++;
                        // if possible increase beginInvalid
                        while ((beginInvalid < i) && ((a[beginInvalid] > k) || (c[a[beginInvalid]] > 1))) {
                            if (a[beginInvalid] <= k) {
                                c[a[beginInvalid]]--;
                            }
                            beginInvalid++;
                        }
                        if (invalidMissing == 0) {
                            result[i] -= result[beginInvalid];
                        }
                    }
                }
                result[i] %= MOD;
                if (result[i] < 0) result[i] += MOD;
            }
            out.println(result[n - 1]);
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

