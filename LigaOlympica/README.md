# LigaOlympica

## Overview
LigaOlympica is a text-based turn-based game developed using Java's Object-Oriented Programming principles. The game features various characters, each with unique abilities, and allows players to engage in strategic battles.

## Project Structure
The project is organized into the following directories:

- **src/**: Contains the main source code for the game.
  - **main/**: The main application code.
    - **java/**: Java source files.
      - **ligaolympica/**: The main package for the game.
        - **Main.java**: Entry point for the game, initializes and starts the game loop.
        - **Game.java**: Manages overall game logic, player turns, and character interactions.
        - **battle/**: Contains battle-related classes.
          - **Battle.java**: Handles battle mechanics, including turn-based actions and outcomes.
        - **character/**: Contains character-related classes.
          - **Character.java**: Base class for all characters, defining properties and actions.
          - **Achiron.java**: Defines the Achiron character class with specific attributes.
          - **Atalyn.java**: Defines the Atalyn character class with specific attributes.
          - **Orris.java**: Defines the Orris character class with specific attributes.
          - **Heralde.java**: Defines the Heralde character class with specific attributes.
          - **Orven.java**: Defines the Orven character class with specific attributes.
          - **Perrin.java**: Defines the Perrin character class with specific attributes.
          - **Medra.java**: Defines the Medra character class with specific attributes.
          - **Thayen.java**: Defines the Thayen character class with specific attributes.
        - **skill/**: Contains skill-related classes.
          - **Skill.java**: Represents various skills that characters can use.
          - **Effect.java**: Represents the effects of skills on characters.
        - **ui/**: Contains user interface classes.
          - **Menu.java**: Handles the user interface for displaying menus and options.
          - **Display.java**: Manages the display of game information.

- **test/**: Contains unit tests for the game.
  - **java/**: Java test files.
    - **ligaolympica/**: Package for test classes.
      - **CharacterTest.java**: Unit tests for the Character class and its subclasses.
      - **SkillTest.java**: Unit tests for the Skill class.
      - **BattleTest.java**: Unit tests for the Battle class.

## How to Run the Game
1. Ensure you have Java Development Kit (JDK) installed on your machine.
2. Clone the repository or download the source code.
3. Navigate to the `src/main/java/ligaolympica` directory.
4. Compile the Java files using the command:
   ```
   javac Main.java Game.java battle/Battle.java character/*.java skill/*.java ui/*.java
   ```
5. Run the game using the command:
   ```
   java Main
   ```

## Features
- Engaging turn-based battles with various characters.
- Unique skills and effects that influence gameplay.
- A user-friendly interface for navigating the game.

Enjoy your adventure in LigaOlympica!