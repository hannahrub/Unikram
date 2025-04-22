# script bis

class NurAdd(object):
    @staticmethod
    def addieren(a, b):
        return a + b

class NurSub(object):
    @staticmethod
    def subtrahieren(a, b):
        return a - b

class NurDiv(object):
    @staticmethod
    def dividieren(a, b):
        return a / b

class NurMult(object):
    @staticmethod
    def multiplizieren(a, b):
        return a * b

# rechnen erbt alle seine methoden vn darüberliegenden klassen
class Rechnen(NurAdd, NurSub, NurMult, NurDiv):
    pass

class Rechnen2(NurAdd, NurSub, NurMult, NurDiv):
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def add(self):
        return super().addieren(self.a,self.b)

    @staticmethod
    def sub(self):
        return super().subtrahieren(self.a, self.b)

    @staticmethod
    def mul(self):
        return super().multiplizieren(self.a, self.b)

    @staticmethod
    def div(self):
        return super().dividieren(self.a, self.b)


def main():
    print(Rechnen.addieren(2,3))
    print(Rechnen.subtrahieren(2, 3))
    print(Rechnen.multiplizieren(2, 3))
    print(Rechnen.dividieren(2, 3))

    r = Rechnen2(2,3)
    print(Rechnen2.add(r))
    print(Rechnen2.sub(r))
    print(Rechnen2.mul(r))
    print(Rechnen2.div(r))


if __name__ == '__main__':
    main()
