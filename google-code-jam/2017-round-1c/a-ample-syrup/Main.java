// Google Code Jam 2017 Round 1C
// A - Ample Syrup
// Problem statement:
// https://code.google.com/codejam/contest/3274486/dashboard

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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Collections;
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
            int k = in.nextInt();
            long maxRadius = 0;
            Pancake[] pancakes = new Pancake[n];
            for (int i = 0; i < n; i++) {
                long r = in.nextInt();
                long h = in.nextInt();
                maxRadius = Math.max(maxRadius, r);
                pancakes[i] = new Pancake(r, h);
            }

            double result = 0;
            for (Pancake bottom : pancakes) {
                List<Long> sides = new ArrayList<>();
                for (Pancake pk : pancakes) {
                    if ((pk != bottom) && (pk.radius <= bottom.radius)) {
                        sides.add(pk.mantel);
                    }
                }
                if (sides.size() >= k - 1) {
                    Collections.sort(sides);
                    long sum = 0;
                    for (int i = sides.size() - 1; i > sides.size() - k; i--) {
                        sum += sides.get(i);
                    }
                    double current = sum * 2 * Math.PI;
                    current += bottom.radius * bottom.radius * Math.PI;
                    current += bottom.mantel * 2 * Math.PI;
                    result = Math.max(result, current);
                }
            }

            out.println("Case #" + testNumber + ": " + result);
        }

        class Pancake {
            long radius;
            long height;
            long mantel;

            public Pancake(long r, long h) {
                radius = r;
                height = h;
                mantel = radius * height;
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

