// Educational Codeforces Round 24
// Sofa Thief
// Problem statement:
// http://codeforces.com/problemset/problem/818/C

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
        int[] tops;
        int[] bottoms;
        int[] lefts;
        int[] rights;
        int rows;
        int cols;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int sofaNumber = in.nextInt();
            cols = in.nextInt();
            rows = in.nextInt();
            tops = new int[rows + 1];
            bottoms = new int[rows + 1];
            lefts = new int[cols + 1];
            rights = new int[cols + 1];
            Sofa[] sofas = new Sofa[sofaNumber];
            for (int i = 0; i < sofaNumber; i++) {
                int col1 = in.nextInt();
                int row1 = in.nextInt();
                int col2 = in.nextInt();
                int row2 = in.nextInt();
                tops[Math.min(row1, row2)]++;
                bottoms[Math.max(row1, row2)]++;
                lefts[Math.min(col1, col2)]++;
                rights[Math.max(col1, col2)]++;
                sofas[i] = new Sofa(col1, row1, col2, row2);
            }

            for (int i = 1; i <= cols; i++) {
                lefts[i] += lefts[i - 1];
            }
            for (int i = 1; i <= rows; i++) {
                tops[i] += tops[i - 1];
            }
            for (int i = cols - 1; i >= 0; i--) {
                rights[i] += rights[i + 1];
            }
            for (int i = rows - 1; i >= 0; i--) {
                bottoms[i] += bottoms[i + 1];
            }

            int countLeft = in.nextInt();
            int countRight = in.nextInt();
            int countTop = in.nextInt();
            int countBottom = in.nextInt();

            for (int i = 0; i < sofaNumber; i++) {
                //System.out.println(sofas[i].getLeft());
                if (sofas[i].getLeft() == countLeft) {
                    if (sofas[i].getRight() == countRight) {
                        if (sofas[i].getTop() == countTop) {
                            if (sofas[i].getBottom() == countBottom) {
                                out.println(i + 1);
                                return;
                            }
                        }
                    }

                }
            }
            out.println(-1);
        }

        class Sofa {
            int row1;
            int row2;
            int col1;
            int col2;

            public Sofa(int c1, int r1, int c2, int r2) {
                row1 = r1;
                row2 = r2;
                col1 = c1;
                col2 = c2;
            }

            int getLeft() {
                int x = Math.max(col1, col2);
                if (x == 1) return 0;
                if (col1 == col2) return lefts[x - 1];
                else return lefts[x - 1] - 1;
            }

            int getRight() {
                int x = Math.min(col1, col2);
                if (x == cols) return 0;
                if (col1 == col2) return rights[x + 1];
                else return rights[x + 1] - 1;
            }

            int getTop() {
                int x = Math.max(row1, row2);
                if (x == 1) return 0;
                if (row1 == row2) return tops[x - 1];
                else return tops[x - 1] - 1;
            }

            int getBottom() {
                int x = Math.min(row1, row2);
                if (x == rows) return 0;
                if (row1 == row2) return bottoms[x + 1];
                else return bottoms[x + 1] - 1;
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

