// Codeforces Round 423
// Restaurant Tables
// Problem statement:
// http://codeforces.com/problemset/problem/828/A

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
            int n = in.nextInt();
            int ones = in.nextInt();
            int twos = in.nextInt();
            int[] a = in.readIntArray(n);
            int halfFull = 0;
            int reject = 0;

            for (int i = 0; i < n; i++) {
                if (a[i] == 1) {
                    if (ones > 0) ones--;
                    else if (twos > 0) {
                        twos--;
                        halfFull++;
                    } else if (halfFull > 0) {
                        halfFull--;
                    } else {
                        reject++;
                    }
                } else {
                    if (twos > 0) twos--;
                    else reject += 2;
                }
            }
            out.println(reject);
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

