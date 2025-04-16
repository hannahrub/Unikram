#C:\Users\hanna\AppData\Local\Microsoft\WindowsApps\python3.exe
# -*-coding: utf-8 -*-

# in der Vorlesung bin ich inhaltlich so bis S 217 durch

# A5.1

"""def primes(n):
    prims = [2]
    for i in range(3, n+1):   # gehe alle zahlen [3,n] durch und finde prims
        p = True
        for j in range(2, i):   #teste alle teiler
            if((i % j)== 0):
                p = False       # teiler gefunden also keine primzahl
                break
        if(p):
            prims.append(i)
    return prims




file = open("A5_primzahlen.txt", "a") 
for i in primes(1000):
    file.write(str(i) + "\n")
file.close
 
 
# A5.2
f = open("A5_namen.txt", "r") # open read access
namen = []
for line in f:
    line.replace("\n", "")
    namen.append(line)
namen.sort()
f.close()

f = open("A5_namen.txt", "a") # open write access
f.write("\n")
for name in namen:
    f.write(name)

f.close()


# A5.3
import gzip

# zip
f = open("A5_namen.txt", "rb")
data = f.read() #inhalt als string auslesen
bindata = bytearray(data)       # in bytes umwandeln weil gzip mit bytes arbeitet

with gzip.open("A5_namen.txt.gz", "wb") as f_out: #bytes ins zip atchiv schreiben
    f_out.write(bindata)

print("data zipped")

# unzip
# erstelle leeres file und schreibe da den inhalt des zip archives rein
fp = open("A5_namen1.txt", "wb")
with gzip.open("A5_namen.txt.gz", "rb") as f:
    bindata = f.read()
    fp.write(bindata)
    fp.close()

print("data un-zipped")"""

# A5.4
import sqlite3
import csv

connection = sqlite3.connect("test.db")
print("opened db...")

#connection.execute('''CREATE TABLE NAMEN(NAME);''')
#print("created table...")

cursor = connection.cursor()
cursor.execute("SELECT name FROM sqlite_master WHERE type='table';")
print(cursor.fetchall())

f = open("A5_namen.txt", "r")
for n in f.readlines():
    connection.execute("INSERT INTO NAMEN (NAME) VALUES ('" + str(n) + "')")

data = cursor.execute("SELECT * FROM NAMEN")
print("data:", data)


# A5.7
f = open("output.csv", "w")

writer = csv.writer(f)
for i in data:
    writer.writerow([i])

    
connection.close()
print("closed db...")

