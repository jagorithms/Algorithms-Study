from collections import deque

N, L, R = map(int, input().split())
world = []
deltas = [[1, 0], [-1, 0], [0, 1], [0, -1]]

for _ in range(N):
    row = list(map(int, input().split()))
    world.append(row)

# flood fill
def bfs(start):
    global N, L, R
    global world, deltas, visited

    dq = deque()
    dq.append(start)

    union = [] # 연합 국가
    s = 0

    while dq:
        r, c = dq.popleft()
        # 이미 방문했으면
        if visited[r][c]:
            continue
        # 방문 처리
        visited[r][c] = True
        # 연합에 국가 추가
        union.append((r, c))
        s += world[r][c]
        # 사방 탐색
        for delta in deltas:
            nr = r + delta[0]
            nc = c + delta[1]
            # 범위를 벗어나면
            if nr < 0 or nc < 0 or nr >= N or nc >= N:
                continue
            differ = abs(world[r][c] - world[nr][nc])
            # 두 나라의 인구수 차이 조건을 만족하면 덱에 삽입
            if L <= differ <= R:
                dq.append((nr, nc))

    count = len(union)

    # 모든 국경선이 닫혀있으면
    if count == 1:
        return False

    # 인구 수 변경 (인구 이동)
    for nation in union:
        r, c = nation
        world[r][c] = s // count

    return True

time = 0

while True:
    flag = False
    visited = [[False for _ in range(N)] for _ in range(N)]

    # 인구 이동
    for r in range(N):
        for c in range(N):
            if not visited[r][c]:
                if bfs((r, c)):
                    flag = True

    if not flag:
        break

    time += 1

print(time)