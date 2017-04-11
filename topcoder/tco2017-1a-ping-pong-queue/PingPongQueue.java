// Topcoder Open 2017 Round 1A
// Ping Pong Queue
// Problem statement:
// https://community.topcoder.com/stat?c=problem_statement&pm=14516

import java.util.LinkedList;
import java.util.Queue;

public class PingPongQueue {
    public int[] whoPlaysNext(int[] skills, int N, int K) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < skills.length; i++) {
            q.add(skills[i]);
        }
        int currentWinner = skills[0];
        int gamesWon = 0;
        int op = -1;
        for (int i = 0; i < K; i++) {
            if (gamesWon == N) {
                q.add(currentWinner);
                currentWinner = q.remove();
                gamesWon = 0;
            }
            op = q.remove();
            if (op > currentWinner) {
                int temp = currentWinner;
                currentWinner = op;
                op = temp;
                gamesWon = 0;
            }
            q.add(op);
            gamesWon++;
        }
        return new int[] {op, currentWinner};
    }
}