// Codeforces Round 406
// Not Afraid
// Problem statement:
// http://codeforces.com/problemset/problem/787/B

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            // a group is safe as long as there is an x, such that +x and -x are both members of the group
            // if all groups are safe, output "NO", otherwise "YES"
            int n = in.nextInt();
            int m = in.nextInt();

            boolean isSafe;
            for (int i = 0; i < m; i++) {
                int size = in.nextInt();
                int[] a = in.readIntArray(size);
                Set<Integer> members = new HashSet<>();
                isSafe = false;
                for (int j = 0; j < size; j++) {
                    if (members.contains(-a[j])) {
                        isSafe = true;
                        break;
                    }
                    members.add(a[j]);
                }
                if (!isSafe) {
                    out.println("YES");
                    return;
                }
            }
            out.println("NO");
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

