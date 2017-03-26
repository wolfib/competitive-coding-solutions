// Codeforces Round 406
// Berzerk
// Problem statement:
// http://codeforces.com/problemset/problem/786/A

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
            int k1 = in.nextInt();
            int[] s1 = in.readIntArray(k1);
            int k2 = in.nextInt();
            int[] s2 = in.readIntArray(k2);

            int[] res1 = new int[n]; // results for player 1
            int[] res2 = new int[n]; // results for player 2

            for (int i = 0; i < k1; i++) {
                res1[n - s1[i]] = 1; // player 1 wins on all fields from where he can directly reach the black hole at 0
            }
            for (int i = 0; i < k2; i++) {
                res2[n - s2[i]] = 1;  // player 2 wins on all fields from where he can directly reach the black hole at 0
            }
            res2[0] = -1; // player loses at black hole (because other player has moved monster there in prev. turn)
            res1[0] = -1;

            // now recursively determine winner/loser for all other positions
            // a player wins if he can reach one of the other player's losing fields
            // a player loses if all the fields he can reach are winning fields for the other player
            // if there is no additional field that can be fixed in a whole iteration, we are done and all unknown fields are loops
            boolean changed = true;
            while (changed) {
                changed = false;
                for (int i = n - 1; i > 0; i--) {

                    if (res1[i] == 0) {
                        boolean allWins = true;
                        boolean singleLoss = false;
                        for (int j = 0; j < k1; j++) {
                            if (res2[(i + s1[j]) % n] == -1) {
                                singleLoss = true;
                                break;
                            }
                            if (res2[(i + s1[j]) % n] != 1) {
                                allWins = false;
                            }
                        }
                        if (singleLoss) {
                            res1[i] = 1;
                            changed = true;
                        } else if (allWins) {
                            res1[i] = -1;
                            changed = true;
                        }
                    }

                    if (res2[i] == 0) {
                        boolean allWins = true;
                        boolean singleLoss = false;

                        for (int j = 0; j < k2; j++) {
                            if (res1[(i + s2[j]) % n] == -1) {
                                singleLoss = true;
                                break;
                            }
                            if (res1[(i + s2[j]) % n] != 1) {
                                allWins = false;
                            }
                        }
                        if (singleLoss) {
                            res2[i] = 1;
                            changed = true;
                        } else if (allWins) {
                            res2[i] = -1;
                            changed = true;
                        }
                    }
                }
            }

            for (int i = 1; i < n; i++) {
                if (res1[i] == 1) out.print("Win ");
                else if (res1[i] == -1) out.print("Lose ");
                else out.print("Loop ");
            }
            out.println();
            for (int i = 1; i < n; i++) {
                if (res2[i] == 1) out.print("Win ");
                else if (res2[i] == -1) out.print("Lose ");
                else out.print("Loop ");
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

        public int[] readIntArray(int n) {
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) {
                ar[i] = nextInt();
            }
            return ar;
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

