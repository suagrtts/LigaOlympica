package ligaolympica.character;
import java.util.*;

public class Orven extends GameCharacter {
    Scanner scan = new Scanner(System.in);
    private boolean extraAttack = false;

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

            this.damageBonus = 0.0; // Become untargetable
            this.statusEffectTurns = 1;

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
        if(this.statusEffectTurns > 0 && this.damageBonus < 1.0){
            typewriter(name + " evades the attack using VANISH!", 30);
            return; // No damage taken
        }
        super.takeDamage(damage);
    }

    @Override
    public void restoreMana(int amount) {
        this.mana = Math.min(this.mana + amount, this.maxMana);

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
        typewriter("\nChoose a skill for " + name + ":", 30);
        typewriter("1) Skill 1: Swift Strike - 250 Base Damage", 10);
        typewriter("\n 2) Skill 2: Vanish - Become untargetable for the next turn", 10);
        typewriter("\n 3) God's Gift: Hermes' Speed - Attack twice in one turn", 10);
        int choice = scan.nextInt();
        boolean validChoice = false;
        while(!validChoice) {
            switch (choice) {
                case 1 -> {
                    if(skill1Cooldown > 0) {
                        typewriter(skill1 + " is on cooldown for " + this.skill1Cooldown + " more turns. Choose another skill.", 10);
                    } else {
                        validChoice = true;
                        skill1(target);
                    }
                }
                case 2 -> {
                    if(skill2Cooldown > 0) {
                        typewriter(skill2 + " is on cooldown for " + this.skill2Cooldown + " more turns. Choose another skill.", 10);
                    } else {
                        validChoice = true;
                        skill2(target);
                    }
                }
                case 3 -> {
                    if(skill3Cooldown > 0) {
                        typewriter(skill3 + " is on cooldown for " + this.skill3Cooldown + " more turns. Choose another skill.", 10);
                    } else {
                        validChoice = true;
                        skill3(target);
                        if(extraAttack) {
                            extraAttack = false;
                            typewriter(name + " gets an extra attack due to HERMES' SPEED!", 10);
                            skill3(target);
                        }
                    }
                }
                default -> typewriter("Invalid choice. Please select 1, 2, or 3.", 10);
            }
        }
    }
}