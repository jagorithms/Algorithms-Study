import sys
import heapq
input = sys.stdin.readline

N = int(input())
schedule = []
min_heap = []
available_computer = [] # 사용 가능한 컴퓨터
computer_count = [0] * N  # 컴퓨터 이용한 사람 수

for _ in range(N):
    start, end = map(int, input().split())
    schedule.append((start, end))

schedule.sort(key=lambda x: x[0])  # 시작 시각을 기준으로 정렬

count = 0
for start, end in schedule:
    # 사용 가능한 컴퓨터 갱신
    while min_heap and min_heap[0][0] <= start:
        temp = heapq.heappop(min_heap)
        # 번호가 가장 작은 자리에 앉도록 최소 힙에 삽입
        heapq.heappush(available_computer, temp[2])

    # 사용 가능한 컴퓨터가 있다면
    if available_computer:
        index = heapq.heappop(available_computer)
        computer_count[index] += 1
        heapq.heappush(min_heap, (end, start, index))

    # 사용 가능한 컴퓨터가 없다면
    else:
        computer_count[count] += 1
        heapq.heappush(min_heap, (end, start, count))
        count += 1
        
print(count)
print(*computer_count[:count])