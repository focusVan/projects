import sys
dict = {"a":"banana","b":"apple","c":"orange"}

print(dict)
print(dict.items())
print(dict.keys())
print(dict.values())
print(dict["a"])
print(dict.get("a"))
print(dict.get("m","none"))

print(sorted(dict.items(), key=lambda d:d[0]))

print(sys.modules.keys())