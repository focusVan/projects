import random;

def compareNum(num1, num2):
    if (num1 > num2):
        return 1
    elif (num1 == num2):
        return 0
    else:
        return 2
num1 = random.randrange(1,9)
num2 = random.randrange(1,9)

print("num1 = ", num1)
print("num2 = ", num2)
print(compareNum(num1, num2))

single = 'hello'
double = "word"
trible = ''''hello' "word
hahaha"'''

print(single)
print(trible)

class MyClass(object):
    def func(self, s):
        return s
    def doStuff(self, rdd):
        return rdd.map(self.func)

class MyClass(object):
    def __init__(self):
        self.field = "hello"
    def doStuff(self, rdd):
        return rdd.map(lambda s : self.field + s)

class MyClass(object):
    def __init__(self):
        self.field = "hello"
    def doStuff(self, rdd):
        field = self.field
        return rdd.map(lambda s : field + s)
