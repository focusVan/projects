class Fruit:
    pass

def add(self):
    print("grow....")

Fruit.grow = add
fruit = Fruit()
fruit.grow()