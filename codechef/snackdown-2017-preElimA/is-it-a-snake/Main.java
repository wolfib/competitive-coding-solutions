// Codechef SnackDown 2017 Pre-elimination Round A
// Is it a Snake
// Problem statement:
// https://www.codechef.com/SNCKPA17/problems/ISSNAKE

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
        IsItASnake solver = new IsItASnake();
        solver.solve(1, in, out);
        out.close();
    }

    static class IsItASnake {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int t = in.nextInt();
            for (int i = 0; i < t; i++) {
                boolean result = solveSnake(in, out);
                if (result) out.println("yes");
                else out.println("no");
            }
        }

        boolean solveSnake(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            String s1 = in.next();
            String s2 = in.next();

            int pos = 0;

            //find begin
            while ((s1.charAt(pos) == '.') && (s2.charAt(pos) == '.')) pos++;

            boolean endReached = false;
            int rowMarker = 3;
            while (pos < n) {
                char c1 = s1.charAt(pos);
                char c2 = s2.charAt(pos);

                if ((c1 == '#') && (c2 == '.')) {
                    if ((endReached) || (rowMarker == 2)) return false;
                    rowMarker = 1;
                } else if ((c1 == '.') && (c2 == '#')) {
                    if ((endReached) || (rowMarker == 1)) return false;
                    rowMarker = 2;
                } else if ((c1 == '#') && (c2 == '#')) {
                    if (endReached) return false;
                    if (rowMarker == 1) rowMarker = 2;
                    else if (rowMarker == 2) rowMarker = 1;
                } else if ((c1 == '.') && (c2 == '.')) {
                    endReached = true;
                }
                pos++;
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

