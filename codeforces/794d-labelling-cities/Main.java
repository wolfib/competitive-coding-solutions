// Tinkoff Challenge - Final Round
// Labelling Cities
// Problem statement:
// http://codeforces.com/problemset/problem/794/D

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
import java.util.Collections;
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
        List<Integer>[] adjList;
        int[] label;
        boolean[] visited;
        Map<Integer, Integer> labelToNode;
        int n;
        int m;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            m = in.nextInt();
            adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                adjList[u].add(v);
                adjList[v].add(u);
            }
            for (int i = 0; i < n; i++) {
                adjList[i].add(i);
                Collections.sort(adjList[i]);
            }

            label = new int[n];
            visited = new boolean[n];
            label[0] = 0;
            labelToNode = new HashMap<>();
            labelToNode.put(0, 0);
            boolean result = dfs(0);
            if (!result) {
                out.println("NO");
                return;
            }
            out.println("YES");
            int minLabel = 0;
            for (int i = 0; i < n; i++) {
                minLabel = Math.min(minLabel, label[i]);
            }
            for (int i = 0; i < n; i++) {
                out.print((label[i] - minLabel + 1) + " ");
            }
        }

        boolean dfs(int node) {
            visited[node] = true;
            for (int child : adjList[node]) {
                if (visited[child]) {
                    if (Math.abs(label[child] - label[node]) > 1) {
                        return false;
                    }
                } else { // each child can only ever have label -1, label or label +1 as its label
                    if (hasSameNeighbors(child, node)) { //same label
                        label[child] = label[node];
                    } else if ((labelToNode.containsKey(label[node] - 1)) && (hasSameNeighbors(child, labelToNode.get(label[node] - 1)))) { //label -1
                        label[child] = label[labelToNode.get(label[node] - 1)];
                    } else if ((labelToNode.containsKey(label[node] + 1)) && (hasSameNeighbors(child, labelToNode.get(label[node] + 1)))) { //label +1
                        label[child] = label[labelToNode.get(label[node] + 1)];
                    } else { // we need new label
                        if (!labelToNode.containsKey(label[node] + 1)) {
                            label[child] = label[node] + 1;
                            labelToNode.put(label[child], child);
                        } else if (!labelToNode.containsKey(label[node] - 1)) {
                            label[child] = label[node] - 1;
                            labelToNode.put(label[child], child);
                        } else { // no unused new label available -> no solution possible
                            return false;
                        }
                    }
                    boolean result = dfs(child);
                    if (!result) return false;
                }
            }
            return true;
        }

        boolean hasSameNeighbors(int a, int b) {
            if (adjList[a].size() != adjList[b].size()) return false;
            for (int i = 0; i < adjList[a].size(); i++) {
                if (!adjList[a].get(i).equals(adjList[b].get(i))) { // == does not work because we're comparing Integer objects not ints
                    return false;
                }
            }
            return true;
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

