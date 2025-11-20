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
            System.out.println(attacker.getName() + "'s turn!");

            // Character acts: if defender is player2 and player2IsComputer is true, use autoTakeTurn for that character when they act
            if (attacker == player2 && player2IsComputer) {
                attacker.autoTakeTurn(defender);
            } else {
                attacker.takeTurn(defender);
            }

            // Update cooldowns and buffs
            attacker.updateTurnEffects();

            // Show status
            attacker.displayStats();
            System.out.println();

            typewriter(player1.getName() + " - HP: " + player1.getHealth() + "/" + player1.getMaxHealth() + " | " + player2.getName() + " - HP: " + player2.getHealth() + "/" + player2.getMaxHealth(), 10);
            // Both characters regain some mana each turn
            attacker.restoreMana(150);
            defender.restoreMana(150);

            // Swap attacker/defender
            GameCharacter temp = attacker;
            attacker = defender;
            defender = temp;

            turn++;
        }

        if (turn > maxTurns) {
            typewriter("\nThe battle ended in a draw!", 10);
        } else if (player1.isAlive()) {
            typewriter("\n╔════════════════════════════╗", 0);
            typewriter("   \t" + player1.getName() + " wins!        ", 0);
            typewriter("╚════════════════════════════╝", 0);
            System.out.println();
        } else {
            typewriter("\n╔════════════════════════════╗", 0);
            typewriter("   \t" + player2.getName() + " wins!        ", 0);
            typewriter("╚════════════════════════════╝", 0);
            System.out.println();
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