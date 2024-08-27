# N = 원생의 수, K = 나누고자 하는 조의 개수
N, K = map(int, input().split())
height = list(map(int, input().split()))
height_differ = []

# 이웃한 원생 간 키의 차이 저장
for i in range(N - 1):
    height_differ.append((height[i + 1] - height[i], i))

# 인접한 두 원생의 키 차이가 크면 조를 나누기 위해 내림차순 정렬
height_differ.sort(reverse=True)

# 해당 조에서 가장 오른쪽에 있는 사람(가장 키 큰 사람)
end_person = []

# 조를 나눌 위치 저장
for i in range(K - 1):
    differ, end = height_differ[i]
    end_person.append(end)

end_person.sort()
sum = 0
start = 0
for end in end_person:
    sum += height[end] - height[start]
    start = end + 1

sum += height[N - 1] - height[start]

print(sum)