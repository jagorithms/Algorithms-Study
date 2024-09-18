import sys
input = sys.stdin.readline

N, T = map(int, input().split())
dp = [0] * (T + 1)

problem_info = []
sum = 0
for _ in range(N):
    day, fine = map(int, input().split())
    problem_info.append((day, fine))
    sum += fine

for day, fine in problem_info:
    for i in range(T, day - 1, -1):
        dp[i] = max(dp[i], dp[i - day] + fine)

print(sum - dp[T])