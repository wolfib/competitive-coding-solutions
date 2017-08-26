// Codeforces Round 424
// Sagheer and Kindergarten
// Problem statement:
// http://codeforces.com/problemset/problem/812/D

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
        List<Integer>[] blocks;
        int[] subtreeSize;
        int[] enteringTime;
        int[] leavingTime;
        int time;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int children = in.nextInt();
            int toys = in.nextInt();
            int requests = in.nextInt();
            int queries = in.nextInt();

            int[] lastUserOf = new int[toys + 1];
            int[] waitingFor = new int[children + 1];
            blocks = new List[children + 1];
            for (int i = 0; i <= children; i++) {
                blocks[i] = new ArrayList<>();
            }

            for (int i = 0; i < requests; i++) {
                int child = in.nextInt();
                int toy = in.nextInt();
                if (lastUserOf[toy] == 0) {
                    lastUserOf[toy] = child;
                } else {
                    waitingFor[child] = lastUserOf[toy];
                    blocks[lastUserOf[toy]].add(child);
                    lastUserOf[toy] = child;
                }
            }

            subtreeSize = new int[children + 1];
            enteringTime = new int[children + 1];
            leavingTime = new int[children + 1];
            time = 1;
            for (int i = 1; i <= children; i++) {
                if (waitingFor[i] == 0) {
                    dfs(i);
                }
            }

            for (int i = 0; i < queries; i++) {
                int child = in.nextInt();
                int toy = in.nextInt();
                if (lastUserOf[toy] == 0) {
                    out.println(0);
                } else {
                    if (isAncestor(child, lastUserOf[toy])) {
                        out.println(subtreeSize[child]);
                    } else {
                        out.println(0);
                    }
                }
            }
        }

        void dfs(int node) {
            subtreeSize[node] = 1;
            enteringTime[node] = time++;
            for (int childNode : blocks[node]) {
                dfs(childNode);
                subtreeSize[node] += subtreeSize[childNode];
            }
            leavingTime[node] = time++;
        }

        boolean isAncestor(int ancestorNode, int node) {
            return ((enteringTime[ancestorNode] < enteringTime[node]) && (leavingTime[ancestorNode] > leavingTime[node]));
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

