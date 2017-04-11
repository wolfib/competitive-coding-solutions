// Codeforces Round 407
// Masha and geometric depression
// Problem statement:
// http://codeforces.com/contest/789/problem/B

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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            long first = in.nextLong();
            long mult = in.nextLong();
            long limit = in.nextInt();
            int m = in.nextInt();
            Set<Long> a = new HashSet<>();
            for (int i = 0; i < m; i++) {
                a.add(in.nextLong());
            }

            if (first == 0) {
                if (a.contains(0L)) {
                    out.println(0);
                    return;
                } else {
                    out.println("inf");
                    return;
                }
            }

            if (Math.abs(first) > limit) {
                out.println(0);
                return;
            }

            if (mult == 0) {
                if (a.contains(0L)) {
                    if (a.contains(first)) {
                        out.println(0);
                        return;
                    } else {
                        out.println(1);
                        return;
                    }
                } else {
                    out.println("inf");
                    return;
                }
            }

            if (mult == 1) {
                if (a.contains(first)) {
                    out.println(0);
                    return;
                } else {
                    out.println("inf");
                    return;
                }
            }

            if (mult == -1) {
                if ((a.contains(first)) && (a.contains(-first))) {
                    out.println(0);
                    return;
                } else {
                    out.println("inf");
                    return;
                }
            }

            long current = first;
            int count = 0;
            while (Math.abs(current) <= limit) {
                if (!a.contains(current)) {
                    count++;
                }
                current *= mult;
            }
            out.println(count);
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
                    //tok = new StringTokenizer(in.readLine(), ", \t\n\r\f"); //adds commas as delimeter
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }
    }
}

