// Topcoder SRM 711
// Consecutive Ones
// Problem statement:
// https://community.topcoder.com/stat?c=problem_statement&pm=14558

public class ConsecutiveOnes {

    public long get(long n, int k) {

        long res = n;

        // is input already a valid solution?
        int streak = 0;
        for (int i = 0; i < 51; i++) {
            if (getBit(res, i)) {
                streak++;
                if (streak >= k) {
                    return res;
                }
            } else {
                streak = 0;
            }
        }

        // set the rightmost k digits to 1
        for (int i = 0; i < k; i++) {
            //res |= (1L << i);
            res = setBit(res, i);
        }

        // keep going left until we hit the first 0
        int idx = k - 1;
        while (getBit(res, idx + 1)) idx++;

        // jump k digits to the right
        idx -= k;

        // set the rightmost digits to 0
        while (idx >= 0) {
            res = unsetBit(res, idx);
            idx--;
        }
        return res;
    }
	
	 // returns true if the d'th lowest bit is set
    public static boolean getBit(long a, int d) {
        return ((a & (1L << d)) > 0);
    }

    // sets d'th lowest bit to 1
    public static long setBit(long a, int d) {
        return (a | (1L << d));
    }

    // sets d'th lowest bit to 0
    public static long unsetBit(long a, int d) {
        return (a & ~(1L << d));
    }
}
