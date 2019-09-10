def unaryOp0(a):
    return +a


def unaryOp1(a):
    return -a


def unaryOp2(a):
    return ~a


def unaryOp3(a):
    return +~-a


def unaryOp4(a, b):
    return +a + -b


def unaryOp5(a, b):
    return not +a


def unaryOp6(a, b):
    return ~a and -b


def unaryOp7(a, b):
    return (~a) and (-b)


def unaryOp8(a, b):
    return ~(a and b)


def unaryOp9(a):
    return not (+a)


def unaryOp10(a):
    return not (+-a)


def unaryOp11(a, b):
    return +(not a)


def unaryOp12(a, b):
    return ~(a + b)


def unaryOp13(a, b):
    return +(a + b)
