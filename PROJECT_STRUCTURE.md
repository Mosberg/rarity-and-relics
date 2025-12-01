# Project Structure Documentation

This document provides a comprehensive overview of the Rarity and Relics mod architecture and file organization.

## Directory Structure

```
rarity-and-relics/
├── .github/                          # GitHub specific files
│   ├── ISSUE_TEMPLATE/              # Issue templates
│   │   ├── bug_report.md
│   │   └── feature_request.md
│   └── workflows/                   # GitHub Actions
│       └── build.yml                # Automated builds
├── src/
│   ├── main/                        # Server-side and shared code
│   │   ├── java/com/mosberg/
│   │   │   ├── RarityAndRelics.java   # Main mod initializer
│   │   │   ├── component/              # Data component system
│   │   │   │   ├── ModDataComponents.java
│   │   │   │   └── RarityData.java
│   │   │   ├── rarity/                 # Rarity system
│   │   │   │   ├── Rarity.java
│   │   │   │   └── RarityManager.java
│   │   │   ├── modifier/               # Stat modifiers
│   │   │   │   ├── ModifierType.java
│   │   │   │   └── StatModifier.java
│   │   │   ├── event/                  # Event handlers
│   │   │   │   └── LootTableModifier.java
│   │   │   └── mixin/                  # Mixins for core game hooks
│   │   │       ├── ItemStackMixin.java
│   │   │       └── CraftingScreenHandlerMixin.java
│   │   └── resources/
│   │       ├── fabric.mod.json        # Mod metadata
│   │       ├── rarity-and-relics.mixins.json  # Mixin config
│   │       └── assets/
│   │           └── rarity-and-relics/
│   │               └── icon.png        # Mod icon (needs to be added)
│   └── client/                     # Client-side only code
│       ├── java/com/mosberg/
│       │   ├── RarityAndRelicsClient.java  # Client initializer
│       │   ├── client/tooltip/
│       │   │   └── RarityTooltipHandler.java
│       │   └── mixin/client/
│       │       ├── ItemRendererMixin.java
│       │       └── ItemStackClientMixin.java
│       └── resources/
│           └── rarity-and-relics.client.mixins.json
├── build.gradle                  # Build configuration
├── gradle.properties             # Gradle properties
├── settings.gradle               # Gradle settings
├── gradlew                       # Gradle wrapper (Unix)
├── gradlew.bat                   # Gradle wrapper (Windows)
├── .gitignore                    # Git ignore rules
├── .gitattributes                # Git attributes
├── LICENSE                       # CC0-1.0 license
├── README.md                     # Main documentation
├── CONTRIBUTING.md               # Contribution guide
├── CHANGELOG.md                  # Version history
├── SETUP.md                      # Development setup
└── PROJECT_STRUCTURE.md          # This file
```

## Core Components

### 1. Main Module (`src/main/`)

Server-side and shared code that runs on both client and server.

#### RarityAndRelics.java
**Purpose:** Main mod entry point
**Key Responsibilities:**
- Initialize mod components
- Register data components
- Load configuration
- Register event handlers

#### Component System

**ModDataComponents.java**
- Registers custom data component types
- Uses Minecraft 1.21's Data Component API
- Replaces old NBT system

**RarityData.java**
- Record class storing item rarity information
- Contains: rarity ID and list of modifiers
- Serialized with Codec for network sync
- Implements PacketCodec for client-server communication

#### Rarity System

**Rarity.java**
- Represents a single rarity tier
- Properties: ID, name, color, weight, modifier range
- Deserializes from JSON configuration

**RarityManager.java**
- Manages all rarities
- Loads configuration from JSON
- Handles weighted random selection
- Generates rarity data with random modifiers

#### Modifier System

**ModifierType.java**
- Enum of all available stat types
- Each type has display name and icon
- Types: Attack Damage, Attack Speed, Durability, etc.

**StatModifier.java**
- Record representing a single stat modification
- Contains type and value (percentage)
- Formats values for display (+15.5%, -10.2%, etc.)

