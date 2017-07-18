// Codeforces Round 423
// String Reconstruction
// Problem statement:
// http://codeforces.com/problemset/problem/827/A

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
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
            int n = in.nextInt();

            String[] s = new String[n];
            int[][] pos = new int[n][];
            int resultLength = 0;

            int[] lookup = new int[1000001];
            Arrays.fill(lookup, -1);

            for (int i = 0; i < n; i++) {
                s[i] = in.next();
                int m = in.nextInt();
                pos[i] = new int[m];
                for (int j = 0; j < m; j++) {
                    pos[i][j] = in.nextInt() - 1;
                    if (lookup[pos[i][j]] == -1 || s[lookup[pos[i][j]]].length() < s[i].length()) {
                        lookup[pos[i][j]] = i;
                    }
                }
                resultLength = Math.max(resultLength, s[i].length() + pos[i][m - 1]);
            }
            char[] result = new char[resultLength];
            Arrays.fill(result, 'a');

            int rightEnd = -1;
            int stringIndex = -1;
            int leftEnd = -1;
            for (int i = 0; i < resultLength; i++) {
                if (i < lookup.length && lookup[i] != -1) {
                    if (i - 1 + s[lookup[i]].length() > rightEnd) {
                        stringIndex = lookup[i];
                        leftEnd = i;
                        rightEnd = i + s[stringIndex].length() - 1;
                    }
                }
                if (rightEnd >= i) {
                    result[i] = s[stringIndex].charAt(i - leftEnd);
                }
            }
            out.print(result);
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

