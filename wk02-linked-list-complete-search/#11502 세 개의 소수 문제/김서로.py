import sys
import math
input = sys.stdin.readline

def is_prime(num):
    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0:
            return False
    return True

def three_odd_num(num):
    answer = []
    for i in range(2, num):
        for j in range(2, num):
            if num-i-j > 0 and prime[i] and prime[j] and prime[num-i-j]:
                answer.append(i)
                answer.append(j)
                answer.append(num-i-j)
                answer = list(map(str, sorted(answer)))
                return " ".join(answer)
    return 0

# 소수 미리 구해두기
prime = [False] * 1000

for i in range(2, 1000):
    prime[i] = is_prime(i)

T = int(input())

for _ in range(T):
    k = int(input())
    print(three_odd_num(k))
        