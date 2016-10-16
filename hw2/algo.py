import matplotlib.pyplot as plt

if __name__ == '__main__':
    states = xrange(0,100)
    dictOfState2ValuePrevIter = {}
    # Initialize states to 0 for first iteration
    for i in states:
        dictOfState2ValuePrevIter[i] = 0
    
    dictOfState2ValuePrevIter[100] = 0
    ph = 0.4
   
    
        
    iterCount = 0
    while iterCount < 10000:
        dictOfState2ValueCurrIter = {}
        dictOfState2ValueCurrIter[0] = 0
        dictOfState2ValueCurrIter[100] = 0
        for s in states:
            if s == 0 or s == 100:
                dictOfState2ValueCurrIter[s]=0
                continue
            valuesForStates = []
            maxActionAvailableForThisState = min([s, 100-s])
            for a in xrange(1,maxActionAvailableForThisState+1):
                reward = ph * 1 if s + a == 100 else 0
                valuesForStates.append(reward + (ph * dictOfState2ValuePrevIter[s + a]) + ((1-ph) * dictOfState2ValuePrevIter[s - a]))
                
            #print 'Iteration Count %s State is %s complete'%(iterCount,s)
            dictOfState2ValueCurrIter[s] = max(valuesForStates)
            
        iterCount = iterCount + 1
        dictOfState2ValuePrevIter = dictOfState2ValueCurrIter
    print dictOfState2ValuePrevIter
        
    dictOfState2ValuePrevIter.pop(100)
    
    plt.plot(dictOfState2ValuePrevIter.keys(), dictOfState2ValuePrevIter.values())
    plt.show()