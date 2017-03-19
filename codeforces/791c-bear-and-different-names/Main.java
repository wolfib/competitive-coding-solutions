// Codeforces Round 405
// Bear and Different Names
// Problem statement:
// http://codeforces.com/problemset/problem/791/C

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
            int n = in.nextInt();
            int k = in.nextInt();
            boolean[] effective = new boolean[n];
            int firstYes = -1;
            for (int i = k - 1; i < n; i++) {
                String s = in.next();
                if (s.equals("YES")) {
                    effective[i] = true;
                    if (firstYes == -1) firstYes = i;
                }
            }

            if (firstYes == -1) {
                for (int i = 0; i < n; i++) {
                    out.print("A ");
                }
                return;
            }

            int[] result = new int[n];
            result[firstYes] = k;
            for (int i = firstYes - 1; i >= 0; i--) {
                result[i] = Math.max(1, result[i + 1] - 1);
            }

            int missing = -1;
            for (int i = firstYes + 1; i < n; i++) {
                if (effective[i - 1]) {
                    if (effective[i]) {
                        result[i] = result[i - k];
                    } else {
                        result[i] = result[i - k + 1];
                        missing = result[i - k];
                    }
                } else {
                    if (effective[i]) {
                        result[i] = missing;
                    } else {
                        result[i] = result[i - k + 1];
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                out.print(makeName(result[i]) + " ");
            }
        }

        public String makeName(int i) {
            if (i <= 26) {
                char c = (char) ('A' + i - 1);
                return String.valueOf(c);
            } else {
                char c = (char) ('a' + i - 1 - 26);
                return "A" + String.valueOf(c);
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

