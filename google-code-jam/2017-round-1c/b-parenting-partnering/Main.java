// Google Code Jam 2017 Round 1C
// B - Parenting Partnering
// Problem statement:
// https://code.google.com/codejam/contest/3274486/dashboard

import java.io.OutputStream;
import java.io.FilenameFilter;
import java.util.Locale;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
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
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "B-(small|large).*[.]in";
            File directory = new File(".");
            File[] candidates = directory.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.matches(regex);
                }
            });
            File toRun = null;
            for (File candidate : candidates) {
                if (toRun == null || candidate.lastModified() > toRun.lastModified())
                    toRun = candidate;
            }
            inputStream = new FileInputStream(toRun);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("b.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int ac = in.nextInt();
            int aj = in.nextInt();

            int[] timeSteps = new int[1440];
            for (int i = 0; i < ac; i++) {
                int begin = in.nextInt();
                int end = in.nextInt();
                for (int j = begin; j < end; j++) {
                    timeSteps[j] = 1;
                }
            }
            for (int i = 0; i < aj; i++) {
                int begin = in.nextInt();
                int end = in.nextInt();
                for (int j = begin; j < end; j++) {
                    timeSteps[j] = 2;
                }
            }

            int[][] dp = new int[2][721];
            Arrays.fill(dp[0], 999999);
            Arrays.fill(dp[1], 999999);
            dp[0][0] = 0;
            dp[1][0] = 0;

            for (int time = 0; time < 1440; time++) {
                int[][] dpNew = new int[2][721];
                dpNew[0][0] = 999999;
                dpNew[1][0] = Math.min(dp[0][0] + 1, dp[1][0]);
                if (timeSteps[time] == 1) {
                    dpNew[0][0] = 999999;
                } else if (timeSteps[time] == 2) {
                    dpNew[1][0] = 999999;
                }

                for (int i = 1; i <= 720; i++) {
                    dpNew[0][i] = Math.min(dp[0][i - 1], dp[1][i - 1] + 1);
                    dpNew[1][i] = Math.min(dp[0][i] + 1, dp[1][i]);
                    if (timeSteps[time] == 1) {
                        dpNew[0][i] = 999999;
                    } else if (timeSteps[time] == 2) {
                        dpNew[1][i] = 999999;
                    }
                }
                dp = dpNew;
            }
            int result = Math.min(dp[0][720], dp[1][720]);
            if (result % 2 == 1) result++;

            out.println("Case #" + testNumber + ": " + result);
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

