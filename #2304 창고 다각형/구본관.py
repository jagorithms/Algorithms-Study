import sys
from collections import deque
input = sys.stdin.readline

result = 0

li = []
maxh = 0

n = int(input())
for i in range(n):
    li.append(list(map(int,input().split())))
    if maxh<=li[-1][1]:
        maxh = li[-1][1]

li.sort()

preh = 0
prex = 0


for i in range(n):
    x,h = li[i]
    result += (x-prex)*preh
    prex = x
    preh = max(preh, h)
    if(h==maxh):
        break

li = li[i:]
li.sort(reverse=True)

preh = 0
prex = 1000

n = len(li)
for i in range(n):
    x,h = li[i]
    result += (prex-x)*preh
    prex = x
    preh = max(preh, h)


print(result + maxh)
