import heapq

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]
in_degree = [0 for _ in range(N+1)] # 진입 차수
answer = []
heap = []

# A → B 연결
for _ in range(M):
    A, B = map(int, input().split())
    graph[A].append(B)
    in_degree[B] += 1

# 진입 차수가 0인 노드 큐에 삽입
for i in range(1, N+1):
    if in_degree[i] == 0:
        heapq.heappush(heap, i)

while heap:
    node = heapq.heappop(heap)
    answer.append(node)

    for next_node in graph[node]:
        in_degree[next_node] -= 1
        if in_degree[next_node] == 0:
            heapq.heappush(heap, next_node)

print(*answer)