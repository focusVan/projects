str1 = "version"
str2 = 1.0
format = "%s %d" % (str1,str2)
print(format)

print(str1.center(20))
print(str1.center(20,"*"))
print(str1.ljust(3))
print(str1.ljust(12,"*"))
print(str1.rjust(20,"*"))
str3 = str1 + "\t" + "word" + "\n" + "ok"
print(str3)
print(str3.strip("\t"))

print(str3[::2])

print("a12s".startswith("a"))

def reverse(str):
    out = ""
    li = list(str)
    for i in range(len(li),0,-1):
        out += li[i-1]
    return out

word = "word"
print(word)
print(reverse(word))

print(word.rfind("w"))

import time,datetime

print(time.strftime("%Y-%m-%d", time.localtime()))
t = time.strptime("2018-01-01", "%Y-%m-%d")
y,m,d = t[0:3]
print(y)
print(m)
print(d)
print(datetime.datetime(y,m,d))

print(time.localtime())