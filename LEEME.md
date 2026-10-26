# UMBRAL — proyecto Android (APK)

Un WebView a pantalla completa, horizontal, que carga `app/src/main/assets/index.html` (el juego).
Sin dependencias externas. Creado por 15ce & 11ce — Rusitos Boys.

## Opción A — Android Studio
1. Abre esta carpeta con Android Studio (Hedgehog o más nuevo) y espera la sincronización de Gradle.
2. Conecta el móvil con depuración USB y pulsa Run, o usa Build > Build APK(s).
3. El APK queda en `app/build/outputs/apk/debug/app-debug.apk`.

## Opción B — Sin instalar nada (GitHub Actions)
1. Crea un repositorio en GitHub y sube TODO el contenido de esta carpeta (incluida `.github`).
2. En la pestaña Actions ejecuta "Build APK" (o se lanza solo al subir).
3. Descarga el artefacto `Umbral-APK`, pásalo al móvil y ábrelo (permite "instalar apps desconocidas").

## Notas
- Botón Atrás: pausa y reanuda; en el menú principal cierra la app. "Salir" del menú también cierra.
- Se pausa solo al salir de la app. Pantalla siempre encendida mientras juegas.
- Es un APK de depuración (firmado con la clave debug): vale para instalar y compartir. Para Play Store hace falta firmar uno release.
