from collections import deque

# 트럭 수, 다리 길이, 최대 하중
truck_num, bridge_length, max_weight = map(int, input().split())
trucks = list(map(int, input().split()))

time = 0
drive = [0] * bridge_length
drive = deque(drive)
drive_sum = 0

for truck in trucks:
    while drive_sum - drive[0] + truck > max_weight:
        drive_sum -= drive.popleft()
        drive.append(0) # 다리 길이 유지하기 위해 0 삽입
        time += 1
    drive_sum -= drive.popleft()
    drive_sum += truck
    drive.append(truck)
    time += 1

time += bridge_length
print(time)