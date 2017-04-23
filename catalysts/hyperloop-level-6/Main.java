// Catalysts Coding Contest 2017
// Hyperloop Level 6
// Problem statement:
// https://catcoder.catalysts.cc/

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
        Hyperloop6 solver = new Hyperloop6();
        solver.solve(1, in, out);
        out.close();
    }

    static class Hyperloop6 {
        long[] x;
        long[] y;
        Map<String, Integer> nameToIndex = new HashMap<>();
        String[] cities;
        int n;
        int m;
        List<String> hyperStops;
        String[] journeyStart;
        String[] journeyEnd;
        int[] convTime;
        Random rnd = new Random();
        long maxDistance;
        int minBetter;

        void randomRoute() {
            hyperStops = new ArrayList<>();
            int nextIndex = rnd.nextInt(n);
            hyperStops.add(cities[nextIndex]);
            int prevIndex = nextIndex;
            while (nextIndex == prevIndex) nextIndex = rnd.nextInt(n);
            double distance = getDistance(hyperStops.get(0), cities[nextIndex]);
            while ((distance < maxDistance) && (hyperStops.size() < n)) {
                hyperStops.add(cities[nextIndex]);
                prevIndex = nextIndex;
                while (nextIndex == prevIndex) nextIndex = rnd.nextInt(n);
                distance += getDistance(hyperStops.get(hyperStops.size() - 1), cities[nextIndex]);
            }
        }

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

            minBetter = in.nextInt();
            maxDistance = in.nextLong();

            int numberImprov = 0;

            while (numberImprov < minBetter) {
                //generate route
                randomRoute();

                //evaluate route
                numberImprov = evalRoute();
            }

            out.print(hyperStops.size() + " ");
            for (String s : hyperStops) {
                out.print(s + " ");
            }
        }

        int evalRoute() {
            int result = 0;
            for (int i = 0; i < m; i++) {
                if (tripTime(journeyStart[i], journeyEnd[i]) < convTime[i]) {
                    result++;
                }
            }
            return result;
        }

        double tripTime(String from, String to) {
            int closestToStart = -1;
            double minDistStart = Double.MAX_VALUE;
            int closestToEnd = -1;
            double minDistEnd = Double.MAX_VALUE;

            for (int i = 0; i < hyperStops.size(); i++) {
                double a = getDistance(from, hyperStops.get(i));
                if (a < minDistStart) {
                    minDistStart = a;
                    closestToStart = i;
                }
                a = getDistance(to, hyperStops.get(i));
                if (a < minDistEnd) {
                    minDistEnd = a;
                    closestToEnd = i;
                }
            }
            //out.println("closest to start: " + cities[])

            double result = driveTime(from, hyperStops.get(closestToStart)) + driveTime(hyperStops.get(closestToEnd), to);
            if (closestToStart != closestToEnd) {
                if (closestToEnd < closestToStart) {
                    int t = closestToStart;
                    closestToStart = closestToEnd;
                    closestToEnd = t;
                }
                for (int i = closestToStart; i < closestToEnd; i++) {
                    result += hyperTime(hyperStops.get(i), hyperStops.get(i + 1));
                }
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

        public long nextLong() {
            return Long.parseLong(next());
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

