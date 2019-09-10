def listgen0():
    testlist = [1,2,3,4,5,6]
    genlist = [x**2 for x in testlist if x % 2 == 0 if x > 2]
    return genlist


def listgen1():
    testlist = [1,2,3,4,5,6]
    genlist = [(x**2 + 4) for x in testlist if x % 2 == 0 if x > 2]
    return genlist


def listgen2():
    testlist = [1,2,3,4,5,6]
    genlist = [(x**(2 + 4)) for x in testlist if x % 2 == 0 if x > 2]
    return genlist


def listgen3():
    testlist = [1,2,3,4,5,6]
    genlist = [x**y for x, y in testlist if x % 2 == 0 if x > 2]
    return genlist


def dictgen4():
    testdict = {1: 10, 2: 20, 3: 30}
    gendict = {(x**(2 + 4)): x for x in testdict if x % 2 == 0 if x > 2}
    return gendict


def dictgen5():
    testset = {1, 2, 3, 4, 5}
    genset = {(x**(2 + 4)) for x in testset if x % 2 == 0 if x > 2}
    return genset
