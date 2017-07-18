// Codeforces Round 419
// Karen and Morning
// Problem statement:
// http://codeforces.com/problemset/problem/816/A

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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String s = in.next();
            int hour = Integer.parseInt(s.substring(0, 2));
            int minute = Integer.parseInt(s.substring(3));

            int count = 0;
            while (!isPalindrome(hour, minute)) {
                count++;
                if (minute < 59) minute++;
                else {
                    minute = 0;
                    if (hour < 23) hour++;
                    else hour = 0;
                }
            }
            out.println(count);
        }

        boolean isPalindrome(int hour, int minute) {
            return (hour / 10 == minute % 10) && (hour % 10 == minute / 10);
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

