g
# **Dungeon Adventure**


## **Table of Contents**
- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [How to Play](#how-to-play)
    - [Commands](#commands)
- [Game Mechanics](#game-mechanics)
- [Contributing](#contributing)
- [License](#license)

## **Introduction**

**Dungeon Adventure** is a text-based Java game developed as a ** project for the Advanced Object-Oriented Programming (OOP) module** in the **3rd year** at Anglia Ruskin University. This game challenges players to navigate through a mysterious dungeon filled with treasures, traps, and magical items. Explore different rooms, collect items, cast spells, and overcome various challenges to achieve victory!

## **Features**

- **Command Parsing:** Intuitive command system allowing players to interact with the game world using natural language inputs.
- **Inventory Management:** Pick up, drop, use, eat, and drink various items to aid your adventure.
- **Magical Items:** Discover and utilize potions and spells with unique and hidden effects.
- **Dynamic Environment:** Room contents are randomly generated to keep each playthrough unique.
- **Status Tracking:** Monitor your Power Points and other vital stats as you progress through the dungeon.

## **Getting Started**

### **Prerequisites**

- **Java Development Kit (JDK):** Ensure you have JDK 8 or higher installed on your system.
- **IDE (Optional):** An Integrated Development Environment like IntelliJ IDEA, Eclipse, or VS Code can enhance your development experience.

### **Installation**

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/dungeon-adventure.git
   ```
2. **Navigate to the Project Directory:**
   ```bash
   cd dungeon-adventure
   ```
3. **Compile the Game:**

    *For Unix/Linux/macOS*
   ```bash
   javac -d bin -sourcepath src $(find src -name "*.java")
   ```
4. **Run Tests (Optional):**
   ```bash
    java -cp bin Run all
   ```
5. **Run the Game:**
   ```bash
   java -cp bin com.university.Main
   ```

*Alternatively, you can import the project into your preferred IDE and run the `Main` class directly.*

## **How to Play**

Upon starting the game, you'll find yourself in the entrance hall of a dark dungeon. Your objective is to explore the dungeon, collect valuable items, cast spells, and ultimately find the hidden treasure to win the game.

### **Commands**

Interact with the game using the following commands. You can type commands in natural language, and the parser will interpret your intentions.

- **Movement:**
    - `move north`
    - `go south`
    - `walk east`

  *Note: you can use forward, left, right, back*

- **Item Interaction:**
    - `pick up the cake`
    - `drop ancient sword`
    - `use healing potion`
    - `eat sandwich`
    - `drink potion`

- **Spell Casting:**
    - `use teleportation spell`
    - `cast frozen spell`

- **Environment Interaction:**
    - `look around`

  *Note: you can use spanner to open chest. Item from chest will be added to room contents.*

- **Inventory and Status:**
    - `inventory`
    - `show inventory`
    - `status`
    - `show status`

- **Game Controls:**
    - `help`
    - `quit`
    - `exit`

*Note: The parser ignores dummy words like "a," "the," and "an" to focus on meaningful commands.*

## **Game Mechanics**

### **Inventory Management**

- **Picking Up Items:** Collect items from rooms to add them to your inventory.
- **Dropping Items:** Remove items from your inventory and leave them in the current room.
- **Using Items:** Utilize items like potions and tools to aid your adventure.
- **Eating and Drinking:** Consume consumable items to restore health or gain other benefits.

### **Magical Items and Spells**

- **Potions:**
    - **X-ray Vision Potion:** Allows you to see into a random adjacent room without entering it.
    - **Sleeping Potion:** Puts you to sleep for a turn and costs 10 Power Points unless negated by an Alarm Clock.

- **Spells:**
    - **Teleportation Spell:** Instantly moves you to a random room within the dungeon.
    - **Freeze Spell:** Allows you to escape Mad Scientists and Trap without losing points.

*Note: The types of potions are hidden. Use them at your own risk!*

### **Player Status**

- **Power Points:** Represents your energy and capabilities. Certain actions consume Power Points.

## **Contributing**

1. **Fork the Repository**
2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/YourFeature
   ```
3. **Commit Your Changes**
   ```bash
   git commit -m "Add Your Feature"
   ```
4. **Push to the Branch**
   ```bash
   git push origin feature/YourFeature
   ```
5. **Open a Pull Request**

Please ensure your code adheres to the project's coding standards and includes appropriate comments and documentation.

## **License**

Distributed under the [MIT License](LICENSE). See `LICENSE` for more information.

---