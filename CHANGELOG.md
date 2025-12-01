# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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

[1.0.0]: https://github.com/Mosberg/rarity-and-relics/releases/tag/v1.0.0
