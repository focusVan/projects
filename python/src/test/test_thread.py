import threading
import time

class ThreadSkeleton(threading.Thread):
    def __init__(self, index, create_time):
        threading.Thread.__init__(self)
        self.index = index
        self.create_time = create_time
    def run(self):
        time.sleep(1)
        print(time.time() - self.create_time)
        print(self.index)

#for i in range(5):
    #thread = ThreadSkeleton(i, time.time())
    #thread.start()

import random
class ThreadLocalVal(threading.Thread):
    def __init__(self, index):
        threading.Thread.__init__(self)
        self.num = random.choice(range(10))
        self.index = index
    def run(self):
        print(self.num)

for i in  range(5):
    thread = ThreadLocalVal(i)
    thread.start()