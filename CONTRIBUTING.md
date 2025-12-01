# Contributing to Rarity and Relics

Thank you for your interest in contributing to Rarity and Relics! We welcome contributions from the community.

## How to Contribute

### Reporting Bugs

1. Check if the bug has already been reported in [Issues](https://github.com/Mosberg/rarity-and-relics/issues)
2. If not, create a new issue with:
   - Clear title
   - Detailed description
   - Steps to reproduce
   - Expected vs actual behavior
   - Minecraft version, mod version, and other mods installed
   - Crash logs or screenshots if applicable

### Suggesting Features

1. Check if the feature has already been suggested
2. Create a new issue with:
   - Clear title starting with "[Feature Request]"
   - Detailed description of the feature
   - Use cases and benefits
   - Any implementation ideas you have

### Code Contributions

1. **Fork the repository**
   ```bash
   git clone https://github.com/YOUR-USERNAME/rarity-and-relics.git
   cd rarity-and-relics
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/my-amazing-feature
   ```

3. **Set up development environment**
   ```bash
   ./gradlew genSources
   ```

4. **Make your changes**
   - Follow existing code style
   - Add comments for complex logic
   - Test your changes thoroughly

5. **Commit your changes**
   ```bash
   git commit -m "Add amazing feature: description"
   ```

6. **Push to your fork**
   ```bash
   git push origin feature/my-amazing-feature
   ```

7. **Open a Pull Request**
   - Provide clear description of changes
   - Reference any related issues
   - Wait for review

## Code Style Guidelines

- Use **4 spaces** for indentation (not tabs)
- Follow **Java naming conventions**:
  - Classes: `PascalCase`
  - Methods/variables: `camelCase`
  - Constants: `UPPER_SNAKE_CASE`
- Add **JavaDoc** comments for public APIs
- Keep methods focused and concise
- Use meaningful variable names

## Development Setup

### Prerequisites
- Java 21 JDK
- Git
- IDE (IntelliJ IDEA recommended)

### Building
```bash
./gradlew build
```

### Running in Development
```bash
./gradlew runClient  # Run Minecraft client
./gradlew runServer  # Run Minecraft server
```

### Testing
- Test with vanilla items
- Test with modded items if possible
- Test in single-player and multiplayer
- Test with different rarity configurations

## Pull Request Process

1. Update README.md if needed
2. Update documentation for new features
3. Ensure the build passes
4. Request review from maintainers
5. Address any feedback
6. Once approved, your PR will be merged!

## Questions?

Feel free to ask questions in:
- GitHub Discussions
- Issue comments
- Pull request comments

Thank you for contributing! 🎉
