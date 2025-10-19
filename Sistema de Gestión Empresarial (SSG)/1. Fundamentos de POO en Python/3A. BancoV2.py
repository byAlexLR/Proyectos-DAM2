# byAlexLR

# 3A. Crear una clase banco, iniciando la variable saldo y la cuenta en la que aparece. Además, de se ha sacado o ingresado dinero.
# Se debe mostrar por pantalla a que cuenta quiere entrar (Ahorro, jubilación o nómina), luego si quieres sacar dinero o ingresarlo y la cantidad,
# y consultar el dinero de cada cuenta. Que no se pueda sacar dinero si el caso es negativo. Luego que muestre la operación realizada y el saldo actual.

# Declaración de la clase Banco.
class Banco:
    # Método constructor de la clase Banco.
    def __init__(self, tipo_cuenta, saldo_inicial=0):
        self.tipo_cuenta = tipo_cuenta
        self.saldo = saldo_inicial
        self.transacciones = []

    # Método para realizar un ingreso en la cuenta.
    def ingreso(self, cantidad):
        if cantidad > 0:
            self.saldo += cantidad
            self.transacciones.append(f"Ingreso de {cantidad}")
            print(f"\nSe ha ingresado {cantidad}. Nuevo saldo: {self.saldo}")
        else:
            print("\nLa cantidad a ingresar debe ser positiva.")

    # Método para realizar una extracción de la cuenta.
    def extraccion(self, cantidad):
        if 0 < cantidad <= self.saldo:
            self.saldo -= cantidad
            self.transacciones.append(f"Extracción de {cantidad}")
            print(f"\nSe ha extraido {cantidad} realizada. Nuevo saldo: {self.saldo}")
        else:
            print("\nCantidad inválida o saldo insuficiente.")

    # Método para obtener el historial de transacciones.
    def obtener_transacciones(self):
        return self.transacciones

    # Método para obtener el saldo actual.
    def obtener_saldo(self):
        return self.saldo

    # Método para mostrar el saldo actual de la cuenta.
    def mostrar_saldo(self):
        print(f"\nEl saldo actual de la cuenta {self.tipo_cuenta} es: {self.saldo}")
        
# Subclases para diferentes tipos de cuentas.
# Cuenta de Ahorro con interés.
class CuentaAhorro(Banco):
    # Método constructor de la clase CuentaAhorro.
    def __init__(self, saldo_inicial=0, tasa_interes=0.01):
        super().__init__("Ahorro", saldo_inicial)
        self.tasa_interes = tasa_interes
    
    # Método para aplicar interés al saldo.
    def aplicar_interes(self):
        interes = self.saldo * self.tasa_interes
        self.saldo += interes
        print(f"Se ha aplicado un interés de {interes}. Nuevo saldo: {self.saldo}")
            
    # Método para mostrar el interés acumulado.
    def mostrar_interes(self):
        interes = self.saldo * self.tasa_interes
        print(f"El interés acumulado es: {interes}")
    
    # Sobrescribir el método de ingreso para aplicar interés después de cada ingreso.
    def ingreso(self, cantidad):
        super().ingreso(cantidad)
        self.aplicar_interes()
        self.mostrar_interes()

# Cuenta de Jubilación con restricción de edad para retiros.
class CuentaJubilacion(Banco):
    # Método constructor de la clase CuentaJubilacion.
    def __init__(self, saldo_inicial=0, edad_retiro=65):
        super().__init__("Jubilación", saldo_inicial)
        self.edad_retiro = edad_retiro
        
    # Método para verificar si se puede realizar un retiro según la edad.
    def verificar_retiro(self, edad_actual):
        if edad_actual >= self.edad_retiro:
            print("Puede retirar fondos de la cuenta de jubilación.")
        else:
            print("No puede retirar fondos de la cuenta de jubilación hasta alcanzar la edad de retiro.")

# Cuenta Corriente.
class CuentaCorriente(Banco):
    # Método constructor de la clase CuentaCorriente.
    def __init__(self, saldo_inicial=0):
        super().__init__("Corriente", saldo_inicial)
        
    # Sobrescribir el método de extracción para permitir sobregiros hasta un límite.    
    def extraccion(self, cantidad):
        if cantidad > 0 and cantidad <= self.saldo:
            self.saldo -= cantidad
            self.transacciones.append(f"Extracción de {cantidad}")
            print(f"Se ha extraido {cantidad}. Nuevo saldo: {self.saldo}")
        else:
            print("Cantidad inválida o saldo insuficiente.")

