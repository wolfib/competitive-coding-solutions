// Codeforces Round 418
// An impassioned circulation of affection
// Problem statement:
// http://codeforces.com/problemset/problem/814/C

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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        int n;
        Map<Character, List<Integer>> cols;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            String s = in.next();
            cols = new HashMap<>();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (cols.containsKey(c)) {
                    cols.get(c).add(i);
                } else {
                    ArrayList<Integer> clist = new ArrayList<>();
                    clist.add(i);
                    cols.put(c, clist);
                }
            }

            int q = in.nextInt();
            for (int i = 0; i < q; i++) {
                int m = in.nextInt();
                String c = in.next();
                out.println(query(m, c.charAt(0)));
            }
        }

        int query(int m, char c) {
            if (m >= n) return n;
            if (!cols.containsKey(c)) return m;
            if (m + cols.get(c).size() >= n) return n;

            List<Integer> pos = cols.get(c);
            int last = 0;
            int result = 0;
            for (int first = 0; first <= pos.size(); first++) {
                while ((last < pos.size() - 1) && (pos.get(last + 1) - pos.get(first) + 1 - (last - first + 2) <= m)) {
                    last++;
                }
                result = Math.max(result, m + last - first + 1);
            }
            return result;
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

