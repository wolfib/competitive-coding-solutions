// Tinkoff Challenge 2017 - Elimination Round
// Igor and his way to work
// Problem statement:
// http://codeforces.com/problemset/problem/793/B

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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        int[][] grid;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int rows = in.nextInt();
            int cols = in.nextInt();
            grid = new int[rows + 2][cols + 2];
            int xStart = 0;
            int yStart = 0;
            int xGoal = 0;
            int yGoal = 0;
            for (int i = 0; i < rows; i++) {
                String s = in.next();
                for (int j = 0; j < cols; j++) {
                    char c = s.charAt(j);
                    if (c == '.') {
                        grid[i + 1][j + 1] = 99;
                    } else if (c == 'S') {
                        grid[i + 1][j + 1] = 1;
                        xStart = j + 1;
                        yStart = i + 1;
                    } else if (c == 'T') {
                        grid[i + 1][j + 1] = 99;
                        xGoal = j + 1;
                        yGoal = i + 1;
                    }
                }
            }

            boolean[] startRow = new boolean[cols + 2];
            int col = xStart;
            while (grid[yStart][col] > 0) {
                startRow[col] = true;
                col++;
            }
            col = xStart;
            while (grid[yStart][col] > 0) {
                startRow[col] = true;
                col--;
            }
            boolean[] goalRow = new boolean[cols + 2];
            col = xGoal;
            while (grid[yGoal][col] > 0) {
                goalRow[col] = true;
                col++;
            }
            col = xGoal;
            while (grid[yGoal][col] > 0) {
                goalRow[col] = true;
                col--;
            }

            for (int i = 1; i <= cols; i++) {
                if (startRow[i] && goalRow[i]) {
                    if (isColFree(i, yStart, yGoal)) {
                        out.println("YES");
                        return;
                    }
                }
            }

            boolean[] startCol = new boolean[rows + 2];
            int row = yStart;
            while (grid[row][xStart] > 0) {
                startCol[row] = true;
                row++;
            }
            row = yStart;
            while (grid[row][xStart] > 0) {
                startCol[row] = true;
                row--;
            }

            boolean[] goalCol = new boolean[rows + 2];
            row = yGoal;
            while (grid[row][xGoal] > 0) {
                goalCol[row] = true;
                row++;
            }
            row = yGoal;
            while (grid[row][xGoal] > 0) {
                goalCol[row] = true;
                row--;
            }
            for (int i = 1; i <= rows; i++) {
                if (startCol[i] && goalCol[i]) {
                    if (isRowFree(i, xStart, xGoal)) {
                        out.println("YES");
                        return;
                    }
                }
            }
            out.println("NO");
        }

        boolean isColFree(int col, int row1, int row2) {
            if (row1 > row2) {
                int temp = row1;
                row1 = row2;
                row2 = temp;
            }

            boolean result = true;
            for (int i = row1; i <= row2; i++) {
                if (grid[i][col] == 0) {
                    result = false;
                    break;
                }
            }
            return result;
        }

        boolean isRowFree(int row, int col1, int col2) {
            if (col1 > col2) {
                int temp = col1;
                col1 = col2;
                col2 = temp;
            }

            boolean result = true;
            for (int i = col1; i <= col2; i++) {
                if (grid[row][i] == 0) {
                    result = false;
                    break;
                }
            }
            return result;
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

