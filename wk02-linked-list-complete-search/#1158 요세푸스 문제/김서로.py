N, K = map(int, input().split())
lst = [i + 1 for i in range(N)] # 1부터 N까지의 숫자로 이루어진 리스트 생성
josephus = []
index = 0
for _ in range(N):
    index = (index + K - 1) % len(lst)
    josephus.append(lst.pop(index))
print("<" + ", ".join(map(str, josephus)) + ">")