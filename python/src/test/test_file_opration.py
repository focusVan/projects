file = open("./test_hash.py","r+")
while True:
    line = file.readline()
    if line:
        print(line)
    else:
        break
file.close()

file = open("./test_01.py","r",1,"utf-8")
print(file.read())

import os
import os.path

path = "./test_hash_copy.py"
if os.path.exists(path):
    os.remove(path)

import shutil
path = "./test_01.py"
shutil.copyfile(path,"test_01_copy.py")

import sys

sys.stdout = open("./temp.py","w")
print('''hello word''')