import threading,queue
import time,random

class Worker(threading.Thread):
    def __init__(self, index, queue):
        threading.Thread.__init__(self)
        self.index = index
        self.queue = queue
    def run(self):
        while 1 :
            time.sleep(random.random())
            item = self.queue.get()
            if item is None:
                break
            print("index: ", self.index, " task", item, "finished")

queue = queue.Queue(0)
for i in range(2):
    Worker(i, queue).start()

for i in range(10):
    queue.put(i)
for i in range(2):
    queue.put(None)