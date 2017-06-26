// Codeforces Round 418
// Sagheer and Apple Tree
// Problem statement:
// http://codeforces.com/problemset/problem/814/D

import java.io.OutputStream;
import java.io.IOException;
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
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            Circle[] circles = new Circle[n];
            for (int i = 0; i < n; i++) {
                circles[i] = new Circle(in.nextInt(), in.nextInt(), in.nextInt());
            }
            // greedily place circles, starting with biggest one
            Arrays.sort(circles);
            double result = 0;
            for (int i = 0; i < n; i++) {
                // look if circle lies withing another circle
                for (int j = i - 1; j >= 0; j--) {
                    if (circles[j].isParent(circles[i])) {
                        circles[i].parent = j;
                        break;
                    }
                }
                // circle lies fully within other circle
                if (circles[i].parent > -1) {
                    // put 2nd circle on 2nd dancefloor
                    if (circles[circles[i].parent].parent == -1) {
                        result += circles[i].r * circles[i].r;
                        circles[i].df = 1;
                    } else {
                        // for circles within multiple other circles, stack them all on first dancefloor
                        if (circles[circles[i].parent].isPositive) {
                            circles[i].isPositive = false;
                            result -= circles[i].r * circles[i].r;
                            circles[i].df = 0;
                        } else {
                            result += circles[i].r * circles[i].r;
                            circles[i].df = 0;
                        }
                    }
                } else {
                    // no engulfing circle -> just place it
                    result += circles[i].r * circles[i].r;
                    circles[i].df = 0;
                }
            }

            result *= Math.PI;
            out.println(result);

        }

        class Circle implements Comparable<Circle> {
            long x;
            long y;
            long r;
            int parent;
            int df;
            boolean isPositive;

            public Circle(long x, long y, long r) {
                this.x = x;
                this.y = y;
                this.r = r;
                parent = -1;
                df = -1;
                isPositive = true;
            }


            public int compareTo(Circle other) {
                return (int) (other.r - r);
            }

            boolean isParent(Circle other) {
                return Math.sqrt((long) (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y)) <= r;
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

