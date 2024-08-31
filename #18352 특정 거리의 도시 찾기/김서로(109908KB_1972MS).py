import heapq
import sys
input = sys.stdin.readline

city_num, road_num, length, start = map(int, input().split())
graph = [[] for _ in range(city_num + 1)]

for _ in range(road_num):
    A, B = map(int, input().split())
    graph[A].append(B) # A 도시에서 B 도시로 이동 가능

# 다익스트라
def dijkstra(start):
    heap = []
    visited = [False] * (city_num + 1)
    distance = [sys.maxsize] * (city_num + 1)

    heapq.heappush(heap, (0, start))
    distance[start] = 0

    while heap:
        current_cost, current_city = heapq.heappop(heap)
        # 방문 여부 확인
        if visited[current_city]:
            continue
        # 방문 처리
        visited[current_city] = True
        # 다른 도시로 이동
        for next_city in graph[current_city]:
            cost = current_cost + 1
            if distance[next_city] > cost:
                distance[next_city] = cost
                heapq.heappush(heap, (cost, next_city))

    return distance

distance = dijkstra(start)
flag = False

for i in range(1, len(distance)):
    if distance[i] == length:
        flag = True
        print(i)

if not flag:
    print(-1)