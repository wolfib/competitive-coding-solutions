// Codeforces Round 417
// Sagheer and Crossroads
// Problem statement:
// http://codeforces.com/problemset/problem/812/A

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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            Part[] parts = new Part[4];
            for (int i = 0; i < 4; i++) {
                parts[i] = new Part(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
            }

            boolean result = false;
            for (int i = 0; i < 4; i++) {
                if (parts[i].pedestrian == 1) {
                    if (parts[i].left == 1) result = true;
                    if (parts[i].right == 1) result = true;
                    if (parts[i].straight == 1) result = true;
                    if (parts[(i + 1) % 4].left == 1) result = true;
                    if (parts[(i + 3) % 4].right == 1) result = true;
                    if (parts[(i + 2) % 4].straight == 1) result = true;
                }
            }

            if (result) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        class Part {
            int left;
            int right;
            int straight;
            int pedestrian;

            public Part(int left, int straight, int right, int pedestrian) {
                this.left = left;
                this.right = right;
                this.straight = straight;
                this.pedestrian = pedestrian;
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

