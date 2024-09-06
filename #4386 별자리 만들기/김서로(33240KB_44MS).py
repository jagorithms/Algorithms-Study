import sys
import math

n = int(input())
# 인접 행렬
graph = [[0 for _ in range(n)] for _ in range(n)]
# 입력받을 별의 좌표들
coords = []

for _ in range(n):
    x, y = map(float, input().split())
    coords.append((x, y))

# 두 개의 별을 뽑는 조합을 구하고 거리 구하기 (자신과의 거리는 0으로 만들기 위해 중복 허용)
for i in range(n):
    for j in range(i, n):
        # 두 별 사이의 거리
        d = math.sqrt((coords[i][0] - coords[j][0]) ** 2 + (coords[i][1] - coords[j][1]) ** 2)
        graph[i][j] = d
        graph[j][i] = d

# 프림
min_distance = [sys.maxsize] * n
visited = [False] * n

cost = 0
# 0번째 별이 시작 노드
min_distance[0] = 0

for _ in range(n):
    d = sys.maxsize
    star = -1

    # 가장 가까운 별 선택
    for i in range(n):
        if visited[i]:
            continue
        if d > min_distance[i]:
            d = min_distance[i]
            star = i
    
    visited[star] = True
    cost += d

    # min_distance 갱신
    for i in range(n):
        if visited[i]:
            continue
        if graph[star][i] == 0:
            continue
        if min_distance[i] > graph[star][i]:
            min_distance[i] = graph[star][i]

print(f'{cost:.2f}')
