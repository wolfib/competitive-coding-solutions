// Codeforces Round 416
// Vladik and Memorable Tripe
// Problem statement:
// http://codeforces.com/problemset/problem/811/C

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Collections;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        int[] a;
        int n;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            a = in.readIntArray(n);

            // find leftmost and rightmost position of each value
            int[] leftMost = new int[5001];
            Arrays.fill(leftMost, -1);
            int[] rightMost = new int[5001];
            Arrays.fill(rightMost, -1);
            for (int i = 0; i < n; i++) {
                if (leftMost[a[i]] == -1) leftMost[a[i]] = i;
                rightMost[a[i]] = i;
            }

            // find all potential wagons
            // (minimum size is always optimal)
            List<Wagon> wagons = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (leftMost[a[i]] == i) {
                    int maxRight = rightMost[a[i]];
                    //int minLeft = i;
                    int j = i;
                    while (j < maxRight) {
                        if (leftMost[a[j]] < i) break;
                        maxRight = Math.max(maxRight, rightMost[a[j]]);
                        //minLeft = Math.min(min)
                        j++;
                    }
                    if (j == maxRight) {
                        wagons.add(new Wagon(i, j));
                    }
                }
            }

            Collections.sort(wagons);

            int[] dp = new int[n + 1];
            int nextWagonIndex = 0;
            for (int i = 0; i < n; i++) {
                dp[i + 1] = dp[i];
                Wagon w = wagons.get(nextWagonIndex);
                if (w.right == i) {
                    int alternative = w.value + dp[w.left];
                    dp[i + 1] = Math.max(dp[i + 1], alternative);
                    nextWagonIndex++;
                }
            }
            out.println(dp[n]);
        }

        class Wagon implements Comparable<Wagon> {
            int left;
            int right;
            int value;

            public Wagon(int left, int right) {
                this.left = left;
                this.right = right;
                value = 0;
                boolean[] visited = new boolean[5001];
                for (int i = left; i <= right; i++) {
                    if (!visited[a[i]]) {
                        value ^= a[i];
                        visited[a[i]] = true;
                    }
                }
            }


            public int compareTo(Wagon other) {
                return right - other.right;
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

