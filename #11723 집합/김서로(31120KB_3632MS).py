import sys
input = sys.stdin.readline

M = int(input())
S = 0
# 비트마스크를 이용한 풀이
for _ in range(M):
    command = list(input().split())
    if command[0] == 'add':
        S = S | (1 << int(command[1]))
    elif command[0] == 'remove':
        S = S & ~(1 << int(command[1]))
    elif command[0] == 'check':
        if S & (1 << int(command[1])):
            print(1)
        else:
            print(0)
    elif command[0] == 'toggle':
        S = S ^ (1 << int(command[1]))
    elif command[0] == 'all':
        S = 0b111111111111111111111
    elif command[0] == 'empty':
        S = 0