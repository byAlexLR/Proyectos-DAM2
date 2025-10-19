# Aqui creamos la funcion para comprobar la contraseña.
def login():
    print("Usuario: Jose")
    #Aqui tenemos la contraseña real.
    contrasenaReal = "jose123"
    #Aqui le pedimos al usuario que ingrese cualquier contraseña.
    contrasena = str(input("Ingrese su contrasena: "))
    #Aqui comprueba que es la misma contraseña pasando las dos en minuscula.
    if contrasena.lower() == contrasenaReal.lower():
        print("Bienvenido Jose!")
    else:
        print("La contraseña es erronea, inente denuevo")
        login()
    
def main():
    login()
    
main()