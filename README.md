# 🐶 DogNet

DogNet is a simple two-screen Android app built with Jetpack Compose. It demonstrates clean architecture, Hilt DI, error handling, and good Compose practices.

## ✨ Features

- Lists all dog breeds from [Dog API](https://dog.ceo/dog-api/)
- On selecting a breed, shows 10 random images of that breed
- Clean MVVM architecture
- Uses Hilt for dependency injection
- Navigation with Compose
- Image loading with Coil
- Proper loading/error states

## 🧱 Tech Stack

- Jetpack Compose
- Hilt
- Retrofit + Gson
- Coil
- Kotlin Coroutines + StateFlow
- MVVM
- Material 3

## 🚀 Getting Started

1. Clone or download the project
2. Open in Android Studio
3. Sync Gradle and Run

## 🧪 Tests

Includes a unit tests for `BreedListViewModel` and `BreedImagesViewModelTest` using a fake repository.

## 🧠 Author Notes

This app is designed to be robust, modular and readable. Future enhancements

## 🔮 Future Improvements

### 1. Introduce Use Cases (Clean Architecture)
- Isolate business logic from ViewModels for better testing and maintainability.

### 2. Room Caching Layer
- Minimize API calls and allow offline access.

### 3. Pagination for Breed Images
- Avoid downloading large image sets all at once using Paging 3 or manual pagination with LazyColumn.

### 4. Error Categories
- Right now errors are generic. Classify them:
  - Network Error
  - API Error
  - Timeout
  - Empty Data
- UX: Show meaningful messages or retry options.

### 5. Better UX Enhancements
- Skeleton loading with Accompanist Placeholder
- Lottie animations for empty/error states
- Swipe to refresh
- Smooth image fade-in animations

### 6. Testing Enhancements
- Add UI tests with ComposeTestRule
- Mock network calls using MockWebServer
- Add coverage reports (e.g. Jacoco)

### 7. CI/CD Integration
- GitHub Actions or Bitrise for:
  - Build + Unit Test
  - Lint + Static Analysis (Detekt)
  - Test Coverage Reports

### 8. Modularization
Split into feature-based modules:
- :core
- :feature-breedlist
- :feature-breedimages
- :data, :domain

Improves scalability, build speed, testability.

### 9. Theming and Dark Mode
- Add proper support for dynamic colors
- Let user switch themes via settings screen

### 10. Image Details Screen
- Tap an image → open fullscreen with zoom
- Show image metadata if available

