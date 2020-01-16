#!/usr/bin/python3
import sys
import random
import operator

USER_MOVES = []
MY_MOVES = []
PAYOFFS = []

USER_MOVE = ''
MY_MOVE = ''

WINS = {
    'p': 'r',
    'r': 's',
    's': 'p'
}


def user_move():
    global USER_MOVE

    USER_MOVE = input("[?] What's your move? [r/p/s]?: ")[:1].lower()
    return USER_MOVE not in 'rps' or not USER_MOVE


def make_move():
    global USER_MOVES, MY_MOVE

    probability = {
        'p': 0,
        'r': 0,
        's': 0,
    }

    # check if we have some moves to analize
    if len(USER_MOVES) > 2:
        user_last_two_moves = USER_MOVES[-3:-1]

        for i in range(len(USER_MOVES[:-3])):
            # look for sequence of last two moves in rest of previous moves
            if USER_MOVES[i] == user_last_two_moves[0] and USER_MOVES[i+1] == user_last_two_moves[1]:
                # add probability of next move
                if USER_MOVES[i+2] == 'p':
                    probability['p'] += 1
                elif USER_MOVES[i+2] == 'r':
                    probability['r'] += 1
                elif USER_MOVES[i+2] == 's':
                    probability['s'] += 1

        print(probability)

        # look of some pattern
        # if pattern not found
        if all(value == 0 for value in probability.values()):
            MY_MOVE = random.choice(['r', 'p', 's'])
        else:
        # if pattern found
            user_next_move = max(probability.items(), key=operator.itemgetter(1))[0]
            if user_next_move == 'p':
                MY_MOVE = 's'
            elif user_next_move == 'r':
                MY_MOVE = 'p'
            elif user_next_move == 's':
                MY_MOVE = 'r'
    else:
        # make random choice if not enough data to analize
        MY_MOVE = random.choice(['r', 'p', 's'])

    MY_MOVES.append(MY_MOVE)
    print(f'[+] Compuer move: {MY_MOVE}')


def fight():
    global PAYOFFS

    if USER_MOVE == MY_MOVE:
        PAYOFFS.append(0)
        print('[+] Draw!')
    else:
        global WINS

        if WINS[USER_MOVE] == MY_MOVE:
            PAYOFFS.append(1)
            print('[+] User win this round!')
        else:
            PAYOFFS.append(-1)
            print('[+] Computer win this round!')


def print_results():
    user_points = str(PAYOFFS.count(1)).rjust(2, '0')
    draws = str(PAYOFFS.count(0)).rjust(2, '0')
    ai_points = str(PAYOFFS.count(-1)).rjust(2, '0')

    print()
    print('---------------------------------')
    print('|   USER   |   DRAW   |    AI   |')
    print('|    %s    |    %s    |    %s   |' % (user_points, draws, ai_points))
    print('---------------------------------')
    result = sum(PAYOFFS)

    if result > 0:
        print('[+] User won the game')
    elif result == 0:
        print('[+] Draw')
    else:
        print('[+] Computer won the game')


try:
    while len(PAYOFFS) != 40:
        while user_move():
            print("[!] Wrong move!")
        USER_MOVES.append(USER_MOVE)
        make_move()
        fight()

    print_results()
except KeyboardInterrupt:
    sys.exit(0)
