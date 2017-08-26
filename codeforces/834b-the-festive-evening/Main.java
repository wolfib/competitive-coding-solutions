// Codeforces Round 426
// The Festive Evening
// Problem statement:
// http://codeforces.com/problemset/problem/834/B

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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            String s = in.next();

            Door[] doors = new Door[26];
            for (int i = 0; i < 26; i++) doors[i] = new Door(-1, -1);

            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'A';
                if (doors[c].first == -1) doors[c].first = i;
                doors[c].last = i;
            }

            Arrays.sort(doors);
            int result = 0;
            for (int i = 0; i < 26; i++) {
                if (doors[i].first > -1) {
                    int open = 0;
                    int pos = doors[i].first;
                    for (int j = 0; j < 26; j++) {
                        if (doors[j].first <= pos) open++;
                        if (doors[j].last < pos) open--;
                    }
                    result = Math.max(result, open);
                }
            }

            if (result > k) out.println("YES");
            else out.println("NO");
        }

        class Door implements Comparable<Door> {
            int first;
            int last;

            public Door(int f, int l) {
                first = f;
                last = l;
            }

            public int compareTo(Door other) {
                return first - other.first;
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

