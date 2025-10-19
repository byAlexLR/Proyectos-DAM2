import math

def ecuacion2():
    # Le da la bienvenida personalizada pidiendole el nombre.
    print("¡Bienvenido! Por favor, introduzca su nombre: ")
    nombre = input()

    # Le pide que introduzca el valor de a.
    a = int(input(f"{nombre}, introduzca el valor de a: "))

    if a == 0: # Validar que 'a' no sea cero
        print("El valor de 'a' no puede ser cero. No es una ecuación cuadrática.")
        return

    # Le pide que introduzca el valor de b y c.
    b = int(input("Introduzca el valor de b: "))
    c = int(input("Introduzca el valor de c: "))

    if b == 0 and c == 0: # En caso de que b y c sean 0
        print("La ecuación tiene solución: x = 0")
    elif c == 0: # En caso de que solo c sea 0
        print("Primera solución: 0")
        print("Segunda solución:", -(b / a))
    elif b == 0: # En caso de que b sea 0
        div = -c / a
        if div < 0:
            print("La ecuación no tiene solución real.")
        else:
            print("Primera solución:", math.sqrt(div))
            print("Segunda solución:", -math.sqrt(div))
    
    else:
        # Realiza el discriminante.
        discriminante = b**2 - 4*a*c
        # Si el discriminante es menor que cero no es un real.
        if discriminante < 0:
            print("La ecuación no tiene solución real.")
        else:
            # Realiza la raiz y luego, realiza la operación de x1 y x2.
            raiz = math.sqrt(discriminante)
            x1 = (-b + raiz) / (2*a)
            x2 = (-b - raiz) / (2*a)
            print("La primera solución es:", x1)
            print("La segunda solución es:", x2)

def main():
    ecuacion2()

main()
