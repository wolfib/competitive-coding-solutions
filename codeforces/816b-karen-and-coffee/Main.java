// Codeforces Round 419
// Karen and Coffee
// Problem statement:
// http://codeforces.com/problemset/problem/816/B

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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        int[] a;
        final int maxTemp = 200000;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int q = in.nextInt();
            a = new int[maxTemp + 2];
            for (int i = 0; i < n; i++) {
                a[in.nextInt()]++;
                a[in.nextInt() + 1]--;
            }
            int current = 0;
            for (int i = 0; i <= maxTemp; i++) {
                current += a[i];
                if (current < k) a[i] = 0;
                else a[i] = 1;
            }

            SegmentTree segTree = new SegmentTree(a);

            for (int i = 0; i < q; i++) {
                int left = in.nextInt();
                int right = in.nextInt();
                out.println(segTree.query(left, right));
            }
        }

        class SegmentTree {
            int n;
            int[] tree;

            public SegmentTree(int[] a) {
                n = a.length;
                tree = new int[4 * n];
                build(a, 0, 0, n - 1);
            }

            int build(int[] a, int index, int left, int right) {
                if (left == right) {
                    tree[index] = a[left];
                    return a[left];
                }

                int mid = left + (right - left) / 2;
                int leftValue = build(a, 2 * index + 1, left, mid);
                int rightValue = build(a, 2 * index + 2, mid + 1, right);

                tree[index] = leftValue + rightValue; // interval sum
                return tree[index];
            }

            public int query(int qLeft, int qRight) {
                return query(qLeft, qRight, 0, 0, n - 1);
            }

            int query(int qLeft, int qRight, int index, int left, int right) {
                if (qLeft > right || qRight < left) return 0;
                if (qLeft <= left && qRight >= right) return tree[index];
                int mid = left + (right - left) / 2;
                int leftValue = query(qLeft, qRight, 2 * index + 1, left, mid);
                int rightValue = query(qLeft, qRight, 2 * index + 2, mid + 1, right);

                return leftValue + rightValue; // interval sum
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

