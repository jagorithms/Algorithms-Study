import sys
input = sys.stdin.readline

# 스택 2개를 활용한 방법
left = list(input().rstrip()) # 입력된 문자열 리스트
right = []
M = int(input())

for _ in range(M):
    cmd = input().split()
    if cmd[0] == 'L':
        if left:
            right.append(left.pop())
    elif cmd[0] == 'D':
        if right:
            left.append(right.pop())
    elif cmd[0] == 'B':
        if left:
            left.pop()
    elif cmd[0] == 'P':
        left.append(cmd[1])

answer = left + right[::-1]
print(''.join(answer))