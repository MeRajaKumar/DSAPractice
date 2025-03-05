# Merge Sort in Ruby

# Merge two subarrays L and M into arr
def merge(arr, p, q, r)
    n1 = q - p + 1
    n2 = r - q
  
    L = arr[p..q]
    M = arr[q + 1..r]
  
    i = 0
    j = 0
    k = p
  
    while i < n1 && j < n2
      if L[i] <= M[j]
        arr[k] = L[i]
        i += 1
      else
        arr[k] = M[j]
        j += 1
      end
      k += 1
    end
  
    # Pick up the remaining elements of L
    while i < n1
      arr[k] = L[i]
      i += 1
      k += 1
    end
  
    # Pick up the remaining elements of M
    while j < n2
      arr[k] = M[j]
      j += 1
      k += 1
    end
  end
  
  # Divide the array into two subarrays, sort them, and merge them
  def merge_sort(arr, l, r)
    if l < r
      m = l + (r - l) / 2
  
      merge_sort(arr, l, m)
      merge_sort(arr, m + 1, r)
  
      merge(arr, l, m, r)
    end
  end
  
  # Print the array
  def print_array(arr)
    puts arr.join(' ')
  end
  
  # Driver program
  arr = [6, 5, 12, 10, 9, 1]
  puts "Unsorted array:"
  print_array(arr)
  
  merge_sort(arr, 0, arr.length - 1)
  
  puts "Sorted array:"
  print_array(arr)
  