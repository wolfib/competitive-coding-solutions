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

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Wolfgang Beyer
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        RelatedQuestions solver = new RelatedQuestions();
        solver.solve(1, in, out);
        out.close();
    }

    static class RelatedQuestions {
        ArrayList<Integer>[] graph;
        int[] t;
        int n;
        double[] bottomUpResults;
        double[] topDownResults;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            t = in.readIntArray(n);
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                graph[u].add(v);
                graph[v].add(u);
            }
            long startTime = System.currentTimeMillis();
            solve1(testNumber, in, out);
            long elapsedTime = System.currentTimeMillis() - startTime;
            //System.out.println("elapsed time: " + elapsedTime);

        /*startTime = System.currentTimeMillis();
        solve2(testNumber, in, out);
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("elapsed time: " + elapsedTime);*/
        }

        public void solve1(int testNumber, InputReader in, PrintWriter out) {
            bottomUpResults = new double[n];
            topDownResults = new double[n];
            bottomUp(0, -1);
            topDown(0, -1);

            int minIndex = -1;
            double min = Double.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int children = graph[i].size() - 1;
                double temp;
                if (i == 0) {
                    temp = bottomUpResults[i];
                } else {
                    temp = (bottomUpResults[i] * children + topDownResults[i]) / (children + 1);
                }
                temp += t[i];
                //System.out.println(i + ": " + temp);
                if (temp < min) {
                    min = temp;
                    minIndex = i + 1;
                }
            }
            out.println(minIndex);
        }

        double bottomUp(int node, int parent) {
            int childCount = 0;
            double result = 0;
            for (int child : graph[node]) {
                if (child != parent) {
                    childCount++;
                    result += bottomUp(child, node);
                }
            }
            if (childCount > 0) result /= childCount;
            bottomUpResults[node] = result;
            result += t[node];
            return result;
        }

        void topDown(int node, int parent) {
            double result = 0;
            if (parent != -1) {
                if (parent == 0) {
                    result = bottomUpResults[parent] * graph[parent].size() - (bottomUpResults[node] + t[node]);
                    if (graph[parent].size() > 1) result /= graph[parent].size() - 1;
                    result += t[parent];
                } else {
                    result = bottomUpResults[parent] * (graph[parent].size() - 1) - (bottomUpResults[node] + t[node]) + topDownResults[parent];
                    if (graph[parent].size() > 1) result /= graph[parent].size() - 1;
                    result += t[parent];
                }
            }
            topDownResults[node] = result;
            for (int child : graph[node]) {
                if (child != parent) {
                    topDown(child, node);
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

