# 🎮 Rarity and Relics

**Gear up, take risks, and uncover legendary treasures in every adventure!**

A fully-featured Minecraft 1.21.10 Fabric mod that transforms equipment with dynamic rarity tiers, random stat modifiers, and persistent item properties.

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.10-green)
![Mod Loader](https://img.shields.io/badge/Mod%20Loader-Fabric-blue)
![License](https://img.shields.io/badge/License-CC0--1.0-lightgrey)

## ✨ Core Features

### 🎲 Dynamic Item Rarity System
- Every weapon, tool, and armor piece is automatically assigned a **random rarity tier**
- **12 unique rarity tiers**: Trash, Common, Uncommon, Rare, Epic, Legendary, Mythic, Ancient, Celestial, Divine, Transcendent, and Owner
- Each rarity has:
  - Unique color for item names
  - Custom description
  - Separate drop rates for crafting, mob drops, and loot
  - Scaling modifier counts and strengths
- Items display their rarity with **colored names** in inventories and detailed tooltips

### 📊 Random Buffs & Debuffs
- Items gain **stat modifiers** when crafted or discovered
- Nine modifier types:
  - ⚔️ Attack Damage
  - ⚡ Attack Speed
  - ◆ Durability
  - ⛏ Efficiency
  - 🛡️ Armor
  - 💪 Armor Toughness
  - ⚓ Knockback Resistance
  - 👟 Movement Speed
  - 🍀 Luck
- Modifiers scale with rarity (0.3x to 5.0x multiplier)
- Values range from -20% to +30% (base) with rarity multipliers
- Color-coded: green for buffs, red for debuffs

### 🎒 Smart Drop System
- **Three independent drop rate systems:**
  - **Crafting**: When you craft items at crafting tables
  - **Mob Drops**: When enemies are killed
  - **Loot Chests**: Found in structures and containers
- Higher rarities more accessible through exploration vs crafting
- Configurable weights for each source type

### 🔧 Fully Data-Driven
- Rarities defined in **JSON configuration** with schema validation
- Schema: `https://mosberg.github.io/src/schemas/rarities.schema.json`
- Easy to customize:
  - Colors and descriptions
  - Drop rates per source
  - Asset references for icons and frames
  - Modifier scaling
- No code changes needed to add new rarities

### 💾 Persistent Properties
- Item properties stored using Minecraft 1.21's **Data Component system**
- Properties persist when items are:
  - Moved between inventories
  - Dropped on the ground
  - Traded with players or villagers
  - Stored in chests or shulker boxes
- Fully networked between client and server
- Compatible with vanilla item mechanics

### 🎨 Visual Feedback
- **Colored item names** based on rarity
- **Detailed tooltips** with:
  - Rarity tier name and description
  - All stat modifiers with icons
  - Asset information (in advanced mode)
- **Icon indicators** for each stat type
- Special formatting for ultra-rare tiers

## 📦 Installation

1. **Install Fabric Loader** for Minecraft 1.21.10
   - Download from [Fabric MC](https://fabricmc.net/use/)

2. **Install Fabric API**
   - Download from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) or [Modrinth](https://modrinth.com/mod/fabric-api)
   - Place in your `mods` folder

3. **Install Rarity and Relics**
   - Download the latest release from [Releases](https://github.com/Mosberg/rarity-and-relics/releases)
   - Place the JAR file in your `mods` folder

4. **Launch Minecraft** with the Fabric profile

## 🎯 Rarity Tiers

For detailed information about all 12 rarity tiers, see **[RARITIES.md](RARITIES.md)**

### Quick Overview

| Tier | Color | Crafting % | Modifiers | Multiplier |
|------|-------|------------|-----------|------------|
| 🗑️ Trash | Brown | 0.1% | 0-1 | 0.3x |
| ⚪ Common | Gray | 60.0% | 1-2 | 0.5x |
| 🟢 Uncommon | Green | 20.0% | 2-3 | 0.7x |
| 🔵 Rare | Blue | 10.0% | 3-4 | 1.0x |
| 🟣 Epic | Purple | 5.0% | 4-5 | 1.3x |
| 🟠 Legendary | Orange | 2.0% | 5-6 | 1.6x |
| 🔴 Mythic | Red | 1.0% | 6-7 | 2.0x |
| 💧 Ancient | Cyan | 0.7% | 7-8 | 2.5x |
| 💗 Celestial | Pink | 0.5% | 8-9 | 3.0x |
| 🌟 Divine | Gold | 0.3% | 9-10 | 3.5x |
| ⬛ Transcendent | Black | 0.1% | 10-11 | 4.0x |
| 👑 Owner | Transparent | 0.0% | 10-12 | 5.0x |

## ⚙️ Configuration

The mod creates a configuration file at:
```
config/rarity-and-relics/rarities.json
```

### Configuration Format

```json
{
  "$id": "https://mosberg.github.io/src/schemas/rarities.schema.json",
  "rarities": [
    {
      "id": "legendary",
      "name": "Legendary",
      "color": "#FF9600",
      "description": "Extremely rare, with legendary enhancements.",
      "assets": {
        "icon": "rarity:icons/legendary",
        "frame": "rarity:frames/legendary"
      },
      "dropRates": {
        "crafting": 2.0,
        "drop": 2.0,
        "loot": 2.0
      }
    }
  ]
}
```

### Configuration Options

- `id`: Unique identifier (alphanumeric, dashes, underscores)
- `name`: Display name shown to players
- `color`: Hex color code (#RRGGBB format)
- `description`: Flavor text describing the rarity
- `assets.icon`: Resource path for rarity icon
- `assets.frame`: Resource path for item frame decoration
- `dropRates.crafting`: Weight for crafting table drops
- `dropRates.drop`: Weight for mob kill drops
- `dropRates.loot`: Weight for chest/structure loot

### Adding Custom Rarities

See **[RARITIES.md](RARITIES.md)** for detailed customization guide.

## 🛠️ For Developers

### Building from Source

```bash
git clone https://github.com/Mosberg/rarity-and-relics.git
cd rarity-and-relics
./gradlew build
```

The compiled JAR will be in `build/libs/`

### Documentation

- **[SETUP.md](SETUP.md)** - Development environment setup
- **[PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md)** - Architecture and code organization
- **[RARITIES.md](RARITIES.md)** - Complete rarity tier documentation
- **[CONTRIBUTING.md](CONTRIBUTING.md)** - Contribution guidelines
- **[CHANGELOG.md](CHANGELOG.md)** - Version history

### Key Technical Features

- **Data Components (1.21.10+)**: Modern persistent storage replacing NBT
- **Schema Validation**: JSON config with formal schema
- **Source-Aware Selection**: Different drop rates for crafting/drops/loot
- **Mixin System**: Non-invasive integration
- **Client/Server Split**: Proper code separation
- **Weighted Randomization**: Fair distribution algorithm

## 🎯 Roadmap

- [ ] Asset system implementation (icons and frames)
- [ ] Actual stat application (currently cosmetic)
- [ ] Custom loot table integration
- [ ] Enchantment synergy bonuses
- [ ] Set bonuses for matching rarity equipment
- [ ] Rarity reroll items/mechanics
- [ ] Visual particle effects for high rarities
- [ ] Sound effects on item discovery
- [ ] Integration with popular mods (REI, EMI)
- [ ] Data pack support for custom rarities
- [ ] Owner tier special permissions system

## 🤝 Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## 📄 License

This project is licensed under the **CC0 1.0 Universal** license - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built with [Fabric](https://fabricmc.net/)
- Uses [Fabric API](https://github.com/FabricMC/fabric)
- Minecraft by Mojang Studios
- Schema design inspired by modern JSON standards

## 📞 Support

If you encounter any issues or have questions:
- Open an [Issue](https://github.com/Mosberg/rarity-and-relics/issues)
- Read the documentation in this repository
- Check existing issues for solutions

---

**Made with ❤️ by Mosberg**
