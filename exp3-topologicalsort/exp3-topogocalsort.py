import numpy as np

def find_indegree(a, indegree, n):
    for j in range(n):
        indegree[j] = sum(a[i][j] for i in range(n))

def main():
    n = int(input("Enter no. of vertices: "))
    a = np.zeros((100, 100), dtype=int)
    indegree = np.zeros(100, dtype=int)
    s = np.zeros(100, dtype=int)
    res = np.zeros(100, dtype=int)
    top = -1
    
    print("Enter adjacency matrix:")
    for i in range(n):
        a[i][:n] = list(map(int, input().split()))
    
    find_indegree(a, indegree, n)
    print("Indegree:", " ".join(map(str, indegree[:n])))
    
    stack = []
    for i in range(n):
        if indegree[i] == 0:
            stack.append(i)
    
    k = 0
    while stack:
        curr = stack.pop()
        res[k] = curr
        k += 1
        for j in range(n):
            if a[curr][j] == 1:
                indegree[j] -= 1
                if indegree[j] == 0:
                    stack.append(j)
    
    print("Topological sorting:", " ".join(map(str, res[:n])))
    
if __name__ == "__main__":
    main()
