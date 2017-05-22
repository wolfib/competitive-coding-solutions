// Codechef SnackDown 2017 Qualifier Round
// Temple Land
// Problem statement:
// https://www.codechef.com/SNCKQL17/problems/TEMPLELA

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
        TempleLand solver = new TempleLand();
        solver.solve(1, in, out);
        out.close();
    }

    static class TempleLand {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                checkHeights(in, out);
            }
        }

        void checkHeights(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] h = in.readIntArray(n);
            if (n % 2 == 0) {
                out.println("no");
                return;
            }
            int i;
            for (i = 1; i <= n / 2 + 1; i++) {
                if ((h[i - 1] != i) || (h[n - i] != i)) {
                    out.println("no");
                    return;
                }
            }
            out.println("yes");
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

