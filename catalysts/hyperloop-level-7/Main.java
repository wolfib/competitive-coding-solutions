// Catalysts Coding Contest 2017
// Hyperloop Level 7
// Problem statement:
// https://catcoder.catalysts.cc/

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.io.IOException;
import java.util.HashMap;
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
        Hyperloop7 solver = new Hyperloop7();
        solver.solve(1, in, out);
        out.close();
    }

    static class Hyperloop7 {
        long[] x;
        long[] y;
        Map<String, Integer> nameToIndex = new HashMap<>();
        String[] cities;
        int n;
        int m;
        String[][] hyperStops;
        List<String> allStops = new ArrayList<>();
        String hubName;
        int hubIndex;
        int numberOfRoutes;
        int[] routeInCity;

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

            String journeyStart = in.next();
            String journeyEnd = in.next();

            hubName = in.next();
            hubIndex = nameToIndex.get(hubName);
            allStops.add(hubName);

            numberOfRoutes = in.nextInt();
            hyperStops = new String[numberOfRoutes][];
            routeInCity = new int[n];
            Arrays.fill(routeInCity, -2);
            for (int i = 0; i < numberOfRoutes; i++) {
                m = in.nextInt();
                hyperStops[i] = new String[m];
                for (int j = 0; j < m; j++) {
                    String currentStop = in.next();
                    hyperStops[i][j] = currentStop;
                    routeInCity[nameToIndex.get(currentStop)] = i;
                    if (!currentStop.equals(hubName)) {
                        allStops.add(currentStop);
                    }
                }
            }
            routeInCity[hubIndex] = -1;

            int closestToStart = -1;
            double minDistStart = Double.MAX_VALUE;
            int closestToEnd = -1;
            double minDistEnd = Double.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (routeInCity[i] != -2) {
                    double a = getDistance(journeyStart, cities[i]);
                    if (a < minDistStart) {
                        minDistStart = a;
                        closestToStart = i;
                    }
                    a = getDistance(journeyEnd, cities[i]);
                    if (a < minDistEnd) {
                        minDistEnd = a;
                        closestToEnd = i;
                    }
                }
            }

            double result = driveTime(journeyStart, cities[closestToStart]) + driveTime(cities[closestToEnd], journeyEnd);

            if ((hubIndex == closestToStart) || (hubIndex == closestToEnd) || (routeInCity[closestToStart] == routeInCity[closestToEnd])) {
                //case1: only need single route
                int route = Math.max(routeInCity[closestToStart], routeInCity[closestToEnd]);
                result += multStopTime(route, cities[closestToStart], cities[closestToEnd]);
            } else {
                //case 2: change route at hub
                int route = routeInCity[closestToStart];
                result += multStopTime(route, cities[closestToStart], hubName);
                route = routeInCity[closestToEnd];
                result += multStopTime(route, cities[closestToEnd], hubName);
                result += 300;
            }
            out.println(Math.round(result));
        }

        double multStopTime(int line, String first, String last) {
            int firstStop = 0;
            while (!hyperStops[line][firstStop].equals(first)) {
                firstStop++;
            }
            int lastStop = 0;
            while (!hyperStops[line][lastStop].equals(last)) {
                lastStop++;
            }
            if (lastStop < firstStop) {
                int t = firstStop;
                firstStop = lastStop;
                lastStop = t;
            }

            double result = 0;
            for (int i = firstStop; i < lastStop; i++) {
                result += hyperTime(hyperStops[line][i], hyperStops[line][i + 1]);
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

