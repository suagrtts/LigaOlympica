package ligaolympica.character;
import java.util.*;

public class Vor extends GameCharacter {
    Scanner scan = new Scanner(System.in);
    private boolean nextAttackEvaded = false;


    public Vor() {
        super("Vor", """
                                              Incarnation of Keith, God of Time - Raised in the realms of time where gods dwell, Vor's mastery over time is unparalleled.
                                              Said to be a legend at birth by Keith, he wields the power of time itself.""",
              1700, 1000, "Skill 1: Time Slash - Slashing time that deals 300 damage and ignores 20% of armor.", "Skill 2: Temporal Shift - Stop time to evade the next attack.", "Gods Gift: Chrono Mark - Mark the target, increasing damage dealt by 25% for 2 turns.");
    }

    @Override
    public void skill1(GameCharacter target) {
        if(skill1Cooldown > 0){
            return;
        }else{
        typewriter("\n" + name + " slashes time with a TIME SLASH!", 30);
        }
        if (this.mana >= 150) {
            this.useMana(150);
            this.skill1Cooldown = 1;

            int baseDamage = 300;
            int damage = randomDamage(baseDamage, 20); // ±20 damage variance

            // 20% armor pierce effect
            damage += (int)(damage * 0.20);
            typewriter("Time itself is slashed!", 30);
            typewriter("Dealt " + damage + " damage to " + target.getName() + "!", 10);

            target.takeDamage(damage);
        } else {
            typewriter("Not enough mana!", 30);
        }
    }

    @Override
    public void skill2(GameCharacter target) {
        if(skill2Cooldown > 0){
            return;
        }else{
        typewriter("\n" + name + " activates TEMPORAL SHIFT!", 30);
        }
        if (this.mana >= 120) {
            this.useMana(120);
            this.skill2Cooldown = 2;

            this.nextAttackEvaded = true;
            typewriter("Time freezes momentarily, allowing " + name + " to evade the next attack!", 30);
        } else {
            typewriter("Not enough mana!", 30);
        }
    }

    @Override
    public void skill3(GameCharacter target) {
        if(skill3Cooldown > 0){
            return;
        }else{
        typewriter("\n" + name + " wields the power of time with CHRONO MARK!", 30);
        }
        if (this.mana >= 500) {
            this.useMana(500);
            this.skill3Cooldown = 5;

            this.statusEffectTurns = 2;
            this.damageBonus = 1.5;

            typewriter("KEITH'S LEGEND! Time is marked on the target - damage increased by 25% for 2 turns!", 30);
        } else {
            typewriter("Not enough mana!", 30);
        }
    }
    @Override
    public void displayStats() {
            if(skill1Cooldown > 0) {
                typewriter("Time Slash: is on cooldown for " + this.skill1Cooldown + " turns.", 10);
            }
            if(skill2Cooldown > 0) {
                typewriter("Temporal Shift: is on cooldown for " + this.skill2Cooldown + " turns.", 10);
            }
            if(skill3Cooldown > 0) {
                typewriter("Chrono Mark: is on cooldown for " + this.skill3Cooldown + " turns.", 10);
            }

            System.out.println();
            typewriter(name + " - Health: " + health + "|" + maxHealth + ", Mana: " + mana + "/" + maxMana, 10);
            }

    @Override
    public void takeDamage(int damage) {
        if (nextAttackEvaded) {
            typewriter(this.name + " timely evades the attack!", 30);
            nextAttackEvaded = false;
            return;
        }
        super.takeDamage(damage);
    }

    @Override
    public void restoreMana(int amount) {
        this.mana = Math.min(this.mana + amount, this.maxMana);

    }

    @Override
    public void takeTurn(GameCharacter target) {
        typewriter("\nChoose a skill for " + name + ":", 10);
        typewriter("1) Time Slash - 300 Base Damage", 10);
        typewriter("2) Temporal Shift - Evade Next Attack", 10);
        typewriter("3) Chrono Mark - Increase Damage by 25%", 10);

        boolean validChoice = false;
        while (!validChoice) {
            try {
                int choice;
                if (scan.hasNextInt()) {
                    choice = scan.nextInt();
                    scan.nextLine(); // clear trailing newline
                } else {
                    choice = random.nextInt(3) + 1;
                    scan.next(); // clear invalid input
                }

                switch (choice) {
                    case 1 -> {
                        if (skill1Cooldown > 0) {
                            typewriter("Skill is on cooldown for " + skill1Cooldown + " more turns!", 5);
                        } else {
                            skill1(target);
                            validChoice = true;
                        }
                    }
                    case 2 -> {
                        if (skill2Cooldown > 0) {
                            typewriter("Skill is on cooldown for " + skill2Cooldown + " more turns!", 5);
                        } else {
                            skill2(target);
                            validChoice = true;
                        }
                    }
                    case 3 -> {
                        if (skill3Cooldown > 0) {
                            typewriter("Skill is on cooldown for " + skill3Cooldown + " more turns!", 5);
                        } else {
                            skill3(target);
                            validChoice = true;
                        }
                    }
                    default -> {
                        typewriter("Invalid choice. Please enter 1, 2, or 3.", 5);
                        scan.next(); // clear invalid input
                    }
                }
            } catch (Exception e) {
                typewriter("Invalid input. Please enter a number between 1 and 3.", 5);
                scan.next(); // clear invalid input
            }
        }
    }
}

