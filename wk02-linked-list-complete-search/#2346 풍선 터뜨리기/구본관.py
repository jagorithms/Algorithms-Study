import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
dq = deque()
li = list(map(int,input().split()))
for i in range(n):
    dq.append((li[i], i+1))

while dq:
    s = dq.popleft()
    print(s[1], end=" ")
    if not dq:
        break

    if s[0]>0:
        for _ in range(s[0]-1):
            dq.append(dq.popleft())
    else:
        t = s[0]
        while t<0:
            t+=len(dq)
        for _ in range(t):
            dq.append(dq.popleft())
