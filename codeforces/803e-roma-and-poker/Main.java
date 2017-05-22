// Educational Codeforces Round 20
// Roma and Poker
// Problem statement:
// http://codeforces.com/problemset/problem/803/E

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            String s = in.next();

            int[] min = new int[n + 1];
            int[] max = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                char c = s.charAt(i - 1);
                if (c == 'W') {
                    min[i] = min[i - 1] + 1;
                    max[i] = max[i - 1] + 1;
                } else if (c == 'L') {
                    min[i] = min[i - 1] - 1;
                    max[i] = max[i - 1] - 1;
                } else if (c == 'D') {
                    min[i] = min[i - 1];
                    max[i] = max[i - 1];
                } else { // '?'
                    min[i] = min[i - 1] - 1;
                    max[i] = max[i - 1] + 1;
                }
                if (i < n) {
                    if ((min[i] >= k) || (max[i] <= -k)) {
                        out.println("NO");
                        return;
                    }
                    min[i] = Math.max(min[i], -k + 1);
                    max[i] = Math.max(max[i], -k + 1);
                    min[i] = Math.min(min[i], k - 1);
                    max[i] = Math.min(max[i], k - 1);
                }
            }

            int actual = 0;
            if (min[n] == -k) actual = -k;
            if (max[n] == k) actual = k;

            if (actual == 0) {
                out.println("NO");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = n - 1; i >= 0; i--) {
                char c = s.charAt(i);
                if (c == 'W') {
                    actual -= 1;
                } else if (c == 'L') {
                    actual += 1;
                } else if (c == 'D') {
                } else { // '?'
                    if (max[i] < actual) {
                        actual--;
                        c = 'W';
                    } else if (min[i] > actual) {
                        actual++;
                        c = 'L';
                    } else {
                        c = 'D';
                    }

                }
                sb.insert(0, c);
            }
            out.println(sb.toString());
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
                    //tok = new StringTokenizer(in.readLine(), ", \t\n\r\f"); //adds commas as delimeter
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

