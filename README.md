# Redmi 14C Beast Optimizer

Aplikasi optimizer Android lengkap untuk **Redmi 14C** (Helio G81 Ultra + HyperOS) menggunakan Shizuku untuk menjalankan perintah shell tanpa root.

## Features

### 40+ System Tweaks
- One Tap Beast Mode
- Lock Maximum Refresh Rate (120Hz)
- Force High Performance Mode
- Disable Battery Optimization
- Reduce Animation Scale
- Force GPU Rendering (Vulkan/OpenGL)
- Touch Response Booster
- Aggressive RAM Manager
- Network Optimizer (DNS, TCP Fast Open)
- CPU Governor Changer
- Max CPU/GPU Frequency Lock
- Thermal Throttling Reducer
- Disable MIUI/HyperOS Bloat
- And 30+ more...

### 155+ Optimizer Strings Database
- Built-in collection of useful SetEdit commands
- Search and filter by category
- Copy commands to clipboard
- Apply directly via Shizuku
- Add custom strings

### Gaming Features
- Per-App Game Profiles (PUBG, MLBB, CODM, etc.)
- Auto Game Turbo (Notification Listener)
- Floating FPS Monitor
- Real-time Temperature Monitor
- Disable Unused Sensors

### Advanced Tools
- Advanced SetEdit Panel
- Shizuku Setup Guide
- Backup & Restore
- Profile Manager (Balanced, Extreme FPS, Battery Saver)
- One Tap Reboot

## Requirements

- Android 7.0+ (API 24)
- Shizuku app installed and running
- Wireless Debugging enabled (Android 11+) or ADB

## Tech Stack

- Kotlin
- Jetpack Compose (Material You 3)
- Navigation Compose
- Shizuku API 13.1.5
- Room Database
- DataStore Preferences
- Coroutines

## Setup

1. Install Shizuku from Play Store
2. Enable Developer Options > Wireless Debugging
3. Pair Shizuku with pairing code
4. Open Beast Optimizer and grant Shizuku permission
5. Apply tweaks!

## Building

```bash
./gradlew assembleDebug
```

## GitHub Actions

The project includes a GitHub Actions workflow that automatically builds debug and release APKs on every push.

## Disclaimer

This app modifies system settings. Use at your own risk. The developers are not responsible for any damage to your device.

## License

MIT License
