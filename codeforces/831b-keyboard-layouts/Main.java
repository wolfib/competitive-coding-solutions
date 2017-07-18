// Codeforces Round 424
// Keyboard Layouts
// Problem statement:
// http://codeforces.com/problemset/problem/831/B

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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String first = in.next();
            String second = in.next();
            String query = in.next();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < query.length(); i++) {
                char q = query.charAt(i);

                if (Character.isLetter(q)) {
                    if (Character.isLowerCase(q)) {
                        result.append(second.charAt(first.indexOf(q)));
                    } else {
                        result.append(Character.toUpperCase(second.charAt(first.indexOf(Character.toLowerCase(q)))));
                    }
                } else {
                    result.append(q);
                }

            }
            out.println(result.toString());
        }

    }

    static class InputReader {
        private static BufferedReader in;
        private static StringTokenizer tok;

        public InputReader(InputStream in) {
            this.in = new BufferedReader(new InputStreamReader(in));
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

