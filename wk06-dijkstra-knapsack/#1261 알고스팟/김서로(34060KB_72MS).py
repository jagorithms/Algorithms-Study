from collections import deque

M, N = map(int, input().split())
matrix = []
for _ in range(N):
    row = list(map(int, list(input())))
    matrix.append(row)

def bfs():
    dq = deque()
    visited = [[False for _ in range(M)] for _ in range(N)]
    deltas = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    # 현재 좌표, 벽을 부순 횟수
    dq.append((0, 0, 0))
    visited[0][0] = True

    while dq:
        r, c, count = dq.popleft()

        # 목적지에 도착하면
        if r == N - 1 and c == M - 1:
            return count

        # 사방 탐색
        for delta in deltas:
            nr = r + delta[0]
            nc = c + delta[1]
            # 범위를 벗어나면
            if nr < 0 or nc < 0 or nr >= N or nc >= M:
                continue
            # 이미 방문했으면
            if visited[nr][nc]:
                continue
            # 방문 처리
            visited[nr][nc] = True
            # 벽이면 부순 뒤 큐의 맨 뒤에 추가
            if matrix[nr][nc] == 1:
                dq.append((nr, nc, count + 1))
            # 벽이 아니면 큐의 맨 앞에 추가
            else:
                dq.appendleft((nr, nc, count))

print(bfs())