#C:\Users\hanna\AppData\Local\Microsoft\WindowsApps\python3.exe
# -*-coding: utf-8 -*-

# A3.1
meine_liste = ["Katze", "Hund", 17, "Super", 3.14]
print(meine_liste[2])
print("length: ", len(meine_liste))
meine_liste.append("Ni")
print("append: " ,meine_liste)
meine_liste.extend([4,5,3.14])
print("extend: ", meine_liste)
meine_liste.insert(2, "Taube")
print("insert:", meine_liste)
print("count: ", meine_liste.count(3.14))
print(meine_liste.index(3.14))
meine_liste.remove(3.14)
print("remove:", meine_liste)
meine_liste.pop()
print("pop: ", meine_liste)
meine_liste.reverse()
print("reverse:", meine_liste)
print("sum: ", sum([1,3,5]))

#A3.2

listea = ["hallo", "schönes", "wetter"]
listeb = listea

listeb[1] = "schlechtes"

print("A3.2:  ", listea[0], listea[1], listea[2])


# A3.3
# dieses programm initialisiert eine var aufv wahr, und falls alle namen im 
# array mit m anfangen bleibt das wahr sonst falsch

# A3.4
# das programm quadriert und -4 alle einträge des arrays und speichert sie in nem neuen array

#A3.5
zahlen = []
for i in range(100):
    zahlen.append(i)

print(zahlen)

for x in range(len(zahlen)):
    zahlen[x] = 2* zahlen[x]

print(zahlen)

# A3.6
namen = ["Carl", "Matha", "Monika", "Hansi", "Peter", "Mia", "Maximus", "Metchild", 
"Klaus", "Carsten", "Mats", "Maurits", "Mette",
"Nils", "Jan", "Harald", "Simone", "Mette", "Martha"]
m_namen = []

for x in namen:
    if x[0] == "M":
        m_namen.append(x)
print(namen)
print(m_namen)


# A3.7
L = [0,0]
M = L # pointer zu werten werden kopiert
M[0] = "HA"
print(L)
print(M)

H = [0,0]
I = H[:] # jeder einzelne WERT wird kopiert
I[0] = "HA"
print(H)
print(I)
    

L = [1,2,3]
print("L: ", L)

X = L
print("X: ", X)
for i in range(len(L)):
    L[i] = X[len(L)- i -1]
    print("i: ", i, "X", X, "L: ", L)

print("L: ", L)
# i = 0: L=[3,2,3]
# i= 1:L = [3,2,3]
# i = 2: L = 
