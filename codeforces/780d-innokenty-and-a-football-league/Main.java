// Codeforces Round 403
// Innokenty and a Football League
// Problem statement:
// http://codeforces.com/problemset/problem/780/D

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Set;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.util.HashSet;
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
        Team[] teams;
        Map<String, Integer> usedFirsts = new HashMap<>();
        Set<String> usedSeconds = new HashSet<>();

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            // constructive, greedy approach:
            // pick first variant,
            // in case of conflict: move to second variant (+ as a consequence move others to 2nd variant too)
            // if still conflict: no solution possible

            int n = in.nextInt();
            teams = new Team[n];
            for (int i = 0; i < n; i++) {
                String name = in.next();
                String city = in.next();
                teams[i] = new Team(name, city);
            }

            for (int i = 0; i < n; i++) {
                if (teams[i].choice == 0) {
                    if (assign(i) == false) {
                        out.println("NO");
                        return;
                    }
                }
            }

            out.println("YES");
            for (int i = 0; i < n; i++) {
                if (teams[i].choice == 1) {
                    out.println(teams[i].first);
                } else {
                    out.println(teams[i].second);
                }
            }
        }

        boolean assign(int i) {
            // if possible use first variant
            if (!usedFirsts.containsKey(teams[i].first)) {
                usedFirsts.put(teams[i].first, i);
                teams[i].choice = 1;
                return true;
            }

            // move other team occupying conflicting solution to second variant
            if (pushToSecond(usedFirsts.get(teams[i].first)) == false) {
                return false;
            }

            // use second variant for current team
            if (pushToSecond(i) == false) {
                return false;
            }
            return true;
        }

        boolean pushToSecond(int i) {
            // move team from using variant 1 to using variant 2

            if (i < 0) return true;

            // remember to not use as a variant 1 solution for other team in the future
            usedFirsts.put(teams[i].first, -1);

            // if conflict -> no solution possible
            if (usedSeconds.contains(teams[i].second)) {
                return false;
            }

            usedSeconds.add(teams[i].second);
            teams[i].choice = 2;

            // if other team uses variant 1 which is the same as current team's variant 2
            // move other team to variant 2
            // otherwise remember not to use as variant 1 solution in the future
            if (usedFirsts.containsKey(teams[i].second)) {
                return pushToSecond(usedFirsts.get(teams[i].second));
            } else {
                usedFirsts.put(teams[i].second, -1);
            }

            return true;
        }

        class Team {
            String first;
            String second;
            int choice;

            public Team(String name, String city) {
                first = name.substring(0, 3);
                second = name.substring(0, 2) + city.charAt(0);
                choice = 0;
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
                }
            } catch (IOException ex) {
                System.err.println("An IOException was caught :" + ex.getMessage());
            }
            return tok.nextToken();
        }

    }
}

