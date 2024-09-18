import sys
from collections import deque
input = sys.stdin.readline

deltas = [
    (0, 1, 3), (0, 1, 2, 4), (1, 2, 5),
    (0, 3, 4, 6), (1, 3, 4, 5, 7), (2, 4, 5, 8),
    (3, 6, 7), (4, 6, 7, 8), (5, 7, 8)
]

# 보드판 클릭하기
def click_board(board, i):
    new_board = list(board)
    for delta in deltas[i]:
        if new_board[delta] == '0':
            new_board[delta] = '1'
        else:
            new_board[delta] = '0'
    return ''.join(new_board)

def BFS(board):
    # 1이면 클릭, 0이면 클릭 안 했음
    dq = deque([(board, 0, '000000000')])

    while dq:
        board, count, clicked = dq.popleft()
        # 보드의 칸이 모두 흰색이면
        if board == '000000000':
            return count
        # 모든 칸 클릭하기
        for i in range(9):
            if clicked[i] == '0':
                new_clicked = clicked[:i] + '1' + clicked[i+1:]
                dq.append((click_board(board, i), count + 1, new_clicked))

P = int(input())

for _ in range(P):
    board = ''
    # 문자열로 저장 (검은색을 1, 흰색을 0)
    for _ in range(3):
        row = list(input())
        for i in range(3):
            if row[i] == '*':
                board += '1'
            else:
                board += '0'

    print(BFS(board))