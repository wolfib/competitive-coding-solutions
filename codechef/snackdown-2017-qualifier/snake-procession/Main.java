// Codechef SnackDown 2017 Qualifier Round
// Snake Procession
// Problem statement:
// https://www.codechef.com/SNCKQL17/problems/SNAKPROC


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
        SnakeProcession solver = new SnakeProcession();
        solver.solve(1, in, out);
        out.close();
    }

    static class SnakeProcession {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int len = in.nextInt();
                String s = in.next();
                s = s.replace(".", "");
                boolean valid = true;
                if (s.length() % 2 == 0) {
                    for (int j = 0; j < s.length(); j += 2) {
                        if ((s.charAt(j) != 'H') || (s.charAt(j + 1) != 'T')) {
                            valid = false;
                            break;
                        }
                    }
                } else {
                    valid = false;
                }
                if (valid) {
                    out.println("Valid");
                } else {
                    out.println("Invalid");
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

