
# vl ca bis 252
# 6.1
import math

c = 0
while(c <= 2):
    print("c =", c)
    print("sin:", math.sin(c * math.pi))
    print("cos:", math.cos(c * math.pi))
    print("tan:", math.tan(c * math.pi))
    c += 0.01

# 6.2
import importlib
import A6_supplemental

for i in range(2,100):
    file = open("A6_supplemental.py", "a")
    if(i == 2):
      s = "\n    if i == " + str(i) + ": \n"
      file.write(s)
      s = "         print('is prim')"
      file.write(s)
      file.close()
      importlib.reload(A6_supplemental)
    else:
        for j in range(2,i):
            if (i % j == 0):  # Teiler gefunden
                s = "\n\n    if i == " + str(i) + ": \n"
                file.write(s)
                s = "        print('is not prim')"
                file.write(s)
                file.close()
                importlib.reload(A6_supplemental)
                break
        # kein teiler gefunden
        file = open("A6_supplemental.py", "a")
        s = "\n\n    if i == " + str(i) + ": \n"
        file.write(s)
        s = "        print('is prim')"
        file.write(s)
        file.close()
        importlib.reload(A6_supplemental)


# A6.3
import os
import sys

# verzeichnis
print(os.getcwd())
# username
id = os.getpid()
print(id)

# festplattenauslastung
print(os.cpu_count())


# 6.4
"""die idee ist
 - prozess starten mit os.getpid die prozess id kreigen
 im anderen skript wird gelauscht ob dieser aktiv ist
 wenn sie aus der liste der aktiven prozesse verschwindet wird 
 dass programm neu gestatet"""
