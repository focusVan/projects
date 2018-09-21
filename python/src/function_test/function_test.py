def arithmetic(x,y,operator):
    result = {"+": x + y,"-": x - y,"*": x * y, "/": x / y}
    return result.get(operator)

print(arithmetic(1,2,"+"))
print(arithmetic(2,4,"/"))

def search(*tuple,**dict):
    keys = dict.keys()
    for t in tuple:
        for key in keys:
            if (t == key):
                print(dict[key])


def returnFunc(n):
    for i in range(n):
        return i

def yieldFunc(n):
    for i in range(n):
        yield i

print(returnFunc(3))
yieldResult = yieldFunc(3)
print(yieldResult)
