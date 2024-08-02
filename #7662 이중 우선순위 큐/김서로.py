import heapq
import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    number_count = {} # 동기화
    max_heap = [] # 최대 힙
    min_heap = [] # 최소 힙

    k = int(input())
    for _ in range(k):
        cmd, n = input().split()
        n = int(n)

        # 삽입
        if cmd == 'I':
            number_count[n] = number_count.get(n, 0) + 1
            heapq.heappush(max_heap, -n)
            heapq.heappush(min_heap, n)

        # 삭제
        elif cmd == 'D':
            while max_heap and min_heap:
                # 최댓값 삭제
                if n == 1:
                    temp = -heapq.heappop(max_heap)
                # 최솟값 삭제
                elif n == -1:
                    temp = heapq.heappop(min_heap)
                # number_count 동기화
                if number_count.get(temp, 0) != 0:
                    number_count[temp] -= 1
                    # 수가 없으면 key 삭제
                    if number_count[temp] == 0:
                        del number_count[temp]
                    break
    # 결과 출력
    if number_count:
        while max_heap:
            mx = -heapq.heappop(max_heap)
            if number_count.get(mx, 0) != 0:
                break
        while min_heap:
            mn = heapq.heappop(min_heap)
            if number_count.get(mn, 0) != 0:
                break
        print(mx, mn)
    else:
        print('EMPTY')