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
        Upvotes solver = new Upvotes();
        solver.solve(1, in, out);
        out.close();
    }

    static class Upvotes {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = in.readIntArray(n);

            int[] increasingForward = new int[n];
            int[] decreasingForward = new int[n];
            increasingForward[0] = 1;
            decreasingForward[0] = 1;
            for (int i = 1; i < n; i++) {
                if (a[i] >= a[i - 1]) {
                    increasingForward[i] = increasingForward[i - 1] + 1;
                } else {
                    increasingForward[i] = 1;
                }
                if (a[i] <= a[i - 1]) {
                    decreasingForward[i] = decreasingForward[i - 1] + 1;
                } else {
                    decreasingForward[i] = 1;
                }
            }

            int[] decreasingBackward = new int[n];
            int[] increasingBackward = new int[n];
            decreasingBackward[n - 1] = 1;
            increasingBackward[n - 1] = 1;
            for (int i = n - 2; i >= 0; i--) {
                if (a[i] <= a[i + 1]) {
                    decreasingBackward[i] = decreasingBackward[i + 1] + 1;
                } else {
                    decreasingBackward[i] = 1;
                }
                if (a[i] >= a[i + 1]) {
                    increasingBackward[i] = increasingBackward[i + 1] + 1;
                } else {
                    increasingBackward[i] = 1;
                }
            }

            long countIncreasingSubranges = 0;
            long countDecreasingSubranges = 0;
            for (int i = 1; i < k; i++) {
                if (increasingForward[i] > 1) {
                    countIncreasingSubranges += increasingForward[i] - 1;
                }
                if (decreasingForward[i] > 1) {
                    countDecreasingSubranges += decreasingForward[i] - 1;
                }
            }
            out.println(countIncreasingSubranges - countDecreasingSubranges);
            // System.out.println("incr: " + countIncreasingSubranges + ", decr: " + countDecreasingSubranges);

            for (int i = k; i < n; i++) {
                if (increasingForward[i] > 1) {
                    countIncreasingSubranges += Math.min(increasingForward[i] - 1, k - 1);
                }
                int leftEnd = i - k;
                if (decreasingBackward[leftEnd] > 1) {
                    countIncreasingSubranges -= Math.min(decreasingBackward[leftEnd] - 1, k - 1);
                }

                if (decreasingForward[i] > 1) {
                    countDecreasingSubranges += Math.min(decreasingForward[i] - 1, k - 1);
                }
                if (increasingBackward[leftEnd] > 1) {
                    countDecreasingSubranges -= Math.min(increasingBackward[leftEnd] - 1, k - 1);
                }
                out.println(countIncreasingSubranges - countDecreasingSubranges);
                // System.out.println("incr: " + countIncreasingSubranges + ", decr: " + countDecreasingSubranges);
            }
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

