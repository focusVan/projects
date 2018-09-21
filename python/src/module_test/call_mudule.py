import my_module

my_module.func()
object = my_module.MyClass()
object.classFunc()

def func(x):
    if x > 0:
        return x

print(filter(func, range(-9,10)))
print(list(filter(func, range(-9,10))))

def sum(x,y):
    return x + y

from functools import reduce
print(reduce(sum,range(0,10)))
print(reduce(sum,range(0,10),10))
print(reduce(sum,range(0,0),10))


def power(x):
    return x ** x
print(map(power,range(1,5)))
print(list(map(power,range(1,5))))

def power(x,y):
    return x ** y
print(list(map(power,range(1,5),range(5,1,-1))))

if __name__ == "__main__":
    print("my_module is main")
else:
    print("my_module is not main")


