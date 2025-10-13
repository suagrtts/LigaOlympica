package ligaolympica.battle;

import ligaolympica.character.GameCharacter;

public class Battle {
    private final GameCharacter player1;
    private final GameCharacter player2;

    public Battle(GameCharacter player1, GameCharacter player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startBattle() {
        startBattle(false);
    }

    // New overload: if player2IsComputer is true, player2 will use autoTakeTurn
    public void startBattle(boolean player2IsComputer) {
        System.out.println("\nBattle Start: " + player1.getName() + " vs " + player2.getName());

        GameCharacter attacker = player1;
        GameCharacter defender = player2;
        int turn = 1;
        int maxTurns = 40;

        while (player1.isAlive() && player2.isAlive() && turn <= maxTurns) {
            System.out.println("\n--- Turn " + turn + " ---");
            typewriter(attacker.getName() + "'s turn!", 10);

            // Update effects at start of turn
            attacker.updateTurnEffects();
            defender.updateTurnEffects();

            // Restore some mana at start of turn (reduced from 150 to 100 for better balance)
            attacker.restoreMana(100);

            // Character acts based on whether they're computer-controlled
            boolean isComputerTurn = (attacker == player2 && player2IsComputer);
            if (isComputerTurn) {
                attacker.autoTakeTurn(defender);
            } else {
                attacker.takeTurn(defender);
            }

            // Show battle status
            System.out.println();
            typewriter("Battle Status:", 10);
            typewriter(player1.getName() + " - HP: " + player1.getHealth() + "/" + player1.getMaxHealth() + 
                     " | MP: " + player1.getMana() + "/" + player1.getMaxMana(), 10);
            typewriter(player2.getName() + " - HP: " + player2.getHealth() + "/" + player2.getMaxHealth() + 
                     " | MP: " + player2.getMana() + "/" + player2.getMaxMana(), 10);
            
            if (attacker.getHealth() > 0) {
                attacker.displayStats();
            }

            // Swap attacker/defender
            GameCharacter temp = attacker;
            attacker = defender;
            defender = temp;

            turn++;
        }

        if (turn > maxTurns) {
            typewriter("\nThe battle ended in a draw!", 10);
        } else if (player1.isAlive()) {
            typewriter("\n" + player1.getName() + " wins!", 10);
        } else {
            typewriter("\n" + player2.getName() + " wins!", 10);
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