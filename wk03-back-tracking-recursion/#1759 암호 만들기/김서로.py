L, C = map(int, input().split())
chars = sorted(list(input().split()))
vowel = ['a', 'e', 'i', 'o', 'u'] # 모음
visited = []

# 유효한 모음 개수인지 판단
def valid_vowel_count(lst):
    count = 0
    for l in lst:
        if l in vowel:
            count += 1
    if count >= 1 and count <= len(lst) - 2:
        return True
    return False

def dfs(start):
    if len(visited) == L:
        if valid_vowel_count(visited):
            print(''.join(visited))
        return
    
    for i in range(start, C):
        if chars[i] not in visited:
            visited.append(chars[i])
            dfs(i+1)
            visited.pop() # 백트래킹

dfs(0)