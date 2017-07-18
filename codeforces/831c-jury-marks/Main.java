// Codeforces Round 424
// Jury Marks
// Problem statement:
// http://codeforces.com/problemset/problem/831/C

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
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
            int k = in.nextInt();
            int n = in.nextInt();
            int[] judge = in.readIntArray(k);
            int[] tempScores = in.readIntArray(n);

            int[] scoreSums = new int[k];
            scoreSums[0] = judge[0];
            for (int i = 1; i < k; i++) {
                scoreSums[i] = scoreSums[i - 1] + judge[i];
            }
            Arrays.sort(scoreSums);
            Arrays.sort(tempScores);

            Set<Integer> iniScores = new HashSet<>();
            for (int offset = 0; offset < k - n + 1; offset++) {

                int prevJudge = offset;
                int currentJudge = offset + 1;
                boolean isPossible = true;

                for (int i = 1; i < n; i++) {
                    int diff = tempScores[i] - tempScores[i - 1];
                    while ((currentJudge < k - 1) && (scoreSums[currentJudge] - scoreSums[prevJudge] < diff)) {
                        currentJudge++;
                    }
                    if (scoreSums[currentJudge] - scoreSums[prevJudge] != diff) {
                        isPossible = false;
                        break;
                    }
                    prevJudge = currentJudge;
                    currentJudge++;
                }
                if (isPossible) {
                    iniScores.add(tempScores[0] - scoreSums[offset]);
                }
            }
            out.println(iniScores.size());
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
                    //tok = new StringTokenizer(in.readLine(), ", \t\n\r\f"); //adds commas as delimeter
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

