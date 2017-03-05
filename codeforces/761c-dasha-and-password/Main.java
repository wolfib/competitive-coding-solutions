// Codeforces Round 394
// Dasha and Password
// Problem statement:
// http://codeforces.com/contest/761/problem/C

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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            String[] a = new String[n];
            int[][] b = new int[n][m];
            for (int i = 0; i < n; i++) {
                a[i] = in.next();
                for (int j = 0; j < m; j++) {
                    char c = a[i].charAt(j);
                    if (c == '#' || c == '*' || c == '&') b[i][j] = 2;
                    if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')
                        b[i][j] = 1;
                }
            }
            int has0 = 0;
            int has1 = 0;
            int has2 = 0;
            for (int i = 0; i < n; i++) {
                if (b[i][0] == 0) has0++;
                if (b[i][0] == 1) has1++;
                if (b[i][0] == 2) has2++;
            }
            if (has0 > 0 && has1 > 0 && has2 > 0) {
                out.println(0);
                return;
            }

            int result = 2 * m;
            for (int i = 0; i < n; i++) {
                if (b[i][0] == 0) has0--;
                if (b[i][0] == 1) has1--;
                if (b[i][0] == 2) has2--;
                for (int j = 1; j < m; j++) {
                    if (b[i][j] == 0) has0++;
                    if (b[i][j] == 1) has1++;
                    if (b[i][j] == 2) has2++;
                    if (has0 > 0 && has1 > 0 && has2 > 0) {
                        result = Math.min(result, Math.min(j, m - j));
                    }
                    for (int k = i + 1; k < n; k++) {
                        if (b[k][0] == 0) has0--;
                        if (b[k][0] == 1) has1--;
                        if (b[k][0] == 2) has2--;
                        for (int l = 1; l < m; l++) {
                            if (b[k][l] == 0) has0++;
                            if (b[k][l] == 1) has1++;
                            if (b[k][l] == 2) has2++;
                            if (has0 > 0 && has1 > 0 && has2 > 0) {
                                result = Math.min(result, Math.min(j, m - j) + Math.min(l, m - l));
                            }
                            if (b[k][l] == 0) has0--;
                            if (b[k][l] == 1) has1--;
                            if (b[k][l] == 2) has2--;
                        }
                        if (b[k][0] == 0) has0++;
                        if (b[k][0] == 1) has1++;
                        if (b[k][0] == 2) has2++;
                    }
                    if (b[i][j] == 0) has0--;
                    if (b[i][j] == 1) has1--;
                    if (b[i][j] == 2) has2--;
                }
                if (b[i][0] == 0) has0++;
                if (b[i][0] == 1) has1++;
                if (b[i][0] == 2) has2++;
            }
            out.println(result);
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
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

