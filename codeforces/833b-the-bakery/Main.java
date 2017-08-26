// Codeforces Round 426
// The Bakery
// Problem statement:
// http://codeforces.com/problemset/problem/833/B

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Set;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            // dp with segTree with lazy propagation
            int cakes = in.nextInt();
            int boxes = in.nextInt();
            int[] a = in.read1BasedIntArray(cakes);

            int[][] dp = new int[boxes + 1][cakes + 1];

            // initialize first row of dp array with number of distinct cakes for each interval [1..i]
            Set<Integer> seen = new HashSet<>();
            for (int i = 1; i <= cakes; i++) {
                seen.add(a[i]);
                dp[1][i] = seen.size();
            }

            for (int row = 2; row <= boxes; row++) {

                // init maxSegTree for this row
                // for each j: contains dp[row - 1][j] + value of new interval(j + 1, col)
                SegTreeLazy st = new SegTreeLazy(dp[row - 1]);

                Map<Integer, Integer> prev = new HashMap<>(); // position of previous occurence of number

                // step through row, updating dp[row][col] and segTree (range update) in each step
                for (int col = 1; col <= cakes; col++) {
                    if (col >= row) {
                        // update segTree
                        // add 1 to all columns from index of prev. occurence of a[col] until col - 1
                        int beginIndex = 1;
                        if (prev.containsKey(a[col])) beginIndex = prev.get(a[col]);
                        st.modify(beginIndex, col - 1, 1);

                        // query the segTree to get dp[row][col]
                        dp[row][col] = st.query(row - 1, col - 1);
                    }
                    prev.put(a[col], col);
                }
            }
            out.println(dp[boxes][cakes]);
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

        public int[] read1BasedIntArray(int n) {
            int[] ar = new int[n + 1];
            for (int i = 1; i <= n; i++) {
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

    static class SegTreeLazy {
        int n;
        int[] tree;
        int[] lazy;

        public SegTreeLazy(int[] a) {
            n = a.length;
            tree = new int[4 * n];
            lazy = new int[4 * n];
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

            //tree[index] = leftValue + rightValue; // interval sum (change here for min/max/other tree)
            tree[index] = Math.max(leftValue, rightValue); // interval max

            return tree[index];
        }

        public int query(int qLeft, int qRight) {
            return query(qLeft, qRight, 0, 0, n - 1);
        }

        int query(int qLeft, int qRight, int index, int left, int right) {
            if (lazy[index] != 0) {
                tree[index] += lazy[index];
                if (left != right) {
                    lazy[2 * index + 1] += lazy[index];
                    lazy[2 * index + 2] += lazy[index];
                }
                lazy[index] = 0;
            }
            if (qLeft > right || qRight < left) return 0;
            if (qLeft <= left && qRight >= right) return tree[index];
            int mid = left + (right - left) / 2;
            int leftValue = query(qLeft, qRight, 2 * index + 1, left, mid);
            int rightValue = query(qLeft, qRight, 2 * index + 2, mid + 1, right);

            //return leftValue + rightValue; // interval sum (change here for min/max/other tree)
            return Math.max(leftValue, rightValue); // interval max
        }

        public void modify(int left, int right, int diff) {
            modify(left, right, diff, 0, 0, n - 1);
        }

        void modify(int mLeft, int mRight, int diff, int index, int left, int right) {
            if (lazy[index] != 0) {
                tree[index] += lazy[index];
                if (left != right) {
                    lazy[2 * index + 1] += lazy[index];
                    lazy[2 * index + 2] += lazy[index];
                }
                lazy[index] = 0;
            }
            if (mLeft > right || mRight < left) return;

            // node is fully within range
            if (left >= mLeft && right <= mRight) {
                tree[index] += diff;

                if (left != right) {
                    lazy[2 * index + 1] += diff;
                    lazy[2 * index + 2] += diff;
                }
                return;
            }

            int mid = left + (right - left) / 2;
            modify(mLeft, mRight, diff, 2 * index + 1, left, mid);
            modify(mLeft, mRight, diff, 2 * index + 2, mid + 1, right);

            tree[index] = Math.max(tree[2 * index + 1], tree[2 * index + 2]);
        }

    }
}

