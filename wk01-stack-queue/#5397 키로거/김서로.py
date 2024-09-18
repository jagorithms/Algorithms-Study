T = int(input())

for _ in range(T):
    inputs = list(input())
    inputs.reverse()
    left_stack = []
    right_stack = []
    while inputs:
        c = inputs.pop()
        if c.isalnum():
            left_stack.append(c)
        if left_stack and c == '<':
            right_stack.append(left_stack.pop())
        if right_stack and c == '>':
            left_stack.append(right_stack.pop())
        if left_stack and c == '-':
            left_stack.pop()
    while right_stack:
        left_stack.append(right_stack.pop())
    print("".join(left_stack))
