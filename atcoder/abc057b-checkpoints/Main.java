// AtCoder Beginner Contest 057
// Checkpoints
// Problem statement:
// http://abc057.contest.atcoder.jp/tasks/abc057_b

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
        Checkpoint solver = new Checkpoint();
        solver.solve(1, in, out);
        out.close();
    }

    static class Checkpoint {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] px = new int[n];
            int[] py = new int[n];
            for (int i = 0; i < n; i++) {
                px[i] = in.nextInt();
                py[i] = in.nextInt();
            }
            int[] cx = new int[m];
            int[] cy = new int[m];
            for (int i = 0; i < m; i++) {
                cx[i] = in.nextInt();
                cy[i] = in.nextInt();
            }

            for (int i = 0; i < n; i++) {
                int minDist = editDistance(px[i], py[i], cx[0], cy[0]);
                int cp = 1;
                for (int j = 1; j < m; j++) {
                    int dist = editDistance(px[i], py[i], cx[j], cy[j]);
                    if (dist < minDist) {
                        minDist = dist;
                        cp = j + 1;
                    }
                }
                out.println(cp);
            }
        }

        public int editDistance(int a, int b, int x, int y) {
            return Math.abs(a - x) + Math.abs(b - y);
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
