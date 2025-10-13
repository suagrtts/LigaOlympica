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
    GameCharacter achiron = new Achiron();
    GameCharacter atalyn = new Atalyn();
    GameCharacter vor = new Vor();
    GameCharacter heralde = new Heralde();
    GameCharacter orris = new Orris();
    GameCharacter orven = new Orven();


    public void showMainMenu() {
        Boolean running = true;
        while(running) {
            typewriter("Main Menu", 30);
            typewriter("1. Start Game", 30);
            typewriter("2. Show Characters", 30);
            typewriter("3. Exit", 30);
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> startGame();
                case 2 -> {
                    typewriter("Character List:", 30);
                    typewriter("1. Achiron", 30);
                    typewriter("2. Atalyn", 30);
                    typewriter("3. Heralde", 30);
                    typewriter("4. Orris", 30);
                    typewriter("5. Orven", 30);
                    typewriter("6. Vor", 30);
                    System.out.print("Select a character to view details (1-6): ");
                    int pick = scanner.nextInt();
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
                        typewriter("You selected Orris!", 30);
                        orris.showInfo();
                    }
                    case 5 -> {
                        typewriter("You selected Orven!", 30);
                        orven.showInfo();
                    }
                    case 6 -> {
                        typewriter("You selected Vor!", 30);
                        vor.showInfo();
                    }
                    default -> typewriter("Invalid character choice.", 30);
                }
                }
                case 3 -> {
                    typewriter("Exiting game. Goodbye!", 30);
                    running = false;
                }
                default -> {
                    typewriter("Invalid choice. Please try again.", 30);
                    showMainMenu();
                }
            }
        }
    }

    private void startGame() {
        typewriter("Choose your game mode:", 5);
        typewriter("1. Player vs Player", 5);
        typewriter("2. Player vs Computer", 5);
        typewriter("Enter your choice (1 or 2): ", 5);
        int gameMode = scanner.nextInt();


        typewriter("\nPlayer 1, select your character:", 5);
        typewriter("1. Achiron", 5);
        typewriter("2. Atalyn", 5);
        typewriter("3. Heralde", 5);
        typewriter("4. Orris", 5);
        typewriter("5. Orven", 5);
        typewriter("6. Vor", 5);
        typewriter("Choose your character (1-6): ", 5);
        int choice1 = scanner.nextInt();
        GameCharacter player1;
        switch (choice1) {
            case 1 -> player1 = new Achiron();
            case 2 -> player1 = new Atalyn();
            case 3 -> player1 = new Heralde();
            case 4 -> player1 = new Orris();
            case 5 -> player1 = new Orven();
            case 6 -> player1 = new Vor();

            default -> {
                typewriter("Invalid choice. Returning to main menu.", 5);
                return;
            }
        }

        GameCharacter player2;
        if (gameMode == 2) {  // PvC mode
            // Computer randomly selects a character
            int computerChoice = (int)(Math.random() * 6) + 1;
            switch (computerChoice) {
                case 1 -> player2 = new Achiron();
                case 2 -> player2 = new Atalyn();
                case 3 -> player2 = new Heralde();
                case 4 -> player2 = new Orris();
                case 5 -> player2 = new Orven();
                case 6 -> player2 = new Vor();
                default -> {
                    typewriter("Error in computer selection. Returning to main menu.", 5);
                    return;
                }
            }
            typewriter("\nComputer selected: " + player2.getName(), 5);
        } else {  // PvP mode
            typewriter("\nPlayer 2, select your character:", 5);
            typewriter("1. Achiron", 5);
            typewriter("2. Atalyn", 5);
            typewriter("3. Heralde", 5);
            typewriter("4. Orris", 5);
            typewriter("5. Orven", 5);
            typewriter("6. Vor", 5);
            typewriter("Choose your character (1-6): ", 5);
            int choice2 = scanner.nextInt();
            
            switch (choice2) {
                case 1 -> player2 = new Achiron();
                case 2 -> player2 = new Atalyn();
                case 3 -> player2 = new Heralde();
                case 4 -> player2 = new Orris();
                case 5 -> player2 = new Orven();
                case 6 -> player2 = new Vor();
                default -> {
                    typewriter("Invalid choice. Returning to main menu.", 5);
                    return;
                }
            }
        }

        typewriter("\nPlayer 1 selected: " + player1.getName(), 10);
        typewriter("Player 2 selected: " + player2.getName(), 10);
        
        // Start battle with appropriate mode
        Battle battle = new Battle(player1, player2);
        if (gameMode == 2) {
            battle.startBattle(true);  // PvC mode
        } else {
            battle.startBattle(false); // PvP mode
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
