from collections import deque

N, M = map(int, input().split())
indexs = list(map(int, input().split()))
numbers = [i for i in range(1, N+1)]
dq = deque(numbers)
count = 0

for number in indexs:
    l = len(dq)
    number_index = dq.index(number)
    if number_index * 2 <= l:
        # 뽑아내려고 하는 수가 큐의 맨 앞에 올 때까지
        while dq[0] != number:
            count += 1
            dq.append(dq.popleft())
        dq.popleft()
    else:
        # 뽑아내려고 하는 수가 큐의 맨 앞에 올 때까지
        while dq[0] != number:
            count += 1
            dq.appendleft(dq.pop())
        dq.popleft()
print(count)