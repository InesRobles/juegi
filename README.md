# Three Keys Quest 🗝️
 
Un juego de acción y aventura inspirado en el Zelda original de NES, desarrollado en Java con arquitectura MVC.
 
---
 
## 📋 Descripción
 
Three Keys Quest es un juego 2D de vista cenital donde el jugador explora distintas salas, combate enemigos, recoge objetos y derrota a un jefe final para completar la aventura.
 
---
 
## 🏗️ Arquitectura
 
El proyecto sigue el patrón **MVC (Modelo - Vista - Controlador)**:

---
 
## 🎮 Controles
 
| Tecla | Acción |
|---|---|
| ↑ ↓ ← → / A W S D | Mover al jugador |
| Espacio / Z | Atacar con la espada |
| X | Acción |
| ESC | Pausar / Reanudar |

---
 
## ⚙️ Mecánicas principales
 
### Jugador
- Movimiento en 4 direcciones con animación de sprites
- Sistema de vida con corazones
- Ataque con espada con cooldown
- Invencibilidad temporal al recibir daño
- Inventario de llaves y rupias
 
### Enemigos
- **FollowerEnemy**: Se mueve aleatoriamente y persigue al jugador cuando está cerca
- **ShooterEnemy**: Dispara proyectiles en línea recta cuando el jugador está alineado
- **TackleEnemy**: Embiste al jugador si se encuentre frente a él
- **Boss**: Ataca disparando proyectiles y solo puede ser dañado al abrir el ojo
 
### Proyectiles
- **StraightProjectile**: Se mueve en línea recta en una dirección fija. Puede ser desviado por el escudo del jugador
- **TargetProjectile**: Se mueve hacia donde estaba el jugador en el momento del disparo
 
### Salas
- El mapa se construye con matrices 2D donde cada número representa un tipo de tile
- Cambio de sala automático al llegar al borde
- Eventos por sala: puertas que se abren al eliminar todos los enemigos, llaves que aparecen como recompensa
 
---
 
## 🔊 Audio
 
- Música de fondo en bucle que no se interrumpe al cambiar entre salas con la misma canción
- Efectos de sonido para ataques, daño, muerte de enemigos y recogida de objetos
- Música diferente según la zona del mapa
 
---
 
## 🚀 Cómo ejecutar
 
1. Clona o descarga el proyecto
2. Ábrelo en Eclipse u otro IDE de Java
3. Asegúrate de que la carpeta `Resources` está marcada como **Source Folder** en el Build Path
4. Ejecuta la clase `Principal.java`

 O 

 1. Descarga el .jar
 2. Ejecutalo

--- 
## 👤 Autor
 
Desarrollado como proyecto de clase.
