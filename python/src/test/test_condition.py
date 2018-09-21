from threading import Thread,Condition,currentThread
import time

class Goods:
    def __init__(self):
        self.count = 0
    def produce(self, num=1):
        self.count += num

    def consume(self):
        if self.count:
            self.count -= 1

    def isEmpty(self):
        return not self.count

class Producer(Thread):

    def __init__(self, goods, condition, sleeptime=1):
        Thread.__init__(self)
        self.goods = goods
        self.condition = condition
        self.sleeptime = sleeptime

    def run(self):
        condition = self.condition
        goods = self.goods
        while 1 :
            condition.acquire()
            goods.produce()
            print("Goods count: ", goods.count, "Producer thread produced")
            condition.notifyAll()
            condition.release()
            time.sleep(self.sleeptime)

class Consumer(Thread):

    def __init__(self,index, goods, condition, sleeptime=0):
        Thread.__init__(self, name = str(index))
        self.goods = goods
        self.condition = condition
        self.sleeptime = sleeptime

    def run(self):
        condition = self.condition
        goods = self.goods
        while 1 :
            time.sleep(self.sleeptime)
            condition.acquire()
            while goods.isEmpty():
                condition.wait()
            goods.consume()
            print("Goods Count: ", goods.count, "Counsumer Thread ", currentThread())
            condition.release()

goods = Goods()
condition = Condition()
produce = Producer(goods, condition)
produce.start()

for i in range(5):
    consumer = Consumer(i, goods, condition)
    consumer.start()
