# Merge Sort in Python

# Merge two subarrays L and M into arr
def merge(arr, p, q, r):
    # Create L ← A[p..q] and M ← A[q+1..r]
    n1 = q - p + 1
    n2 = r - q

    L = arr[p:p + n1]
    M = arr[q + 1:q + 1 + n2]

    # Maintain current index of subarrays and main array
    i = 0  # Initial index of L
    j = 0  # Initial index of M
    k = p  # Initial index of merged subarray

    # Until we reach either end of either L or M
    while i < n1 and j < n2:
        if L[i] <= M[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = M[j]
            j += 1
        k += 1

    # Pick up the remaining elements of L, if any
    while i < n1:
        arr[k] = L[i]
        i += 1
        k += 1

    # Pick up the remaining elements of M, if any
    while j < n2:
        arr[k] = M[j]
        j += 1
        k += 1


# Divide the array into two subarrays, sort them, and merge them
def merge_sort(arr, l, r):
    if l < r:
        # m is the midpoint where the array is divided into two subarrays
        m = l + (r - l) // 2

        # Recursively sort the two subarrays
        merge_sort(arr, l, m)
        merge_sort(arr, m + 1, r)

        # Merge the sorted subarrays
        merge(arr, l, m, r)

# Print the array
def print_array(arr):
    print(" ".join(map(str, arr)))


# Driver program
if __name__ == "__main__":
    arr = [6, 89, 111, 10, 29, 1]
    print("Unsorted array:")
    print_array(arr)

    merge_sort(arr, 0, len(arr) - 1)

    print("Sorted array:")
    print_array(arr)
