#!/usr/bin/python3
import random

USER_MOVES = []
MY_MOVES = []
PAYOFFS = []

USER_MOVE = ''
MY_MOVE = 'r'

WINS = {
    'p' : 'r',
    'r' : 's',
    's' : 'p'
}

def user_move():
    global USER_MOVE
    USER_MOVE = input("What's your move? [r/p/s]?: ")[:1].lower()
    print(f'Your move: {USER_MOVE}')
    return USER_MOVE not in 'rps' or not USER_MOVE

def make_move():
    global USER_MOVES, MY_MOVE
    MY_MOVE = max(set(USER_MOVES), key=USER_MOVES.count) if USER_MOVES else random.choice(['r','p','s'])
    print(f'Probably next user move: {MY_MOVE}')
    MY_MOVES.append(MY_MOVE)
    print(f'Compuer move: {MY_MOVE}')


def fight():
    global PAYOFFS
    if USER_MOVE == MY_MOVE:
        PAYOFFS.append(0)
        print('Draw!')
    else:
        global WINS    
        if WINS[USER_MOVE] == MY_MOVE:
            PAYOFFS.append(1)
            print('User win this round!')
        else:
            PAYOFFS.append(-1)
            print('Computer win this round!')
    print(PAYOFFS)


while len(PAYOFFS) != 30:
    res = user_move()
    if res:
        print("Wrong move! Don't try to cheat!")
        continue
    USER_MOVES.append(USER_MOVE)
    make_move()
    fight()
print('Game ended')