#### Event Handlers

**LootTableModifier.java**
- Hooks into Fabric's loot table events
- Could be extended to modify specific loot tables
- Currently set up for future expansion

#### Mixins (Server/Shared)

**ItemStackMixin.java**
- Injects into ItemStack constructor
- Applies rarity when items are created
- Checks if item type should have rarity
- Only applies to tools, weapons, and armor

**CraftingScreenHandlerMixin.java**
- Hooks into crafting table GUI
- Applies rarity to crafted items
- Ensures consistent behavior across different crafting methods

### 2. Client Module (`src/client/`)

Client-only code for rendering and display.

#### RarityAndRelicsClient.java
**Purpose:** Client-side initialization
**Key Responsibilities:**
- Register tooltip handlers
- Initialize client-side rendering

#### Tooltip System

**RarityTooltipHandler.java**
- Uses Fabric's ItemTooltipCallback
- Reads rarity data from item
- Adds formatted lines to tooltip:
  - Rarity tier name (colored)
  - Each modifier with icon and value
  - Color coding: green for buffs, red for debuffs

#### Client Mixins

**ItemStackClientMixin.java**
- Modifies `getName()` method
- Colors item names based on rarity
- Preserves original name, only changes color

**ItemRendererMixin.java**
- Hooks into item rendering
- Could be used for border effects or visual indicators
- Currently set up for future features

## Data Flow

### Item Creation Flow

```
1. Item created (crafted, looted, spawned)
   ↓
2. ItemStackMixin.onItemStackCreation() triggered
   ↓
3. Check if item type should have rarity
   ↓
4. RarityManager.generateRarityData()
   ↓
5. Select random rarity (weighted)
   ↓
6. Generate N random modifiers
   ↓
7. Create RarityData record
   ↓
8. Store in ItemStack via data component
   ↓
9. Data persists with item
```

### Display Flow

```
1. Player hovers over item
   ↓
2. ItemStackClientMixin.modifyItemName()
   - Colors item name
   ↓
3. RarityTooltipHandler.register() callback
   - Reads RarityData component
   - Adds rarity tier line
   - Adds modifier lines
   ↓
4. Tooltip displayed to player
```

## Configuration System

### Location
```
config/rarity-and-relics/rarities.json
```

### Structure
```json
{
  "rarities": {
    "rarity_id": {
      "display_name": "Display Name",
      "color": "#RRGGBB",
      "weight": 0.0-100.0,
      "min_modifiers": 0-10,
      "max_modifiers": 0-10
    }
  }
}
```

### Loading Process

1. Check if config exists
2. If not, create default from template
3. Parse JSON with Gson
4. Create Rarity objects
5. Store in RarityManager maps
6. Calculate total weight for random selection

## Mixin System

### Configuration Files

**rarity-and-relics.mixins.json** (Server/Shared)
```json
{
  "required": true,
  "package": "com.mosberg.mixin",
  "compatibilityLevel": "JAVA_21",
  "mixins": [
    "ItemStackMixin",
    "CraftingScreenHandlerMixin"
  ]
}
```

**rarity-and-relics.client.mixins.json** (Client)
```json
{
  "required": true,
  "package": "com.mosberg.mixin.client",
  "compatibilityLevel": "JAVA_21",
  "client": [
    "ItemRendererMixin",
    "ItemStackClientMixin"
  ]
}
```

### Mixin Targets

| Mixin | Target | Method | Purpose |
|-------|--------|--------|----------|
| ItemStackMixin | `ItemStack` | `<init>` | Apply rarity on creation |
| CraftingScreenHandlerMixin | `CraftingScreenHandler` | `onContentChanged` | Apply rarity to crafted items |
| ItemStackClientMixin | `ItemStack` | `getName` | Color item names |
| ItemRendererMixin | `ItemRenderer` | `renderGuiItemOverlay` | Future visual effects |

## Build System

