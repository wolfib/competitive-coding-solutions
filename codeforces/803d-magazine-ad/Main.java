// Educational Codeforces Round 20
// Magazine Ad
// Problem statement:
// http://codeforces.com/problemset/problem/803/D

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        ArrayList<Integer> words;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int maxLines = in.nextInt();
            String s = in.nextLine();
            words = new ArrayList<>();
            int current = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                current++;
                if (c == '-' || c == ' ') {
                    words.add(current);
                    current = 0;
                }
            }
            words.add(current);

            int left = 0;
            int right = s.length();
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (linesNeeded(mid) <= maxLines) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            out.println(left);
        }

        int linesNeeded(int w) {
            int result = 1;
            int current = 0;
            for (int word : words) {
                if (word > w) return Integer.MAX_VALUE;
                current += word;
                if (current > w) {
                    result++;
                    current = word;
                }
            }
            return result;
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

        public String nextLine() {
            String s = "";
            try {
                s = in.readLine();
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return s;
        }

    }
}

