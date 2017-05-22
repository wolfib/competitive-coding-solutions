// Tinkoff Challenge - Final Round
// Naming Company
// Problem statement:
// http://codeforces.com/contest/794/problem/C

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String low = in.next();
            char[] lows = low.toCharArray();
            String high = in.next();
            char[] highs = high.toCharArray();
            Arrays.sort(lows);
            Arrays.sort(highs);
            int n = low.length();
            char[] result = new char[n];

            int lowLeft = 0;
            int lowRight = (n + 1) / 2 - 1;
            int highLeft = (n + 1) / 2;
            int highRight = n - 1;
            boolean nextLow = true;

            int currentLeft = 0;
            int currentRight = n - 1;
            for (int i = 0; i < n; i++) {
                if (nextLow) {
                    if (lows[lowLeft] < highs[highRight]) {
                        result[currentLeft] = lows[lowLeft];
                        lowLeft++;
                        currentLeft++;
                    } else {
                        result[currentRight] = lows[lowRight];
                        lowRight--;
                        currentRight--;
                    }
                } else {
                    if (highs[highRight] > lows[lowLeft]) {
                        result[currentLeft] = highs[highRight];
                        highRight--;
                        currentLeft++;
                    } else {
                        result[currentRight] = highs[highLeft];
                        highLeft++;
                        currentRight--;
                    }
                }
                nextLow = !nextLow;
            }
            for (int i = 0; i < n; i++) {
                out.print("" + result[i]);
            }

        }

    }

    static class InputReader {
        private static BufferedReader in;
        private static StringTokenizer tok;

        public InputReader(InputStream in) {
            this.in = new BufferedReader(new InputStreamReader(in));
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

