package main

import "fmt"

// Merge two subarrays L and M into arr
func merge(arr []int, p, q, r int) {
    n1 := q - p + 1
    n2 := r - q

    L := make([]int, n1)
    M := make([]int, n2)

    copy(L, arr[p:q+1])
    copy(M, arr[q+1:r+1])

    i, j, k := 0, 0, p

    for i < n1 && j < n2 {
        if L[i] <= M[j] {
            arr[k] = L[i]
            i++
        } else {
            arr[k] = M[j]
            j++
        }
        k++
    }

    for i < n1 {
        arr[k] = L[i]
        i++
        k++
    }

    for j < n2 {
        arr[k] = M[j]
        j++
        k++
    }
}

// Divide the array into two subarrays, sort them, and merge them
func mergeSort(arr []int, l, r int) {
    if l < r {
        m := l + (r-l)/2
        mergeSort(arr, l, m)
        mergeSort(arr, m+1, r)
        merge(arr, l, m, r)
    }
}

// Print the array
func printArray(arr []int) {
    for _, num := range arr {
        fmt.Print(num, " ")
    }
    fmt.Println()
}

func main() {
    arr := []int{6, 5, 12, 10, 9, 1}
    fmt.Println("Unsorted array:")
    printArray(arr)

    mergeSort(arr, 0, len(arr)-1)

    fmt.Println("Sorted array:")
    printArray(arr)
}
