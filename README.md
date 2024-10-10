
# Visit Sri Lanka Mobile Application - MAD Project (2Y2S)

This repository contains the code for the **Visit Sri Lanka Mobile Application**, developed as part of the Mobile Application Development (MAD) module for the 2nd year, 2nd semester. The project aims to boost Sri Lanka's tourism by creating an app that addresses key areas in the tourism industry.

## Project Overview

The app includes four main components:
1. **Tourist Registration and Experience Sharing**
2. **Taxi Driver Management System**
3. **Hotel Management System**
4. **Tour Guide Registration and Selection**

---

## Prerequisites

- **Android Studio**: Make sure Android Studio is installed on your system.
- **Node.js**: Required for managing the backend and installing dependencies.
- **npm**: Node Package Manager (usually installed with Node.js).

---

## Running the Project in Android Studio

### 1. **Clone the Repository**:
   Clone the project repository to your local machine using the following command:
   ```bash
   git clone <repository-url>
   ```

### 2. **Open the Project in Android Studio**:
   - Launch **Android Studio**.
   - Open the project folder by navigating to **File > Open** and selecting the project directory (`Visit-Sri-Lanka-Mobile-Application`).

### 3. **Install Dependencies**:
   You will need to install the project dependencies using npm:
   1. Open the **Terminal** in Android Studio (bottom tab or use `Alt + F12`).
   2. Run the following command in the project root:
      ```bash
      npm install
      ```

### 4. **Configure Android SDK**:
   Ensure the Android SDK is correctly installed and configured:
   - In Android Studio, go to **File > Project Structure > SDK Location**.
   - Check that the Android SDK path is set correctly.

### 5. **Start the Metro Bundler**:
   Before running the app, start the React Native Metro bundler:
   ```bash
   npx react-native start
   ```

### 6. **Run the App on an Emulator/Device**:
   Once the bundler is running, you can launch the app:
   - Ensure an Android Emulator is running, or connect a physical Android device via USB with **Developer Mode** and **USB Debugging** enabled.
   - Run the following command to launch the app:
     ```bash
     npx react-native run-android
     ```

   Alternatively, you can click the **Run** button in Android Studio to compile and run the app directly from the IDE.

---

## Common Issues and Troubleshooting:

1. **Metro Bundler Issues**:
   - If the bundler is stuck or not loading, restart it using:
     ```bash
     npx react-native start --reset-cache
     ```

2. **Emulator Problems**:
   - If the emulator is not starting, make sure you’ve configured the Android Virtual Device (AVD) in Android Studio by going to **Tools > AVD Manager**.

3. **USB Debugging Issues**:
   - If a physical device is not detected, ensure **Developer Mode** and **USB Debugging** are enabled on your Android device.

---
