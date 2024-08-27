N = int(input()) # 센서의 개수
K = int(input()) # 집중국의 개수

if K >= N:
    print(0)
else:
    sensor = list(map(int, input().split())) # 센서 좌표
    distance = [] # 센서 사이의 거리
    center = [] # 집중국 좌표

    sensor.sort() # 센서 좌표 오름차순 정렬

    for i in range(N - 1):
        d = sensor[i + 1] - sensor[i]
        distance.append((d, i))

    distance.sort(reverse=True) # 센서 사이의 거리 내림차순 정렬

    for i in range(K - 1):
        d, end = distance[i]
        center.append(end)

    center.sort() # 집중국 좌표 오름차순 정렬

    sum = 0
    start = 0
    for end in center:
        sum += sensor[end] - sensor[start]
        start = end + 1

    sum += sensor[N - 1] - sensor[start]
    print(sum)