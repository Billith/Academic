#!/usr/bin/env python

import numpy as np
from matplotlib import pyplot

previous = ''
next = 'P'
pointsArr = []
points = 0

def calculatePoints(userSign, aiSign):
    global points
    if userSign == 'P':
        if aiSign == 'K':
            points = points - 1
        if aiSign == 'N':
            points = points + 1
    if userSign == 'K':
        if aiSign == 'N':
            points -=1
        if aiSign == 'P':
            points +=1
    if userSign == 'N':
        if aiSign == 'P':
            points -=1
        if aiSign == 'K':
            points +=1
    pointsArr.append(points)

def getPreviousIndex(sign):
    if sign == 'P':
        return 0
    if sign == 'K':
        return 1
    if sign == 'N':
        return 2
    return 0

def getWinningSign(index):
    if index == 0:
        return 'N'
    if index == 1:
        return 'P'
    if index == 2:
        return 'K'

def calculateNextSign(sign, arr):
    if userInput == 'P':
        index = np.argmax(arr[0])
        prevIndex = getPreviousIndex(previous)
        arr[prevIndex][0] = arr[prevIndex][0]+1
        return getWinningSign(index)
    if userInput == 'K':
        index = np.argmax(arr[1])
        prevIndex = getPreviousIndex(previous)
        arr[prevIndex][1] = arr[prevIndex][1]+1
        return getWinningSign(index)
    if userInput == 'N':
        index = np.argmax(arr[2])
        prevIndex = getPreviousIndex(previous)
        arr[prevIndex][2] = arr[prevIndex][2]+1
        return getWinningSign(index)

A = [[0, 0, 0],
    [0, 0, 0],
    [0, 0, 0]]

i = 1
while i < 80:
    userInput = input ("Wpisz P,K,N: ")
    if userInput == 'P' or userInput == 'K' or userInput == 'N':
        print('Wybrane przez uzytkownika %s '% userInput)
        print('Wybrane przez komputer %s '% next)
        calculatePoints(userInput,next)
        next = calculateNextSign(userInput, A)
        previous = userInput
        i += 1
        
pyplot.plot(pointsArr)
pyplot.show()