# Only Time! - Minecraft 1.21.8

A Fabric mod that displays a day/night timer and day counter with customizable positioning and UI controls.

## Features

### ✨ Core Functionality
- **Day/Night Timer**: Shows remaining time until day/night cycle changes
- **Day Counter**: Tracks the current day in your Minecraft world
- **Smart Positioning**: 8 preset positions with fine-tuning offset controls
- **Quick Toggle**: Press `G` to enable/disable the HUD instantly
- **Config Access**: Press `H` to open the configuration screen

### 🎮 Controls
- **G Key**: Toggle HUD on/off
- **H Key**: Open configuration screen

### 📍 Positioning System
The mod offers 8 preset positions for optimal HUD placement:
- **Top Left** - Top Center - **Top Right**
- **Left Center** - **Right Center**
- **Bottom Left** - Bottom Center - **Bottom Right**

Each position can be fine-tuned with X and Y offset controls for pixel-perfect placement.

### ⚙️ Configuration Options
- Enable/disable daylight HUD and day counter separately
- Choose from 8 preset positions
- Fine-tune positioning with offset controls
- Text color customization
- Background display toggle
- Seconds display toggle
- HUD scale adjustment (50% - 200%)

## Installation

### Prerequisites
- **Minecraft 1.21.8**
- **Fabric Loader** (0.16.14 or higher)
- **Fabric API**
- **Java 21** or higher

### Dependencies
```gradle
dependencies {
    modImplementation "net.fabricmc:fabric-api:${fabric_version}"
    modApi "me.shedaniel.cloth:cloth-config-fabric:19.0.147"
    modApi "dev.isxander.yet-another-config-lib:yet_another_config_lib_v3:3.7.1+1.21.6"
}
```

### Manual Installation
1. Download the latest release JAR file
2. Place it in your `mods` folder
3. Ensure you have Fabric API installed
4. Launch Minecraft with Fabric Loader

### Building from Source
1. Clone this repository
2. Run `./gradlew build` (Linux/Mac) or `gradlew.bat build` (Windows)
3. Find the built JAR in `build/libs/`

## Configuration

### In-Game Configuration
1. Press `H` to open the configuration screen
2. Adjust HUD positions, colors, and display options
3. Use offset controls for precise positioning
4. Changes are saved automatically

### Configuration File Location
```
.minecraft/config/only-time.json
```

## Recent Changes (v1.1.0)

### ✅ What's Working
- Minecraft 1.21.8 compatibility
- G key HUD toggle functionality
- H key configuration access
- 8-position HUD positioning system
- Offset controls for fine-tuning
- Basic text color system
- Background display option
- Seconds display toggle
- HUD scale adjustment
- Proper error handling and logging

### 🚧 Known Issues & Limitations
- **Text Color System**: The text color customization is partially implemented but may not work as expected in all scenarios
- **Position Persistence**: Some position settings may reset between game sessions
- **Background Rendering**: Background display may have visual glitches in certain positions
- **Multiplayer Compatibility**: Not fully tested in multiplayer environments

### 🔧 Technical Details
- Built with Fabric API
- Uses Cloth Config for configuration management
- Implements custom position calculation system
- Includes comprehensive error handling and debug logging

## Development

### Project Structure
```
src/main/java/com/onlytime/
├── DaylightHUDMod.java          # Main mod class
├── DaylightHUDConfig.java       # Configuration class
├── HudPosition.java             # Position enum
├── HudPositionCalculator.java   # Position calculation logic
├── HudColor.java                # Color enum
└── mixin/ExampleMixin.java     # Mixin example
```

### Building
```bash
# Build the mod
./gradlew build

# Run in development environment
./gradlew runClient

# Generate sources JAR
./gradlew sourcesJar
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is licensed under the ARR License.

## Credits

- **Original Mod**: Sandwichdevi/Only-Time
- **Fork & Updates**: xdrushxd
- **Minecraft Version**: 1.21.8
- **Mod Loader**: Fabric

## Support

If you encounter issues or have suggestions:
1. Check the known issues section above
2. Review the debug logs in the console
3. Open an issue on GitHub with detailed information

## Roadmap

### Planned Features
- [ ] Fix text color customization system
- [ ] Improve position persistence
- [ ] Enhanced background rendering
- [ ] Multiplayer compatibility testing
- [ ] Additional HUD themes
- [ ] Performance optimizations

### Version History
- **v1.1.0**: Minecraft 1.21.8 support, UI toggle, positioning system
- **v1.0.2**: Beta version for 1.21-1.21.5
- **v1.0.0**: Initial release for 1.21-1.21.5

---

**Note**: This mod is actively maintained and updated for the latest Minecraft versions. Check the releases page for the most recent updates.
