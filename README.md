# 🎮 Rarity and Relics

**Gear up, take risks, and uncover legendary treasures in every adventure!**

A fully-featured Minecraft 1.21.10 Fabric mod that transforms equipment with dynamic rarity tiers, random stat modifiers, and persistent item properties.

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.10-green)
![Mod Loader](https://img.shields.io/badge/Mod%20Loader-Fabric-blue)
![License](https://img.shields.io/badge/License-CC0--1.0-lightgrey)

## ✨ Core Features

### 🎲 Dynamic Item Rarity System
- Every weapon, tool, and armor piece is automatically assigned a **random rarity tier**
- Five default tiers: Common, Uncommon, Rare, Epic, and Legendary
- Each rarity has a unique color and appearance probability
- Items display their rarity with **colored names** both in inventories and tooltips

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
- Modifiers can be positive (buffs) or negative (debuffs)
- Values range from -20% to +30%

### 🎒 Universal Loot Integration
Rarity and effects apply seamlessly to:
- ✅ **Crafted items** - Gain rarity when you craft them
- ✅ **Chest loot** - Found items have random properties
- ✅ **Mob drops** - Enemy equipment has unique stats
- ✅ **Trading** - Villager trades produce varied items

### 🔧 Fully Data-Driven
- Rarities are defined in **JSON configuration files**
- Easy to customize colors, weights, and modifier ranges
- No code changes needed to add new rarities
- Hot-reloadable configuration

### 💾 Persistent Properties
- Item properties stored using Minecraft 1.21's **Data Component system**
- Properties persist when items are:
  - Moved between inventories
  - Dropped on the ground
  - Traded with players or villagers
  - Stored in chests or shulker boxes
- Compatible with vanilla item mechanics

### 🎨 Visual Feedback
- **Colored item names** based on rarity
- **Detailed tooltips** showing all modifiers
- **Icon indicators** for each stat type
- Color-coded positive (green) and negative (red) modifiers

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

## ⚙️ Configuration

The mod creates a configuration file at:
```
config/rarity-and-relics/rarities.json
```

### Default Configuration

```json
{
  "rarities": {
    "common": {
      "display_name": "Common",
      "color": "#AAAAAA",
      "weight": 50.0,
      "min_modifiers": 0,
      "max_modifiers": 1
    },
    "uncommon": {
      "display_name": "Uncommon",
      "color": "#55FF55",
      "weight": 30.0,
      "min_modifiers": 1,
      "max_modifiers": 2
    },
    "rare": {
      "display_name": "Rare",
      "color": "#5555FF",
      "weight": 15.0,
      "min_modifiers": 2,
      "max_modifiers": 3
    },
    "epic": {
      "display_name": "Epic",
      "color": "#AA00AA",
      "weight": 4.0,
      "min_modifiers": 3,
      "max_modifiers": 4
    },
    "legendary": {
      "display_name": "Legendary",
      "color": "#FFAA00",
      "weight": 1.0,
      "min_modifiers": 4,
      "max_modifiers": 5
    }
  }
}
```

### Customization Guide

**Adding a New Rarity:**
```json
"mythic": {
  "display_name": "Mythic",
  "color": "#FF00FF",
  "weight": 0.1,
  "min_modifiers": 5,
  "max_modifiers": 7
}
```

**Configuration Options:**
- `display_name`: Name shown to players
- `color`: Hex color code for item names
- `weight`: Relative probability (higher = more common)
- `min_modifiers`: Minimum random stat bonuses
- `max_modifiers`: Maximum random stat bonuses

## 🛠️ For Developers

### Building from Source

```bash
git clone https://github.com/Mosberg/rarity-and-relics.git
cd rarity-and-relics
./gradlew build
```

The compiled JAR will be in `build/libs/`

### Project Structure

```
src/
├── main/
│   ├── java/com/mosberg/
│   │   ├── RarityAndRelics.java          # Main mod entry point
│   │   ├── component/                     # Data component system
│   │   │   ├── ModDataComponents.java
│   │   │   └── RarityData.java
│   │   ├── rarity/                        # Rarity management
│   │   │   ├── Rarity.java
│   │   │   └── RarityManager.java
│   │   ├── modifier/                      # Stat modifiers
│   │   │   ├── ModifierType.java
│   │   │   └── StatModifier.java
│   │   ├── event/                         # Event handlers
│   │   │   └── LootTableModifier.java
│   │   └── mixin/                         # Core mixins
│   │       ├── ItemStackMixin.java
│   │       └── CraftingScreenHandlerMixin.java
│   └── resources/
│       ├── fabric.mod.json
│       └── rarity-and-relics.mixins.json
└── client/
    ├── java/com/mosberg/
    │   ├── RarityAndRelicsClient.java
    │   ├── client/tooltip/
    │   │   └── RarityTooltipHandler.java
    │   └── mixin/client/
    │       ├── ItemRendererMixin.java
    │       └── ItemStackClientMixin.java
    └── resources/
        └── rarity-and-relics.client.mixins.json
```

### Key Components

**Data Components (1.21.10+):**
- Replaces NBT system with typed, persistent data
- `RarityData` record stores rarity ID and modifiers
- Serialized with Codec for network sync

**Rarity System:**
- JSON-based configuration
- Weighted random selection
- Customizable modifier ranges

**Mixins:**
- `ItemStackMixin`: Applies rarity on item creation
- `CraftingScreenHandlerMixin`: Applies rarity to crafted items
- `ItemStackClientMixin`: Colors item names
- `ItemRendererMixin`: Visual rendering hooks

## 🎯 Roadmap

- [ ] Custom loot table integration for specific chests
- [ ] Enchantment synergy bonuses
- [ ] Set bonuses for matching rarity equipment
- [ ] Rarity reroll items/mechanics
- [ ] Visual particle effects for legendary items
- [ ] Sound effects on item discovery
- [ ] Integration with popular mods (REI, EMI)
- [ ] Data pack support for custom rarities

## 🤝 Contributing

Contributions are welcome! Please feel free to submit pull requests.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the **CC0 1.0 Universal** license - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built with [Fabric](https://fabricmc.net/)
- Uses [Fabric API](https://github.com/FabricMC/fabric)
- Minecraft by Mojang Studios

## 📞 Support

If you encounter any issues or have questions:
- Open an [Issue](https://github.com/Mosberg/rarity-and-relics/issues)
- Check existing issues for solutions

---

**Made with ❤️ by Mosberg**
