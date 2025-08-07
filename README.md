# 🌍 WorldManagerFabric

**WorldManagerFabric** es un mod para Minecraft Java 1.21.8 (Fabric) que te permite gestionar múltiples mundos directamente desde comandos en el juego o servidor.

---

## ✅ Características

- Crear mundos personalizados (Overworld, Nether, End, Flat).
- Cargar o descargar mundos en caliente.
- Teletransportar jugadores entre mundos.
- Crear y restaurar backups de mundos.
- Eliminar mundos directamente desde el servidor.
- Listar mundos existentes en disco o en memoria.

---

## 🧾 Comandos disponibles

| Comando                             | Descripción                                                     |
|------------------------------------|-----------------------------------------------------------------|
| `/world create <nombre> [tipo]`     | Crea un nuevo mundo con el nombre y tipo (`overworld`, `flat`, `nether`, `end`). |
| `/world load <nombre>`              | Carga un mundo previamente creado desde disco.                  |
| `/world unload <nombre>`            | Descarga un mundo de la memoria del servidor.                   |
| `/world delete <nombre>`            | Elimina un mundo del disco permanentemente.                     |
| `/world teleport <jugador> <mundo>` | Teletransporta al jugador al punto de aparición del mundo.      |
| `/world list`                       | Muestra la lista de mundos cargados y/o disponibles en disco.   |
| `/world info <mundo>`               | Muestra información detallada de un mundo (estado, tipo, ruta). |
| `/world backup <mundo>`             | Crea una copia de seguridad del mundo (como archivo `.zip`).    |
| `/world restore <mundo> <archivo>`  | Restaura un mundo desde un archivo de backup `.zip`.            |
