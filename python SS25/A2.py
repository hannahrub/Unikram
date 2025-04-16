#C:\Users\hanna\AppData\Local\Microsoft\WindowsApps\python3.exe
# -*-coding: utf-8 -*-

# A2.1
satz = "Die Sonne ist ein Stern und die Planten kreisen um die Sonne."
woerter = satz[: len(satz)-1].split()
print("wörter:", woerter)
print("#wörter = ", len(woerter))

for i in woerter:
    print(i, ": ", satz.count(i))

satz.replace('.', '!')
print(satz)

und_pos = satz.rfind("und")
neuer_satz = satz[0: und_pos + 3] + " ist ca. 4.6Mrd. Jahre alt " + satz[und_pos + 3 :]
print(neuer_satz)

#A2.2
liste = []
for i in range(5):
    liste.append([])
    for j in range(i):
        liste[i].append(j)
    print(liste)
    

flatlist = []
for i in range(len(liste)):
    for j in range(len(liste[i])):
        flatlist.append(liste[i][j])
print(flatlist)


print("liste in place verändern:")
l = len(liste)
for i in range(l):
    for j in liste[i]:
        liste.append(j)
    print(liste)       
        
for i in range(l):
        liste.pop(0)
        print(liste)

# A2.3
staedte = {"b" : "berlin", "e" : "london" , "f" : "paris", "a" : "new york"}
print("keys: ", staedte.keys())
keys_sorted = list(staedte.keys())
keys_sorted.sort()
print(keys_sorted)
for i in keys_sorted:
    print(i, ": ", staedte[i])
    

