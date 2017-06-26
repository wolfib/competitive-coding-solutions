// Codeforces Round 417
// Sagheer and Apple Tree
// Problem statement:
// http://codeforces.com/problemset/problem/812/E

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
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        Node[] nodes;
        int n;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            // Problem is similar to staircase nim
            n = in.nextInt();
            nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(in.nextInt());
            }
            for (int i = 1; i < n; i++) {
                int p = in.nextInt() - 1;
                nodes[p].children.add(nodes[i]);
            }
            determineRelevance(nodes[0]);
            int xorSum = 0;
            int relevantNodes = 0;
            for (Node node : nodes) {
                if (node.isRelevant) {
                    relevantNodes++;
                    xorSum = (xorSum ^ node.apples);
                }
            }
            // if xorSum == 0, Sagheer wins this game

            int irrelevantNodes = n - relevantNodes;
            if (xorSum == 0) {
                // to keep it a winning game, swap 2 relevant nodes or 2 irrelevant nodes
                long result = (long) relevantNodes * (relevantNodes - 1) / 2;
                result += (long) irrelevantNodes * (irrelevantNodes - 1) / 2;

                // or swap 1 relevant and 1 irrelevant node which have the same number of apples
                Map<Integer, Integer> appleFreq = new HashMap<>();
                for (Node node : nodes) {
                    if (node.isRelevant) {
                        if (!appleFreq.containsKey(node.apples)) {
                            appleFreq.put(node.apples, 1);
                        } else {
                            appleFreq.put(node.apples, appleFreq.get(node.apples) + 1);
                        }
                    }
                }
                for (Node node : nodes) {
                    if (!node.isRelevant) {
                        if (appleFreq.containsKey(node.apples)) {
                            result += appleFreq.get(node.apples);
                        }
                    }
                }

                out.println(result);
            } else {
                // to turn a losing game into a winning game
                // swap a relevant with an irrelevant node such that the xor-sum becomes 0
                Map<Integer, Integer> xorFreq = new HashMap<>();
                for (Node node : nodes) {
                    if (node.isRelevant) {
                        int xor = xorSum ^ node.apples;
                        if (!xorFreq.containsKey(xor)) {
                            xorFreq.put(xor, 1);
                        } else {
                            xorFreq.put(xor, xorFreq.get(xor) + 1);
                        }
                    }
                }

                long result = 0;
                for (Node node : nodes) {
                    if (!node.isRelevant) {
                        if (xorFreq.containsKey(node.apples)) {
                            result += xorFreq.get(node.apples);
                        }
                    }
                }

                out.println(result);
            }


        }

        void determineRelevance(Node node) {
            // only leaves and nodes with even distance from leaves are relevant
            if (node.children.size() == 0) {
                node.isRelevant = true;
                return;
            }
            for (Node child : node.children) {
                determineRelevance(child);
            }
            node.isRelevant = !node.children.get(0).isRelevant;

        }

        class Node {
            List<Node> children;
            int apples;
            boolean isRelevant;

            public Node(int apples) {
                this.apples = apples;
                children = new ArrayList<>();
                isRelevant = false;
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

