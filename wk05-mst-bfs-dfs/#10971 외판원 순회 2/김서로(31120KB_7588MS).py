N = int(input())
costs = []
for _ in range(N):
    row = list(map(int, input().split()))
    costs.append(row)

visited = [] # 방문한 도시
min_cost = 10000000

def dfs(current_city, current_cost):
    global min_cost

    if len(visited) == N:
        # 처음 출발했던 도시로 돌아갈 수 없는 경우
        if costs[visited[-1]][visited[0]] == 0:
            return
        total_cost = current_cost + costs[visited[-1]][visited[0]]
        if min_cost > total_cost:
            min_cost = total_cost
        return
    
    for i in range(N):
        if i not in visited and costs[current_city][i] != 0:
            visited.append(i)
            dfs(i, current_cost + costs[current_city][i])
            visited.pop()

for i in range(N):
    visited.append(i)
    dfs(i, 0)
    visited.pop()

print(min_cost)