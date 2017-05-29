// Codechef SnackDown 2017 Pre-elimination Round A
// Protecting the Poison
// Problem statement:
// https://www.codechef.com/SNCKPA17/problems/PROTEPOI

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
import java.util.Collections;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        ProtectingThePoison solver = new ProtectingThePoison();
        solver.solve(1, in, out);
        out.close();
    }

    static class ProtectingThePoison {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int t = in.nextInt();
            for (int i = 0; i < t; i++) {
                out.println(solvePoison(in, out));
            }
        }

        int solvePoison(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int m = in.nextInt();
            List<Interval> vert = new ArrayList<>();
            List<Interval> hor = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int hx = in.nextInt();
                int hy = in.nextInt();
                int tx = in.nextInt();
                int ty = in.nextInt();
                vert.add(new Interval(Math.min(hy, ty), Math.max(hy, ty)));
                hor.add(new Interval(Math.min(hx, tx), Math.max(hx, tx)));
            }

            int r1 = countCover(vert, (n - k) / 2 + 1, (n - k) / 2 + k);
            int r2 = countCover(hor, (n - k) / 2 + 1, (n - k) / 2 + k);
            //System.out.println(r1);
            //System.out.println(r2);
            if (r1 < 1) return -1;
            if (r2 < 1) return -1;
            return r1 + r2;
        }

        int countCover(List<Interval> intervals, int start, int stop) {
            Collections.sort(intervals);

            int currentEnd = start - 1;
            int nextEnd = 0;
            int result = 0;
            for (Interval a : intervals) {
                if (a.left <= currentEnd) {
                    nextEnd = Math.max(nextEnd, a.right);
                } else if (a.left == currentEnd + 1) {
                    result++;
                    nextEnd = Math.max(nextEnd, a.right);
                    currentEnd = nextEnd;
                    if (currentEnd >= stop) return result;
                } else {
                    result++;
                    currentEnd = nextEnd;
                    if (currentEnd >= stop) return result;
                    if (a.left > currentEnd + 1) {
                        return -1;
                    }
                    if (a.left == currentEnd + 1) {
                        result++;
                        currentEnd = a.right;
                        if (currentEnd >= stop) return result;
                    }
                    nextEnd = Math.max(nextEnd, a.right);
                }
            }
            if (nextEnd >= stop) return result + 1;
            return -2;
        }

        class Interval implements Comparable<Interval> {
            int left;
            int right;

            public Interval(int left, int right) {
                this.left = left;
                this.right = right;
            }


            public int compareTo(Interval other) {
                return left - other.left;
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

