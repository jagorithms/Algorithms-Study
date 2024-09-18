N = int(input())
paper = []
for _ in range(N):
    row = list(map(int, input().split()))
    paper.append(row)

count = [0, 0, 0]

def cut(start_r, start_c, length):
    if length == 0:
        return
    
    num = paper[start_r][start_c]

    for i in range(start_r, start_r + length):
        for j in range(start_c, start_c + length):
            if paper[i][j] != num:
                # 재귀
                cut(start_r, start_c, length // 3)
                cut(start_r + length // 3, start_c, length // 3)
                cut(start_r + length // 3 * 2, start_c, length // 3)

                cut(start_r, start_c + length // 3, length // 3)
                cut(start_r + length // 3, start_c + length // 3, length // 3)
                cut(start_r + length // 3 * 2, start_c + length // 3, length // 3)

                cut(start_r, start_c + length // 3 * 2, length // 3)
                cut(start_r + length // 3, start_c + length // 3 * 2, length // 3)
                cut(start_r + length // 3 * 2, start_c + length // 3 * 2, length // 3)
                return
            
    count[num + 1] += 1

cut(0, 0, N)

for i in range(3):
    print(count[i])