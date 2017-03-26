// AtCoder Grand Contest 11
// Airport Bus
// Problem statement:
// http://agc011.contest.atcoder.jp/tasks/agc011_a

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        AirportBus solver = new AirportBus();
        solver.solve(1, in, out);
        out.close();
    }

    static class AirportBus {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int cap = in.nextInt();
            int maxWait = in.nextInt();
            int[] a = in.readIntArray(n);
            Arrays.sort(a); // sort by arrival time

            int res = 0; // number of buses needed
            int passengers = 0; // number of passengers in the current bus
            int firstPassTime = a[0]; // arrival time of first passenger of current bus

            for (int i = 0; i < n; i++) {
                if (a[i] - firstPassTime > maxWait) { // start new bus if using current one would result in too long of a wait
                    res++;
                    firstPassTime = a[i];
                    passengers = 1;
                } else {
                    passengers++;
                    if (passengers > cap) { // start new bus if current one has no capacity left
                        passengers = 1;
                        firstPassTime = a[i];
                        res++;
                    }
                }
            }
            if (passengers > 0) res++;
            out.println(res);
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

        public int[] readIntArray(int n) {
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) {
                ar[i] = nextInt();
            }
            return ar;
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

