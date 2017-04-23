// Catalysts Coding Contest 2017
// Hyperloop Level 4
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
        Hyperloop4 solver = new Hyperloop4();
        solver.solve(1, in, out);
        out.close();
    }

    static class Hyperloop4 {
        long[] x;
        long[] y;
        Map<String, Integer> nameToIndex = new HashMap<>();
        String[] cities;
        int n;
        int m;
        String[] journeyStart;
        String[] journeyEnd;
        int[] convTime;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            x = new long[n];
            y = new long[n];
            cities = new String[n];
            for (int i = 0; i < n; i++) {
                String s = in.next();
                nameToIndex.put(s, i);
                cities[i] = s;
                x[i] = in.nextInt();
                y[i] = in.nextInt();
            }

            m = in.nextInt();
            journeyStart = new String[m];
            journeyEnd = new String[m];
            convTime = new int[m];
            for (int i = 0; i < m; i++) {
                journeyStart[i] = in.next();
                journeyEnd[i] = in.next();
                convTime[i] = in.nextInt();
            }

            int minBetter = in.nextInt();

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (getNumberImprovements(i, j) >= minBetter) {
                        out.println(cities[i] + " " + cities[j]);
                        return;
                    }
                }
            }
            out.println("no solution found");

        }

        int getNumberImprovements(int a, int b) {
            int result = 0;
            String hyperStart = cities[a];
            String hyperEnd = cities[b];
            for (int i = 0; i < m; i++) {
                double journeyTime = driveTime(journeyStart[i], hyperStart) + hyperTime(hyperStart, hyperEnd) + driveTime(hyperEnd, journeyEnd[i]);
                journeyTime = Math.min(journeyTime, driveTime(journeyStart[i], hyperEnd) + hyperTime(hyperEnd, hyperStart) + driveTime(hyperStart, journeyEnd[i]));
                if (Math.round(journeyTime) < convTime[i]) result++;
            }
            return result;
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

