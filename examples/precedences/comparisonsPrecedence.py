def bool1(a, b, c, d):
    return a < (b < (c < d))


def bool2(a, b):
    return a < b


def bool3(a, b, c, d):
    return a < b < c < d


def bool4(a, b, c, d):
    return ((a < b) < c) < d


def bool5(a, b, c, d):
    return a < b > c == d


def bool6(a, b, c):
    return a + (b * c)


def bool7(a, b, c):
    return a + (b - c)


def bool8(a, b, c):
    return a + (b < c)


def bool9(a, b, c, d):
    return ((a < b) + 4) == ((c + 5) < d)


def bool10(a, b, c, d):
    return (a < b + 4) == ((c + 5) < d)


def bool11(a, b, c, d):
    return a < b + 4 == ((c + 5) < d)


def bool12(a, b):
    return a < b + 4


def bool13(a, b, c, d):
    return a < b + 4 == c + 5 < d


def bool14(a, b, c, d):
    return a < b + 4 == (c + 5 < d)


def bool15(a, b, c, d):
    return (a < b + 4) == c + 5 < d


def bool16(a, b, c, d):
    return (a <= b >= (c != d))


def bool17(a, b, c, d):
    return a in b not in (c is d)


def bool18(a, b, c, d):
    return (a is not b) >= (c + d)
