def test0():
    return 1, 2, 3


def test1(a, b, c):
    del a
    del a, b, c
    del a.x, b.y


def test2(list):
    for element1, element2 in list:
        element1 + 1
        element2 - 1
    for element in list:
        element + 1


def test3():
    1, 2 + 3, 4
    (1, 2) + (3, 4)


def test4(a, x, list):
    b = (yield from a)
    c = (yield a)
    d = (yield a, x)
    e = (z**2 for z in list if x % 2 == 0)
    f = (yield (z**2 for z in list if x % 2 == 0))
    # grammar does not accept it, but Intellij (SDK Python 3.6) does accept it:
    # g = (yield z**2 for z in list if x % 2 == 0)
    h = ((yield z**2) for z in list if x % 2 == 0)
    i = ()
    return b, c, d, e


def test5(list):
    for a, (x, y) in list:
        z = y + 1

    for a, b in list:
        a = b + 1
