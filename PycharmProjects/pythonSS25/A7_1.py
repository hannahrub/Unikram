# Vorlesung bis
import threading


# A7.1
# a)
def pi_a(n):
    pi_quarter = 0

    for k in range(0, n+1):
        s_k = ((-1)**k) / (2*k + 1)
        pi_quarter += s_k
        print(s_k, pi_quarter)
    return pi_quarter * 4


#b)
"""ich bin hier glaub an der aufgabebstellung voebeigeschrammt: ich glaub die idee wär gewesen dass 4 prozesse sich aus einem array bedienen und da die verbleibenden ks berechnen wie im bsp aus der vorlesung und meiner lösung für 7.2 a)"""
def pi_b(n):
    threads = []

    for k in range(0, n+1):
        t = CalcSummand_c(k)

        threads += [t]
        print(f"threads: {threads} \n")

        t.start()


class CalcSummand(threading.Thread):
    pi_quarter = 0
    pi = 0

    lock = threading.Lock()

    # überschreibe init methode des threads
    def __init__(self,k):
        threading.Thread.__init__(self)
        self.k = k

    #überschreibe run methode des threads
    def run(self):
        # hier findet die berechnung des summanden statt
        sum_k = ((-1)**self.k) / (2*self.k + 1)

        CalcSummand_c.lock.acquire()

        CalcSummand_c.pi_quarter += sum_k
        CalcSummand_c.pi += sum_k * 4
        print(f"thread {self.k} updated pi_quarter: {CalcSummand_c.pi_quarter} \n")
        print(f"thread {self.k} updated pi: {CalcSummand_c.pi} \n")


        CalcSummand_c.lock.release()



# c)
def pi_c():
    threads = []

    for k in range(0, 4):
        t = CalcSummand_c(k)
        threads += [t]
        print(f"threads: {threads} \n")
        t.start()


class CalcSummand_c(threading.Thread):
    pi_quarter = 0
    pi = 0
    lock = threading.Lock()

    # überschreibe init methode des threads
    def __init__(self,k):
        threading.Thread.__init__(self)
        self.k = k

    #überschreibe run methode des threads
    def run(self):
        # hier findet die berechnung des summanden statt
        sum = 0
        for i in range(100*self.k, 100*(self.k +1)):
           sum += ((-1)**i) / (2*i + 1)


        CalcSummand_c.lock.acquire()

        CalcSummand_c.pi_quarter += sum
        CalcSummand_c.pi += sum * 4
        print(f"thread {self.k} updated pi_quarter: {CalcSummand_c.pi_quarter} \n")
        print(f"thread {self.k} updated pi: {CalcSummand_c.pi} \n")


        CalcSummand_c.lock.release()



def main():
    # print("result a): ", pi_a(20))
    #print(pi_b(10))
    print(pi_c())


if __name__ == '__main__':
    main()
