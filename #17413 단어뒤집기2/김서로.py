S = input() + ' '
word = "" # 뒤집을 단어 문자열
result = "" # 결과 문자열
isTag = False # 현재 태그를 출력하고 있는지 여부

for s in S:
    if s == '<':
        isTag = True
        if word:
            result += word[::-1]
            word = ""
    if isTag:
        result += s
    else:
        if s == ' ':
            result += word[::-1] + ' '
            word = ""
        else:
            word += s
    if s == '>':
        isTag = False

print(result)