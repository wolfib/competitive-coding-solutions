// Tinkoff Challenge - Elimination Round
// Presents in Bankopolis
// Problem statement:
// http://codeforces.com/problemset/problem/793/D

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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        int inf = 9999999;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int m = in.nextInt();

            if (k == 1) {
                out.println(0);
                return;
            }

            // dp [bikelanes taken] [location] [border]
            int dp[][][] = new int[k][n + 1][n + 1];
            for (int i = 0; i < k; i++) {
                for (int j = 0; j <= n; j++) {
                    Arrays.fill(dp[i][j], inf);
                }
            }

            int[] from = new int[m];
            int[] to = new int[m];
            int[] cost = new int[m];
            for (int i = 0; i < m; i++) {
                from[i] = in.nextInt();
                to[i] = in.nextInt();
                cost[i] = in.nextInt();
                if (from[i] < to[i]) {
                    dp[1][to[i]][from[i] + 1] = Math.min(dp[1][to[i]][from[i] + 1], cost[i]);
                    dp[1][to[i]][n] = Math.min(dp[1][to[i]][n], cost[i]);
                } else if (to[i] < from[i]) {
                    dp[1][to[i]][1] = Math.min(dp[1][to[i]][1], cost[i]);
                    dp[1][to[i]][from[i] - 1] = Math.min(dp[1][to[i]][from[i] - 1], cost[i]);
                }
            }

            for (int move = 2; move < k; move++) {
                for (int lane = 0; lane < m; lane++) {
                    int orig = from[lane];
                    int dest = to[lane];
                    if (orig < dest) {
                        for (int border = dest; border <= n; border++) {
                            if (dp[move - 1][orig][border] < inf) {
                                dp[move][dest][border] = Math.min(dp[move][dest][border], dp[move - 1][orig][border] + cost[lane]);
                                dp[move][dest][orig + 1] = Math.min(dp[move][dest][orig + 1], dp[move - 1][orig][border] + cost[lane]);
                            }
                        }
                    } else if (dest < orig) {
                        for (int border = 1; border <= dest; border++) {
                            if (dp[move - 1][orig][border] < inf) {
                                dp[move][dest][border] = Math.min(dp[move][dest][border], dp[move - 1][orig][border] + cost[lane]);
                                dp[move][dest][orig - 1] = Math.min(dp[move][dest][orig - 1], dp[move - 1][orig][border] + cost[lane]);
                            }
                        }
                    }

                }
            }

            int result = inf;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    result = Math.min(result, dp[k - 1][i][j]);
                }
            }
            if (result < inf) {
                out.println(result);
            } else {
                out.println(-1);
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

