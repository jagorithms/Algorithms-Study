N = int(input())
stairs = [0]
for _ in range(N):
    stairs.append(int(input()))

dp = [[0, 0] for _ in range(N+1)]
dp[1][0] = dp[1][1] = stairs[1]

for i in range(2, N+1):
    dp[i][0] = stairs[i] + dp[i-1][1]
    dp[i][1] = stairs[i] + max(dp[i-2][0], dp[i-2][1])

print(max(dp[N]))