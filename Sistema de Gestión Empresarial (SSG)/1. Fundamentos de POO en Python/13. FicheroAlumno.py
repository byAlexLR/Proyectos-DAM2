# byAlexLR

# 13. Crear en Python un programa que registre en un archivo .txt las veces que un alumno vaya al
# baño. Deberá contener la siguiente información: Nombre del alumno, fecha, hora (puede introducirla
# por pantalla o cogerla del sistema), asignatura en ese momento y profesor/a. El programa debe estar
# pendiente de que le introduzca un nuevo valor y que salga del programa con una tecla en concreto.

# Importa la librería datetime.
from datetime import datetime

# Clase RegistroBano.
class RegistroBano:
    # Método constructor, donde se le pasa el nombre del archivo.
    def __init__(self, archivo="registro_bano.txt"):
        self.archivo = archivo

    # Método para añadir un nuevo registro.
    def registrar(self):
        print("\n--- NUEVO REGISTRO ---")
        nombre = input("Nombre del alumno: ").strip()
        asignatura = input("Asignatura: ").strip()
        profesor = input("Profesor: ").strip()
        
        if not nombre or not asignatura or not profesor:
            print("Error: Todos los campos son obligatorios.")
            return
            
        # Obtiene la fecha y hora actuales.
        ahora = datetime.now()
        fecha = ahora.strftime("%d/%m/%Y")
        hora = ahora.strftime("%H:%M")
        
        # Formato del registro.
        linea = f"{nombre}|{fecha}|{hora}|{asignatura}|{profesor}\n"
        
        # Guarda el registro en el archivo.
        try:
            with open(self.archivo, "a", encoding="utf-8") as f:
                f.write(linea)
            print("Registro guardado correctamente.")
        except IOError as e:
            print(f"Error al guardar el archivo: {e}")

    # Método para mostrar todos los registros.
    def mostrar(self):
        print("\n--- HISTORIAL DE REGISTROS ---")
        try:
            with open(self.archivo, "r", encoding="utf-8") as f:
                registros = f.readlines()
            
            if not registros:
                print("No hay registros.")
                return
            
            # Imprime la cabecera.
            print(f"{'ALUMNO':<20} {'FECHA':<12} {'HORA':<8} {'ASIGNATURA':<20} {'PROFESOR':<20}")
            print("-" * 80)
            
            # Imprime cada registro.
            for linea in registros:
                partes = linea.strip().split("|")
                if len(partes) == 5:
                    nombre, fecha, hora, asignatura, profesor = partes
                    print(f"{nombre:<20} {fecha:<12} {hora:<8} {asignatura:<20} {profesor:<20}")
        
        except FileNotFoundError:
            print("No se ha creado el archivo de registros todavía.")
        except IOError as e:
            print(f"Error al leer el archivo: {e}")

    # Método para iniciar el menú principal.
    def iniciar(self):
        while True:
            opcion = int(input("\n--- REGISTRO DE IDAS AL BAÑO ---\n1. Registrar nueva ida\n2. Ver todos los registros\n3. Salir\nSelecciona una opción: "))
            
            if opcion == 1:
                self.registrar()
            elif opcion == 2:
                self.mostrar()
            elif opcion == 3:
                print("Saliendo del programa.")
                break
            else:
                print("Error: Opción no válida.")
                
# Inicializa la función de iniciar.
if __name__ == "__main__":
    sistema = RegistroBano()
    sistema.iniciar()