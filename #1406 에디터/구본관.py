import sys
from collections import deque
input = sys.stdin.readline

frontdq = deque()
backdq = deque()

for s in input().strip():
    frontdq.append(s)

for _ in range(int(input())):
    s = input().strip()
    if s=="L":
        if frontdq:
            backdq.appendleft(frontdq.pop())
    elif s=="D":
        if backdq:
            frontdq.append(backdq.popleft())
    elif s=="B":
        if frontdq:
            frontdq.pop()
    else:
        frontdq.append(s.split()[1])

for i in frontdq:
    print(i, end="")

for i in backdq:
    print(i, end="")
