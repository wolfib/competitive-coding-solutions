// Topcoder Open 2017 Round 1A
// Cheese Slicing
// Problem statement:
// https://community.topcoder.com/stat?c=problem_statement&pm=10868

public class CheeseSlicing {
    public int totalArea(int A, int B, int C, int S) {
        if ((S > A) || (S > B) || (S > C)) return 0;
        if ((A % S == 0) || (B % S == 0) || (C % S == 0)) return (A * B * C / S);
        int a = (A % S) + S;
        int b = (B % S) + S;
        int c = (C % S) + S;
        int result = ((A * B * C) - (a * b * c)) / S;
        if ((a <= b) && (a <= c)) result += b * c;
        else if ((b <= a) && (b <= c)) result += a * c;
        else result += a * b;
        return result;
    }
}

