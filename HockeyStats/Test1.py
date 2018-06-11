


def analyzeFile(fileName):
    bigList = []
    with open(fileName, "r") as file:
        bigList = file.read().splitlines()
    i = 0
    while i < len(bigList):
        roster = []
        while bigList[i] != "":
            roster.append(bigList[i])
            i+=1
        analyzeRoster(roster)
        i+=1
    
        
def analyzeRoster(roster):
    for i in range(len(roster)):
        playerDict[roster[i]] = 1
        
    print("Roster Analyzed")
    print(playerDict)
        
        
def main():
    analyzeFile("SharksRosters.txt")
    

playerDict = {}
main()