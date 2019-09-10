import ast

source = 'a < b == c > d'
node = ast.parse(source)

# print(eval(compile(node, '<string>', mode='eval')))

print(ast.dump(node, True, False))
