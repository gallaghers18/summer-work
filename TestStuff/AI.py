from Dot import *
from Graphics import *
import random as rd

def clear(window):
    for item in window.items[:]:
        item.undraw()
    window.update()
              
def spawnDots(window):
    dotList = []
    dotMoveHistory = []
    for i in range(100): # Need more automation to do more than 100 exact dots
        dotList.append(Dot(400, 0, window))
        dotList[i].draw()
        dotMoveHistory.append([])
    return (dotList, dotMoveHistory)

def takeRandomStep(dot):
    dx = rd.randrange(-20, 20, 1)
    dy = rd.randrange(-20, 20, 1)
    newX = dot.getX() + dx
    newY = dot.getY() + dy
    
    if 0 < newX < 800 and 0 < newY < 800:
        dot.move(dx, dy)
        
    return (dx, dy)
    
def findBestDots(dotList):# Be more particular about allowing diff numbers than 10
    distanceList = []
    for i in range(len(dotList)):
        distanceList.append(dotList[i].getDistanceFromEnd())
    
    bestDotsIndices = sorted(range(len(distanceList)), key=lambda i: distanceList[i])[-10:]
    
    return(bestDotsIndices)

def cloneBestDots(bestDotsIndices, dotMoveHistory):
    newDotMoveHistory = []
    
    for i in range(100):
#        newDotList.append(dotList[bestDotsIndices[i%10]])
        newDotMoveHistory.append(dotMoveHistory[bestDotsIndices[i%10]])

    return newDotMoveHistory

def startNewWave(window, dotMoveHistory):
    dotList = runHistorySteps(window, dotMoveHistory)
    (dotList, dotMoveHistory) = runNewSteps(dotList, dotMoveHistory)

def runHistorySteps(window, dotMoveHistory):
    (dotList, dummy) = spawnDots(window)
    for i in range(100):
        for move in dotMoveHistory[i]:
            dotList[i].move(move[0], move[1])
    
    return dotList

def runNewSteps(dotList, dotMoveHistory):
    for j in range(10):
        for i in range(100):
            dotMoveHistory[i].append(takeRandomStep(dotList[i]))
            
    return (dotList, dotMoveHistory)
    



def main():
    window = GraphWin('Dancing Dots', 800, 800)
    
    (dotList, dotMoveHistory) = spawnDots(window)
      
#    startNewWave(window, dotMoveHistory)
#    bestDotsIndices = findBestDots(dotList) 
#    dotMoveHistory = cloneBestDots(bestDotsIndices, dotMoveHistory)
    

    for i in range(20):
        input('Press Enter...')
        clear(window)
        startNewWave(window, dotMoveHistory)
        bestDotsIndices = findBestDots(dotList) 
        dotMoveHistory = cloneBestDots(bestDotsIndices, dotMoveHistory)
        print('Run Number: {0}'.format(i+1))


    window.getMouse()

main()