import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = in.readIntArray(n);
            Sorting.shuffle(a, new Random());
            Arrays.sort(a);
            long result;
            if (a[0] == a[2]) {
                int count = 2;
                while ((count < n) && (a[count] == a[0])) count++;
                result = (long) count * (count - 1) * (count - 2) / 6;
            } else if (a[1] == a[2]) {
                int i = 3;
                while ((i < n) && (a[i] == a[2])) i++;
                result = (i - 1) * (i - 2) / 2;
            } else {
                int i = 3;
                while ((i < n) && (a[i] == a[2])) i++;
                result = i - 2;
            }
            out.println(result);
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

    static class Sorting {
        public static int[] shuffle(int[] a, Random gen) {
            for (int i = 0, n = a.length; i < n; i++) {
                int ind = gen.nextInt(n - i) + i;
                int d = a[i];
                a[i] = a[ind];
                a[ind] = d;
            }
            return a;
        }

    }
}

