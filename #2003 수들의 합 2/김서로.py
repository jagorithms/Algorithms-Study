N, M = map(int, input().split())
P = list(map(int, input().split()))
acc_sum = [0] * (N+1)
count = 0

# 누적 합 미리 구하기
for i in range(1, N+1):
    acc_sum[i] = acc_sum[i-1] + P[i-1]

count = 0
left = 1
right = 1

while right <= N and left <= N:
    sum = acc_sum[right] - acc_sum[left-1]
    if sum == M:
        right += 1
        count += 1
    elif sum > M:
        left += 1
    else:
        right += 1

print(count)