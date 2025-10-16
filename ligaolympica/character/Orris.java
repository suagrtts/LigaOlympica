package ligaolympica.character;
import java.util.*;

public class Orris extends GameCharacter {
    Scanner scan = new Scanner(System.in);

    public Orris() {
        super("Orris", """
                            A demigod son of Poseidon, Orris commands the power of water and storms.
                            His temper is as fierce as the sea, and his loyalty to friends is unwavering.""",
                2500, 600,  "Skill 1: Tidal Wave - Unleash a wave that deals 300 damage to all enemies.",
                            "Skill 2: Ocean's Shield - Create a shield that absorbs 200 damage for 2 turns.",
                            "Gods Gift: Poseidon's Wrath - Summon a devastating tsunami that deals 500 true damage to all enemies."
        );
    }

    @Override
    public void skill1(GameCharacter target){
        if(skill1Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " summons a TIDAL WAVE!", 30);
        }
        if(this.mana >= 130){
            this.useMana(130);
            this.skill1Cooldown = 2;

            int baseDamage = 300;
            int damage = randomDamage(baseDamage, 25); // ±25 damage variance

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
            typewriter("\n" + name + " activates OCEAN'S SHIELD!", 30);
        }
        if(this.mana >= 110){
            this.useMana(110);
            this.skill2Cooldown = 3;

            this.damageBonus = 0.8; // Absorb 20% of incoming damage
            this.statusEffectTurns = 2; // Lasts for 2 turns

            typewriter(name + "'s shield rises, absorbing incoming damage for 2 turns!", 10);
        }else{
            typewriter("Not enough mana!", 30);
        }
    }
    @Override
    public void skill3(GameCharacter target){
        if(skill3Cooldown > 0){
            return;
        }else{
            typewriter("\n" + name + " calls upon POSEIDON'S WRATH!", 30);
        }
        if(this.mana >= 180){
            this.useMana(180);
            this.skill3Cooldown = 6;

            int trueDamage = 500; // True damage ignores defenses

            target.takeTrueDamage(trueDamage);
            typewriter("Dealt " + trueDamage + " true damage to " + target.getName() + "!", 10);
        }else{
            typewriter("Not enough mana!", 30);
        }
    }
    @Override
    public void takeDamage(int damage) {
        // If Ocean's Shield is active, damageBonus will be < 1.0 and statusEffectTurns > 0
        if (this.statusEffectTurns > 0 && this.damageBonus < 1.0) {
            damage = (int)(damage * this.damageBonus);
            typewriter(name + "'s Ocean's Shield absorbs part of the damage, reduced to " + damage + "!", 10);
        }
        super.takeDamage(damage);
    }

    @Override
    public void takeTurn(GameCharacter target) {
        typewriter("\nChoose a skill for " + name + ":", 10);
        typewriter("1) Tidal Wave - 300 Base Damage", 10);
        typewriter("2) Ocean's Shield - Absorb part of incoming damage for 2 turns", 10);
        typewriter("3) Poseidon's Wrath - 500 True Damage", 10);

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