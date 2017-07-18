// Codeforces Round 419
// Karen and Game
// Problem statement:
// http://codeforces.com/problemset/problem/815/A

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
        int rows;
        int cols;
        int[][] goal;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            rows = in.nextInt();
            cols = in.nextInt();
            goal = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    goal[i][j] = in.nextInt();
                }
            }

            //check grid;
            for (int i = 1; i < rows; i++) {
                if (!checkRows(i - 1, i)) {
                    out.println(-1);
                    return;
                }
            }
            for (int i = 1; i < cols; i++) {
                if (!checkCols(i - 1, i)) {
                    out.println(-1);
                    return;
                }
            }

            int moves = 0;
            int minFirstCol = Integer.MAX_VALUE;
            for (int row = 0; row < rows; row++) {
                minFirstCol = Math.min(minFirstCol, goal[row][0]);
            }
            int[] rowMoves = new int[rows];
            for (int row = 0; row < rows; row++) {
                rowMoves[row] = goal[row][0] - minFirstCol;
                moves += rowMoves[row];
            }

            int minFirstRow = Integer.MAX_VALUE;
            for (int col = 0; col < cols; col++) {
                minFirstRow = Math.min(minFirstRow, goal[0][col]);
            }
            int[] colMoves = new int[cols];
            for (int col = 0; col < cols; col++) {
                colMoves[col] = goal[0][col] - minFirstRow;
                moves += colMoves[col];
            }

            int diff = goal[0][0] - rowMoves[0] - colMoves[0];
            if (diff < 0) {
                out.println(-1);
                return;
            }

            if (rows < cols) {
                for (int i = 0; i < rows; i++) rowMoves[i] += diff;
            } else {
                for (int i = 0; i < cols; i++) colMoves[i] += diff;
            }

            moves += Math.min(rows, cols) * diff;
            out.println(moves);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < rowMoves[i]; j++) {
                    out.println("row " + (i + 1));
                }
            }
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < colMoves[i]; j++) {
                    out.println("col " + (i + 1));
                }
            }
        }

        boolean checkRows(int a, int b) {
            int diff = goal[a][0] - goal[b][0];
            for (int i = 1; i < cols; i++) {
                if (goal[a][i] != goal[b][i] + diff) return false;
            }
            return true;
        }

        boolean checkCols(int a, int b) {
            int diff = goal[0][a] - goal[0][b];
            for (int i = 1; i < rows; i++) {
                if (goal[i][a] != goal[i][b] + diff) return false;
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

