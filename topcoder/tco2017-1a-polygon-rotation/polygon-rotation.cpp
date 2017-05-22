#include <algorithm>
#include <vector>
#include <cmath>

#define mp make_pair
#define pii pair<int,int> 
#define x first 
#define y second 
#define L(s) (int)((s).size()) 
#define all(c) (c).begin(), (c).end()
#define pi M_PI

using namespace std;

inline double ds(pii p1, pii p2, double sy) {
    if (p1.y == p2.y) return abs(max(p1.x, p2.x));
    return abs(p1.x + (double)(p2.x - p1.x) / (p2.y - p1.y) * (sy - p1.y));
}

double f(double sy, const vector<int>& X, const vector<int>& Y) {
    int n = L(X);
    double mx = 0.0;
    for(int i = 0; i < n; ++i) {
     if (abs(Y[i] - sy) < 1e-9) {
     mx = max<double>(mx, abs(X[i]));
     }
     int nx = i < n - 1 ? i + 1 : 0;
     if (min(Y[i], Y[nx]) > sy) continue;
     if (max(Y[i], Y[nx]) < sy) continue;
     double cur = ds(mp(X[i], Y[i]), mp(X[nx], Y[nx]), sy);
     mx = max(mx, cur);
    }
    return pi * mx * mx;
}

class PolygonRotation {
public:
    double getVolume(vector <int> X, vector <int> Y) {
     double a = *min_element(all(Y)), b = *max_element(all(Y));
     const int N = 1000000;
     double s = 0;
     double h = (b - a) / N;
     for (int i = 0; i <= N; ++i) {
     double y = a + h * i;
     s += f(y, X, Y) * ((i == 0 || i == N) ? 1 : ((i & 1) == 0) ? 2 : 4);
     }
     s *= h / 3;
     return s;
    }
};
