// Google Code Jam 2017 Qualification Round
// C - Bathroom Stalls
// Problem statement:
// https://code.google.com/codejam/contest/3264486/dashboard

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
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            long n = in.nextLong();
            long k = in.nextLong();

			// Version 1
			// uses priority queue, each interval is added individually
			// too slow for large instance
			/*PriorityQueue<Long> q = new PriorityQueue<Long>(10, Collections.reverseOrder());
			q.add(n);
			for (int i = 0; i < k - 1; i++) {
				Long intervalSize = q.poll();
				q.add(intervalSize / 2);
				q.add((intervalSize - 1) / 2);
			}
			long r = q.poll();
			out.println("Case #" + testNumber + ": " + (r / 2) + " " + ((r - 1) / 2));*/


			// Version 2
			// uses treeMap, to save interval size together with its frequency of occurence
			/*TreeMap<Long, Long> tm = new TreeMap<>();
			tm.put(n, 1L);
			long count = 0;
			long intervalSize = 0;
			while (count < k) {
				intervalSize = tm.lastKey();
				long freq = tm.get(intervalSize);
				count += freq;
				tm.remove(intervalSize);
				if (tm.containsKey(intervalSize / 2)) {
					tm.put(intervalSize / 2, freq + tm.get(intervalSize / 2));
				} else {
					tm.put(intervalSize / 2, freq);
				}
				if (tm.containsKey((intervalSize - 1) / 2)) {
					tm.put((intervalSize - 1) / 2, freq + tm.get((intervalSize - 1) / 2));
				} else {
					tm.put((intervalSize - 1) / 2, freq);
				}
			}
			out.println("Case #" + testNumber + ": " + (intervalSize / 2) + " " + ((intervalSize - 1) / 2));*/

            // Version 3
            // direct calculation of tree level of k
            if (k == 1) {
                out.println("Case #" + testNumber + ": " + (n / 2) + " " + ((n - 1) / 2));
                return;
            }

            int level = 1;
            long potenz = 1;
            while (potenz * 2 - 1 < k) {
                level++;
                potenz *= 2;
            }
            long fullyPlaced = (long) Math.pow(2, level - 1) - 1;
            long toPlace = fullyPlaced + 1;
            long openPos = n - fullyPlaced;
            long intervalSize = openPos / toPlace;
            if (k - fullyPlaced <= openPos % toPlace) {
                intervalSize++;
            }
            out.println("Case #" + testNumber + ": " + (intervalSize / 2) + " " + ((intervalSize - 1) / 2));
        }
    }

    static class InputReader {
        private static BufferedReader in;
        private static StringTokenizer tok;

        public InputReader(InputStream in) {
            this.in = new BufferedReader(new InputStreamReader(in));
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

