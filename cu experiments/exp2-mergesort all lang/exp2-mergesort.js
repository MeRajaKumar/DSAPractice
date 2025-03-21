// Merge Sort in JavaScript

// Merge two subarrays L and M into arr
function merge(arr, p, q, r) {
    // Create L ← A[p..q] and M ← A[q+1..r]
    const n1 = q - p + 1;
    const n2 = r - q;

    const L = [];
    const M = [];

    for (let i = 0; i < n1; i++) {
        L[i] = arr[p + i];
    }
    for (let j = 0; j < n2; j++) {
        M[j] = arr[q + 1 + j];
    }

    // Maintain current index of subarrays and main array
    let i = 0;
    let j = 0;
    let k = p;

    // Until we reach either end of either L or M
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
function mergeSort(arr, l, r) {
    if (l < r) {
        // m is the midpoint where the array is divided into two subarrays
        const m = l + Math.floor((r - l) / 2);

        // Recursively sort the two subarrays
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);

        // Merge the sorted subarrays
        merge(arr, l, m, r);
    }
}

// Print the array
function printArray(arr) {
    console.log(arr.join(' '));
}

// Driver program
const arr = [6, 5, 12, 10, 9, 1];
console.log("Unsorted array:");
printArray(arr);

mergeSort(arr, 0, arr.length - 1);

console.log("Sorted array:");
printArray(arr);
