package ligaolympica.ui;

import java.util.Scanner;
import ligaolympica.battle.Battle;
import ligaolympica.character.Achiron;
import ligaolympica.character.Atalyn;
import ligaolympica.character.GameCharacter;
import ligaolympica.character.Heralde;
import ligaolympica.character.Orris;
import ligaolympica.character.Orven;
import ligaolympica.character.Vor;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    java.util.Random random = new java.util.Random();
    GameCharacter achiron = new Achiron();
    GameCharacter atalyn = new Atalyn();
    GameCharacter vor = new Vor();
    GameCharacter heralde = new Heralde();
    GameCharacter orris = new Orris();
    GameCharacter orven = new Orven();

    private void startBattle(GameCharacter player1, GameCharacter player2) {
        Battle battle = new Battle(player1, player2);
        battle.startBattle();
    }


    public void showMainMenu() {
        boolean running = true;
        while(running) {
            typewriter("Main Menu", 30);
            typewriter("1. Start PvP Game", 30);
            typewriter("2. Start PvAI Game", 30);
            typewriter("3. OOP Demo", 30);
            typewriter("4. Show Characters", 30);
            typewriter("5. Exit", 30);
            System.out.print("Choose an option: ");
            int choice;
            try{
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        startGame();
                    }
                    case 2 -> {
                        startGameAI();
                    }
                    case 3 -> {
                        ligaolympica.demo.OOPDemo.runDemo();
                    }
                    case 4 -> {
                        typewriter("Character List:", 30);
                        typewriter("1. Achiron", 30);
                        typewriter("2. Atalyn", 30);
                        typewriter("3. Heralde", 30);
                        typewriter("4. Vor", 30);
                        typewriter("5. Orris", 30);
                        typewriter("6. Orven", 30);
                        System.out.print("Select a character to view details (1-6): ");

                        int pick = scanner.nextInt();
                        scanner.nextLine();
                        switch (pick) {
                            case 1 -> {
                                typewriter("You selected Achiron!", 30);
                                achiron.showInfo();
                            }
                            case 2 -> {
                                typewriter("You selected Atalyn!", 30);
                                atalyn.showInfo();
                            }
                            case 3 -> {
                                typewriter("You selected Heralde!", 30);
                                heralde.showInfo();
                            }
                            case 4 -> {
                                typewriter("You selected Vor!", 30);
                                vor.showInfo();
                            }
                            case 5 -> {
                                typewriter("You selected Orris!", 30);
                                orris.showInfo();
                            }
                            case 6 -> {
                                typewriter("You selected Orven!", 30);
                                orven.showInfo();
                            }
                            default -> typewriter("Invalid character choice.", 30);
                        }
                    }
                    case 5 -> {
                        typewriter("Exiting game. Goodbye!", 30);
                        running = false;
                    }
                    default -> {
                        typewriter("Invalid choice. Please enter a number between 1 and 5.", 30);
                    }
                }
            }catch (Exception e) {
                typewriter("Invalid input. Please enter a valid number.", 30);
                scanner.nextLine();
            }
        }
    }

    private void startGame() {
        typewriter("\nPlayer 1, select your character:", 5);
        typewriter("1. Achiron", 5);
        typewriter("2. Atalyn", 5);
        typewriter("3. Heralde", 5);
        typewriter("4. Vor", 5);
        typewriter("5. Orris", 5);
        typewriter("6. Orven", 5);
        typewriter("7. Exit to Main Menu", 5);
        typewriter("Choose your character (1-7): ", 5);
        int choice1 = scanner.nextInt();
        scanner.nextLine();
        GameCharacter player1;
        switch (choice1) {
            case 1 -> player1 = new Achiron();
            case 2 -> player1 = new Atalyn();
            case 3 -> player1 = new Heralde();
            case 4 -> player1 = new Vor();
            case 5 -> player1 = new Orris();
            case 6 -> player1 = new Orven();
            case 7 -> {
                typewriter("Returning to Main Menu.", 5);
                return;
            }
            default -> {
                typewriter("Invalid choice. Returning to main menu.", 5);
                return;
            }
        }

        typewriter("\nPlayer 2, select your character:", 5);
        typewriter("1. Achiron", 5);
        typewriter("2. Atalyn", 5);
        typewriter("3. Heralde", 5);
        typewriter("4. Vor", 5);
        typewriter("5. Orris", 5);
        typewriter("6. Orven", 5);
        typewriter("7. Exit to Main Menu", 5);
        typewriter("Choose your character (1-7): ", 5);
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        GameCharacter player2;
        switch (choice2) {
            case 1 -> player2 = new Achiron();
            case 2 -> player2 = new Atalyn();
            case 3 -> player2 = new Heralde();
            case 4 -> player2 = new Vor();
            case 5 -> player2 = new Orris();
            case 6 -> player2 = new Orven();
            case 7 -> {
                typewriter("Returning to Main Menu.", 5);
                return;
            }
            default -> {
                typewriter("Invalid choice. Returning to main menu.", 5);
                return;
            }
        }

        typewriter("\nPlayer 1 selected: ", 10);
        typewriter(player1.getName(), 10);
        typewriter("\nPlayer 2 selected: ", 10);
        typewriter(player2.getName(), 10);
        startBattle(player1, player2);
    }

    // New method: Player vs AI
    private void startGameAI() {
    while (true) {
        typewriter("\nPlayer, select your character:", 5);
        typewriter("1. Achiron", 5);
        typewriter("2. Atalyn", 5);
        typewriter("3. Heralde", 5);
        typewriter("4. Vor", 5);
        typewriter("5. Orris", 5);
        typewriter("6. Orven", 5);
        typewriter("7. Exit to Main Menu", 5);

        GameCharacter player1 = null;
        GameCharacter player2 = null;
        boolean validChoice = false;

        while (!validChoice) {
            try {
                typewriter("Choose your character (1-7): ", 5);
                int choice1 = scanner.nextInt();
                scanner.nextLine(); // clear newline

                switch (choice1) {
                    case 1 -> {
                        player1 = new Achiron();
                        validChoice = true;
                    }

                    case 2 -> {
                        player1 = new Atalyn();
                        validChoice = true;
                    }
                    case 3 -> {
                        player1 = new Heralde();
                        validChoice = true;
                    }
                    case 4 -> {
                        player1 = new Vor();
                        validChoice = true;
                    }
                    case 5 -> {
                        player1 = new Orris();
                        validChoice = true;
                    }
                    case 6 -> {
                        player1 = new Orven();
                        validChoice = true;
                    }
                    case 7 -> {
                        typewriter("Returning to Main Menu.", 5);
                        return;
                    }
                    default -> {
                        typewriter("Invalid choice. Please enter a number between 1 and 7.", 5);
                        scanner.next();
                    }
                }
            } catch (Exception e) {
                typewriter("Invalid input. Please enter a number between 1 and 7.", 5);
                scanner.nextLine();
            }
        }

        // AI selects a random character
        int aiPick = random.nextInt(6) + 1;
        switch (aiPick) {
            case 1 -> player2 = new Achiron();
            case 2 -> player2 = new Atalyn();
            case 3 -> player2 = new Heralde();
            case 4 -> player2 = new Vor();
            case 5 -> player2 = new Orris();
            case 6 -> player2 = new Orven();
        }

        typewriter("\nPlayer selected: ", 10);
        typewriter(player1.getName(), 10);
        typewriter("\nAI selected: ", 10);
        typewriter(player2.getName(), 10);

        // Start battle
        Battle battle = new Battle(player1, player2);
        battle.startBattle(true);
    }
}

    public void typewriter(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}
