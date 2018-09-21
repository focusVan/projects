import os

path = os.environ.get("PATH")
#print(path)

#print(os.system("dir"))

notepad = "c:\\windows\\notepad.exe"
#os.execl(notepad, 'notepad.exe')

import subprocess

ping = subprocess.Popen(args='ping -n 4 www.baidu.com', shell=True)
print(ping.pid)
print(ping.returncode)