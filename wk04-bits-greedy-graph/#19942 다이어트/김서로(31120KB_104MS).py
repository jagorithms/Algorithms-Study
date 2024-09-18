# 모든 부분 집합을 구하고 조건을 만족하는 부분 집합이라면 최소 가격 갱신
import sys

N = int(input()) # 식재료 개수
protein, fat, carbo, vitamin = map(int, input().split())

ingredients = [] # 식재료 정보
for i in range(N):
    ingredients.append(list(map(int, input().split())))

visited = [False for _ in range(N)]
min_price = sys.maxsize

# 조건을 만족하는 부분 집합이라면 최소 가격 갱신
def renew_min_price():
    global min_price
    global choosed

    protein_sum, fat_sum, carbo_sum, vitamin_sum, price_sum = 0, 0, 0, 0, 0
    for i in range(N):
        if visited[i]:
            protein_sum += ingredients[i][0]
            fat_sum += ingredients[i][1]
            carbo_sum += ingredients[i][2]
            vitamin_sum += ingredients[i][3]
            price_sum += ingredients[i][4]
    
    # 최소 영양 성분을 충족하면
    if protein_sum >= protein and fat_sum >= fat \
    and carbo_sum >= carbo and vitamin_sum >= vitamin:
        # 최소 가격 갱신
        if min_price > price_sum:
            min_price = price_sum
            # 선택한 식재료 기록
            choosed = []
            for i in range(N):
                # 모든 영양분이 0인 식재료 포함하지 않음(예외 처리)
                if visited[i] and not (ingredients[i][0] == 0 and ingredients[i][1] == 0 and ingredients[i][2] == 0 and ingredients[i][3] == 0):
                    choosed.append(i + 1)

# 모든 부분 집합 구하기
def make_set(index):
    global min_price
    
    if index == N:
        renew_min_price()
        return
    
    visited[index] = True
    make_set(index + 1)
    visited[index] = False
    make_set(index + 1)

make_set(0)
if min_price == sys.maxsize:
    print(-1)
else:
    print(min_price)
    print(*choosed)

#--------99%에서 틀리는 반례--------#
# 입력
# 3
# 0 0 0 1
# 0 0 0 1 1
# 0 0 0 0 0
# 0 0 0 0 0
# 정답
# 1
# 1
#---------------이유---------------#
# 모든 영양분이 0인 식재료가 있다면
# 포함하지 않는 게 최선의 선택이다.