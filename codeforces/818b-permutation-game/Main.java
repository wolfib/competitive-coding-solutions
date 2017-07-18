// Educational Codeforces Round 24
// Permutation Game
// Problem statement:
// http://codeforces.com/problemset/problem/818/B

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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] pos = in.readIntArray(m);
            int[] res = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            for (int i = 0; i < m - 1; i++) {
                int diff = pos[i + 1] - pos[i];
                if (diff <= 0) diff += n;
                if (res[pos[i]] != diff && (visited[diff] || res[pos[i]] != 0)) {
                    out.println(-1);
                    return;
                }
                res[pos[i]] = diff;
                visited[diff] = true;
            }
            int firstUnused = 1;
            for (int i = 1; i <= n; i++) {
                if (res[i] == 0) {
                    while (visited[firstUnused]) firstUnused++;
                    res[i] = firstUnused;
                    visited[firstUnused] = true;
                }
                out.print(res[i] + " ");
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

