# 10. Desarrollar una aplicación interactiva de juego de memoria que implemente el clásico "Memory" o "Concentración".

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


# Clase principal del quiz del juego de memoria
class Juego:
    # Inicializa la ventana y configura todos los elementos de la interfaz
    def __init__(self, root):
        root.title("Juego de Memoria") # Establece el título de la ventana
        self.root = root
        
        # Frame principal con padding y color de fondo
        self.frame = tk.Frame(root, padx=20, pady=20, bg="#f7f7fa")
        self.frame.pack()
        
        # Crea un marco superior para los controles
        self.top = tk.Frame(self.frame, bg="#f7f7fa")
        self.top.pack()
        
        # Selector de dificultad (5 o 10 pares)
        self.modo = tk.StringVar(value="fila")
        tk.Label(self.top, text="Tablero:", bg="#f7f7fa", font=("Arial", 12)).pack(side="left")
        tk.Radiobutton(self.top, text="1 fila (5 pares)", variable=self.modo, value="fila", 
                      bg="#f7f7fa", font=("Arial", 11)).pack(side="left")
        tk.Radiobutton(self.top, text="2 filas (10 pares)", variable=self.modo, value="doble", 
                      bg="#f7f7fa", font=("Arial", 11)).pack(side="left")
        
        # Botones de acción
        tk.Button(self.top, text="Cargar imágenes (carpeta)...", command=self.cargar_imagenes, 
                 font=("Arial", 11)).pack(side="left", padx=5)
        tk.Button(self.top, text="Reiniciar", command=self.reiniciar, 
                 font=("Arial", 11)).pack(side="left", padx=5)
        tk.Button(self.top, text="Salir", command=self.root.quit, 
                 font=("Arial", 11)).pack(side="left", padx=5)
        
        # Panel de información (intentos y tiempo)
        self.info = tk.Label(self.frame, text="Intentos: 0    Tiempo: 0s", 
                            bg="#f7f7fa", font=("Arial", 13, "bold"))
        self.info.pack(pady=10)
        
        # Frame para el tablero de juego
        self.board_frame = tk.Frame(self.frame, bg="#f7f7fa")
        self.board_frame.pack()
        
        # Variables de estado del juego
        self.timer = 0              # Contador de segundos
        self.attempts = 0           # Número de intentos realizados
        self.running = False        # Indica si el juego está en progreso
        self.after_id = None        # ID del temporizador para poder cancelarlo
        self.cards = []             # Lista con las rutas de las imágenes (duplicadas y mezcladas)
        self.buttons = []           # Lista de botones (referencias a las cartas en pantalla)
        self.flipped = []           # Índices de las cartas actualmente volteadas (máximo 2)
        self.matched = set()        # Set con los índices de las cartas ya emparejadas
        self.thumb_cache = {}       # Cache para las miniaturas de imágenes (optimización)
        self.rows = 0               # Número de filas del tablero
        self.cols = 0               # Número de columnas del tablero
        self.pares = 0              # Número de pares a encontrar

    # Función para cargar imágenes y configurar el tablero
    def cargar_imagenes(self):
        # Diálogo para seleccionar la carpeta
        folder = filedialog.askdirectory(title="Selecciona carpeta con imágenes para Memory", 
                                        initialdir=".")
        if not folder:
            return
        
        # Filtra solo archivos con extensiones válidas
        files = [os.path.join(folder, f) for f in os.listdir(folder) 
                if f.lower().endswith(SUPPORTED)]
        
        # Configura dimensiones según el modo: 1 fila (5 pares) o 2 filas (10 pares)
        modo = self.modo.get()
        if modo == "fila":
            self.rows, self.cols, self.pares = 1, 10, 5
        else:
            self.rows, self.cols, self.pares = 2, 10, 10
        
        # Validación: verifica que hay suficientes imágenes
        if len(files) < self.pares:
            messagebox.showinfo("Info", f"Se requieren al menos {self.pares} imágenes en la carpeta")
            return
        
        # Selección aleatoria de imágenes
        random.shuffle(files)
        chosen = files[:self.pares]
        
        # Duplicar imágenes para crear pares y mezclar todo
        pool = chosen * 2  # Cada imagen aparece 2 veces
        random.shuffle(pool)
        self.cards = pool
        
        # Construir el tablero visual
        self.configurar_tablero()
        
        # Resetear estado del juego
        self.attempts = 0
        self.timer = 0
        self.matched = set()
        self.flipped = []
        self.running = True
        
        # Iniciar cronómetro y actualizar interfaz
        self.actualizar_info()
        self.tictac()

    # Configura la cuadrícula del tablero con botones (cartas)
    def configurar_tablero(self):
        # Limpiar tablero anterior
        for w in self.board_frame.winfo_children():
            w.destroy()
        
        # Resetear listas y cachés
        self.buttons.clear()
        self.thumb_cache = {}
        
        # Parámetros visuales de las cartas
        CARD_WIDTH = 100
        CARD_HEIGHT = 100
        CARD_PAD = 8
        CARD_BG = "#e0e7ef"       # Color de fondo de carta boca abajo
        CARD_BORDER = "#b0b8c1"   # Color del borde
        CARD_ACTIVE = "#d1e7dd"   # Color al hacer hover
        
        idx = 0
        # Crear cada carta del tablero
        for r in range(self.rows):
            for c in range(self.cols):
                path = self.cards[idx]
                
                # Frame contenedor con tamaño fijo
                frame = tk.Frame(self.board_frame, width=CARD_WIDTH, height=CARD_HEIGHT, bg="#f7f7fa")
                frame.grid(row=r, column=c, padx=CARD_PAD, pady=CARD_PAD)
                frame.grid_propagate(False)  # Bloquea cambios de tamaño
                
                # Botón de la carta
                btn = tk.Button(
                    frame,
                    command=lambda i=idx: self.voltear(i),
                    bg=CARD_BG, fg="#444", relief="raised",
                    font=("Arial", 18, "bold"),
                    bd=2,
                    highlightthickness=2,
                    highlightbackground=CARD_BORDER,
                    activebackground=CARD_ACTIVE,
                    cursor="hand2"
                )
                btn.place(x=0, y=0, width=CARD_WIDTH, height=CARD_HEIGHT)
                btn.config(text="?", image="", compound="center")  # Símbolo inicial
                self.buttons.append(btn)
                idx += 1
        
        # Las columnas y filas no se expanden al redimensionar
        for c in range(self.cols):
            self.board_frame.grid_columnconfigure(c, weight=0)
        for r in range(self.rows):
            self.board_frame.grid_rowconfigure(r, weight=0)

    # Función para voltear una carta al hacer clic
    def voltear(self, indice):
        # Validaciones múltiples
        if not self.running:                    # Juego no iniciado
            return
        if indice in self.matched:              # Carta ya emparejada
            return
        if indice in self.flipped:              # Carta ya volteada en este turno
            return
        if len(self.flipped) >= 2:              # Ya hay 2 cartas volteadas
            return
        
        path = self.cards[indice]
        
        # Cargar y cachear la miniatura de la imagen
        if path not in self.thumb_cache:
            img = Image.open(path)
            img = img.convert("RGBA")
            img.thumbnail((70, 70))  # Redimensionar a 70x70px
            self.thumb_cache[path] = ImageTk.PhotoImage(img)
        
        # Mostrar la imagen en el botón
        self.buttons[indice].config(
            image=self.thumb_cache[path], 
            text="",                        # Quitar el "?"
            compound="center", 
            bg="#fff",                      # Fondo blanco al voltear
            relief="sunken",                # Efecto hundido
            highlightbackground="#4caf50" # Borde verde
        )
        
        # Registrar carta volteada
        self.flipped.append(indice)
        
        # Si hay 2 cartas volteadas, evaluar después de 700ms
        if len(self.flipped) == 2:
            self.root.after(700, self.evaluar)

    # Función para evaluar las cartas volteadas
    def evaluar(self):
        a, b = self.flipped
        pa = self.cards[a]  # Ruta de la imagen A
        pb = self.cards[b]  # Ruta de la imagen B
        
        self.attempts += 1  # Contar este intento
        
        if pa == pb:  # Coinciden
            # Marcar como emparejadas (fondo verde y deshabilitar)
            self.buttons[a].config(bg="#b9f6ca", highlightbackground="#388e3c", state="disabled")
            self.buttons[b].config(bg="#b9f6ca", highlightbackground="#388e3c", state="disabled")
            self.matched.add(a)
            self.matched.add(b)
        else:  # No coinciden
            # Volver a voltear ambas cartas
            for i in (a, b):
                self.buttons[i].config(
                    image="", 
                    text="?",                   # Volver a mostrar "?"
                    bg="#e0e7ef",             # Color original
                    relief="raised",            # Efecto elevado
                    highlightbackground="#b0b8c1"
                )
        
        # Resetear cartas volteadas
        self.flipped = []
        self.actualizar_info()
        
        # Verificar si se ha ganado
        if len(self.matched) == len(self.cards):
            self.running = False
            if self.after_id:
                self.root.after_cancel(self.after_id)  # Detener cronómetro
            messagebox.showinfo("¡Felicidades!", 
                              f"¡Has ganado en {self.attempts} intentos y {self.timer} segundos!")

    # Función para actualizar el panel de información
    def actualizar_info(self):
        self.info.config(text=f"Intentos: {self.attempts}    Tiempo: {self.timer}s")

    # Función del cronómetro
    def tictac(self):
        # Incrementa el temporizador cada segundo
        if self.running:
            self.timer += 1
            self.actualizar_info()
            self.after_id = self.root.after(1000, self.tictac)  # Llamada recursiva cada 1000ms

    # Función para reiniciar el juego
    def reiniciar(self):
        # Cancelar cronómetro si está activo
        if self.after_id:
            self.root.after_cancel(self.after_id)
        
        # Resetear todas las variables de estado
        self.running = False
        self.timer = 0
        self.attempts = 0
        self.matched = set()
        self.flipped = []
        self.cards = []
        self.buttons = []
        self.thumb_cache = {}
        
        # Limpiar tablero visual
        for w in self.board_frame.winfo_children():
            w.destroy()
        
        self.actualizar_info()

# Inicia la aplicación principal
if __name__ == "__main__":
    root = tk.Tk() # Crea la ventana principal
    app = Juego(root) # Crea una instancia de la clase Juego
    root.mainloop() # Inicia el bucle principal de la ventana