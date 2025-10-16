package ligaolympica.ui;

import java.util.Scanner;
import java.util.Random;
import ligaolympica.battle.Battle;
import ligaolympica.character.Achiron;
import ligaolympica.character.Atalyn;
import ligaolympica.character.GameCharacter;
import ligaolympica.character.Heralde;
import ligaolympica.character.Orris;
import ligaolympica.character.Orven;
import ligaolympica.character.Vor;

public class Menu {
    private final Scanner scanner;
    private final Random random;
    private final GameCharacter achiron;
    private final GameCharacter atalyn;
    private final GameCharacter vor;
    private final GameCharacter heralde;
    private final GameCharacter orris;
    private final GameCharacter orven;

    public Menu() {
        scanner = new Scanner(System.in);
        random = new Random();
        achiron = new Achiron();
        atalyn = new Atalyn();
        vor = new Vor();
        heralde = new Heralde();
        orris = new Orris();
        orven = new Orven();
    }

    private void startBattle(GameCharacter player1, GameCharacter player2) {
        Battle battle = new Battle(player1, player2);
        battle.startBattle();
    }


    public void showMainMenu() {
        boolean running = true;
        while (running) {
            try {
                typewriter("Main Menu", 30);
                typewriter("1. Start PvP Game", 30);
                typewriter("2. Start PvAI Game", 30);
                typewriter("3. Show Characters", 30);
                typewriter("4. Exit", 30);
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        startGame();
                        break;
                    case 2:
                        startGameAI();
                        break;
                    case 3:
                        showCharacterInfo();
                        break;
                    case 4:
                        typewriter("Exiting game. Goodbye!", 30);
                        running = false;
                        break;
                    default:
                        typewriter("Invalid choice. Please enter a number between 1 and 4.", 30);
                }
            } catch (Exception e) {
                typewriter("Invalid input. Please enter a valid number.", 30);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private void showCharacterInfo() {
        try {
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
                case 1:
                    typewriter("You selected Achiron!", 30);
                    achiron.showInfo();
                    break;
                case 2:
                    typewriter("You selected Atalyn!", 30);
                    atalyn.showInfo();
                    break;
                case 3:
                    typewriter("You selected Heralde!", 30);
                    heralde.showInfo();
                    break;
                case 4:
                    typewriter("You selected Vor!", 30);
                    vor.showInfo();
                    break;
                case 5:
                    typewriter("You selected Orris!", 30);
                    orris.showInfo();
                    break;
                case 6:
                    typewriter("You selected Orven!", 30);
                    orven.showInfo();
                    break;
                default:
                    typewriter("Invalid character choice.", 30);
            }
        } catch (Exception e) {
            typewriter("Invalid input. Please enter a number between 1 and 6.", 30);
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private void startGame() {
        GameCharacter player1 = selectCharacter(1);
        if (player1 == null) return;

        GameCharacter player2 = selectCharacter(2);
        if (player2 == null) return;

        typewriter("\nPlayer 1 selected: ", 10);
        typewriter(player1.getName(), 10);
        typewriter("\nPlayer 2 selected: ", 10);
        typewriter(player2.getName(), 10);
        startBattle(player1, player2);
    }

    private GameCharacter selectCharacter(int playerNumber) {
        while (true) {
            try {
                typewriter("\nPlayer " + playerNumber + ", select your character:", 5);
                typewriter("1. Achiron", 5);
                typewriter("2. Atalyn", 5);
                typewriter("3. Heralde", 5);
                typewriter("4. Vor", 5);
                typewriter("5. Orris", 5);
                typewriter("6. Orven", 5);
                typewriter("7. Exit to Main Menu", 5);
                typewriter("Choose your character (1-7): ", 5);
                
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        return new Achiron();
                    case 2:
                        return new Atalyn();
                    case 3:
                        return new Heralde();
                    case 4:
                        return new Vor();
                    case 5:
                        return new Orris();
                    case 6:
                        return new Orven();
                    case 7:
                        typewriter("Returning to Main Menu.", 5);
                        return null;
                    default:
                        typewriter("Invalid choice. Please enter a number between 1 and 7.", 5);
                }
            } catch (Exception e) {
                typewriter("Invalid input. Please enter a number between 1 and 7.", 5);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private void startGameAI() {
        GameCharacter player = selectCharacter(1); // Player selection
        if (player == null) return;

        // AI selects a random character
        GameCharacter ai = null;
        int aiPick = random.nextInt(6) + 1;
        switch (aiPick) {
            case 1:
                ai = new Achiron();
                break;
            case 2:
                ai = new Atalyn();
                break;
            case 3:
                ai = new Heralde();
                break;
            case 4:
                ai = new Vor();
                break;
            case 5:
                ai = new Orris();
                break;
            case 6:
                ai = new Orven();
                break;
        }

        assert ai != null; // AI selection is guaranteed by random range
        typewriter("\nPlayer selected: ", 10);
        typewriter(player.getName(), 10);
        typewriter("\nAI selected: ", 10);
        typewriter(ai.getName(), 10);

        // Start battle
        Battle battle = new Battle(player, ai);
        battle.startBattle(true);
    }

    public void typewriter(String text, int delay) {
        if (text == null) return;
        
        StringBuilder output = new StringBuilder();
        for (char c : text.toCharArray()) {
            output.append(c);
            System.out.print(c);
            try {
                Thread.sleep(Math.min(delay, 50)); // Cap the delay to avoid too slow output
            } catch (InterruptedException e) {
                // Print the rest of the text immediately if interrupted
                System.out.print(text.substring(output.length()));
                System.out.println();
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println();
    }
}
