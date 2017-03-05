// Codeforces Round 403
// The Meeting Place Cannot Be Changed
// Problem statement:
// http://codeforces.com/problemset/problem/782/B

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
        int[] pos;
        int[] speed;
        int n;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            pos = in.readIntArray(n);
            speed = in.readIntArray(n);

            //perform binary search to find the solution
            double left = 0;
            double right = 1000000000;
            //solution has to be correct to 6 digits, to prevent rounding errors calculate to 7 digits
            while (left < right - 0.0000001) {
                double mid = (left + right) / 2;
                if (canMeet(mid)) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            out.printf("%.7f", right);
        }

        boolean canMeet(double t) {
            // checks if all friends can meet at time t
            // first let all of them move north and keep track of the southmost friend
            double meetPoint = pos[0] + t * speed[0];
            for (int i = 1; i < n; i++) {
                meetPoint = Math.min(meetPoint, pos[i] + t * speed[i]);
            }
            // now check if everyone can reach meetPoint
            for (int i = 0; i < n; i++) {
                if (pos[i] - t * speed[i] > meetPoint) return false;
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
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

