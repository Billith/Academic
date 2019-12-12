import random
import numpy as np


MOVE_ARR = ['K', 'P', 'N']

MOVE_PLAYER1 = ['K', 'P', 'P', 'N', 'K', 'P', 'K', 'P', 'N', 'K', 'P', 'K', 'N', 'K', 'N', 'P', 'P', 'N', 'N', 'K', 'K', 'K', 'K', 'N', 'N', 'K', 'K', 'P', 'N', 'K']
MOVE_PLAYER2 = ['N', 'K', 'N', 'N', 'N', 'K', 'N', 'N', 'P', 'K', 'N', 'P', 'P', 'N', 'K', 'P', 'K', 'P', 'N', 'K', 'N', 'K', 'P', 'K', 'K', 'P', 'K', 'N', 'N', 'P']
PAYOFF = [1, 1, -1, 0, 1, 1, 1, -1, 1, 0, -1, -1, 1, 1, -1, 0, 1, 1, 0, 0, 1, 0, -1, -1, -1, -1, 0, -1, 0, -1]


def simulateGame(numer_of_moves):
    #inicjalne wartości tablic
    player1_moves_array = []
    player2_moves_array = []
    payoff_array = []
    count_movement = 0

    while count_movement < numer_of_moves:
        player1_move = generateRandomValue()
        player2_move = generateRandomValue()
        payoff_result = getPayOff(player1_move,player2_move)

        player1_moves_array.append(player1_move)
        player2_moves_array.append(player2_move)
        payoff_array.append(payoff_result)

        count_movement = count_movement + 1

    return  player1_moves_array, player2_moves_array, payoff_array


def generateRandomValue():
    return random.choice(MOVE_ARR)

def getPayOff(mov1,mov2):
    if mov1 == 'K':
        if mov2 == 'K':
            return 0
        elif mov2 == 'P':
            return -1
        else:
            return 1
    elif mov1 == 'P':
        if mov2 == 'K':
            return 1
        elif mov2 == 'P':
            return 0
        else:
            return -1
    else:
        if mov2 == 'K':
            return -1
        elif mov2 == 'P':
            return 1
        else:
            return 0


def countWinDrawLoose(target,movements_array,payoff_array):
    win = 0;
    draw = 0;
    loose = 0;
    for i, move in enumerate(movements_array):
        if move == target:
            payoff = payoff_array[i]

            if payoff == 0:
                draw = draw + 1
            elif payoff == 1:
                win = win + 1
            else:
                loose = loose + 1

    return win, draw, loose

def getProbability(result,count):
    return round(result/count,3)

# Główna metoda
def main():
    #Wybór trybu: nowy/z tablicy
    print("*** START ***")
    print("* WYBIERZ OPCJE *")
    print("1 - nowa symulacja rozgrywki")
    print("2 - liczy z tablic statystycznych")
    numerOfGame = 30

    user_choice1 = int(input("Wybierz opcje: "))
    print("* Wybrales opcje " + str(user_choice1) + " *")

    if user_choice1 == 1:
        #Generowanie gry
        player1_moves_array, player2_moves_array, payoff_araa = simulateGame(numerOfGame);
        # Macierze
        generateMacierzWyjsc(numerOfGame, player1_moves_array, player2_moves_array, payoff_araa)
        generateMacierzPrzejsc(numerOfGame, player1_moves_array)
    elif user_choice1 == 2:
        #TODO
        print("*** DZIALANIE DLA TABLIC STATYCZNYCH")
        generateMacierzWyjsc(numerOfGame, MOVE_PLAYER1, MOVE_PLAYER2, PAYOFF)
        generateMacierzPrzejsc(numerOfGame, MOVE_PLAYER1)
    else:
        print("*** WYBRALES ZLA OPCJE ***")


def generateMacierzWyjsc(numerOfGame,player1_moves_array, player2_moves_array, payoff_araa):
    # player1_moves_array, player2_moves_array, payoff_araa = simulateGame(numerOfGame);

    print("*** WYNIKI ***")
    print("! Player 1 !")
    print(player1_moves_array)

    print("*** WYNIKI ***")
    print("! Player 2 !")
    print(player2_moves_array)

    print("*** WYNIKI ***")
    print("! Payoff !")
    print(payoff_araa)

    rock_win, rock_draw, rock_loose = countWinDrawLoose('K', player1_moves_array, payoff_araa)
    paper_win, paper_draw, paper_loose = countWinDrawLoose('P', player1_moves_array, payoff_araa)
    scizor_win, scizor_draw, scizor_loose = countWinDrawLoose('N', player1_moves_array, payoff_araa)

    print('** EMISSION W LICZBACH **')
    print('  |    W    |    D    |    P    ')
    print('--|---------|---------|---------')
    print('K |    {}    |    {}    |    {}    '.format(rock_win, rock_draw, rock_loose))
    print('P |    {}    |    {}    |    {}    '.format(paper_win, paper_draw, paper_loose))
    print('N |    {}    |    {}    |    {}    '.format(scizor_win, scizor_draw, scizor_loose))

    rock_win_pro = getProbability(rock_win, numerOfGame)
    rock_draw_pro = getProbability(rock_draw, numerOfGame)
    rock_loose_pro = getProbability(rock_loose, numerOfGame)
    paper_win_pro = getProbability(paper_win, numerOfGame)
    paper_draw_pro = getProbability(paper_draw, numerOfGame)
    paper_loose_pro = getProbability(paper_loose, numerOfGame)
    scizor_win_pro = getProbability(scizor_win, numerOfGame)
    scizor_draw_pro = getProbability(scizor_draw, numerOfGame)
    scizor_loose_pro = getProbability(scizor_loose, numerOfGame)

    print('** EMISSION **')
    print('  |    W    |    D    |    P    ')
    print('--|---------|---------|---------')
    print('K |    {}    |    {}    |    {}    '.format(rock_win_pro, rock_draw_pro, rock_loose_pro))
    print('P |    {}    |    {}    |    {}    '.format(paper_win_pro, paper_draw_pro, paper_loose_pro))
    print('N |    {}    |    {}    |    {}    '.format(scizor_win_pro, scizor_draw_pro, scizor_loose_pro))

    matrix_output = []
    matrix_output.insert(0, [rock_win_pro, rock_draw_pro, rock_loose_pro])
    matrix_output.insert(1, [paper_win_pro, paper_draw_pro, paper_loose_pro])
    matrix_output.insert(2, [scizor_win_pro, scizor_draw_pro, scizor_loose_pro])
    print('** MACIERZ WYJSC **')
    print(matrix_output)

