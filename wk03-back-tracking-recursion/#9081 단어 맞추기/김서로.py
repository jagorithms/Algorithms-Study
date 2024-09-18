import sys
input = sys.stdin.readline

def next_permutation(word):
    for i in range(len(word)-1, 0, -1):
        if word[i-1] < word[i]:
            for j in range(len(word)-1, i-1, -1):
                if word[i-1] < word[j]:
                    word[i-1], word[j] = word[j], word[i-1]
                    return word[:i] + word[i::][::-1]
    return word

T = int(input())

for _ in range(T):
    word = list(input().rstrip())
    print(''.join(next_permutation(word)))