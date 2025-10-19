# byAlexLR

# 4. Realizar un programa en el cual se declaren dos valores enteros por teclado utilizando el método __ini__. Calcular después la suma, 
# resta, multiplicación y división. Utilizar un método para cada una e imprimir los resultados obtenidos. Llamar a la clase Calculadora.

# Declaración de la clase Calculadora.
class Calculadora:
    # Método constructor de la clase Calculadora.
    def __init__(self, num1, num2):
        self.num1 = num1
        self.num2 = num2
        
    # Métodos para realizar las operaciones básicas.
    def suma(self):
        result = self.num1 + self.num2
        print(f"El resultado de {self.num1} más {self.num2} es igual a: {result}")
    
    def resta(self):
        result = self.num1 - self.num2
        print(f"El resultado de {self.num1} menos {self.num2} es igual a: {result}")
        
    def multiplicacion(self):
        result = self.num1 * self.num2
        print(f"El resultado de {self.num1} por {self.num2} es igual a: {result}")
        
    def division(self):
        if self.num2 == 0:
            print("No se puede dividir entre cero.")
        else:
            result = self.num1 / self.num2
            print(f"El resultado de {self.num1} entre {self.num2} es igual a: {result}")

# Pide al usuario que introduzca dos números y la operación a realizar.
num1 = float(input("Introduce el 1º Número: "))
num2 = float(input("Introduce el 2º Número: "))
calc = Calculadora(num1, num2)
opcion = int(input("¿Qué deseas hacer con los números?\n1.Suma\n2.Resta\n3.Multiplicación\n4.División\n5.Salir\nIntroduce lo que quieres hacer: "))
# Realiza la operación seleccionada.
if opcion == 1:
    calc.suma()
elif opcion == 2:
    calc.resta()
elif opcion == 3:
    calc.multiplicacion()
elif opcion == 4:
    calc.division()
elif opcion == 5:
    # Sale del programa.
    print("Has salido del programa.")
    exit()
else:
    # En caso de que la opción no sea válida.
    print("El valor introducido no es válido.")