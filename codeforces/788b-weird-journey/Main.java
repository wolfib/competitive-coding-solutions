// Codeforces Round 407
// Weird journey
// Problem statement:
// http://codeforces.com/contest/788/problem/B

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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        boolean[] visited;
        int[][] adjArr;
        boolean[] selfloops;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            long loopCount = 0;
            selfloops = new boolean[n];
            int[] rank = new int[n];
            int[] u = new int[m];
            int[] v = new int[m];

            int start = -1;
            for (int i = 0; i < m; i++) {
                u[i] = in.nextInt() - 1;
                v[i] = in.nextInt() - 1;
                if (u[i] == v[i]) {
                    selfloops[u[i]] = true;
                    loopCount++;
                } else {
                    start = u[i];
                    rank[u[i]]++;
                    rank[v[i]]++;
                }
            }

            adjArr = new int[n][];
            for (int i = 0; i < n; i++) {
                adjArr[i] = new int[rank[i]];
            }
            int[] pointers = new int[n];
            for (int i = 0; i < m; i++) {
                if (u[i] != v[i]) {
                    adjArr[u[i]][pointers[u[i]]++] = v[i];
                    adjArr[v[i]][pointers[v[i]]++] = u[i];
                }
            }

            visited = new boolean[n];

            // edge-case: no non-self-loop edges
            if (start == -1) {
                out.println(0);
                return;
            }

            dfs(start);
            for (int i = 0; i < n; i++) {
                // if more than 1 connected component
                if ((!visited[i]) && ((selfloops[i]) || (rank[i] > 0))) {
                    out.println(0);
                    return;
                }
            }

            // all pairs of adjacent edges are a solution
            long result = 0;
            for (int i = 0; i < n; i++) {
                long x = rank[i];
                if (x > 1) {
                    result += ((x * (x - 1)) / 2);
                }
            }

            // each self-loop with a non-self-loop edge is a solution
            result += loopCount * (m - loopCount);

            // two self-loops are a solution
            result += ((loopCount * (loopCount - 1)) / 2);

            out.println(result);
        }

        public void dfs(int a) {
            visited[a] = true;
            for (int i = 0; i < adjArr[a].length; i++) {
                if (!visited[adjArr[a][i]]) {
                    dfs(adjArr[a][i]);
                }
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
                    //tok = new StringTokenizer(in.readLine(), ", \t\n\r\f"); //adds commas as delimeter
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

