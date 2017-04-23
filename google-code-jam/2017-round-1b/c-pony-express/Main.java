// Google Code Jam 2017 Round 1B
// C - Pony Express
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
import java.util.PriorityQueue;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractCollection;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "C-(small|large).*[.]in";
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
            outputStream = new FileOutputStream("c.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int cities = in.nextInt();
            int queries = in.nextInt();
            int[] maxDist = new int[cities];
            int[] speed = new int[cities];
            for (int i = 0; i < cities; i++) {
                maxDist[i] = in.nextInt();
                speed[i] = in.nextInt();
            }
            int[][] dist = new int[cities][cities];
            for (int i = 0; i < cities; i++) {
                dist[i] = new int[cities];
                for (int j = 0; j < cities; j++) {
                    dist[i][j] = in.nextInt();
                }
            }
            int[] qStart = new int[queries];
            int[] qEnd = new int[queries];
            for (int i = 0; i < queries; i++) {
                qStart[i] = in.nextInt() - 1;
                qEnd[i] = in.nextInt() - 1;
            }

            // create new graph with distances measured in time
            // there is a link from a to b if b can be reached with a's horse
            double[][] timeDist = new double[cities][cities];
            for (int i = 0; i < cities; i++) {
                timeDist[i] = new double[cities];
                Arrays.fill(timeDist[i], Double.MAX_VALUE);
            }

            for (int i = 0; i < cities; i++) {
                PriorityQueue<CityDist> q = new PriorityQueue();
                q.add(new CityDist(i, 0));
                while (!q.isEmpty()) {
                    CityDist current = q.poll();
                    if ((current.distance <= maxDist[i]) && (current.distance < timeDist[i][current.city])) {
                        timeDist[i][current.city] = (double) current.distance / speed[i];
                        for (int j = 0; j < cities; j++) {
                            if ((j != current.city) && (dist[current.city][j] >= 0)) {
                                q.add(new CityDist(j, current.distance + dist[current.city][j]));
                            }
                        }
                    }
                }
            }

            // now run pathfinding Dijkstra on this new graph
            out.print("Case #" + testNumber + ": ");
            for (int i = 0; i < queries; i++) {
                PriorityQueue<CityTime> q = new PriorityQueue();
                boolean[] visited = new boolean[cities];
                q.add(new CityTime(qStart[i], 0));
                while (!q.isEmpty()) {
                    CityTime current = q.poll();
                    if (!visited[current.city]) {
                        if (current.city == qEnd[i]) {
                            out.print(current.time + " ");
                            break;
                        }
                        visited[current.city] = true;
                        for (int j = 0; j < cities; j++) {
                            if ((j != current.city) && (!visited[j])) {
                                q.add(new CityTime(j, current.time + timeDist[current.city][j]));
                            }
                        }
                    }
                }
            }
            out.println();
        }

        class CityDist implements Comparable<CityDist> {
            int city;
            int distance;

            public CityDist(int c, int d) {
                city = c;
                distance = d;
            }


            public int compareTo(CityDist o) {
                //return Double.compare(distance, o.distance);
                return distance - o.distance;
            }

        }

        class CityTime implements Comparable<CityTime> {
            int city;
            double time;

            public CityTime(int c, double t) {
                city = c;
                time = t;
            }


            public int compareTo(CityTime o) {
                return Double.compare(time, o.time);
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