# Variables para controlar los intentos de inicio de sesión y el estado de la sesión.
intentos = 3
iniciamos = False
dnicorrecto = "12345678A"
contracorrecta = "pedritoconoceajuanito"

# Bucle para verificar las credenciales del usuario.
while intentos > 0:
    # Solicita al usuario que ingrese su DNI para iniciar sesión.
    dni = input("Introduce tu DNI: ").upper()
    contra = input("Introduce tu contraseña: ")
    # Verifica si las credenciales son correctas.
    if dni == dnicorrecto and contra == contracorrecta:
        print("\nInicio de sesión exitoso.")
        iniciamos = True
        intentos = 3  # Reinicia los intentos después de un inicio de sesión exitoso.
        # Bucle para realizar operaciones en la cuenta.
        while iniciamos:
            # Mensaje de bienvenida y selección de tipo de cuenta.
            tipo_cuenta = int(input("\nBienvenido al banco. ¿A qué cuenta desea acceder?\n1.Cuenta de Ahorro\n2.Cuenta de Jubilación\n3.Cuenta Corriente\n4.Configuración de la Cuenta\n5.Salir\nSeleccione una opción: "))
            # Selección de la cuenta y creación del objeto correspondiente.
            if tipo_cuenta == 5:
                print("Gracias por usar el servicio bancario.")
                exit()
            elif tipo_cuenta == 1:
                mi_cuenta = CuentaAhorro()
            elif tipo_cuenta == 2:
                mi_cuenta = CuentaJubilacion()
            elif tipo_cuenta == 3:
                mi_cuenta = CuentaCorriente()
            elif tipo_cuenta == 4:
                mi_cuenta = Banco("Configuración")
                print("\nConfiguración de la cuenta:")
                while intentos > 0:
                    nueva_contra = input("Ingrese la nueva contraseña: ")
                    contrasena_confirmacion = input("Confirme la nueva contraseña: ")
                    if nueva_contra == contrasena_confirmacion:
                        contracorrecta = nueva_contra
                        intentos = 3 # Reinicia los intentos.
                        print("\nContraseña actualizada con éxito.\n")
                        break # Salir para reiniciar sesión con la nueva contraseña.
                    else:
                        intentos -= 1
                        if intentos > 0:
                            print(f"\nLas contraseñas no coinciden. Te quedan {intentos} intentos.\n")
                            continue
                        else:
                            print("\nHas agotado todos los intentos.\n")
                            break
            else:
                print("\nIntroduzca una opción válida.")
                continue
            # Bucle para realizar operaciones dentro de la cuenta seleccionada.
            while True:
                # Menú de operaciones.
                opcion = input("\n¿Qué operación desea realizar?\n1.Ingresar dinero\n2.Retirar dinero\n3.Consultar saldo\n4.Consultar transacciones\n5.Volver al menú principal\nSeleccione una opción: ")
                # Realización de la operación seleccionada.
                if opcion == "1":
                    try:
                        cantidad = float(input("Ingrese la cantidad a depositar: "))
                        mi_cuenta.ingreso(cantidad)
                    except ValueError:
                        print("Por favor, introduzca una cantidad válida.")
                # Retira dinero con verificación de edad para la cuenta de jubilación.
                elif opcion == "2":
                    try:
                        cantidad = float(input("Ingrese la cantidad a retirar: "))
                        # Verificación de edad para la cuenta de jubilación.
                        if isinstance(mi_cuenta, CuentaJubilacion):
                            edad = int(input("Ingrese su edad actual: "))
                            mi_cuenta.verificar_retiro(edad)
                            if edad >= mi_cuenta.edad_retiro:
                                mi_cuenta.extraccion(cantidad)
                        else:
                            mi_cuenta.extraccion(cantidad)
                    except ValueError:
                        print("Por favor, introduzca una cantidad válida.")
                # Consulta el saldo actual.
                elif opcion == "3":
                    mi_cuenta.mostrar_saldo()
                # Muestra el historial de transacciones.
                elif opcion == "4":
                    if mi_cuenta.obtener_transacciones():
                        print("\nTransacciones realizadas:")
                        for t in mi_cuenta.obtener_transacciones():
                            print(t)
                    else:
                        print("\nNo se han realizado transacciones.")
                # Vuelve al menú principal.
                elif opcion == "5":
                    break
                else:
                    print("Opción no válida. Intente de nuevo.")
    # Si las credenciales son incorrectas, muestra un mensaje de error y reduce el número de intentos.
    else:
        intentos -= 1
        if intentos > 0:
            print(f"\nDNI o contraseña incorrecta. Te quedan {intentos} intentos.\n")
            continue
        else:
            print("\nHas agotado todos los intentos.\n")
            exit()