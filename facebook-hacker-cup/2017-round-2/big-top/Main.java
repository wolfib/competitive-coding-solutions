// Facebook Hacker Cup 2017 Round 2 - Big Top
// Problem statement:
// https://www.facebook.com/hackercup/problem/1612752199040515/

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        BigTop solver = new BigTop();
        solver.solve(1, in, out);
        out.close();
    }

    static class BigTop {
        // all results are multiples of 0.125
        // for higher precision all areas are measured in multiples of 0.125
        // and only transformed when being printed
        long currentArea = 0;
        TreeSet<Pole> ts = new TreeSet<Pole>();

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) solveTop(i + 1, in, out);
        }

        public void solveTop(int testNumber, InputReader in, PrintWriter out) {
            currentArea = 0;
            // store all poles which make a contribution to the total area in a TreeSet
            ts = new TreeSet<Pole>();
            int n = in.nextInt();

            long x1 = in.nextLong();
            long ax = in.nextLong();
            long bx = in.nextLong();
            long cx = in.nextLong();

            long h1 = in.nextLong();
            long ah = in.nextLong();
            long bh = in.nextLong();
            long ch = in.nextLong();

            long result = 0;
            long currentX = x1;
            long currentH = h1;

            for (int i = 0; i < n; i++) {
                addPole(currentX, currentH);
                result += currentArea;
                currentX = ((ax * currentX + bx) % cx) + 1;
                currentH = ((ah * currentH + bh) % ch) + 1;
            }
            // result is stored in multiples of 1/8, so divide by 8, add comma manually and calculate digits post comma
            out.printf("Case #%d: %d.%d\n", testNumber, result / 8, ((result % 8) / 2) * 25);
        }

        public void addPole(long x, long h) {
            Pole pole = new Pole(x, h);

            Pole right = ts.higher(pole);
            // return if current pole is covered by its right neighbor
            if ((right != null) && (right.height - (right.pos - pole.pos) >= pole.height)) return;

            Pole left = ts.lower(pole);
            // return if current pole is covered by its left neighbor
            if ((left != null) && (left.height - (pole.pos - left.pos) >= pole.height)) return;

            // remove right neighbors which are covered by current pole and adjust area accordingly
            while ((right != null) && (pole.height - (right.pos - pole.pos)) >= right.height) {
                ts.remove(right);
                currentArea -= right.areaLeft + right.areaRight;
                right = ts.higher(pole);
            }

            // remove left neighbors which are covered by current pole and adjust area accordingly
            while ((left != null) && (pole.height - (pole.pos - left.pos)) >= left.height) {
                ts.remove(left);
                currentArea -= left.areaLeft + left.areaRight;
                left = ts.lower(pole);
            }

            if ((right == null) || (right.pos - pole.pos >= right.height + pole.height)) {
                // area is only dependent current pole
                pole.areaRight = h * h * 4;
            } else {
                // area between current pole and its right neighbor
                long mid2 = pole.height - right.height + right.pos - pole.pos;
                currentArea -= right.areaLeft;
                pole.areaRight = mid2 * pole.height * 4 - mid2 * mid2;
                mid2 = right.height - pole.height + right.pos - pole.pos;
                right.areaLeft = mid2 * right.height * 4 - mid2 * mid2;
                currentArea += right.areaLeft;
            }

            if ((left == null) || (pole.pos - left.pos >= pole.height + left.height)) {
                // area is only dependent current pole
                pole.areaLeft = h * h * 4;
            } else {
                // area between current pole and its left neighbor
                long mid2 = pole.height - left.height + pole.pos - left.pos;
                currentArea -= left.areaRight;
                pole.areaLeft = mid2 * pole.height * 4 - mid2 * mid2;
                mid2 = left.height - pole.height + pole.pos - left.pos;
                left.areaRight = mid2 * left.height * 4 - mid2 * mid2;
                currentArea += left.areaRight;
            }
            currentArea += pole.areaRight + pole.areaLeft;
            ts.add(pole);
        }

        class Pole implements Comparable<Pole> {
            long pos;
            long height;
            long areaLeft;
            long areaRight;

            public Pole(long x, long h) {
                pos = x;
                height = h;
            }

            public int compareTo(Pole other) {
                return Long.compare(this.pos, other.pos);
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String next() {
            try {
                while (tok == null || !tok.hasMoreTokens()) {
                    tok = new StringTokenizer(in.readLine());
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

