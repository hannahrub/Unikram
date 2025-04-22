# slides bis s 330
import math


class CalcPi(object):

    @staticmethod
    def calc_a(n):
        pi = 0
        for k in range(0,n+1):
            sum_k = (-1)**k / (2*k + 1)
            pi += sum_k

        pi *= 4 #die formel berechnet pi/4
        return pi

    @staticmethod
    def calc_b(n):
        pi = 3
        vorzeichen = 1
        for k in range(3, n+1, 2):
            vorzeichen *= (-1)
            sum_k = vorzeichen * (4/(k**3 - k))
            print(f"sum_k = {-vorzeichen} * (4/ {k}^3 - {k})")
            pi += sum_k

        return pi

    @staticmethod
    def calc_c(n):
        pi = 0
        for k in range(1, n + 1, 2):
            sum_k = 1 / k**2
            pi += sum_k

        return (pi*8)**0.5

    @staticmethod
    def calc_d(n):
        pi = 0
        for k in range(n + 1):
            sum_k = (math.factorial(4 * k) * (1103 + 26390*k)) / (math.factorial(k)**4 * 396**(4*n))
            pi += sum_k

        pi *= (math.sqrt(8) / 9801)
        return pi**2






def main():
    #print(CalcPi.calc_a(10))
    #print(CalcPi.calc_b(1000))
    print(CalcPi.calc_c(100))
    print(CalcPi.calc_d(15))


if __name__ == '__main__':
    main()






