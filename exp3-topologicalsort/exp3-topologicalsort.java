import java.util.Scanner;

public class TopologicalSorting {
    static void findIndegree(int[][] a, int[] indegree, int n) {
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++)
                sum += a[i][j];
            indegree[j] = sum;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, i, j, k = 0, curr;
        System.out.print("Enter no. of vertices: ");
        n = sc.nextInt();

        int[][] a = new int[100][100];
        int[] indegree = new int[100];
        int[] s = new int[100];
        int[] res = new int[100];
        int top = -1;

        System.out.println("Enter adjacency matrix:");
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                a[i][j] = sc.nextInt();
        }

        findIndegree(a, indegree, n);
        System.out.print("Indegree: ");
        for (i = 0; i < n; i++)
            System.out.print(indegree[i] + " ");

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

        System.out.print("\nTopological sorting: ");
        for (i = 0; i < n; i++)
            System.out.print(res[i] + " ");
        
        sc.close();
    }
}
