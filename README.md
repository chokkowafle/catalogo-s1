
```markdown
# Catálogo de Productos - Solucionador

Aplicación móvil nativa para Android desarrollada en **Kotlin** y **Jetpack Compose**, diseñada para la navegación y visualización interactiva de un catálogo de productos multimedia, gaming, anime y tecnología.

---

## Características

- Interfaz Declarativa: Diseñada completamente con Jetpack Compose y Material 3.
- Catálogo Visual: Galería de productos variados (ropa, periféricos, figuras, videojuegos y accesorios) con carga eficiente de recursos gráficos.
- Tematización Dinámica: Paleta de colores, tipografías y soporte de temas definidos en `ui.theme`.
- Estructura Modular y Moderna: Configuración basada en Gradle Kotlin DSL (`build.gradle.kts`) y catálogo de dependencias (`libs.versions.toml`).

---

## Estructura del Proyecto

```text
catalogo-s1-master/
├── app/
│   ├── build.gradle.kts                  # Configuración de compilación del módulo app
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml       # Manifiesto principal de la aplicación
│       │   ├── java/com/example/solucionador/
│       │   │   ├── MainActivity.kt       # Actividad principal y punto de entrada Compose
│       │   │   └── ui/theme/             # Configuración de tema (Color, Theme, Type)
│       │   └── res/
│       │       └── drawable/             # Galería de imágenes y recursos del catálogo
│       └── test/                         # Pruebas unitarias locales
├── gradle/
│   └── libs.versions.toml                # Version Catalog de dependencias y plugins
├── build.gradle.kts                      # Configuración raíz de Gradle
└── settings.gradle.kts                   # Configuración de repositorios y módulos

```

---

## Requisitos Previos

* **Android Studio:** Ladybug (2024.2) o superior.
* **Java Development Kit (JDK):** Versión 17 o superior.
* **Android SDK:**
* `minSdk`: 24 (Android 7.0 Nougat) o superior.
* `targetSdk`: 34 / 35.



---

## Instalación y Ejecución

1. **Clonar el repositorio:**
```bash
git clone <URL_DEL_REPOSITORIO>
cd catalogo-s1-master

```


2. **Abrir en Android Studio:**
* Selecciona `File` > `Open...` y elige la carpeta raíz del proyecto.
* Espera a que finalice la sincronización de Gradle (`Sync Project with Gradle Files`).


3. **Compilar y desplegar:**
* Conecta un dispositivo físico con depuración USB habilitada o inicia un emulador (AVD).
* Presiona `Run 'app'` (`Shift + F10`) o compila mediante consola:
```bash
./gradlew assembleDebug

```





---

## Tecnologías Utilizadas

* **Lenguaje:** [Kotlin](https://kotlinlang.org/?utm_source=gemini)
* **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose?utm_source=gemini)
* **Gestor de Compilación:** Gradle con Kotlin DSL y Version Catalogs
* **Arquitectura:** Componentes de arquitectura recomendados para Android Jetpack

```
La aplicación está diseñada para ser compilada y ejecutada tanto en emuladores (AVD configurado para Pixel 8, API 37.2, arquitectura x86_64) como en dispositivos físicos (probado en hardware Samsung SM-A176B mediante depuración USB).
