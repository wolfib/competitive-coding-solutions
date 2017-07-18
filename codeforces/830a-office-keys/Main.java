// Codeforces Round 424
// Office Keys
// Problem statement:
// http://codeforces.com/problemset/problem/830/A

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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int people = in.nextInt();
            int keys = in.nextInt();
            long office = in.nextInt();
            long[] peoplePos = in.readLongArray(people);
            long[] keyPos = in.readLongArray(keys);
            Arrays.sort(peoplePos);
            Arrays.sort(keyPos);

            // observation: in the optimal solution the used keys are a contiguous sub-interval of the full array
            // --> no gaps
            // so we can simply try out all possibilities

            long result = Integer.MAX_VALUE;
            for (int i = 0; i < keys - people + 1; i++) {
                long maxT = -1;
                for (int j = 0; j < people; j++) {
                    int keyIndex = i + j;
                    maxT = Math.max(maxT, Math.abs(office - keyPos[keyIndex]) + Math.abs(peoplePos[j] - keyPos[keyIndex]));
                }
                result = Math.min(result, maxT);
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public long[] readLongArray(int n) {
            long[] ar = new long[n];
            for (int i = 0; i < n; i++) {
                ar[i] = nextLong();
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

