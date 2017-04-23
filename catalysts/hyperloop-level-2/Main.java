// Catalysts Coding Contest 2017
// Hyperloop Level 2 
// Problem statement:
// https://catcoder.catalysts.cc/

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Hyperloop2 solver = new Hyperloop2();
        solver.solve(1, in, out);
        out.close();
    }

    static class Hyperloop2 {
        long[] x;
        long[] y;
        Map<String, Integer> nameToIndex = new HashMap<>();

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            x = new long[n];
            y = new long[n];
            for (int i = 0; i < n; i++) {
                String s = in.next();
                nameToIndex.put(s, i);
                x[i] = in.nextInt();
                y[i] = in.nextInt();
            }
            String journeyStart = in.next();
            String journeyEnd = in.next();
            String hyperStart = in.next();
            String hyperEnd = in.next();

            double result = driveTime(journeyStart, hyperStart) + hyperTime(hyperStart, hyperEnd) + driveTime(hyperEnd, journeyEnd);
            result = Math.min(result, driveTime(journeyStart, hyperEnd) + hyperTime(hyperEnd, hyperStart) + driveTime(hyperStart, journeyEnd));
            out.println(Math.round(result));
        }

        double getDistance(String from, String to) {
            int fromIndex = nameToIndex.get(from);
            int toIndex = nameToIndex.get(to);

            double dist = (x[fromIndex] - x[toIndex]) * (x[fromIndex] - x[toIndex]);
            dist += (y[fromIndex] - y[toIndex]) * (y[fromIndex] - y[toIndex]);
            return Math.sqrt(dist);
        }

        double hyperTime(String from, String to) {
            return getDistance(from, to) / 250.0 + 200;
        }

        double driveTime(String from, String to) {
            return getDistance(from, to) / 15.0;
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

