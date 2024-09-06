N, L = map(int, input().split())
ground = []

for _ in range(N):
    row = list(map(int, input().split()))
    ground.append(row)

# 행에 대하여 경사로인지 판단
def is_valid_row(row):
    global count
    visited = [False] * N

    for col in range(1, N):
        # 이전 칸과 높이가 같으면
        if ground[row][col] == ground[row][col - 1]:
            continue
        # 이전 칸보다 1칸 높으면
        elif ground[row][col] == ground[row][col - 1] + 1:
            temp = 0
            nc = col - 1
            while temp < L:
                if nc < 0:
                    return
                if ground[row][col] - 1 != ground[row][nc]:
                    return
                if visited[nc]:
                    return
                visited[nc] = True
                temp += 1
                nc -= 1
        # 이전 칸보다 1칸 낮으면
        elif ground[row][col] == ground[row][col - 1] - 1:
            temp = 0
            nc = col
            while temp < L:
                if nc >= N:
                    return
                if ground[row][col] != ground[row][nc]:
                    return
                if visited[nc]:
                    return
                visited[nc] = True
                temp += 1
                nc += 1
        # 이전 칸과 2칸 이상 차이 나면
        else:
            return

    count += 1

# 열에 대하여 경사로인지 판단
def is_valid_col(col):
    global count
    visited = [False] * N

    for row in range(1, N):
        # 이전 칸과 높이가 같으면
        if ground[row][col] == ground[row - 1][col]:
            continue
        # 이전 칸보다 1칸 높으면
        elif ground[row][col] == ground[row - 1][col] + 1:
            temp = 0
            nr = row - 1
            while temp < L:
                if nr < 0:
                    return
                if ground[row][col] - 1 != ground[nr][col]:
                    return
                if visited[nr]:
                    return
                visited[nr] = True
                temp += 1
                nr -= 1
        # 이전 칸보다 1칸 낮으면
        elif ground[row][col] == ground[row - 1][col] - 1:
            temp = 0
            nr = row
            while temp < L:
                if nr >= N:
                    return
                if ground[row][col] != ground[nr][col]:
                    return
                if visited[nr]:
                    return
                visited[nr] = True
                temp += 1
                nr += 1
        # 이전 칸과 2칸 이상 차이 나면
        else:
            return
    
    count += 1

count = 0 # 경사로 개수

# 각 행에 대하여 오르막길 찾기
for row in range(N):
    is_valid_row(row)

# 각 열에 대하여 오르막길 찾기
for col in range(N):
    is_valid_col(col)

print(count)