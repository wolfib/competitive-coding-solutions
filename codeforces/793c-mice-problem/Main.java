// Tinkoff Challenge 2017 - Elimination Round
// Mice Problem
// Problem statement:
// http://codeforces.com/problemset/problem/793/C

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
		// rather than calculating solution analytically (which requires thinking about a lot of edge cases)
        // it is probably simpler to solve via binary search
		
            int n = in.nextInt();
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            if (x1 > x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (y1 > y2) {
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }

            double minTime = 0;
            double maxTime = Double.MAX_VALUE;

            Line rectTop = new Line(x1, y1, x2, y1);
            Line rectLeft = new Line(x1, y1, x1, y2);
            Line rectRight = new Line(x2, y1, x2, y2);
            Line rectBottom = new Line(x1, y2, x2, y2);

            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int vx = in.nextInt();
                int vy = in.nextInt();

                if ((vx == 0) && (vy == 0)) {
                    if ((x > x1) && (x < x2) && (y > y1) && (y < y2)) continue;
                        //if (strictlyWithinRectangle(x, y, x1, y1, x2, y2)) continue;
                    else {
                        out.println(-1);
                        return;
                    }
                }

                Line mouse = new Line(x, y, x + vx, y + vy);
                List<Double> crossTimes = new ArrayList<>();

                // check for edge cases where mouse runs exactly along rectangle border, but is never strictly inside
                if (mouse.equals(rectTop) || mouse.equals(rectBottom) || mouse.equals(rectLeft) || mouse.equals(rectRight)) {
                    out.println(-1);
                    return;
                }

                Point s1 = VectorGeometry.getLineIntersection(mouse, rectTop);
                if ((s1 != null) && (s1.x >= x1) && (s1.x <= x2)) { // intersection lies within rectangle side
                    double t = (s1.y - y) / vy;
                    if ((t > 0) && (!crossTimes.contains(t))) {
                        crossTimes.add(t);
                    }
                }

                Point s2 = VectorGeometry.getLineIntersection(mouse, rectLeft);
                if ((s2 != null) && (s2.y >= y1) && (s2.y <= y2)) { // intersection lies within rectangle side
                    double t = (s2.x - x) / vx;
                    if ((t > 0) && (!crossTimes.contains(t))) {
                        crossTimes.add(t);
                    }
                }

                Point s3 = VectorGeometry.getLineIntersection(mouse, rectRight);
                if ((s3 != null) && (s3.y >= y1) && (s3.y <= y2)) { // intersection lies within rectangle side
                    double t = (s3.x - x) / vx;
                    if ((t > 0) && (!crossTimes.contains(t))) {
                        crossTimes.add(t);
                    }
                }

                Point s4 = VectorGeometry.getLineIntersection(mouse, rectBottom);
                if ((s4 != null) && (s4.x >= x1) && (s4.x <= x2)) { // intersection lies within rectangle side
                    double t = (s4.y - y) / vy;
                    if ((t > 0) && (!crossTimes.contains(t))) {
                        crossTimes.add(t);
                    }
                }

                if ((x >= x1) && (x <= x2) && (y >= y1) && (y <= y2)) {
                    crossTimes.add(0d);
                }

                if (crossTimes.size() != 2) {
                    out.println(-1);
                    return;
                }

                double enter = Math.min(crossTimes.get(0), crossTimes.get(1));
                double exit = Math.max(crossTimes.get(0), crossTimes.get(1));
                maxTime = Math.min(maxTime, exit);
                minTime = Math.max(minTime, enter);
            }

            if (maxTime <= minTime) {
                out.println(-1);
                return;
            }
            out.println(minTime);
        }

    }

    static class VectorGeometry {
        public static Point getLineIntersection(Line e, Line f) {
            double det = e.a * f.b - f.a * e.b;
            if (det == 0) {
                return null;
            }
            double x = (f.b * e.c - e.b * f.c) / det;
            double y = (e.a * f.c - f.a * e.c) / det;
            return new Point(x, y);
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

    static class Line {
        double x1;
        double y1;
        double x2;
        double y2;
        double a;
        double b;
        double c;

        public Line(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;

            a = y2 - y1;
            b = x1 - x2;
            c = a * x1 + b * y1;
        }


        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            Line other = (Line) o;

            return ((a * other.x1 + b * other.y1 == c) && (a * other.x2 + b * other.y2 == c));
        }

    }

    static class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

    }
}

