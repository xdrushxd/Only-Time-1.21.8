# Only Time! Mod - HUD Positioning Guide

## Overview
The Only Time! mod now features fully configurable HUD positioning, allowing you to choose exactly where on the screen you want the daylight HUD and day counter to appear.

## How to Access Configuration
1. **In-game**: Press the `G` key to open the configuration screen
2. **Mod Menu**: If you have Mod Menu installed, you can also access it from there

## HUD Elements

### 1. Daylight HUD
- **What it shows**: Time remaining until day/night cycle changes
- **Default position**: Top-left corner
- **Configurable options**:
  - **Position**: Choose from 4 corners (Top Left, Top Right, Bottom Left, Bottom Right)
  - **X Offset**: Adjust horizontal position (-50 to +50 pixels)
  - **Y Offset**: Adjust vertical position (-50 to +50 pixels)
  - **Color**: Choose from 5 colors (White, Red, Green, Blue, Yellow)

### 2. Day Counter
- **What it shows**: Current day number
- **Default position**: Bottom-right corner
- **Configurable options**:
  - **Position**: Choose from 4 corners (Top Left, Top Right, Bottom Left, Bottom Right)
  - **X Offset**: Adjust horizontal position (-50 to +50 pixels)
  - **Y Offset**: Adjust vertical position (-50 to +50 pixels)
  - **Color**: Choose from 5 colors (White, Red, Green, Blue, Yellow)

## Positioning System

### Corner Selection
- **Top Left**: HUD appears in the top-left area of the screen
- **Top Right**: HUD appears in the top-right area of the screen
- **Bottom Left**: HUD appears in the bottom-left area of the screen
- **Bottom Right**: HUD appears in the bottom-right area of the screen

### Offset System
- **X Offset**: Positive values move right, negative values move left
- **Y Offset**: Positive values move down, negative values move up
- **Range**: -50 to +50 pixels for fine-tuning

### Smart Positioning
- The mod automatically calculates text width to ensure proper alignment
- Right-aligned text (Top Right, Bottom Right) automatically adjusts for text length
- Off-screen positions are automatically clamped to keep text visible

## Troubleshooting

### HUD Not Visible?
1. **Check if enabled**: Make sure both HUD elements are enabled in the config
2. **Check position**: Try changing to a different corner
3. **Check offsets**: Reset offsets to 0 and gradually adjust
4. **Check colors**: Ensure the color isn't blending with the background

### Recommended Settings
- **Top Left**: X: 5, Y: 5 (default)
- **Top Right**: X: 5, Y: 5
- **Bottom Left**: X: 5, Y: 15
- **Bottom Right**: X: 12, Y: 15 (default)

### Performance Notes
- Position calculations are performed efficiently
- No impact on game performance
- Settings are saved automatically

## Example Configurations

### Minimal HUD (Top corners)
- Daylight HUD: Top Left, X: 5, Y: 5
- Day Counter: Top Right, X: 5, Y: 5

### Balanced Layout (Opposite corners)
- Daylight HUD: Top Left, X: 5, Y: 5
- Day Counter: Bottom Right, X: 12, Y: 15

### Custom Position
- Daylight HUD: Bottom Left, X: 10, Y: 20
- Day Counter: Top Right, X: 15, Y: 10

## Support
If you continue to have issues with HUD visibility:
1. Try different corner positions
2. Reset offsets to default values
3. Check if other mods might be interfering
4. Ensure you're using the latest version of the mod
