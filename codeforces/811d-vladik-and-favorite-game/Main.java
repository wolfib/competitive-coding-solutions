// Codeforces Round 416
// Vladik and Favorite Game
// Problem statement:
// http://codeforces.com/problemset/problem/811/D

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Collection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Queue;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        boolean verticalKnown = false;
        boolean horizontalKnown = false;
        char downChar = 'D';
        char upChar = 'U';
        char leftChar = 'L';
        char rightChar = 'R';

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int rows = in.nextInt();
            int cols = in.nextInt();
            int[][] grid = new int[rows + 2][cols + 2];
            Point goal = new Point(-1, -1);
            for (int i = 1; i <= rows; i++) {
                String s = in.next();
                for (int j = 1; j <= cols; j++) {
                    char c = s.charAt(j - 1);
                    if (c == '.') grid[i][j] = 1;
                    else if (c == 'F') {
                        grid[i][j] = 2;
                        goal = new Point(i, j);
                    }
                }
            }

            Queue<Point> q = new LinkedList<>();
            q.offer(new Point(1, 1));
            boolean[][] visited = new boolean[rows + 2][cols + 2];
            int[][] predRow = new int[rows + 2][cols + 2];
            int[][] predCol = new int[rows + 2][cols + 2];
            visited[1][1] = true;

            while (!q.isEmpty()) {
                Point p = q.poll();
                if ((p.row == goal.row) && (p.col == goal.col)) break;
                if ((grid[p.row + 1][p.col] >= 1) && (!visited[p.row + 1][p.col])) {
                    q.offer(new Point(p.row + 1, p.col));
                    visited[p.row + 1][p.col] = true;
                    predRow[p.row + 1][p.col] = p.row;
                    predCol[p.row + 1][p.col] = p.col;
                }
                if ((grid[p.row][p.col + 1] >= 1) && (!visited[p.row][p.col + 1])) {
                    q.offer(new Point(p.row, p.col + 1));
                    visited[p.row][p.col + 1] = true;
                    predRow[p.row][p.col + 1] = p.row;
                    predCol[p.row][p.col + 1] = p.col;
                }
                if ((grid[p.row - 1][p.col] >= 1) && (!visited[p.row - 1][p.col])) {
                    q.offer(new Point(p.row - 1, p.col));
                    visited[p.row - 1][p.col] = true;
                    predRow[p.row - 1][p.col] = p.row;
                    predCol[p.row - 1][p.col] = p.col;
                }
                if ((grid[p.row][p.col - 1] >= 1) && (!visited[p.row][p.col - 1])) {
                    q.offer(new Point(p.row, p.col - 1));
                    visited[p.row][p.col - 1] = true;
                    predRow[p.row][p.col - 1] = p.row;
                    predCol[p.row][p.col - 1] = p.col;
                }

            }

            Stack<Point> path = new Stack<>();
            Point current = new Point(goal.row, goal.col);
            path.push(current);
            while (predRow[current.row][current.col] != 0) {
                current = new Point(predRow[current.row][current.col], predCol[current.row][current.col]);
                path.push(current);
            }

            current = path.pop();
            while (!path.empty()) {
                Point nextPoint = path.pop();
                if (nextPoint.row > current.row) printDirection('d', in, out, current.row, current.col);
                if (nextPoint.row < current.row) printDirection('u', in, out, current.row, current.col);
                if (nextPoint.col < current.col) printDirection('l', in, out, current.row, current.col);
                if (nextPoint.col > current.col) printDirection('r', in, out, current.row, current.col);
                current = nextPoint;
            }
        }

        void printDirection(char dir, InputReader in, PrintWriter out, int currentRow, int currentCol) {
            if (dir == 'd') {
                if (verticalKnown) {
                    out.println(downChar);
                    out.flush();
                    in.nextInt();
                    in.nextInt();
                } else {
                    out.println("D");
                    out.flush();
                    int nextRow = in.nextInt();
                    int nextCol = in.nextInt();
                    if (nextRow == currentRow) {
                        downChar = 'U';
                        upChar = 'D';
                        verticalKnown = true;
                        printDirection('d', in, out, currentRow, currentCol);
                    } else {
                        verticalKnown = true;
                    }
                }
            } else if (dir == 'r') {
                if (horizontalKnown) {
                    out.println(rightChar);
                    out.flush();
                    in.nextInt();
                    in.nextInt();
                } else {
                    out.println("R");
                    out.flush();
                    int nextRow = in.nextInt();
                    int nextCol = in.nextInt();
                    if (nextCol == currentCol) {
                        rightChar = 'L';
                        leftChar = 'R';
                        horizontalKnown = true;
                        printDirection('r', in, out, currentRow, currentCol);
                    } else {
                        horizontalKnown = true;
                    }
                }
            } else if (dir == 'l') {
                out.println(leftChar);
                out.flush();
                in.nextInt();
                in.nextInt();
            } else if (dir == 'u') {
                out.println(upChar);
                out.flush();
                in.nextInt();
                in.nextInt();
            }
        }

        class Point {
            int row;
            int col;

            public Point(int row, int col) {
                this.row = row;
                this.col = col;
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

