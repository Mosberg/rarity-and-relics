# Development Setup Guide

This guide will help you set up the Rarity and Relics mod for development.

## Prerequisites

### Required Software
1. **Java Development Kit (JDK) 21**
   - Download from [Adoptium](https://adoptium.net/) or [Oracle](https://www.oracle.com/java/technologies/downloads/#java21)
   - Verify installation: `java -version`

2. **Git**
   - Download from [git-scm.com](https://git-scm.com/)
   - Verify installation: `git --version`

3. **IDE (Recommended)**
   - [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Community or Ultimate)
   - [Visual Studio Code](https://code.visualstudio.com/) with Java extensions
   - [Eclipse](https://www.eclipse.org/downloads/)

## Initial Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Mosberg/rarity-and-relics.git
cd rarity-and-relics
```

### 2. Generate Sources

Generate Minecraft source code for reference:

```bash
./gradlew genSources
```

This may take several minutes on first run.

### 3. IDE Setup

#### IntelliJ IDEA (Recommended)

1. Open IntelliJ IDEA
2. Click "Open" and select the `rarity-and-relics` folder
3. Wait for Gradle to sync (bottom right corner)
4. IDEA will automatically detect the Fabric project

#### Visual Studio Code

1. Install extensions:
   - "Extension Pack for Java" by Microsoft
   - "Gradle for Java" by Microsoft
2. Open the folder in VS Code
3. Wait for Java extension to activate

#### Eclipse

1. Run: `./gradlew eclipse`
2. Open Eclipse
3. Import as "Existing Projects into Workspace"

## Building the Mod

### Build JAR

```bash
./gradlew build
```

The compiled JAR will be in `build/libs/rarity-and-relics-1.0.0.jar`

### Clean Build

If you encounter issues:

```bash
./gradlew clean build
```

## Running in Development

### Run Minecraft Client

```bash
./gradlew runClient
```

This launches Minecraft with your mod loaded for testing.

### Run Minecraft Server

```bash
./gradlew runServer
```

Useful for testing multiplayer behavior.

### Run with Data Generation

```bash
./gradlew runDatagen
```

Generates data files (if configured).

## Development Workflow

### 1. Make Changes

Edit source files in `src/main/java/` or `src/client/java/`

### 2. Rebuild

```bash
./gradlew build
```

### 3. Test

Run the client to test your changes:

```bash
./gradlew runClient
```

### 4. Hot Reload (If Supported)

Some changes can be reloaded without restarting:
- JSON configuration files
- Some resource files

Code changes require restarting Minecraft.

## Debugging

### IntelliJ IDEA

1. Open "Run/Debug Configurations"
2. Find "Minecraft Client" or create new Gradle configuration
3. Set breakpoints in your code
4. Click the debug icon (bug)

### Console Logging

Add debug logging in your code:

```java
RarityAndRelics.LOGGER.info("Debug message");
RarityAndRelics.LOGGER.debug("Detailed debug info");
RarityAndRelics.LOGGER.error("Error occurred", exception);
```

Logs appear in:
- IDE console (when running from IDE)
- `logs/latest.log` in the run directory

## Configuration Files

### Rarity Configuration

During development, config files are generated in:
```
run/config/rarity-and-relics/rarities.json
```

Edit this file to test different rarity configurations.

## Common Issues

### Gradle Sync Fails

**Solution:**
```bash
./gradlew --refresh-dependencies
./gradlew clean build
```

### Minecraft Won't Launch

**Check:**
- Java 21 is installed and set as default
- No syntax errors in your code
- All dependencies are available

**Try:**
```bash
./gradlew clean
./gradlew genSources
./gradlew runClient
```

### Mixins Not Applying

**Check:**
- Mixin configuration files are correct
- Target classes exist
- Injection points are valid

**Enable debug logging:**
Add to VM arguments:
```
-Dmixin.debug=true
```

### Changes Not Reflected

**Solutions:**
1. Rebuild the project: `./gradlew build`
2. Restart Minecraft
3. Clear IDE caches (IntelliJ: File > Invalidate Caches)
4. Clean and rebuild: `./gradlew clean build`

## Testing Checklist

- [ ] Mod loads without errors
- [ ] Rarity applies to crafted items
- [ ] Item names are colored correctly
- [ ] Tooltips display modifiers
- [ ] Configuration file generates correctly
- [ ] Works in single-player
- [ ] Works in multiplayer (if applicable)
- [ ] Compatible with other mods (test with popular mods)

## Additional Resources

- [Fabric Wiki](https://fabricmc.net/wiki/)
- [Fabric API Documentation](https://github.com/FabricMC/fabric)
- [Minecraft Development IntelliJ Plugin](https://plugins.jetbrains.com/plugin/8327-minecraft-development)
- [Yarn Mappings Browser](https://mappings.dev/)

## Getting Help

If you encounter issues:

1. Check [existing issues](https://github.com/Mosberg/rarity-and-relics/issues)
2. Read the [Fabric Wiki](https://fabricmc.net/wiki/)
3. Ask in [Fabric Discord](https://discord.gg/v6v4pMv)
4. Create a new issue with details

## Next Steps

Once set up, check out:
- [CONTRIBUTING.md](CONTRIBUTING.md) - Contribution guidelines
- [README.md](README.md) - Project overview
- [Code documentation](src/main/java/com/mosberg/) - Explore the codebase

Happy modding! 🎮
