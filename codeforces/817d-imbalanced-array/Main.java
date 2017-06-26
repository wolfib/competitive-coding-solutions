// Educational Codeforces Round 23
// Imbalanced Array
// Problem statement:
// http://codeforces.com/problemset/problem/817/D

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.Vector;
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
        int[] a;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            a = in.readIntArray(n);

            int[] minLeft = new int[n];
            int[] minRight = new int[n];

            Stack<Integer> stack = new Stack<>();
            minLeft[0] = 0;
            stack.add(0);
            for (int i = 1; i < n; i++) {
                while ((stack.size() > 0) && (a[stack.peek()] > a[i])) stack.pop();
                if (stack.size() > 0) {
                    minLeft[i] = stack.peek() + 1;
                } else {
                    minLeft[i] = 0;
                }
                stack.add(i);
            }

            stack = new Stack<>();
            minRight[n - 1] = n - 1;
            stack.add(n - 1);
            for (int i = n - 2; i >= 0; i--) {
                while ((stack.size() > 0) && (a[stack.peek()] >= a[i])) stack.pop();
                if (stack.size() > 0) {
                    minRight[i] = stack.peek() - 1;
                } else {
                    minRight[i] = n - 1;
                }
                stack.add(i);
            }

            int[] maxLeft = new int[n];
            int[] maxRight = new int[n];

            stack = new Stack<>();
            maxLeft[0] = 0;
            stack.add(0);
            for (int i = 1; i < n; i++) {
                while ((stack.size() > 0) && (a[stack.peek()] < a[i])) stack.pop();
                if (stack.size() > 0) {
                    maxLeft[i] = stack.peek() + 1;
                } else {
                    maxLeft[i] = 0;
                }
                stack.add(i);
            }

            stack = new Stack<>();
            maxRight[n - 1] = n - 1;
            stack.add(n - 1);
            for (int i = n - 2; i >= 0; i--) {
                while ((stack.size() > 0) && (a[stack.peek()] <= a[i])) stack.pop();
                if (stack.size() > 0) {
                    maxRight[i] = stack.peek() - 1;
                } else {
                    maxRight[i] = n - 1;
                }
                stack.add(i);
            }

            long minSum = 0;
            long maxSum = 0;
            for (int i = 0; i < n; i++) {
                long count = (long) (i - minLeft[i] + 1) * (minRight[i] - i + 1);
                minSum += count * a[i];
                count = (long) (i - maxLeft[i] + 1) * (maxRight[i] - i + 1);
                maxSum += count * a[i];
            }

            out.println(maxSum - minSum);
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

