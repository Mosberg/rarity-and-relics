# Mod Icon

This file serves as a placeholder for the mod icon.

## Required

You need to add a **128x128 PNG image** named `icon.png` in this directory.

The icon will be displayed in:
- Minecraft's mod menu
- Mod loaders
- Mod distribution platforms (CurseForge, Modrinth)

## Recommendations

- **Size:** 128x128 pixels (required)
- **Format:** PNG with transparency
- **Style:** Clear, recognizable design
- **Theme:** Should represent rarity/legendary items

## Design Ideas

- Glowing sword or weapon
- Chest with sparkles
- Gem or crystal
- Star or trophy icon
- Combination of multiple rarity-colored elements

## Creating the Icon

1. Create a 128x128 PNG image
2. Name it `icon.png`
3. Place it at: `src/main/resources/assets/rarity-and-relics/icon.png`
4. Remove this `icon.png.md` file
5. Rebuild the mod

## Temporary Solution

Until you add a custom icon, you can use a simple colored square:

```bash
# Using ImageMagick (if installed)
convert -size 128x128 xc:#FFAA00 icon.png
```

Or use any image editor to create a simple design.
