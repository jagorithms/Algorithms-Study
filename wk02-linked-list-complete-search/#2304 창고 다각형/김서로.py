N = int(input())
pillar = []
max_H = 0 # 최고 기둥 높이
max_L = 0 # 최고 기둥 위치

for _ in range(N):
    L, H = map(int, input().split())
    if max_H < H:
        max_H = H
        max_L = L
    pillar.append((L, H))

# 왼쪽부터 정렬
pillar.sort(key=lambda x: x[0])

prev_L = 0
prev_H = 0
sum = max_H

# 최고 기둥을 기준으로 오른쪽 넓이 구하기
while True:
    L, H = pillar.pop()
    if H >= prev_H:
        sum += prev_H * (prev_L - L)
        prev_H = H
        prev_L = L
    if L == max_L:
        pillar.append((L, H))
        break

pillar.reverse()

prev_L = 0
prev_H = 0

# 최고 기둥을 기준으로 왼쪽 넓이 구하기
while pillar:
    L, H = pillar.pop()
    if H >= prev_H:
        sum += prev_H * (L - prev_L)
        prev_H = H
        prev_L = L

print(sum)