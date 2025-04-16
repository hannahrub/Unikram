#C:\Users\hanna\AppData\Local\Microsoft\WindowsApps\python3.exe
# -*-coding: utf-8 -*-

# A1.1
a,b = 3,4
print("address(a) =", hex(id(a)), " adress(b) =" , hex(id(b)))

a,b = b,a
print("address(a) =", hex(id(a)), " adress(b) =" , hex(id(b)))


# A1.2
intListe = [3,1,2,6,4,5,8,7,9,0]
strListe = ["a", "b", "caro", "edli", "dl", "frme", "gbjfei", "t", "btg", "jlj"]
mixedListe = [3, 1.2, 6, 32, "haus", "2", 5.3, 5, 7, "ba"]

intListe.sort()
print("intliste sortiert:", intListe)
intListe.sort(reverse = True)
print("intliste sortiert:", intListe)

strListe.sort()
print("strliste sortiert:", strListe)
strListe.sort(reverse = True)
print("strliste sortiert:", strListe)

strListe.insert(5,"neu")
intListe.insert(5, 100)
mixedListe.insert(5, 31254)

print(intListe)
print(strListe)
print(mixedListe)

print("intiste length:",len(intListe), "strListe length: ",len(strListe), "mixedliste length: ", len(mixedListe))

strListe.pop(2)
intListe.pop(2)
mixedListe.pop(2)

print(intListe)
print(strListe)
print(mixedListe)
