package ligaolympica.character;
import java.util.*;

public class Heralde extends GameCharacter {
    Scanner scan = new Scanner(System.in);

    public Heralde(){
        super("Heralde", """
                            Legends say Heralde strangled a lion with his bare hands as a boy.
                            His rage against monsters earned Zeus' favor, but each victory leaves him hungrier for the next challenge.""",
                3500, 450,  "Skill 1  Lion's Strike (90 mana, 2-turn CD). Deals 220 damage single target.",
                            "Skill 2  Iron Hide (100 mana, 3-turn CD). Reduce incoming damage by 30% for 2 turns.",
                            "Skill 3 (God-Gift  Zeus): Thunder Wrath (180 mana, 6-turn CD). Zeus' lightning strikes, 400 true damage."
        );
    }

    @Override
    public void skill1(GameCharacter target){
        if(skill1Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " uses LION'S STRIKE!", 30);
        }
        if(this.mana >= 90){
            this.useMana(90);
            this.skill1Cooldown = 2;

            int baseDamage = 220;
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
            typewriter("\n" + name + " uses IRON HIDE!", 30);
        }
        if(this.mana >= 100){
            this.useMana(100);
            this.skill2Cooldown = 3;

            this.damageBonus = 0.7; // Reduce incoming damage by 30%
            this.statusEffectTurns = 2; // Lasts for 2 turns

            typewriter(name + "'s skin hardens, reducing incoming damage by 30% for 2 turns!", 10);
        }else{
            typewriter("Not enough mana!", 30);
        }
    }
    @Override
    public void skill3(GameCharacter target){
        if(skill3Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " uses THUNDER WRATH!", 30);
        }
        if(this.mana >= 180){
            this.useMana(180);
            this.skill3Cooldown = 6;

            int trueDamage = 400; // True damage ignores defenses

            target.takeDamage(trueDamage);
            typewriter("Zeus' lightning strikes " + target.getName() + " for " + trueDamage + " true damage!", 10);
        }else{
            typewriter("Not enough mana!", 30);
        }
    }
    @Override
    public void takeDamage(int damage) {
        if(this.statusEffectTurns > 0 && this.damageBonus < 1.0){
            damage = (int)(damage * this.damageBonus);
            typewriter(name + "'s Iron Hide reduces the damage to " + damage + "!", 10);
        }
        super.takeDamage(damage);
    }

    @Override
    public void displayStats() {
        if(skill1Cooldown > 0) {
            typewriter("Lion's Strike: is on cooldown for " + this.skill1Cooldown + " turns.", 10);
        }

        if(skill2Cooldown > 0) {
            typewriter("Iron Hide: is on cooldown for " + this.skill2Cooldown + " turns.", 10);
        }

        if(skill3Cooldown > 0) {
            typewriter("Thunder Wrath: is on cooldown for " + this.skill3Cooldown + " turns.", 10);
        }
        System.out.println();
        typewriter(name + " - Health: " + this.health + "/" + this.maxHealth + " | Mana: " + this.mana + "/" + this.maxMana, 10);
    }
    @Override
    public void takeTurn(GameCharacter target) {
        typewriter("\nChoose a skill for " + name + ":", 10);
        typewriter("1) Lion's Strike - 220 Base Damage", 10);
        typewriter("2) Iron Hide - Reduce incoming damage by 30% for 2 turns", 10);
        typewriter("3) Thunder Wrath (God-Gift Zeus) - 400 True Damage", 10);

        boolean validChoice = false;
        while (!validChoice) {
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
                scan.next(); // clear invalid input
            }
        }
    }
}