package ligaolympica.character;
import java.util.*;

public class Orven extends GameCharacter {
    Scanner scan = new Scanner(System.in);

    public Orven() {
        super("Orven", """
                            A demigod son of Hermes, Orven is swift and cunning.
                            His agility in battle is unmatched, and his quick wit often turns the tide of conflict.""",
                2000, 700,  "Skill 1: Swift Strike - A rapid attack that deals 250 damage.",
                            "Skill 2: Vanish - Become untargetable for the next turn.",
                            "Gods Gift: Hermes' Speed - Move so fast that you can attack twice in one turn."
        );
    }

    @Override
    public void skill1(GameCharacter target){
        if(skill1Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " uses SWIFT STRIKE!", 30);
        }
        if(this.mana >= 140){
            this.useMana(140);
            this.skill1Cooldown = 2;

            int baseDamage = 250;
            int damage = randomDamage(baseDamage, 20); // ±20 damage variance

            target.takeDamage(damage);
            typewriter("Dealt " + damage + " damage to " + target.getName() + "!", 10);
        }else{
            typewriter("Not enough mana!", 30);
        }
    }
    @Override
    public void skill2(GameCharacter target){
        if(skill2Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " uses VANISH!", 30);
        }
        if(this.mana >= 120){
            this.useMana(120);
            this.skill2Cooldown = 3;

            this.untargetable = true; // Become untargetable
            this.statusEffectTurns = 1; // Lasts for next turn

            typewriter(name + " vanishes from sight, becoming untargetable for the next turn!", 10);
        }else{
            typewriter("Not enough mana!", 30);
        }
    }
    @Override
    public void skill3(GameCharacter target){
        if(skill3Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " activates HERMES' SPEED!", 30);
        }
        if(this.mana >= 200){
            this.useMana(200);
            this.skill3Cooldown = 5;
            this.extraAttack = true; // Can attack twice in one turn
            typewriter(name + " moves with the speed of Hermes, allowing an extra attack this turn!", 10);
        }else{
            typewriter("Not enough mana!", 30);
        }
    }
    @Override
    public void takeDamage(int damage) {
        if(this.statusEffectTurns > 0 && this.untargetable){
            typewriter(name + " evades the attack using VANISH!", 30);
            return; // No damage taken
        }
        super.takeDamage(damage);
    }
    @Override
    public void displayStats() {
        if(skill1Cooldown > 0){
            typewriter(skill1 + " (Cooldown: " + skill1Cooldown + " turns)\n", 10);
        }
        if(skill2Cooldown > 0){
            typewriter(skill2 + " (Cooldown: " + skill2Cooldown + " turns)\n", 10);
        }
        if(skill3Cooldown > 0){
            typewriter(skill3 + " (Cooldown: " + skill3Cooldown + " turns)\n", 10);
        }
        System.out.println();
        typewriter(name + " - Health: " + health + "|" + maxHealth + ", Mana: " + mana + "/" + maxMana, 10);
    }

    @Override
    public void takeTurn(GameCharacter target){
        typewriter("\nChoose a skill for " + name + ":", 10);
        typewriter("1) Swift Strike - 250 Base Damage", 10);
        typewriter("2) Vanish - Untargetable Next Turn", 10);
        typewriter("3) Hermes' Speed - Extra Attack This Turn", 10);
        boolean validChoice = false;
        while(!validChoice) {
            try{
                int choice;
                if (scan.hasNextInt()) {
                    choice = scan.nextInt();
                } else {
                    choice = random.nextInt(3) + 1;
                }
                switch (choice) {
                    case 1 -> {
                        if(skill1Cooldown > 0) {
                            typewriter("Skill is on cooldown for " + skill1Cooldown + " more turns!", 5);
                        } else {
                            skill1(target);
                            validChoice = true;
                        }
                    }
                    case 2 -> {
                        if(skill2Cooldown > 0) {
                            typewriter("Skill is on cooldown for " + skill2Cooldown + " more turns!", 5);

                        } else {
                            skill2(target);
                            validChoice = true;
                        }
                    }
                    case 3 -> {
                        if(skill3Cooldown > 0) {
                            typewriter("Skill is on cooldown for " + skill3Cooldown + " more turns!", 5);
                        } else {
                            skill3(target);
                            validChoice = true;
                        }
                    }
                    default -> {
                        typewriter("Invalid choice.", 5);
                        scan.next();
                    }
                }
            }catch(Exception e){
                    typewriter("Invalid input. Please enter a number between 1 and 3.", 5);
                    scan.next();
            }
        }
    }
}
