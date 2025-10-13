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
            // Directly reduce target's health to bypass any damage reduction or evasion
            target.health = Math.max(0, target.health - trueDamage);
            if(target.health <= 0) {
                target.isAlive = false;
            }
            
            typewriter("POSEIDON'S WRATH deals " + trueDamage + " TRUE damage to " + target.getName() + "!", 10);
            if(!target.isAlive) {
                typewriter(target.getName() + " has been defeated by the power of the seas!", 10);
            }
        }else{
            typewriter("Not enough mana!", 30);
        }
    }
    @Override
    public void takeDamage(int damage){
        if(this.statusEffectTurns > 0){
            damage = (int)(damage * this.damageBonus);
            typewriter(name + "'s Ocean's Shield absorbs some damage! Reduced to " + damage + " damage.", 10);
            this.statusEffectTurns--;
            if(this.statusEffectTurns == 0){
                this.damageBonus = 1.0; // Reset damage bonus after effect ends
                typewriter(name + "'s Ocean's Shield has worn off.", 10);
            }
        }
        super.takeDamage(damage);
    }

    @Override
    public void restoreMana(int amount) {
        this.mana = Math.min(this.mana + amount, this.maxMana);

    }

    @Override
    public void displayStats() {
        if(skill1Cooldown > 0) {
            typewriter("Tidal Wave: is on cooldown for " + this.skill1Cooldown + " turns.", 10);
        }
        if(skill2Cooldown > 0) {
            typewriter("Ocean's Shield: is on cooldown for " + this.skill2Cooldown + " turns.", 10);
        }
        if(skill3Cooldown > 0) {
            typewriter("Poseidon's Wrath: is on cooldown for " + this.skill3Cooldown + " turns.", 10);
        }
        System.out.println();
        typewriter(name + " - Health: " + health + "|" + maxHealth + " Mana: " + mana + "/" + maxMana, 10);
    }

    @Override
    public void takeTurn(GameCharacter target){
        typewriter("\nChoose a skill for " + name + ":", 10);
        typewriter("1) Skill 1: Tidal Wave - 300 Base Damage", 10);
        typewriter("2) Skill 2: Ocean's Shield - Absorb 20% damage for 2 turns", 10);
        typewriter("3) God's Gift: Poseidon's Wrath - 500 True Damage", 10);
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
                    }
                }
                default -> {
                    typewriter("Invalid choice. Please select 1, 2, or 3.", 10);
                    choice = scan.nextInt();
                }
            }
        }
    }


}