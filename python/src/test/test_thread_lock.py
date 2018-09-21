import threading
import time

class Counter:
    def __init__(self):
        self.value = 0
        self.lock = threading.Lock()
    def increment(self):
        self.lock.acquire()
        self.value = self.value + 1
        value = self.value
        self.lock.release()
        return value

counter = Counter()

class ThreadDemo(threading.Thread):
    def __init__(self, create_time, index):
        threading.Thread.__init__(self)
        self.create_time = create_time
        self.index = index
    def run(self):
        time.sleep(10)
        value = counter.increment()
        print((time.time() - self.create_time), "\t", self.index, "\tvalue: ", value)

for i in range(100):
    thread = ThreadDemo(time.time(), i)
    thread.start()
