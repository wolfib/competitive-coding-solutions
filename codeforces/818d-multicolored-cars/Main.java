// Educational Codeforces Round 24
// Multicolored Cars
// Problem statement:
// http://codeforces.com/problemset/problem/818/D

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.HashMap;
import java.io.IOException;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int a = in.nextInt();
            int[] c = in.readIntArray(n);

            Map<Integer, List<Integer>> pos = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if (pos.containsKey(c[i])) {
                    pos.get(c[i]).add(i);
                } else {
                    List<Integer> current = new ArrayList<>();
                    current.add(i);
                    pos.put(c[i], current);
                }
            }

            if (!pos.containsKey(a)) {
                if (a != 1) out.println(1);
                else out.println(2);
                return;
            }

            List<Integer> aList = pos.get(a);
            for (Integer color : pos.keySet()) {
                if (color == a) continue;
                List<Integer> bList = pos.get(color);
                int i = 0;
                boolean possible = true;
                if (bList.size() < aList.size()) continue;
                while (i < aList.size()) {
                    if (aList.get(i) < bList.get(i)) {
                        possible = false;
                        break;
                    }
                    i++;
                }
                if (possible) {
                    out.println(color);
                    return;
                }
            }
            out.println(-1);
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
                    //tok = new StringTokenizer(in.readLine(), ", \t\n\r\f"); //adds commas as delimeter
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

