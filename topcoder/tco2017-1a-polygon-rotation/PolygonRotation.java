// Topcoder Open 2017 Round 1A
// Polygon Rotation
// Problem statement:
// https://community.topcoder.com/stat?c=problem_statement&pm=10691&rd=16899&rm=&cr=40095361

public class PolygonRotation {

    public double getVolume(int[] x, int[] y) {
        // solution based on disc integration
        // has accuracy issues (even when using java translation of reference c++ solution)
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (int yy : y) {
            minY = Math.min(minY, yy);
            maxY = Math.max(maxY, yy);
        }
        final int N = 1000000;
        double sliceSum = 0;
        double height = (maxY - minY) / N;

        //based on reference solution
        /*for (int i = 0; i <= N; i++) {
            double currentY = minY + i * height;
            double radius = getRadius(currentY, x, y);
            int mult = ((i == 0) || (i == N)) ? 1 : (i & 1) == 0 ? 2 : 4; // why?
            sliceSum += radius * radius * mult;
        }
        sliceSum *= Math.PI * height / 3;*/

        // Cylinders, average of traversing twice starting at different ends
        /*for (int i = 0; i <= N; i++) {
            double currentY = minY + i * height;
            double radius = getRadius(currentY, x, y);
            int mult = ((i == 0) || (i == N)) ? 1 : 2;
            sliceSum += radius * radius * mult;
        }
        sliceSum *= Math.PI * height / 2;*/

        // Cone frustums
        for (int i = 0; i < N; i++) {
            double currentY = minY + i * height;
            double nextY = currentY + height;
            double r1 = getRadius(currentY, x, y);
            double r2 = getRadius(nextY, x, y);

            sliceSum += (r1 * r1 + r1 * r2 + r2 * r2);
        }
        sliceSum *= height * Math.PI / 3;

        return sliceSum;
    }

    double getRadius(double currentY, int[] x, int[] y) {
        int n = x.length;
        double maxX = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(y[i] - currentY) < 1e-9) {
                maxX = Math.max(maxX, Math.abs(x[i]));
            }
            int j = i < n - 1 ? i + 1: 0;
            if (Math.min(y[i], y[j]) > currentY) continue;
            if (Math.max(y[i], y[j]) < currentY) continue;
            double current = getX(x[i], y[i], x[j], y[j], currentY);
            maxX = Math.max(maxX, current);
        }
        return maxX;
    }

    double getX(int x1, int y1, int x2, int y2, double yMid) {
        if (y1 == y2) {
            return Math.abs(Math.max(x1, x2));
        }
        return Math.abs(x1 + (double) (x2 - x1) / (y2 - y1) * (yMid - y1));
    }
}