// Codechef SnackDown 2017 Elimination Round
// Add or multiply
// Problem statement:
// https://www.codechef.com/problems/PLUSMUL

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
        AddOrMultiply solver = new AddOrMultiply();
        solver.solve(1, in, out);
        out.close();
    }

    static class AddOrMultiply {
        static long MOD = 1000000007;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int t = in.nextInt();
            for (int i = 0; i < t; i++) {
                solveAdd(in, out);
            }
        }

        void solveAdd(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = in.readIntArray(n);
            long[] result = new long[n];
            long[] mv = new long[n]; // if the rightmost operation is *, rightmost term has to be multiplied with this value
            long[] ma = new long[n]; // if the rightmost operation is *, ma contains sum of all values which are added
            long[] count = new long[n]; // number of permutations

            result[0] = a[0];
            mv[0] = a[0];
            ma[0] = 0;
            count[0] = 1;

            for (int i = 1; i < n; i++) {
                count[i] = count[i - 1] * 2;
                count[i] %= MOD;
                // first term is for rightmost operation is +
                // second term is for rightmost operation *, consisiting of 2 parts: multiplication with terms towards the right
                // and addition of the rest (terms on the left up to rightmost + sign)
                result[i] = result[i - 1] + ((count[i - 1] * a[i]) % MOD) + ((mv[i - 1] * a[i]) % MOD) + ma[i - 1];
                result[i] %= MOD;
                mv[i] = a[i] * ((mv[i - 1] + count[i - 1]) % MOD);
                mv[i] %= MOD;
                ma[i] = ma[i - 1] + result[i - 1];
                ma[i] %= MOD;
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

