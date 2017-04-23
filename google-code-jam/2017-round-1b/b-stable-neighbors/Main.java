// Google Code Jam 2017 Round 1B
// B - Stable Neigh-bors
// Problem statement:
// https://code.google.com/codejam/contest/8294486/dashboard

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
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
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
            int n = in.nextInt();
            int red = in.nextInt();
            int orange = in.nextInt();
            int yellow = in.nextInt();
            int green = in.nextInt();
            int blue = in.nextInt();
            int violet = in.nextInt();

            // violet horses can only be between two yellow ones
            // orange horses can only be between two blue ones
            // green horses can only be between two red ones
            if ((yellow < violet) || (blue < orange) || (red < green)) {
                out.println("Case #" + testNumber + ": IMPOSSIBLE");
                return;
            }
            if ((violet > 0) && (yellow == violet)) {
                if (yellow + violet != n) {
                    out.println("Case #" + testNumber + ": IMPOSSIBLE");
                } else {
                    out.print("Case #" + testNumber + ": ");
                    for (int i = 0; i < yellow; i++) {
                        out.print("YV");
                    }
                    out.println();
                }
                return;
            }
            if ((orange > 0) && (blue == orange)) {
                if (blue + orange != n) {
                    out.println("Case #" + testNumber + ": IMPOSSIBLE");
                } else {
                    out.print("Case #" + testNumber + ": ");
                    for (int i = 0; i < blue; i++) {
                        out.print("BO");
                    }
                    out.println();

                }
                return;
            }
            if ((green > 0) && (red == green)) {
                if (red + green != n) {
                    out.println("Case #" + testNumber + ": IMPOSSIBLE");
                } else {
                    out.print("Case #" + testNumber + ": ");
                    for (int i = 0; i < red; i++) {
                        out.print("RG");
                    }
                    out.println();
                }
                return;
            }

            // we will place all violet horses after the first yellow one like this: ..YVYV...VY..
            // the whole YV..VY block can be reduced to a single Y. The amount of Ys is reduced by the amount of V
            // similar for other colors
            int redMod = red - green;
            int blueMod = blue - orange;
            int yellowMod = yellow - violet;

            // check if triangle inequality is violated
            if ((yellowMod > redMod + blueMod) || (redMod > blueMod + yellowMod) || (blueMod > yellowMod + redMod)) {
                out.println("Case #" + testNumber + ": IMPOSSIBLE");
                return;
            }

            // sort the remaining 3 colors Y, R and B according to their frequency and place them, always picking the
            // most frequent of the two possible choices first.
            int modN = yellowMod + redMod + blueMod;
            Color[] col = new Color[3];
            col[0] = new Color('Y', yellowMod);
            col[1] = new Color('R', redMod);
            col[2] = new Color('B', blueMod);
            Arrays.sort(col);
            int prev = 0;
            StringBuilder result = new StringBuilder();
            while (modN > 0) {
                if (prev == 2) {
                    if (col[1].count >= col[0].count) {
                        result.append(col[1].name);
                        col[1].count--;
                        prev = 1;
                    } else {
                        result.append(col[0].name);
                        col[0].count--;
                        prev = 0;
                    }
                } else if (prev == 1) {
                    if (col[2].count >= col[0].count) {
                        result.append(col[2].name);
                        col[2].count--;
                        prev = 2;
                    } else {
                        result.append(col[0].name);
                        col[0].count--;
                        prev = 0;
                    }
                } else { // prev == 0
                    if (col[2].count >= col[1].count) {
                        result.append(col[2].name);
                        col[2].count--;
                        prev = 2;
                    } else {
                        result.append(col[1].name);
                        col[1].count--;
                        prev = 1;
                    }
                }
                modN--;
            }

            // add violet, orange and green after the first occurence of their allowed neighbors
            int firstYellow = result.indexOf("Y");
            if ((firstYellow > -1) && (violet > 0)) {
                StringBuilder ins = new StringBuilder();
                for (int i = 0; i < violet; i++) {
                    ins.append("YV");
                }
                result.insert(firstYellow, ins.toString());
            }
            int firstBlue = result.indexOf("B");
            if ((firstBlue > -1) && (orange > 0)) {
                StringBuilder ins = new StringBuilder();
                for (int i = 0; i < orange; i++) {
                    ins.append("BO");
                }
                result.insert(firstBlue, ins.toString());
            }
            int firstRed = result.indexOf("R");
            if ((firstRed > -1) && (green > 0)) {
                StringBuilder ins = new StringBuilder();
                for (int i = 0; i < green; i++) {
                    ins.append("RG");
                }
                result.insert(firstRed, ins.toString());
            }
            out.println("Case #" + testNumber + ": " + result.toString());
        }

        class Color implements Comparable<Color> {
            char name;
            int count;

            public Color(char ch, int n) {
                name = ch;
                count = n;
            }

            public int compareTo(Color other) {
                return count - other.count;
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

