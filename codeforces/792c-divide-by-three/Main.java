// Educational Codeforces Round 18
// Divide by Three
// Problem statement:
// http://codeforces.com/problemset/problem/792/C

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String s = in.next();
            int sum = 0;
            List<Integer> a = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                a.add(Integer.parseInt(s.substring(i, i + 1)));
                sum = (sum + a.get(i)) % 3;
            }

            if (sum == 0) {
                out.println(s);
                return;
            }
            if (s.length() == 1) {
                out.println(-1);
                return;
            }

            if (sum == 1) {
                List<Integer> res1 = removeLeadingZeros(removeLastOne(a));
                List<Integer> res2 = removeLeadingZeros(removeLastTwo(removeLastTwo(a)));
                if ((res1.size() == 0) && (res2.size() == 0)) {
                    out.println(-1);
                    return;
                }
                if (res1.size() > res2.size()) {
                    for (int i = 0; i < res1.size(); i++) out.print(res1.get(i));
                } else {
                    for (int i = 0; i < res2.size(); i++) out.print(res2.get(i));
                }
                return;
            }

            if (sum == 2) {
                List<Integer> res1 = removeLeadingZeros(removeLastTwo(a));
                List<Integer> res2 = removeLeadingZeros(removeLastOne(removeLastOne(a)));
                if ((res1.size() == 0) && (res2.size() == 0)) {
                    out.println(-1);
                    return;
                }
                if (res1.size() > res2.size()) {
                    for (int i = 0; i < res1.size(); i++) out.print(res1.get(i));
                } else {
                    for (int i = 0; i < res2.size(); i++) out.print(res2.get(i));
                }
                return;
            }
        }

        List<Integer> removeLastOne(List<Integer> a) {
            List<Integer> out = new ArrayList<Integer>(a);
            int idx = out.size() - 1;
            while ((idx > 0) && (out.get(idx) % 3 != 1)) idx--;
            if ((idx >= 0) && (out.get(idx) % 3 == 1)) {
                out.remove((int) idx);
                return out;
            } else {
                return new ArrayList<Integer>();
            }
        }

        List<Integer> removeLastTwo(List<Integer> a) {
            List<Integer> out = new ArrayList<Integer>(a);
            int idx = out.size() - 1;
            while ((idx > 0) && (out.get(idx) % 3 != 2)) idx--;
            if ((idx >= 0) && (out.get(idx) % 3 == 2)) {
                out.remove((int) idx);
                return out;
            } else {
                return new ArrayList<Integer>();
            }
        }

        List<Integer> removeLeadingZeros(List<Integer> a) {

            List<Integer> out = new ArrayList<Integer>(a);
            int idx = out.size() - 1;
            while ((out.size() > 1) && (out.get(0) == 0)) {
                out.remove((int) 0);
            }
            return out;
        }

    }

    static class InputReader {
        private static BufferedReader in;
        private static StringTokenizer tok;

        public InputReader(InputStream in) {
            this.in = new BufferedReader(new InputStreamReader(in));
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
