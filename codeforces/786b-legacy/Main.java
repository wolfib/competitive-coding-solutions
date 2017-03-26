// Codeforces Round 406
// Legacy
// Problem statement:
// http://codeforces.com/problemset/problem/786/B

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractCollection;
import java.util.StringTokenizer;
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
	/* First construct a directed weighted graph, then run Dijkstra's alg. on it
       First, construct graph with all planets as nodes
       Simple connections from a to b are added as edges
       For 1->Many and Many->1 connections construct a segment tree each and include them in the graph
       The segment trees have edges of weight 0 and its leaves are connected to the planet nodes originally in the graph
       For a 1->Many connection the segment tree's connections go from root towards leaves and the 1->Many connection
       connects planet nodes with interior nodes of the seg tree.
       For a Many->1 connection we use the other seg tree with edges going from leaves towards root. A Many->1 connection
       is added by adding connection(s) from seg tree interior nodes to a single planet node.
     */
	 
        final long inf = Long.MAX_VALUE;
        List<Edge>[] edges;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int q = in.nextInt();
            int s = in.nextInt() - 1;
            // nodes are stored as an array of adjacency lists
            // each seg tree needs 4 * n space, so total array size is n + 4n + 4n
            int N = 9 * n;
            int offset1 = n;
            int offset2 = 5 * n;
            edges = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                edges[i] = new ArrayList<>();
            }
            initSegTree(offset1, 0, 0, n - 1);
            initBackwardSegTree(offset2, 0, 0, n - 1);
            while (q-- > 0) {
                int type = in.nextInt();
                if (type == 1) {
                    int v = in.nextInt() - 1;
                    int u = in.nextInt() - 1;
                    int w = in.nextInt();
                    edges[v].add(new Edge(u, w));
                } else if (type == 2) {
                    int from = in.nextInt() - 1;
                    int left = in.nextInt() - 1;
                    int right = in.nextInt() - 1;
                    int cost = in.nextInt();
                    updateSegTree(offset1, 0, 0, n - 1, left, right, from, cost);
                } else {
                    int dest = in.nextInt() - 1;
                    int left = in.nextInt() - 1;
                    int right = in.nextInt() - 1;
                    int cost = in.nextInt();
                    updateBackwardSegTree(offset2, 0, 0, n - 1, left, right, dest, cost);
                }
            }

            // Dijkstra's pathfinding alg:
            long[] dist = new long[N];
            Arrays.fill(dist, inf);
            dist[s] = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(s, 0));
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                if (dist[node.idx] < node.dist) {
                    continue;
                }
                for (Edge toChild : edges[node.idx]) {
                    long newDist = node.dist + toChild.cost;
                    if (dist[toChild.dest] > newDist) {
                        dist[toChild.dest] = newDist;
                        pq.add(new Node(toChild.dest, newDist));
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                if (dist[i] == inf) {
                    out.print("-1 ");
                } else {
                    out.print(dist[i] + " ");
                }
            }
        }

        // connections with weight 0 from root of seg tree towards leaves of seg tree
		// for 1->Many connections
		void initSegTree(int offset, int idx, int left, int right) {
            if (left == right) {
                edges[offset + idx].add(new Edge(left, 0));
            } else {
                int mid = (left + right) / 2;
                initSegTree(offset, idx * 2 + 1, left, mid);
                initSegTree(offset, idx * 2 + 2, mid + 1, right);
                edges[idx + offset].add(new Edge(offset + idx * 2 + 1, 0));
                edges[idx + offset].add(new Edge(offset + idx * 2 + 2, 0));
            }
        }

        // connections with weight 0 from leaves of seg tree towards root of seg tree
		// for Many->1 connections
		void initBackwardSegTree(int offset, int idx, int left, int right) {
            if (left == right) {
                edges[left].add(new Edge(offset + idx, 0));
            } else {
                int mid = (left + right) / 2;
                initBackwardSegTree(offset, idx * 2 + 1, left, mid);
                initBackwardSegTree(offset, idx * 2 + 2, mid + 1, right);
                edges[offset + idx * 2 + 1].add(new Edge(offset + idx, 0));
                edges[offset + idx * 2 + 2].add(new Edge(offset + idx, 0));
            }
        }

		// add connection from original nodes to interior node(s) of seg tree
        void updateSegTree(int offset, int idx, int segLeft, int segRight, int updateLeft, int updateRight, int origin, int cost) {
            if ((segLeft > updateRight) || (segRight < updateLeft)) {
                return;
            }
            if ((segLeft >= updateLeft) && (segRight <= updateRight)) {
                edges[origin].add(new Edge(offset + idx, cost));
            } else {
                int mid = (segLeft + segRight) / 2;
                updateSegTree(offset, idx * 2 + 1, segLeft, mid, updateLeft, updateRight, origin, cost);
                updateSegTree(offset, idx * 2 + 2, mid + 1, segRight, updateLeft, updateRight, origin, cost);
            }
        }

		// add connection from interior node(s) of seg tree to original node
        void updateBackwardSegTree(int offset, int idx, int segLeft, int segRight, int updateLeft, int updateRight, int dest, int cost) {
            if ((segLeft > updateRight) || (segRight < updateLeft)) {
                return;
            }
            if ((segLeft >= updateLeft) && (segRight <= updateRight)) {
                edges[offset + idx].add(new Edge(dest, cost));
            } else {
                int mid = (segLeft + segRight) / 2;
                updateBackwardSegTree(offset, idx * 2 + 1, segLeft, mid, updateLeft, updateRight, dest, cost);
                updateBackwardSegTree(offset, idx * 2 + 2, mid + 1, segRight, updateLeft, updateRight, dest, cost);
            }
        }

        class Edge {
            int dest;
            int cost;

            public Edge(int d, int c) {
                dest = d;
                cost = c;
            }

        }

        class Node implements Comparable<Node> {
            int idx;
            long dist;

            public Node(int i, long d) {
                idx = i;
                dist = d;
            }


            public int compareTo(Node o) {
                return Long.compare(dist, o.dist);
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

