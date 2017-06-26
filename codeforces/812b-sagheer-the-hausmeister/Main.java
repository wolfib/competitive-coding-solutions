// Codeforces Round 417
// Sagheer, the Hausmeister
// Problem statement:
// http://codeforces.com/problemset/problem/812/B

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
        boolean[][] grid;
        int floors;
        int rooms;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
			// greedy solution looking only 1 floor into the future does not work
            floors = in.nextInt();
            rooms = in.nextInt();
            grid = new boolean[floors][rooms + 2];
            int count = 0;
            for (int i = floors - 1; i >= 0; i--) {
                String s = in.next();
                for (int j = 1; j <= rooms; j++) {
                    if (s.charAt(j) == '1') {
                        grid[i][j] = true;
                        count++;
                    }
                }
            }

            out.println(walkRecursive(true, 0));
        }

        int walkRecursive(boolean leftEnd, int currentFloor) {
            int moves;
            int lastRoom;
            if (leftEnd) {
                lastRoom = rooms + 1;
                while ((lastRoom > 0) && (!grid[currentFloor][lastRoom])) {
                    lastRoom--;
                }
                moves = lastRoom;
            } else {
                lastRoom = 0;
                while ((lastRoom < rooms + 1) && (!grid[currentFloor][lastRoom])) {
                    lastRoom++;
                }
                moves = rooms + 1 - lastRoom;
            }
            if (currentFloor == floors - 1) return moves;
            int nextFloorLeft = walkRecursive(true, currentFloor + 1);
            if (nextFloorLeft == 0) {
                return moves;
            }
            int goLeft = nextFloorLeft + moves + lastRoom + 1;
            int goRight = walkRecursive(false, currentFloor + 1) + moves + rooms + 1 - lastRoom + 1;
            return Math.min(goLeft, goRight);
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

