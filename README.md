\# 🍩 Juego de Homero Simpson (Java)



\## Descripción

Aplicación desarrollada en **Java con LibGDX** que simula un juego tipo **“Catch Game”**, donde el jugador controla a **Homero Simpson**, atrapando **cervezas Duff** y **donas**, mientras evita los **vasos de agua** que le quitan vidas.  

El objetivo es conseguir la mayor cantidad de puntos posibles antes de quedarse sin vidas.  



El proyecto utiliza programación orientada a objetos (herencia, polimorfismo e interfaces) e incluye **pantallas de inicio, menú, juego principal y Game Over**.



---



\## Requisitos previos

\- Java JDK 17 o superior (recomendado: JDK 21)  

\- NetBeans 21 o superior 

\- Gradle o LibGDX configurado (ya incluido en el proyecto descargado)  

\- Biblioteca LibGDX instalada o referenciada correctamente  



---



\## Instrucciones para descargar y ejecutar el proyecto en NetBeans



\### 1️⃣ Descargar el proyecto

\- Descarga el archivo ZIP del repositorio o desde tu entrega.  

\- Descomprime el archivo en una carpeta local (por ejemplo:  

&nbsp; `C:\\JavaProjects\\JuegoHomeroSimpson`).



---



\### 2️⃣ Abrir el proyecto en NetBeans

1\. Abre **NetBeans IDE**.  

2\. Ve al menú **Archivo > Abrir proyecto..** 

3\. Navega hasta la carpeta descomprimida del juego.  

4\. Selecciona la carpeta raíz (donde se encuentra el archivo `build.gradle`).  

5\. Haz clic en **Abrir proyecto**.  



> ⚠️ Asegúrate de abrir el proyecto Gradle completo, no solo la carpeta `/core`.



---





---



\### 4️⃣ Ejecutar el juego

1\. En el panel de proyectos, abre la carpeta \*\*`desktop/src/main/java/com/mygdx/game`\*\*.  

2\. Haz clic derecho sobre la clase \*\*`DesktopLauncher.java`\*\*.  

3\. Selecciona \*\*Run File\*\* o \*\*Ejecutar archivo\*\*.  



> Si todo está correcto, se abrirá una ventana del juego con la pantalla de presentación de \*Los Simpson\*.



---



\## 🎮 Instrucciones de juego

\- **Mover a Homero:** 

&nbsp; - Flecha ← para moverse a la izquierda.  

&nbsp; - Flecha → para moverse a la derecha.

\- **Objetivo:** 

&nbsp; - Atrapa cervezas Duff y donas para sumar puntos.  

&nbsp; - Evita los vasos de agua, que restan vidas.

\- **Fin del juego:**  

&nbsp; - Al perder 3 vidas, aparece la pantalla Game Over con tu puntaje.

\- **Reiniciar:** 

&nbsp; - Desde la pantalla Game Over, presiona Enter para volver al menú.



---



\## 🧱 Arquitectura y diseño

El juego aplica principios de Programación Orientada a Objetos (POO):



| Concepto                 | Implementación                                                                                  |

|--------------------------|-------------------------------------------------------------------------------------------------|

| \*\*Abstracción\*\*      | Clase abstracta `ObjetoAtrapar` define la base de los objetos del juego.                        |

| \*\*Herencia\*\*         | Clases `Cerveza`, `Agua` y `Dona` extienden `ObjetoAtrapar`.                                    |

| \*\*Polimorfismo\*\*     | La clase `Ejemplo` utiliza objetos de tipo `Fabricar` sin importar la clase concreta.           |

| \*\*Encapsulamiento\*\*  | Atributos privados y métodos controlados en `Jugador` y `ObjetoAtrapar`.                        |

| \*\*Modularidad\*\*      | Separación por pantallas (`Menu`, `Imagen`, `GameOverScreen`) y lógicas (`Fabricar`, `Jugador`).|



---



\## 💾 Datos técnicos del código



| Elemento                | Descripción                                    | Archivo                                                           |

|-------------------------|------------------------------------------------|-------------------------------------------------------------------|

| Clase abstracta base    | Define la estructura común de objetos que caen | `ObjetoAtrapar.java`                                              |

| Clases hijas concretas  | Cerveza, Agua, Dona                            | `Cerveza.java`, `Agua.java`, `Dona.java`                          |

| Interfaz de fabricación | Define métodos genéricos de creación           | `Fabricar.java`                                                   |

| Jugador controlado      | Movimiento y puntaje de Homero                 | `Jugador.java`                                                    |

| Pantallas del juego     | Inicio, Menú, Juego, Game Over                 | `Imagen.java`, `Menu.java`, `Ejemplo.java`, `GameOverScreen.java` |



---



\## 📊 Dificultad progresiva

El juego incrementa su dificultad conforme el jugador avanza en puntaje:



| Puntos | Velocidad de caída | Descripción    |

|--------|--------------------|----------------|

| 0–19   | 1.0x               | Nivel inicial  |

| 20–39  | 1.5x               | Nivel medio    |

| 40–59  | 2.0x               | Nivel avanzado |

| 60–79  | 2.5x               | Nivel experto  |

| 80+    | 3.0x               | Nivel máximo   |



---



\## 🎵 Recursos multimedia



| Tipo   | Archivo                                   | Descripción               |

|--------|-------------------------------------------|---------------------------|

| Imagen | `the-simpsons.jpg`                        | Pantalla de inicio        |

| Imagen | `casa.png`                                | Fondo principal del juego |

| Sprite | `homero2.png`                             | Personaje controlable     |

| Sprite | `cerveza.png` / `dona2.png` / `agua2.png` | Objetos del juego         |

| Sonido | `yuju.mp3`                                | Efecto al atrapar cerveza |

| Sonido | `rosca.mp3`                               | Efecto al atrapar dona    |

| Sonido | `oh.mp3`                                  | Efecto al atrapar agua    |

| Música | `intro.mp3`                               | Música de introducción    |



---



\## 📈 Flujo del juego



```mermaid

graph TD

&nbsp; A\[Inicio] --> B\[Imagen.java (Intro)]

&nbsp; B --> C\[Menu.java]

&nbsp; C --> D\[Ejemplo.java (Juego Principal)]

&nbsp; D -->|Vidas = 0| E\[GameOverScreen.java]

&nbsp; E -->|Enter| C


\##Notas importantes

No modifiques los nombres de las clases o paquetes (com.mygdx.game), ya que están referenciados en el código.

Si aparecen errores de compilación, verifica que NetBeans use el JDK 17+ y que las dependencias LibGDX estén correctamente importadas.

Los archivos multimedia deben permanecer en la carpeta /assets.

\## Creditos 
Proyecto desarrollado por estudiantes de la Pontificia Universidad Católica de Valparaíso para la asignatura de Programación Avanzada.
Integrantes:
-Martina Ponce.
-Maura Valdebenito.
-Francisca Zamora.

