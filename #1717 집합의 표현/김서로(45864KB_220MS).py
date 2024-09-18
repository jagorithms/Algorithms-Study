import sys
sys.setrecursionlimit(100000)

input = sys.stdin.readline

n, m = map(int, input().split())
# 각 트리의 높이를 음수로 표현
parents = [-1 for _ in range(n+1)]

def find_set(a):
    if parents[a] == -1:
        return a
    
    parents[a] = find_set(parents[a])
    return parents[a]

def is_same_set(a, b):
    if find_set(a) == find_set(b):
        return True
    return False

def union(a, b):
    if is_same_set(a, b):
        return False
    
    a_root = find_set(a)
    b_root = find_set(b)
    # b 트리의 높이가 더 크다면 a 트리를 b 트리에 흡수
    if (a_root > b_root):
        parents[a_root] = b_root
    # a 트리의 높이가 더 크다면 b 트리를 a 트리에 흡수
    elif (a_root < b_root):
        parents[b_root] = a_root
    # 두 트리의 높이가 같다면
    else:
        parents[b_root] = a_root
        parents[a_root] -= 1 # 높이 1 증가

    return True

for _ in range(m):
    cmd, a, b = map(int, input().split())
    if cmd == 0:
        union(a, b)
    else:
        if is_same_set(a, b):
            print("YES")
        else:
            print("NO")