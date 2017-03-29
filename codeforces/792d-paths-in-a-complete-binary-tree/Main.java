// Educational Codeforces Round 18
// Paths in a Complete Binary Tree
// Problem statement:
// http://codeforces.com/problemset/problem/792/D

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
        long n;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextLong();
            int q = in.nextInt();
            while (q-- > 0) {
                long startNode = in.nextLong();
                String s = in.next();
                long endNode = traverse(startNode, s);
                out.println(endNode);
            }
        }

        long traverse(long node, String s) {
            int level = 1;
            long remainder = 1;
            while (node % (2 * remainder) != remainder) {
                remainder *= 2;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'U') {
                    if (node != (n + 1) / 2) {
                        if (node % (remainder * 4) == remainder) {
                            node += remainder;
                        } else {
                            node -= remainder;
                        }
                        remainder *= 2;
                    }
                } else if (c == 'L') {
                    if (node % 2 == 0) {
                        remainder /= 2;
                        node -= remainder;
                    }
                } else if (c == 'R') {
                    if (node % 2 == 0) {
                        remainder /= 2;
                        node += remainder;
                    }
                }
            }
            return node;
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

        public long nextLong() {
            return Long.parseLong(next());
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
