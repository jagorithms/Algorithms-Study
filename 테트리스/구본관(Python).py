import sys
input = sys.stdin.readline

tetris = {
    1: [[0],[0,0,0,0]],
    2: [[0,0]],
    3: [[0,0,1],[0,-1]],
    4: [[0,-1,-1],[0,1]],
    5: [[0,0,0],[0,-1],[0,1],[0,-1,0]],
    6: [[0,0,0],[0,0],[0,-2],[0,1,1]],
    7: [[0,0,0],[0,0],[0,2],[0,0,-1]],
}


C, P = map(int, input().split())
arr = list(map(int, input().split()))
N = len(arr)
result = 0

for i in range(N):
    height = []
    cur = arr[i]
    for t in range(i, i+4):
        if t>=N:
            break
        height.append(arr[t] - cur)
        if height in tetris[P]:
            result += 1
    #print(height)

print(result)
