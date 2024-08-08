import sys
from collections import deque

input = sys.stdin.readline

dydx = [[0,0],[-1,0],[1,0],[0,-1],[0, 1]]

point = []
for y in range(3):
    for x in range(3):
        point.append([y, x])

dic = {}

dq = deque()
dq2 = deque()
dq2.append(0)
num = 0

def paint(bit, y, x):
    for dy, dx in dydx:
        ny = y + dy
        nx = x + dx
        if 0<=nx<3 and 0<=ny<3:
            bit = bit ^ 1 << (8 - point.index([ny,nx]))
    return bit

nbit = paint(0, 1, 0)

while dq2:
    dq = dq2
    dq2 = deque()
    while dq:
        bit = dq.popleft()

        if bit in dic:
            continue
        dic[bit] = num

        for i in range(9):
            y,x = point[i]
            nbit = paint(bit, y,x)

            # b = bin(nbit)[2:]
            # b = b.zfill(9)
            # print(b[:3])
            # print(b[3:6])
            # print(b[6:])
            # print()

            dq2.append(nbit)
    num+=1

for _ in range(int(input())):
    li = "0b"
    for _ in range(3):
        for i in input().strip():
            if i=="*":
                li += "1"
            else:
                li += "0"
    print(dic[int(li,2)])