### Gradle Configuration

**build.gradle**
- Uses Fabric Loom plugin
- Splits source sets (main/client)
- Configures dependencies
- Manages compilation and publishing

**gradle.properties**
- Minecraft version: 1.21.10
- Fabric Loader: 0.18.1
- Fabric API: 0.138.3+1.21.10
- Java version: 21
- Mod version: 1.0.0

### Build Tasks

```bash
./gradlew build          # Build JAR
./gradlew clean          # Clean build files
./gradlew genSources     # Generate Minecraft sources
./gradlew runClient      # Run Minecraft client
./gradlew runServer      # Run Minecraft server
```

## Dependencies

### Required
- **Minecraft**: 1.21.10
- **Fabric Loader**: ≥0.18.1
- **Fabric API**: Any version for 1.21.10
- **Java**: ≥21

### Development Only
- **Fabric Loom**: 1.13-SNAPSHOT (Gradle plugin)
- **Yarn Mappings**: 1.21.10+build.3

## Extension Points

Places where the mod can be easily extended:

### 1. Add New Modifier Types
**File:** `modifier/ModifierType.java`
**Action:** Add enum constant with name and icon

### 2. Add New Rarities
**File:** `config/rarity-and-relics/rarities.json`
**Action:** Add new JSON object with rarity properties

### 3. Customize Item Selection
**File:** `mixin/ItemStackMixin.java`
**Method:** `shouldApplyRarity()`
**Action:** Add/remove item types

### 4. Modify Selection Algorithm
**File:** `rarity/RarityManager.java`
**Method:** `getRandomRarity()`
**Action:** Change weighted selection logic

### 5. Add Visual Effects
**File:** `mixin/client/ItemRendererMixin.java`
**Method:** `renderRarityBorder()`
**Action:** Add rendering code

### 6. Custom Loot Tables
**File:** `event/LootTableModifier.java`
**Method:** `register()`
**Action:** Add specific loot table modifications

## Best Practices

### Adding New Features

1. **Keep client and server separate**
   - Server code in `src/main/`
   - Client code in `src/client/`

2. **Use data components, not NBT**
   - Create new component types in `component/`
   - Register in `ModDataComponents`

3. **Follow naming conventions**
   - Classes: PascalCase
   - Methods: camelCase
   - Constants: UPPER_SNAKE_CASE

4. **Document complex logic**
   - Add JavaDoc for public APIs
   - Explain non-obvious code

5. **Test thoroughly**
   - Single-player
   - Multiplayer
   - With other mods

### Performance Considerations

1. **Rarity generation is lightweight**
   - Random selection is O(n)
   - Only triggered on item creation

2. **Data components are efficient**
   - Native Minecraft system
   - Automatically synced
   - Properly cached

3. **Client rendering is minimal**
   - Only colors text
   - No custom rendering (yet)

## Troubleshooting

### Common Issues

**Issue:** Rarity not applying
**Check:**
- Item type in `shouldApplyRarity()`
- Configuration loaded correctly
- Mixin applied (check logs)

**Issue:** Colors not showing
**Check:**
- Client-side mixin active
- Rarity data present on item
- Color format correct (#RRGGBB)

**Issue:** Tooltips missing
**Check:**
- Fabric API installed
- ItemTooltipCallback registered
- RarityData component present

## Future Development

Planned additions (see CHANGELOG.md and README.md for details):

1. Actual stat application (currently cosmetic)
2. Loot table integration
3. Visual particle effects
4. Sound effects
5. Rarity reroll mechanics
6. Set bonuses
7. Data pack support

## Resources

- **Fabric Wiki**: https://fabricmc.net/wiki/
- **Yarn Mappings**: https://mappings.dev/
- **Fabric API Docs**: https://github.com/FabricMC/fabric
- **Minecraft Wiki**: https://minecraft.wiki/

---

**Last Updated:** December 1, 2025
**Mod Version:** 1.0.0
**Minecraft Version:** 1.21.10
