#C:\Users\hanna\AppData\Local\Microsoft\WindowsApps\python3.exe
# -*-coding: utf-8 -*-

# in der Vorlesung bin ich inhaltlich so bis S 168 durch

# A4.1
s = "[1,2,3,4, [a,b,c,d], help, run, [[a,b],[1,2]]]\n"
print("s: ", s)
print("remove \n:", s.replace("\n", "", 1))

print(s.find("["), s.rfind("]"))
s = s[: s.find("[")] + s[s.find("[") + 1 : s.rfind("]")] + s[s.rfind("]") +1 :]
print("erste und letzte klammer weg: ", s)

w = s.split(",")
print("aufgeteilt: " , w)

print(int (s[s.find("3")]) * int(s[s.rfind("2")]))

# A4.2
print(s.find("[a,b]"))
print(s.replace("2", "1234"))

#A4.3 
#nein.


# A4.4
import datetime
import time

for s in range(4):
    print("time: ", time.ctime())
    time.sleep(2) 
   
# A4.5
d1 = datetime.datetime(year = 1983, month = 3, day = 21, hour = 22, minute = 4, second = 56)
print(datetime.datetime.now())
print(d1)

a = 0
timer = 0;
while(a < 6):
    d = datetime.datetime.now() - d1
    time.sleep(5)
    timer += 5
    if(timer == 10):
        timer = 0
        print("jetzt:",  d)
    
    a += 1

# A4.6


# A4.6
    
def f1(dateiname):
    print("in f1")
    if (dateiname == " "):
        try: 
            return(open(dateiname))
        except FileNotFoundError:
            print("file not found in fucntion 1!")
            return " ";
    else:
        f2(dateiname)
    
    
def f2(dateiname):
    print("in f2")
    if (dateiname == " "):
        try: 
            return(open(dateiname))
        except FileNotFoundError:
            print("file not found in fucntion 2!")
            return " ";
        finally:
            print("auf nach f1...")
            f1(" ")
    else:
        f3(dateiname)
    
    

def f3(dateiname):
    print("in f3")
    try: 
        return(open(dateiname))
    except FileNotFoundError:
        print("file not found in fucntion 3!")
        return " ";
    finally:
        print("auf nach f2...")
        f2(" ")
    
    
print("gleich werden fkt ausgeführt....")
f1("baum")
    

    
def f1(dateiname):
    if (dateiname == " "):
        try: 
            return(open(dateiname))
        except FileNotFoundError:
            print("file not found in fucntion 3!")
            return " ";
    
    else:
        f2(dateiname)
    
    
def f2(dateiname):
    if (dateiname == " "):
        try: 
            return(open(dateiname))
        except FileNotFoundError:
            print("file not found in fucntion 3!")
            return " ";
    
    else:
        f3(dateiname)
    

def f3(dateiname):
    try: 
        return(open(dateiname))
    except FileNotFoundError:
        print("file not found in fucntion 3!")
        return " ";
    
    
print("gleich werden fkt ausgeführt....")
f1("baum")


