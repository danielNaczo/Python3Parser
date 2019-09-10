def ifExpr0(a, b):
    return a if True else b


def ifExpr1(a, b):
    return a if (a and b) else b


def ifExpr2(a, b, c, d):
    return a or (b if c else d)


def ifExpr3(a, b):
    return (a if a else a) & (b if b else b)


def ifExpr4(a, b):
    return (a if a else a) or (b if b else b)


def ifExpr5(a, b, c):
    return a if (a and b) else (c if c else c)


def ifExpr6(a, b, c):
    return (a if a else a) if (b and b) else c


def ifExpr7(a, b, c):
    return a if (c if c else c) else b


def ifExpr8(a, b, c):
    return a if (c if c else c) else (b if b else b)


def ifExpr9(a, b, c):
    return (a if a else a) if (c if c else c) else (b if b else b)
