import sys
input = sys.stdin.readline

decimalli = [False, False] + [True]*1000
prime=[]

for i in range(2,1001):
    if decimalli[i]:
        prime.append(i)
        for t in range(i*2, 1001, i):
            decimalli[t] = False

dic = {}

for a in range(len(prime)):
    for b in range(a, len(prime)):
        for c in range(b, len(prime)):
            dic[prime[a]+prime[b]+prime[c]] = [prime[a],prime[b],prime[c]]

for _ in range(int(input())):
    n = int(input())
    if n in dic:
        print(*dic[n])
