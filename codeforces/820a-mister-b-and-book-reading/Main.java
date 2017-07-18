// Codeforces Round 421
// Mister B and Book Reading
// Problem statement:
// http://codeforces.com/problemset/problem/820/A

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
            int pages = in.nextInt();
            int v0 = in.nextInt();
            int v1 = in.nextInt();
            int a = in.nextInt();
            int l = in.nextInt();

            int startOfDay = 0;
            int currentSpeed = v0;
            int day = 0;
            while (true) {
                day++;
                if (startOfDay + currentSpeed >= pages) {
                    break;
                }
                startOfDay += currentSpeed - l;
                currentSpeed = Math.min(v1, currentSpeed + a);
            }
            out.println(day);
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

