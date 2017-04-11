// Google Code Jam 2017 Qualification Round
// B - Tidy Numbers
// Problem statement:
// https://code.google.com/codejam/contest/3264486/dashboard

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
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            String number = in.next();
            int n = number.length();
            char[] digitChars = number.toCharArray();
            int[] digits = new int[n];
            for (int i = 0; i < n; i++) {
                digits[i] = digitChars[i] - 48;
            }

            int lastAscending = 0;
            while ((lastAscending < n - 1) && (digits[lastAscending + 1] >= digits[lastAscending])) {
                lastAscending++;
            }
            if (lastAscending == n - 1) {
                out.println("Case #" + testNumber + ": " + number);
                return;
            }

            int sameAsLastAsc = lastAscending;
            while ((sameAsLastAsc > 0) && (digits[sameAsLastAsc - 1] == digits[lastAscending])) {
                sameAsLastAsc--;
            }

            Long result = Long.parseLong(number);
            result -= result % ((long) Math.pow(10, n - sameAsLastAsc - 1));
            result--;
            out.println("Case #" + testNumber + ": " + result);
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

