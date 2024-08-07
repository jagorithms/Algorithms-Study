from collections import deque

N = int(input())
balloon = deque(enumerate(map(int, input().split())))
answer = []

while balloon:
    index, delta = balloon.popleft()
    answer.append(index + 1)

    # 오른쪽으로 이동해야 하면
    if delta > 0:
        balloon.rotate(-(delta - 1))
    # 왼쪽으로 이동해야 하면
    else:
        balloon.rotate(-delta)

print(*answer)