# 💪 TrainMe - Fitness Exercise App

TrainMe is a Jetpack Compose-based Android application that showcases a list of fitness exercises with support for offline access and clean architecture.

---

## 🚀 Features

- ✅ Offline support with Room Database
- 🧠 MVVM architecture
- 📦 Clean architecture layers (Presentation, Domain, Data)
- 📡 Network and local caching
- 🗂️ Repository pattern
- 🔧 Dependency Injection (Hilt)
- 🎨 Dark/Light theme toggle
- ✨ Modern UI using Jetpack Compose & Material 3

---

## 🏗️ Project Structure

<pre>
trainme/
├── data/              # Local DB, network API, models, mappers
│   ├── local/
│   ├── remote/
│   └── repository/
├── domain/            # Use cases, entities, interfaces
├── presentation/      # UI, ViewModels, Screens, Navigation
│   └── components/
├── di/                # Hilt modules
├── ui/theme/          # Material 3 theme files
├── utils/             # Utility classes and helpers
└── MainActivity.kt    # App entry point
</pre>

---

## 🧩 Tech Stack

- **Jetpack Compose** for declarative UI
- **Room** for offline caching
- **Retrofit** for network calls
- **Hilt** for dependency injection
- **Kotlin Coroutines + Flow** for async & reactive handling
- **Material 3** for modern design
- **ViewModel + State Management** with Compose

---

## 🤝 Contributing

Pull requests are welcome! Feel free to open issues for suggestions or bug reports.

