// Google Code Jam 2017 Round 2
// A - Fresh Chocolate
// Problem statement:
// https://code.google.com/codejam/contest/5314486/dashboard

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
            final String regex = "A-(small|large).*[.]in";
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
            outputStream = new FileOutputStream("a.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int p = in.nextInt();
            int[] rest = new int[p];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                rest[x % p] += 1;
                sum += x;
            }

            int result = 1;

            if (p == 2) {
                result += rest[0] + rest[1] / 2;
            } else if (p == 3) {
                result += rest[0];

                int a = Math.min(rest[1], rest[2]);
                result += a;
                rest[1] -= a;
                rest[2] -= a;

                result += rest[1] / 3;
                result += rest[2] / 3;
            } else if (p == 4) {
                result += rest[0];

                int a = Math.min(rest[1], rest[3]);
                result += a;
                rest[1] -= a;
                rest[3] -= a;

                a = Math.min(rest[1] / 2, rest[2]);
                result += a;
                rest[1] -= 2 * a;
                rest[2] -= a;

                a = Math.min(rest[3] / 2, rest[2]);
                result += a;
                rest[3] -= 2 * a;
                rest[2] -= a;

                a = rest[2] / 2;
                result += a;
                rest[2] -= 2 * a;

                result += rest[1] / 4;
                result += rest[3] / 4;

            }
            if (sum % p == 0) result--;
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

