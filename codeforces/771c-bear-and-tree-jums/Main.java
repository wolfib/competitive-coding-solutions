// Codeforces Round 405
// Bear and Tree Jumps
// Problem statement:
// http://codeforces.com/problemset/problem/771/C

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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        int n;
        int k;
        Node[] nodes;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            k = in.nextInt();
            nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node();
            }

            for (int i = 0; i < n - 1; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                nodes[u].neighbors.add(v);
                nodes[v].neighbors.add(u);
            }

            // calculate solution for k = 1
            long result = dfsPairwiseDistanceSum(0, 0);

            if (k > 1) {
                dfsCalculateAdjustments(0, 0);
                // for each path with length (i % k), add (k - i) so that the resulting path length is 0 % k
                for (int i = 1; i < k; i++) {
                    result += ((long) (k - i)) * (nodes[0].startingPaths[i] + nodes[0].containedPaths[i]);
                }
                result /= k;
            }

            out.println(result);
        }

        void dfsCalculateAdjustments(int idx, int parentIdx) {
            for (int child : nodes[idx].neighbors) {
                if (child != parentIdx) {
                    dfsCalculateAdjustments(child, idx);
                    for (int i = 0; i < k; i++) {
                        for (int j = 0; j < k; j++) {
                            nodes[idx].containedPaths[(i + j + 1) % k] += nodes[idx].startingPaths[i] * nodes[child].startingPaths[j];
                        }
                        nodes[idx].containedPaths[(i + 1) % k] += nodes[idx].startingPaths[i];
                        nodes[idx].containedPaths[i] += nodes[child].containedPaths[i] + nodes[child].startingPaths[i];
                    }
                    for (int i = 0; i < k; i++) {
                        nodes[idx].startingPaths[(i + 1) % k] += nodes[child].startingPaths[i];
                    }
                    nodes[idx].startingPaths[1]++;
                }
            }
        }

        long dfsPairwiseDistanceSum(int idx, int parentIdx) {
            long result = 0;
            int sizeOfSubtree = 1;
            for (int child : nodes[idx].neighbors) {
                if (child != parentIdx) {
                    result += dfsPairwiseDistanceSum(child, idx);
                    sizeOfSubtree += nodes[child].sizeOfSubtree;
                    result += (long) nodes[child].sizeOfSubtree * (n - nodes[child].sizeOfSubtree);
                }
            }
            nodes[idx].sizeOfSubtree = sizeOfSubtree;
            return result;
        }

        class Node {
            List<Integer> neighbors;
			int sizeOfSubtree; // number of nodes in the subtree rooted in this node
			long[] startingPaths; // number of paths from this node to one of the nodes in its subtree categorized by path length mod k
			long[] containedPaths; // number of paths within this node's subtree which do not end in this node

            public Node() {
                neighbors = new ArrayList<>();
                sizeOfSubtree = 0;
                containedPaths = new long[k];
                startingPaths = new long[k];
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

