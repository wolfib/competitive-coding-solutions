// AtCoder Grand Contest 12
// Splatter Painting
// Problem statement:
// http://agc012.contest.atcoder.jp/tasks/agc012_b

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
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
        SplatterPainting solver = new SplatterPainting();
        solver.solve(1, in, out);
        out.close();
    }

    static class SplatterPainting {
        int[][] g;
        int[] nodeColor;
        int[] coloredDistance;
        int n;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            int m = in.nextInt();
            int[] u = new int[m];
            int[] v = new int[m];
            int[] degree = new int[n];
            for (int i = 0; i < m; i++) {
                u[i] = in.nextInt() - 1;
                v[i] = in.nextInt() - 1;
                degree[u[i]]++;
                degree[v[i]]++;
            }
            int q = in.nextInt();
            int[] origin = new int[q];
            int[] dist = new int[q];
            int[] color = new int[q];
            for (int i = 0; i < q; i++) {
                origin[i] = in.nextInt() - 1;
                dist[i] = in.nextInt();
                color[i] = in.nextInt();
            }

            g = new int[n][];
            for (int i = 0; i < n; i++) {
                g[i] = new int[degree[i]];
            }
            int[] pointers = new int[n];
            for (int i = 0; i < m; i++) {
                g[u[i]][pointers[u[i]]++] = v[i];
                g[v[i]][pointers[v[i]]++] = u[i];
            }

            nodeColor = new int[n];
            coloredDistance = new int[n]; // keeps track of how big of a neighborhood around node is already painted
            Arrays.fill(coloredDistance, -1);

            for (int i = q - 1; i >= 0; i--) {
                dfs(origin[i], dist[i], color[i]);
            }

            for (int i = 0; i < n; i++) {
                out.println(nodeColor[i] + " ");
            }
        }

        void dfs(int node, int dist, int color) {
            if (nodeColor[node] == 0) {
                nodeColor[node] = color;
            }
            coloredDistance[node] = dist;
            for (int neighbor : g[node]) {
                if (dist - 1 > coloredDistance[neighbor]) { // only visit neighbor if neighborhood to paint is bigger than neighborhood of a previous visit
                    dfs(neighbor, dist - 1, color);
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

