def boolop0(a, b):
    return a and b


def boolop1(a, b):
    return a or b


def boolop2(a, b, c):
    return a or b and c


def boolop3(a, b, c):
    return (a or b) and c


def boolop4(a, b, c):
    return a or (b and c)


def boolop5(a, b, c):
    return ((a or b) or c)


def boolop6(a, b, c):
    return (a or (b or c))


def boolop7(a):
    return not a


def boolop8(a, b, c, d):
    return a and (b or c) and not d


def boolop9(a, b, c, d):
    return ((a and b) or c) and not d


def boolop10(a, b, c, d):
    return a and (b or (c and (not d)))


def boolop11(a, b, c, d):
    return a and ((not b) or c)


def boolop12(a, b):
    return not (a < b)


def boolop13(a, b):
    return (not a) < b


def boolop14(a, b):
    return not a + b


def boolop15(a, b):
    return (not a) + b


def boolop16(a, b):
    return not (a + b)