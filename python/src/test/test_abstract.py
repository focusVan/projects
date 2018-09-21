from abc import ABCMeta, abstractclassmethod

class Fruit(classmethod = abstractclassmethod):
    def grow(self):
        pass

class Apple(Fruit):
    def other(self):
        print("other")
    def grow(self):
        print("apple grow")

apple = Apple()
apple.grow()