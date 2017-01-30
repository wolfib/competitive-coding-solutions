// Codeforces 8VC Venture Cup 2017 - Final Round
// Pavel and barbecue
// Problem statement:
// http://codeforces.com/problemset/problem/756/A

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
            int[] p = new int[n];
            for (int i = 0; i < n; i++) p[i] = in.nextInt() - 1;
            int[] b = in.readIntArray(n);

            // b needs to contain an uneven number of 1s
            // to make sure that the skewers reach each position in both directions
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (b[i] == 1) count++;
            }
            int result = 0;
            if (count % 2 == 0) result = 1;

            // the permutation needs to be a single big circle
            // to make sure every position is reached
            // if it consists of multiple cycles
            // we need to introduce 1 change per cycle
            // to transform it into a single cycle
            boolean[] visited = new boolean[n];
            count = 0;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    count++;
                    int current = i;
                    while (!visited[current]) {
                        visited[current] = true;
                        current = p[current];
                    }
                }
            }
            if (count > 1) result += count;
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
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

