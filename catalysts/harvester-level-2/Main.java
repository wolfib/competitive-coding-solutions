// Catalysts Coding Contest
// Harvester Level 2 
// Problem statement:
// https://catcoder.catalysts.cc/

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
        Harvester2 solver = new Harvester2();
        solver.solve(1, in, out);
        out.close();
    }

    static class Harvester2 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int rows = in.nextInt();
            int cols = in.nextInt();
            int startRow = in.nextInt() - 1;
            int startCol = in.nextInt() - 1;

            int[][] field = new int[rows][cols];
            int count = 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    field[i][j] = count++;
                }
            }

            int horDir;
            int verDir;
            if (startCol == 0) {
                horDir = 1;
            } else {
                horDir = -1;
            }
            if (startRow == 0) {
                verDir = 1;
            } else {
                verDir = -1;
            }

            int currentRow = startRow;
            int currentCol = startCol;

            while (true) {
                out.print(field[currentRow][currentCol] + " ");
                if ((horDir == 1) && (currentCol < cols - 1)) {
                    currentCol++;
                } else if ((horDir == -1) && (currentCol > 0)) {
                    currentCol--;
                } else if ((verDir == 1) && (currentRow < rows - 1)) {
                    currentRow++;
                    horDir *= -1;
                } else if ((verDir == -1) && (currentRow > 0)) {
                    currentRow--;
                    horDir *= -1;
                } else {
                    break;
                }
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

