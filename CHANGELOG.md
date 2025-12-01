# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2025-12-01

### Added
- **12 Rarity Tiers** replacing the original 5 tiers:
  - Trash (0.1% drop rate) - Intentionally poor items
  - Common (60.0%) - Standard baseline
  - Uncommon (20.0%) - Minor improvements
  - Rare (10.0%) - Significant improvements
  - Epic (5.0%) - Powerful enhancements
  - Legendary (2.0%) - Legendary quality
  - Mythic (1.0%) - Mythical power
  - Ancient (0.7%/0.9%) - Immense power
  - Celestial (0.5%/0.7%) - Otherworldly
  - Divine (0.3%/0.5%) - Godlike power
  - Transcendent (0.1%/0.3%) - Ultimate power
  - Owner (0.0%) - Admin/special only

- **Schema-Based Configuration System**
  - JSON Schema validation: `https://mosberg.github.io/src/schemas/rarities.schema.json`
  - Formal structure for rarity definitions
  - Asset system with icon and frame references

- **Source-Aware Drop Rates**
  - Separate drop rates for:
    - Crafting (items crafted at crafting tables)
    - Drops (items from killed mobs)
    - Loot (items from chests and structures)
  - SourceType enum for internal tracking
  - Higher rarities more accessible through exploration

- **Scaling Modifier System**
  - Modifier count scales with rarity (0-12 modifiers)
  - Strength multipliers (0.3x to 5.0x) based on tier
  - Trash tier: 0.3x multiplier, 0-1 modifiers
  - Owner tier: 5.0x multiplier, 10-12 modifiers

- **Enhanced Tooltip Display**
  - Rarity name with decorative formatting (✦ Name ✦)
  - Rarity description in italic
  - "Modifiers:" section header
  - Advanced mode shows asset references

- **Comprehensive Documentation**
  - RARITIES.md: Complete guide to all 12 tiers
  - Probability analysis for each source type
  - Modifier strength examples
  - Configuration customization guide

### Changed
- **Rarity Class Restructure**
  - Added RarityAssets inner class for icon/frame paths
  - Added DropRates inner class with crafting/drop/loot fields
  - Removed weight-based system in favor of source-specific rates
  - Updated fromJson() to parse new schema format

- **RarityManager Complete Rewrite**
  - Added SourceType enum (CRAFTING, DROP, LOOT)
  - getRandomRarity() now requires SourceType parameter
  - Separate weight calculation per source type
  - generateRarityData() uses source-aware selection
  - Added getAllRarities() for iteration

- **Modifier Generation Logic**
  - Tier-based modifier count (switch statement)
  - Rarity-based strength multipliers
  - Trash tier gets weaker modifiers
  - Ultra-rare tiers get exponentially stronger modifiers

- **Default Configuration**
  - Replaced 5-tier config with 12-tier config
  - Updated JSON structure to match schema
  - Added descriptions for all tiers
  - Added asset references (for future implementation)

### Fixed
- Proper handling of zero-weight rarities (Owner tier)
- Edge case when total weight is zero

### Technical
- Updated mixins to use SourceType
- Added LivingEntityMixin for mob drop tracking
- Updated mixin configuration files
- Schema validation support

## [1.0.0] - 2025-12-01

### Added
- Initial release of Rarity and Relics mod
- Dynamic item rarity system with 5 default tiers (Common, Uncommon, Rare, Epic, Legendary)
- Random stat modifier system with 9 modifier types
- Data component-based persistent storage (Minecraft 1.21.10+)
- JSON-based configuration system for rarities
- Colored item names based on rarity
- Detailed tooltip display with modifier information
- Icon indicators for each stat type
- Automatic rarity application to:
  - Crafted items
  - Chest loot
  - Mob drops
  - Item creation
- Mixin system for seamless integration
- Client-side rendering for colored names
- Tooltip callback for modifier display

### Technical Details
- Built for Minecraft 1.21.10
- Uses Fabric Loader 0.18.1
- Uses Fabric API 0.138.3+1.21.10
- Requires Java 21
- Uses Yarn mappings 1.21.10+build.3

[1.1.0]: https://github.com/Mosberg/rarity-and-relics/compare/v1.0.0...v1.1.0
[1.0.0]: https://github.com/Mosberg/rarity-and-relics/releases/tag/v1.0.0
