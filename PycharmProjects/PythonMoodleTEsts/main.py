

def add_parameters(a,b,c):
    return a + b + c


def sort_list(liste):
    if (type(liste) == int) or (type(liste) == float) or (type(liste) == complex):
        if liste < 0:
            return "None"
    if liste == []:
        return "None"
    liste.sort(reverse=True)
    return liste

def sqrt_parameter(f): # f ist  nicht-negativen Übergabeparameter vom Typ Float
    if f < 0:
        return "None"
    return f** 0.5



def main():
    print(sqrt_parameter(-2))

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    main()
