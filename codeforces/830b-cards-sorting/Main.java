// Codeforces Round 424
// Cards Sorting
// Problem statement:
// http://codeforces.com/problemset/problem/830/B

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = in.readIntArray(n);

            // instead of counting the cards that lie between 2 consecutive minima
            // determine the lap/round on which each card gets removed
            // this is the number of times we reach that card
            // just  add up those numbers to get the result

            Set<Integer>[] bucket = new HashSet[100001];
            for (int i = 0; i <= 100000; i++) {
                bucket[i] = new HashSet<>();
            }

            for (int i = 0; i < n; i++) {
                bucket[a[i]].add(i);
            }

            int currentRound = 1;
            int currentPos = 0;
            int nextPos = 0;
            long result = 0;
            for (int i = 1; i <= 100000; i++) {
                for (int pos : bucket[i]) {
                    if (pos >= currentPos) {
                        result += currentRound;
                        nextPos = Math.max(nextPos, pos);
                    } else {
                        result += currentRound + 1;
                        nextPos = Math.max(nextPos, pos + n);
                    }
                }
                if (nextPos >= n) {
                    currentRound++;
                    nextPos -= n;
                }
                currentPos = nextPos;
            }
            out.println(result);
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

