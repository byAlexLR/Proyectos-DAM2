# byAlexLR

# 11. Crea un sistema de gestión para un departamento de desarrollo tecnológico donde se pueda administrar empleados y proyectos. Implementa una
# clase DepartamentoDesarrollo que gestione el presupuesto disponible, una clase Empleado con ID único, nombre, nivel (Junior/Senior/Lead) y salario correspondiente,
# y una clase Proyecto con ID único, nombre y presupuesto asignado. El sistema debe permitir contratar empleados (validando ID único y nivel correcto), crear proyectos
# (verificando que el presupuesto no supere el disponible) y asignar empleados a proyectos (comprobando que existan y no estén duplicados). Desarrolla un menú interactivo
# que muestre las opciones de gestión y presupuesto, impidiendo operaciones inválidas como exceder el presupuesto o asignar empleados inexistentes, y que muestre confirmaciones
# de cada operación con el estado actualizado.

# Clase DepartamentoDesarrollo que gestiona empleados y proyectos.
class DepartamentoDesarrollo:
    # Método constructor que inicializa el presupuesto, empleados y proyectos.
    def __init__(self, presupuesto):
        self.presupuesto = presupuesto
        self.empleados = {}
        self.proyectos = {}

    # Método para contratar un empleado.
    def contratar_empleado(self, empleado):
        # Valida el ID único y nivel de empleado, si es correcto.
        if empleado.id in self.empleados:
            print(f"Error: El empleado con ID {empleado.id} ya existe.")
            return
        if empleado.nivel not in ["Junior", "Senior", "Lead"]:
            print("Error: Nivel de empleado inválido. Debe ser Junior, Senior o Lead.")
            return
        self.empleados[empleado.id] = empleado
        print(f"Empleado {empleado.nombre} contratado exitosamente.")

    # Método para crear un proyecto.
    def crear_proyecto(self, proyecto):
        # Valida el ID único y presupuesto disponible, si es correcto.
        if proyecto.id in self.proyectos:
            print(f"Error: El proyecto con ID {proyecto.id} ya existe.")
            return
        if proyecto.presupuesto > self.presupuesto:
            print("Error: El presupuesto del proyecto excede el disponible del departamento.")
            return
        self.proyectos[proyecto.id] = proyecto
        self.presupuesto -= proyecto.presupuesto
        print(f"Proyecto {proyecto.nombre} creado exitosamente.")

    # Método para comprobar si el empleado o el proyecto ya existe, o si ya está un empleado asignado a un proyecto. Sino, lo asigna.
    def asignar_empleado_a_proyecto(self, empleado_id, proyecto_id):
        if empleado_id not in self.empleados:
            print(f"Error: El empleado con ID {empleado_id} no existe.")
            return
        if proyecto_id not in self.proyectos:
            print(f"Error: El proyecto con ID {proyecto_id} no existe.")
            return
        proyecto = self.proyectos[proyecto_id]
        if empleado_id in proyecto.empleados_asignados:
            print(f"Error: El empleado con ID {empleado_id} ya está asignado al proyecto {proyecto.nombre}.")
            return
        proyecto.empleados_asignados.append(empleado_id)
        print(f"El empleado con ID {empleado_id} ha sido asignado al proyecto {proyecto.nombre} exitosamente.")

    # Muestra el presupuesto del departamento, la lista de empleados y proyectos.
    def mostrar_estado(self):
        print(f"Presupuesto disponible: {self.presupuesto}")
        print("- Empleados:")
        for emp in self.empleados.values():
            print(f"  ID: {emp.id}, Nombre: {emp.nombre}, Nivel: {emp.nivel}, Salario: {emp.salario}")
        print("- Proyectos:")
        for proj in self.proyectos.values():
            print(f"  ID: {proj.id}, Nombre: {proj.nombre}, Presupuesto: {proj.presupuesto}, Empleados Asignados: {proj.empleados_asignados}")

# Clase Empleado.
class Empleado:
    # Método constructor que inicializa el id, nombre, nivel y salario del empleado.
    def __init__(self, id, nombre, nivel, salario):
        self.id = id
        self.nombre = nombre
        self.nivel = nivel
        self.salario = salario

# Clase Proyecto.
class Proyecto:
    # Método constructor que inicializa el id, nombre, presupuesto y empleados asignados.
    def __init__(self, id, nombre, presupuesto):
        self.id = id
        self.nombre = nombre
        self.presupuesto = presupuesto
        self.empleados_asignados = []

# Función que muestra el menú e inicializa el presupuesto con un valor.
def menu():
    departamento = DepartamentoDesarrollo(presupuesto = 100000)

    # Bucle While: Muestra un mensaje con las opciones y recibe por teclado la opción.
    while True:
        opcion = int(input("\n---> Gestión de Departamento de Desarrollo <---\n1. Contratar Empleado\n2. Crear Proyecto\n3. Asignar Empleado a Proyecto\n4. Mostrar Estado del Departamento\n5. Salir\nSelecciona una opción: "))

        # Registrar un empleado.
        if opcion == 1:
            id = input("Indica el ID del empleado: ")
            nombre = input("Indica el nombre del empleado: ")
            nivel = input("Indica el nivel del empleado (Junior/Senior/Lead): ")
            salario = float(input("Indica el salario del empleado: "))
            empleado = Empleado(id, nombre, nivel, salario)
            departamento.contratar_empleado(empleado)
        # Registrar un proyecto.
        elif opcion == 2:
            id = input("Indica el ID del proyecto: ")
            nombre = input("Indica el nombre del proyecto: ")
            presupuesto = float(input("Indica el presupuesto del proyecto: "))
            proyecto = Proyecto(id, nombre, presupuesto)
            departamento.crear_proyecto(proyecto)
        # Asigna un empleado a un proyecto.
        elif opcion == 3:
            empleado_id = input("Indica el ID del empleado a asignar: ")
            proyecto_id = input("Indica el ID del proyecto al que asignar el empleado: ")
            departamento.asignar_empleado_a_proyecto(empleado_id, proyecto_id)
        # Muestra los datos del departamento.
        elif opcion == 4:
            departamento.mostrar_estado()
        # Sale del sistema.
        elif opcion == 5:
            print("Saliendo del sistema de gestión...")
            break
        # En el caso de que no sea una opción válida.
        else:
            print("Opción inválida. Por favor, introduce una opción válida.")

# Inicializa la función menu.
if __name__ == "__main__":
    menu()
