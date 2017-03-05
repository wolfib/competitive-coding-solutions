// Codeforces Round 403
// Andryusha and Colored Balloons
// Problem statement:
// http://codeforces.com/problemset/problem/781/A

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
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
        ArrayList<Integer>[] a;
        int[] color;
        int result;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            color = new int[n];

            a = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                a[i] = new ArrayList<>();
            }

            for (int i = 0; i < n - 1; i++) {
                int first = in.nextInt() - 1;
                int second = in.nextInt() - 1;
                a[first].add(second);
                a[second].add(first);
            }
            color[0] = 1;
            result = 1;
            dfs(0, 0);
            out.println(result);
            for (int i = 0; i < n; i++) {
                out.print(color[i] + " ");
            }
        }

        void dfs(int index, int parentIndex) {
            // depth first traversal of graph
            // paint each child node with color in ascending order skipping color of current node and of parent node
            int currentColor = 1;
            for (int childIndex : a[index]) {
                if (color[childIndex] == 0) {
                    while ((currentColor == color[index]) || (currentColor == color[parentIndex])) currentColor++;
                    color[childIndex] = currentColor;
                    result = Math.max(result, currentColor);
                    dfs(childIndex, index);
                    currentColor++;
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

