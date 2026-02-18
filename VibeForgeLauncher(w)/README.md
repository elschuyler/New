# Vibeforge Launcher (v1.0)

A high-performance Android launcher featuring a modular plugin architecture (AIDL), biometric security per-app, and a RAM-conscious design.

## Features
- **Modular Plugin Engine**: Connect external plugins without bloat via AIDL services.
- **Biometric App Lock**: Secure any installed application with your device's fingerprint or face unlock.
- **RAM Dashboard**: Real-time monitoring of plugin and system memory footprints.
- **Glassmorphism UI**: Modern Material 3 design with translucent overlays.
- **Production Architecture**: Built on Room Database for persistence and Coroutines for smooth UI performance.

## Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17
- Android SDK 26 (Android 8.0) or higher
- A physical device or emulator with Biometric support (optional but recommended for security features)

## Installation & Setup

1. **Clone the project** to your local machine.
2. **Open in Android Studio**:
    - Select `File` > `Open` > Choose the root folder.
3. **Build the AIDL**:
    - Go to `Build` > `Make Project`. This will generate the Java interfaces from the `IVibeforgePlugin.aidl` file.
4. **Run**:
    - Click the 'Run' button (Green arrow) to install on your connected device.

## How to use
1. **Set as Default**: After installation, press the Home button and select Vibeforge Launcher as your default home activity.
2. **Locking Apps**: Long-press any app icon on the grid. Select "Lock App". Next time you tap that app, it will trigger the system biometric prompt.
3. **Plugin Management**: Tap "Plugins Dashboard" from any app's long-press menu to see active AIDL connections and their memory usage.

## Technical Details
- **AIDL Integration**: Plugins must implement a service with an intent filter for `com.vibeforge.launcher.PLUGIN`.
- **Zero-Footprint Concept**: The launcher only consumes memory for plugins when they are actively bound. No background tasks are used for passive state.
- **Database**: App lock states are stored in a local SQLite database via Room.

## Troubleshooting
- **AIDL Errors**: If the compiler cannot find `IVibeforgePlugin`, ensure you have run "Make Project" so the build folder generates the implementation.
- **Biometric Crash**: On emulators without a fingerprint configured, the Biometric prompt might show an error. Ensure a lock screen is set up on the virtual device.
