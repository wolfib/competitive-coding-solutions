// Codeforces Round 418
// An express train to reveries
// Problem statement:
// http://codeforces.com/problemset/problem/814/B

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
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
            int[] a = in.readIntArray(n);
            int[] b = in.readIntArray(n);
            boolean[] visited = new boolean[n + 1];
            int[] p = new int[n];
            List<Integer> mismatches = new ArrayList<Integer>();

            for (int i = 0; i < n; i++) {
                if (a[i] == b[i]) {
                    p[i] = a[i];
                    visited[a[i]] = true;
                } else {
                    mismatches.add(i);
                }
            }

            if (mismatches.size() == 1) {
                for (int i = 1; i <= n; i++) {
                    if (!visited[i]) {
                        p[mismatches.get(0)] = i;
                    }
                }
            } else {
                List<Integer> unvisited = new ArrayList<Integer>();
                for (int i = 1; i <= n; i++) {
                    if (!visited[i]) {
                        unvisited.add(i);
                    }
                }

                p[mismatches.get(0)] = unvisited.get(0);
                p[mismatches.get(1)] = unvisited.get(1);

                if ((countMismatches(p, a) != 1) || (countMismatches(p, b) != 1)) {
                    p[mismatches.get(0)] = unvisited.get(1);
                    p[mismatches.get(1)] = unvisited.get(0);
                }
            }

            for (int i = 0; i < n; i++) {
                out.print(p[i] + " ");
            }


        }

        int countMismatches(int[] a, int[] b) {
            int n = a.length;
            int result = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] != b[i]) result++;
            }
            return result;
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

