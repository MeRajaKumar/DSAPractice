#include<iostream>
using namespace std;

void find_indegree(int a[100][100], int indegree[100], int n) {
    for (int j = 0; j < n; j++) {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += a[i][j];
        indegree[j] = sum;
    }
}

int main() {
    int n, i, j, k = 0, curr;
    cout << "Enter no. of vertices: ";
    cin >> n;

    int a[100][100], indegree[100], s[100], top = -1, res[100];
    cout << "Enter adjacency matrix:\n";
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++)
            cin >> a[i][j];
    }

    find_indegree(a, indegree, n);
    cout << "Indegree: ";
    for (i = 0; i < n; i++)
        cout << indegree[i] << " ";

    for (i = 0; i < n; i++) {
        if (indegree[i] == 0)
            s[++top] = i;
    }

    while (top != -1) {
        curr = s[top--];
        res[k++] = curr;
        for (j = 0; j < n; j++) {
            if (a[curr][j] == 1) {
                indegree[j]--;
                if (indegree[j] == 0)
                    s[++top] = j;
            }
        }
    }

    cout << "\nTopological sorting: ";
    for (i = 0; i < n; i++)
        cout << res[i] << " ";

    return 0;
}
