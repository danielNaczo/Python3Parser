def test0(a):
    return [1, 2, 3]


def test1(b):
    return test0(b)


def test2(a, b, c):
    return [a.x, b.y, c.z]


def test3(a, b, c):
    return [(a.x), (b.y), (c.z)]


def test4(a, b, c):
    return [(a.x.u), (b.y.v), (c.z.w)]


def test5(a):
    return a[1].x


def test6(a, b):
    return a(b)


def test7(x, y):
    z = test0(x)
    test1(y)
    y.a
    x[1]
    return test5(z)


def test8(x, y):
    x * y


def test9(x, y, z):
    x = y = z

1 * 2


def test11(x):
    (yield x)


def test12(y):
    yield y


def test13(a, b):
    """Test for Docstring"""
    a.b[1]
    ziffern = "0123456789"
    ziffern[a:b]


def test14(x, y):
    ({1, 2, 3} + {4, 5, 6}) * {7, 8, 9}


def test15(x, y):
    {x.a, y.b}


def test16(x, y):
    {(x.a), (y.b)}


def test17():
    [[1, 2], [3, 4], [5, 6].a]


def test18():
    None.a


def test19(a):
    a


def test20(a):
    a.b


def test21(a, b):
    a + b.x


def test22(a):
    x = a + "one" "two"


def test23():
    [1 + 2, 3 + 4, 4 + 5] + [1 + 2, 3 + 4, 4 + 5]


def test24():
    {1, 2, 3}
    {}


def test25(a, b):
    (a) + (b.x)
    (None) + (a[1])
