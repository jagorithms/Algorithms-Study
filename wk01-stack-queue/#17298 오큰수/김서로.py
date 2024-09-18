N = int(input())
lst = list(map(int, input().split()))

stack = []
result = [-1] * N # 오큰수를 담을 리스트

for i in range(N):
    while stack and lst[i] > lst[stack[-1]]:
        result[stack.pop()] = lst[i]
    stack.append(i)

print(*result)

# 시간복잡도 O(n^2)
# for i in range(N):
#     hasNGE = False
#     for j in range(i, N):
#         if seq[i] < seq[j]:
#             print(seq[j], end=' ')
#             hasNGE = True
#             break
#     if not hasNGE:
#         print('-1', end=' ')