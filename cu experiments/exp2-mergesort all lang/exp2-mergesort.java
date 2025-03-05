// Merge Sort in Java
public class MergeSort {

    // Merge two subarrays L and M into arr
    void merge(int[] arr, int p, int q, int r) {
        // Create L ← A[p..q] and M ← A[q+1..r]
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] L = new int[n1];
        int[] M = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[p + i];
        }
        for (int j = 0; j < n2; j++) {
            M[j] = arr[q + 1 + j];
        }

        // Maintain current index of subarrays and main array
        int i = 0, j = 0, k = p;

        // Until we reach either end of either L or M, pick the smaller element
        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = M[j];
                j++;
            }
            k++;
        }

        // Pick up the remaining elements of L, if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Pick up the remaining elements of M, if any
        while (j < n2) {
            arr[k] = M[j];
            j++;
            k++;
        }
    }

    // Divide the array into two subarrays, sort them, and merge them
    void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // m is the midpoint where the array is divided into two subarrays
            int m = l + (r - l) / 2;

            // Recursively sort the two subarrays
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted subarrays
            merge(arr, l, m, r);
        }
    }

    // Print the array
    void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Driver program
    public static void main(String[] args) {
        int[] arr = {6, 5, 12, 10, 9, 1};
        int size = arr.length;

        MergeSort sorter = new MergeSort();
        System.out.println("Unsorted array:");
        sorter.printArray(arr);

        sorter.mergeSort(arr, 0, size - 1);

        System.out.println("Sorted array:");
        sorter.printArray(arr);
    }
}