def generateMacierzPrzejsc(numberOfGame, movements_array):
    rock_after_rock, paper_after_rock, scizor_after_rock, rock_count = calculateChooicesPlayerForOneTarget('K',
                                                                                                           movements_array)
    rock_after_paper, paper_after_paper, scizor_after_paper, paper_count = calculateChooicesPlayerForOneTarget('P',
                                                                                                           movements_array)
    rock_after_scizor, paper_after_scizor, scizor_after_scizor, scizor_count = calculateChooicesPlayerForOneTarget('N',
                                                                                                           movements_array)
    print('** TRANSITION W LICZBACH **')
    print("X PO Y")
    print('  |    K    |    P    |    N    ')
    print('--|---------|---------|---------')
    print('K |    {}    |    {}    |    {}    '.format(rock_after_rock, paper_after_rock, scizor_after_rock))
    print('P |    {}    |    {}    |    {}    '.format(rock_after_paper, paper_after_paper, scizor_after_paper))
    print('N |    {}    |    {}    |    {}    '.format(rock_after_scizor, paper_after_scizor, scizor_after_scizor))

    rock_after_rock_pro, paper_after_rock_pro, scizor_after_rock_pro = 0,0,0
    if rock_count != 0:
        rock_after_rock_pro = getProbability(rock_after_rock, rock_count)
        paper_after_rock_pro = getProbability(paper_after_rock, rock_count)
        scizor_after_rock_pro = getProbability(scizor_after_rock, rock_count)

    rock_after_paper_pro, paper_after_paper_pro , scizor_after_paper_pro = 0,0,0
    if paper_count != 0:
        rock_after_paper_pro = getProbability(rock_after_paper, paper_count)
        paper_after_paper_pro = getProbability(paper_after_paper, paper_count)
        scizor_after_paper_pro = getProbability(scizor_after_paper, paper_count)

    rock_after_scizor_pro, paper_after_scizor_pro, scizor_after_scizor_pro = 0,0,0
    if scizor_count != 0:
        rock_after_scizor_pro = getProbability(rock_after_scizor, scizor_count)
        paper_after_scizor_pro = getProbability(paper_after_scizor, scizor_count)
        scizor_after_scizor_pro = getProbability(scizor_after_scizor, scizor_count)

    print('** TRANSITION  **')
    print("X PO Y")
    print('  |    K    |    P    |    N    ')
    print('--|---------|---------|---------')
    print('K |    {}    |    {}    |    {}    '.format(rock_after_rock_pro, paper_after_rock_pro, scizor_after_rock_pro))
    print('P |    {}    |    {}    |    {}    '.format(rock_after_paper_pro, paper_after_paper_pro, scizor_after_paper_pro))
    print('N |    {}    |    {}    |    {}    '.format(rock_after_scizor_pro, paper_after_scizor_pro, scizor_after_scizor_pro))

    matrix_output = []
    matrix_output.insert(0, [rock_after_rock_pro, paper_after_rock_pro, scizor_after_rock_pro])
    matrix_output.insert(1, [rock_after_paper_pro, paper_after_paper_pro, scizor_after_paper_pro])
    matrix_output.insert(2, [rock_after_scizor_pro, paper_after_scizor_pro, scizor_after_scizor_pro])
    print('** MACIERZ PRZEJSC **')
    print(matrix_output)


def calculateChooicesPlayerForOneTarget(target,array):
    count = 0;
    rock = 0;
    paper = 0;
    scizor = 0;
    for i,move in enumerate(array):
        if (len(array) - 1) != i:
            if move == target:
                count = count + 1;
                #Kamien po targecie
                if array[i+1] == 'K':
                    rock = rock + 1
                #Papier po targecie
                if array[i + 1] == 'P':
                    paper = paper + 1
                #Nozyce po targecie
                if array[i + 1] == 'N':
                    scizor = scizor + 1

    return rock, paper, scizor, count


if __name__ == '__main__':
    main()
