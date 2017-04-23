// Educational Codeforces Round 19
// Broken BST
// Problem statement:
// http://codeforces.com/problemset/problem/797/D

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
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
        Node[] nodes;
        Set<Integer> correct;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            nodes = new Node[n];
            boolean[] hasParent = new boolean[n];
            for (int i = 0; i < n; i++) {
                int v = in.nextInt();
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                nodes[i] = new Node(l, r, v);
                if (l >= 0) hasParent[l] = true;
                if (r >= 0) hasParent[r] = true;
            }

            int rootIndex = 0;
            while (hasParent[rootIndex]) rootIndex++;

            correct = new HashSet<>();
            dfs(rootIndex, -1, Integer.MAX_VALUE);
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (!correct.contains(nodes[i].value)) {
                    count++;
                }
            }
            out.println(count);
        }

        void dfs(int idx, int min, int max) {
            if ((nodes[idx].value <= max) && (nodes[idx].value >= min)) {
                correct.add(nodes[idx].value);
            }

            if (nodes[idx].left >= 0) {
                dfs(nodes[idx].left, min, Math.min(max, nodes[idx].value));
            }
            if (nodes[idx].right >= 0) {
                dfs(nodes[idx].right, Math.max(min, nodes[idx].value), max);
            }
        }

        class Node {
            int left;
            int right;
            int value;

            public Node(int l, int r, int v) {
                left = l;
                right = r;
                value = v;
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

