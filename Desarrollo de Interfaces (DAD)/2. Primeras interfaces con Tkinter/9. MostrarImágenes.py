# byAlexLR

# 9. El programa deberá tener lo siguiente:
# • Cargar 10 imágenes aleatorias de un directorio con imágenes proporcionadas en esta tarea.
# • Mostrar una imagen con un cuadro de texto y un botón comprobar.
# • La imagen es un caracter hiragana del idioma japonés, el nombre del fichero
# correspondiente a la imagen es la traducción al español (sin la extensión).
# • Se deberá comprobar que ha introducido la traducción correctamente en el cuadro de texto,
# así con las 10 imágenes aleatorias y que no se repitan.
# • Al final del proceso deberá salirle la calificación:
# • Menos de un 5 suspenso.
# • Un 5 es suficiente.
# • Un 6 es bien.
# • Un 7 u 8 notable.
# • Un 9 o 10 sobresaliente.
# • Se valorará que la interfaz esté limpia y cuidada usando bien lo que hemos aprendido:
# • Frame, button, entry, label, etc.
# • Incluso si se usan imagenes extra de decoración, por ejemplo para el resultado, etc.

# Importa el módulo os para operaciones del sistema de archivos
import os

# Importa el módulo random para selección aleatoria de imágenes
import random

# Importa la biblioteca tkinter para la interfaz gráfica
import tkinter as tk
from tkinter import messagebox, filedialog

# Importa PIL para el manejo de imágenes
from PIL import Image, ImageTk

# Extensiones de imagen soportadas
SUPPORTED = (".png", ".jpg", ".jpeg", ".gif", ".bmp")

