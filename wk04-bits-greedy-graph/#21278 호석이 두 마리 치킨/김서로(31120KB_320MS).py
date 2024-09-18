import sys
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[sys.maxsize for _ in range(N + 1)] for _ in range(N + 1)]
choosed = [0, 0]
min_sum = sys.maxsize

for _ in range(M):
    A, B = map(int, input().split())
    graph[A][B] = 1
    graph[B][A] = 1

# 자신과의 거리는 0으로 초기화
for i in range(1, N + 1):
    graph[i][i] = 0

# 모든 건물에서 다른 건물들까지 거리 계산
def calc_distance():
    for k in range(1, N + 1):
        for i in range(1, N + 1):
            for j in range(1, N + 1):
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

# 치킨집에서 다른 건물들까지 거리 계산
def calc_chicken_distance(a, b):
    sum = 0
    for i in range(1, N + 1):
        sum += min(graph[a][i], graph[b][i])

    return sum

# 치킨집을 2개 뽑는 조합
def make_combination():
    global min_sum

    for i in range(1, N):
        for j in range(i + 1, N + 1):
            sum = calc_chicken_distance(i, j)
            if min_sum > sum:
                min_sum = sum
                choosed[0] = i
                choosed[1] = j

calc_distance()
make_combination()
print(*choosed, min_sum * 2)