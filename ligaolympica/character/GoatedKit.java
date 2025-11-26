package ligaolympica.character;
import java.util.*;

public class GoatedKit extends GameCharacter {
    Scanner scan = new Scanner(System.in);

    public GoatedKit() {
        super("Goated Kit", """
                            A legendary warrior blessed by Talona, goddess of disease and poison. Goated Kit is known for unparalleled agility and deadly rabies-inducing attacks.
                            His presence on the battlefield inspires allies and strikes fear into the hearts of enemies.""",
                1200, 500,
                "Skill 1: Kit Kit - A powerful bite that causes diabolic amounts of rabies to the enemy.",
                "Skill 2: Rat Spot - Becomes very elusive, dodging all attacks for 2 turns.",
                "Gods Gift: Talona's Might - Temporarily increase bite power by 50% for 3 turns."
        );
    }

    @Override
    public void skill1(GameCharacter target){
        if(skill1Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " uses KIT KIT!", 30);
        }
        if(this.mana >= 120){
            this.useMana(120);
            this.skill1Cooldown = 1;

            int baseDamage = 300;
            int damage = randomDamage(baseDamage, 20);

            if(this.damageBonus > 1.0) {
                damage = (int)(damage * this.damageBonus);
                typewriter("Enhanced by Talona's Might!", 30);
            }

            target.takeDamage(damage);
            typewriter("Rabies infects " + target.getName() + "!", 30);
            typewriter("Dealt " + damage + " damage!", 10);
        }else{
            typewriter("Not enough mana!", 30);
        }
    }

    @Override
    public void skill2(GameCharacter target) {
        if(skill2Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " uses RAT SPOT!", 30);
        }
        if (this.mana >= 250) {
            this.useMana(250);
            this.skill2Cooldown = 4;

            this.damageBonus = 0.0; // 100% dodge
            this.statusEffectTurns = 2;
            typewriter("Becomes incredibly elusive! Dodging all attacks for 2 turns!", 30);
        } else {
            typewriter("Not enough mana!", 30);
        }
    }

    @Override
    public void skill3(GameCharacter target) {
        if(skill3Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " channels TALONA'S MIGHT!", 10);
        }
        if (this.mana >= 400) {
            this.useMana(400);
            this.skill3Cooldown = 5;

            this.damageBonus = 1.5;
            this.statusEffectTurns = 3;
            typewriter("TALONA'S MIGHT ACTIVATED! +50% bite damage for 3 turns!", 30);
        } else {
            typewriter("Not enough mana!", 30);
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (this.statusEffectTurns > 0 && this.damageBonus == 0.0) {
            typewriter(name + " dodges the attack with Rat Spot!", 10);
            return; // No damage taken
        }
        super.takeDamage(damage);
    }

    @Override
    public void displayStats() {
        if(skill1Cooldown > 0) {
            typewriter("Kit Kit is on cooldown for " + this.skill1Cooldown + " turns.", 10);
        }
        if(skill2Cooldown > 0) {
            typewriter("Rat Spot is on cooldown for " + this.skill2Cooldown + " turns.", 10);
        }
        if(skill3Cooldown > 0) {
            typewriter("Talona's Might is on cooldown for " + this.skill3Cooldown + " turns.", 10);
        }
        System.out.println();
        typewriter(name + " - Health: " + health + "|" + maxHealth + " Mana: " + mana + "/" + maxMana, 10);
    }

    @Override
    public void takeTurn(GameCharacter target) {
        if (this.isStunned) {
            typewriter(name + " is stunned and cannot act!", 30);
            this.isStunned = false; // Stun wears off after missing a turn
            return; // Skip turn
        }
        typewriter("\nChoose a skill for " + name + ":", 30);
        typewriter("1) Kit Kit - 300 Base Damage - CD: " + skill1Cooldown, 30);
        typewriter("2) Rat Spot - Dodge All Attacks for 2 Turns - CD: " + skill2Cooldown, 30);
        typewriter("3) Talona's Might - +50% Damage for 3 Turns - CD: " + skill3Cooldown, 30);

        boolean validChoice = false;
        while (!validChoice) {
            try{
                int choice;
                System.out.print("Enter the number of your choice: ");
                choice = scan.nextInt();
                switch (choice) {
                    case 1 -> {
                        if(skill1Cooldown > 0){
                            typewriter("Skill is on cooldown for " + skill1Cooldown + " more turns!", 5);
                        }else {
                            skill1(target);
                            validChoice = true;
                        }
                    }
                    case 2 -> {
                        if(skill2Cooldown > 0){
                            typewriter("Skill is on cooldown for " + skill2Cooldown + " more turns!", 5);
                        }else {
                            skill2(target);
                            validChoice = true;
                        }
                    }
                    case 3 -> {
                        if(skill3Cooldown > 0){
                            typewriter("Skill is on cooldown for " + skill3Cooldown + " more turns!", 5);
                        }else {
                            skill3(target);
                            validChoice = true;
                        }
                    }
                    default -> {
                        typewriter("Invalid choice.", 10);
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