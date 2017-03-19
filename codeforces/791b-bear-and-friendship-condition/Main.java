// Codeforces Round 405
// Bear and Friendship Condition
// Problem statement:
// http://codeforces.com/problemset/problem/791/B

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
        public List<Integer>[] adjList;
        boolean[] visited;
        int nodes;
        int edges;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                adjList[u].add(v);
                adjList[v].add(u);
            }

            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    nodes = 0;
                    edges = 0;
                    dfs(i);
                    if ((long) nodes * (nodes - 1) != edges) {
                        out.println("NO");
                        return;
                    }
                }
            }
            out.println("YES");
        }

        public void dfs(int idx) {
            visited[idx] = true;
            nodes++;
            edges += adjList[idx].size();
            for (int child : adjList[idx]) {
                if (!visited[child]) {
                    dfs(child);
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
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

