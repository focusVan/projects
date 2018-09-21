class Fruit:
    def __init__(self, color):
        self.color = color
        print("fruit's color : %s" % self.color)

    def grow(self):
        print("grow...")

class Apple(Fruit):
    def __init__(self, color):
        super(Apple, self).__init__(color)
        print("apple's color : %s" % self.color)

class Banana(Fruit):
    def __init__(self, color):
        self.color = color
        print("banana's color : %s" % self.color)
    def grow(self):
        print("banana grow")

apple = Apple("red")
apple.grow()

banana = Banana("yellow")
banana.grow()