# Clase principal del quiz de hiragana
class HiraganaQuiz:
    # Inicializa la ventana y configura todos los elementos de la interfaz
    def __init__(self, root):
        root.title("Quiz Hiragana") # Establece el título de la ventana
        self.root = root
        # Crea el marco principal con espaciado interno
        frame = tk.Frame(root, padx=10, pady=10)
        frame.pack()
        # Crea un marco superior para los controles
        top = tk.Frame(frame)
        top.pack()
        # Crea el botón para seleccionar la carpeta de imágenes
        tk.Button(top, text="Seleccionar carpeta de imágenes...", command=self.seleccionar_carpeta).pack()
        # Crea la etiqueta que muestra información sobre la carpeta seleccionada
        self.info = tk.Label(frame, text="No hay carpeta seleccionada")
        self.info.pack(pady=5)
        # Crea la etiqueta donde se mostrará la imagen del hiragana
        self.canvas = tk.Label(frame)
        self.canvas.pack(pady=5)
        # Crea un marco para el campo de entrada y botón de comprobación
        entryf = tk.Frame(frame)
        entryf.pack()
        # Crea la etiqueta y campo de entrada para la traducción
        tk.Label(entryf, text="Traducción:").pack(side="left")
        self.entry = tk.Entry(entryf, width=30) # Campo para escribir la traducción
        self.entry.pack(side="left", padx=5)
        # Crea el botón para comprobar la respuesta
        tk.Button(entryf, text="Comprobar", command=self.comprobar).pack(side="left")
        # Crea la etiqueta que muestra el estado actual
        self.status = tk.Label(frame, text="")
        self.status.pack(pady=5)
        # Lista para almacenar las rutas de las imágenes seleccionadas
        self.images = []
        # Índice de la imagen actual del quiz
        self.index = 0
        # Contador de respuestas correctas
        self.score = 0
        # Objeto PhotoImage para mostrar la imagen actual
        self.photo = None
        # Lista de las 10 imágenes seleccionadas aleatoriamente para el quiz
        self.chosen = []

    # Función para seleccionar la carpeta con imágenes de hiragana
    def seleccionar_carpeta(self):
        # Abre un diálogo para seleccionar una carpeta
        folder = filedialog.askdirectory(title="Selecciona carpeta con imágenes de hiragana", initialdir=".")
        if not folder: # Si no se selecciona carpeta, termina la función
            return
        # Filtra los archivos para obtener solo las imágenes soportadas
        files = [f for f in os.listdir(folder) if f.lower().endswith(SUPPORTED)]
        # Verifica que haya al menos 10 imágenes disponibles
        if len(files) < 10:
            messagebox.showinfo("Info", "Se requieren al menos 10 imágenes en la carpeta")
            return
        # Construye las rutas completas de los archivos
        files_full = [os.path.join(folder, f) for f in files]
        random.shuffle(files_full) # Mezcla aleatoriamente las imágenes
        # Selecciona las primeras 10 imágenes para el quiz
        self.chosen = files_full[:10]
        self.index = 0 # Reinicia el índice a la primera imagen
        self.score = 0 # Reinicia el contador de puntuación
        # Actualiza la información mostrada y muestra la primera imagen
        self.info.config(text=f"Carpeta: {folder} - 10 imágenes seleccionadas")
        self.mostrar_actual()

    # Función para mostrar la imagen actual del quiz
    def mostrar_actual(self):
        path = self.chosen[self.index] # Obtiene la ruta de la imagen actual
        img = Image.open(path) # Abre la imagen con PIL
        img.thumbnail((400, 400)) # Redimensiona la imagen a 400x400 píxeles
        self.photo = ImageTk.PhotoImage(img) # Convierta la imagen a un formato compatible con tkinter
        self.canvas.config(image=self.photo) # Muestra la imagen en el canvas
        self.entry.delete(0, "end") # Limpia el campo de entrada
        # Actualiza el estado con el número de imagen actual
        self.status.config(text=f"Imagen {self.index+1}/10")

    # Función para comprobar la respuesta del usuario
    def comprobar(self):
        if not self.chosen: # Si no hay imágenes seleccionadas, muestra un mensaje
            messagebox.showinfo("Info", "Selecciona la carpeta primero")
            return
        # Obtiene y normaliza la respuesta del usuario
        usuario = self.entry.get().strip().lower()
        # Obtiene la respuesta correcta del nombre del archivo
        correcto = os.path.splitext(os.path.basename(self.chosen[self.index]))[0].lower()
        # Compara la respuesta del usuario con la correcta
        if usuario == correcto:
            self.score += 1 # Incrementa el contador de aciertos
            self.status.config(text="Correcto!", fg="green") # Muestra un mensaje de acierto
        else:
            # Muestra mensaje de error con la respuesta correcta
            self.status.config(text=f"Incorrecto. Correcto: {correcto}", fg="red")
        self.index += 1 # Pasa a la siguiente imagen
        # Verifica si se han completado las 10 imágenes
        if self.index >= 10:
            self.terminar() # Termina el quiz
        else:
            # Espera 800ms antes de mostrar la siguiente imagen
            self.root.after(800, self.mostrar_actual)

    # Función que se ejecuta al terminar el quiz
    def terminar(self):
        calificacion = self.calificar(self.score) # Obtiene la calificación basada en la puntuación
        msg = f"Puntuación: {self.score}/10 - {calificacion}" # Crea el mensaje de resultado
        messagebox.showinfo("Resultado", msg) # Muestra el resultado en un cuadro de diálogo
        # Resetea la aplicación para permitir volver a jugar
        self.chosen = []
        self.info.config(text="No hay carpeta seleccionada")
        self.canvas.config(image="")
        self.status.config(text="")

    # Función estática para calcular la calificación basada en la puntuación
    @staticmethod
    def calificar(puntuacion):
        if puntuacion < 5: # Menos de 5 puntos
            return "Suspenso"
        elif puntuacion == 5: # Exactamente 5 puntos
            return "Suficiente"
        elif puntuacion == 6: # Exactamente 6 puntos
            return "Bien"
        elif puntuacion in (7,8): # 7 u 8 puntos
            return "Notable"
        elif puntuacion in (9,10): # 9 o 10 puntos
            return "Sobresaliente"
        return ""

# Inicia la aplicación principal
if __name__ == "__main__":
    root = tk.Tk() # Crea la ventana principal
    HiraganaQuiz(root) # Crea una instancia de la clase HiraganaQuiz
    root.mainloop() # Inicia el bucle principal de la ventana