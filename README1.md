/* import java.util.*;

// Utility class for damage calculations
class DamageCalculator {
    private static Random random = new Random();
    
    public static int calculateDamage(int baseDamage) {
        // Add randomization: 80% to 120% of base damage
        double multiplier = 0.8 + (random.nextDouble() * 0.4); // 0.8 to 1.2
        return (int) Math.round(baseDamage * multiplier);
    }
    
    public static int calculateCriticalDamage(int baseDamage) {
        // Critical hit: 150% to 200% damage
        double multiplier = 1.5 + (random.nextDouble() * 0.5); // 1.5 to 2.0
        return (int) Math.round(baseDamage * multiplier);
    }
    
    public static boolean isCriticalHit() {
        return random.nextInt(100) < 15; // 15% critical hit chance
    }
}

// Skill class to represent hero abilities
class Skill {
    private String name;
    private int cost;
    private int cooldown;
    private int damage;
    private int heal;
    private String type;
    private Map<String, Integer> effects;
    private int currentCooldown;
    
    public Skill(String name, int cost, int cooldown, int damage, int heal, String type) {
        this.name = name;
        this.cost = cost;
        this.cooldown = cooldown;
        this.damage = damage;
        this.heal = heal;
        this.type = type;
        this.effects = new HashMap<>();
        this.currentCooldown = 0;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public int getCost() { return cost; }
    public int getCooldown() { return cooldown; }
    public int getDamage() { return damage; }
    public int getHeal() { return heal; }
    public String getType() { return type; }
    public int getCurrentCooldown() { return currentCooldown; }
    public void setCurrentCooldown(int cooldown) { this.currentCooldown = cooldown; }
    public void reduceCooldown() { if (currentCooldown > 0) currentCooldown--; }
    public boolean isReady() { return currentCooldown == 0; }
    
    public void addEffect(String effect, int value) {
        effects.put(effect, value);
    }
    
    public Map<String, Integer> getEffects() { return effects; }
}

// Abstract Character class - parent class for all heroes
abstract class Character {
    protected String name;
    protected String title;
    protected String backstory;
    protected int maxHp;
    protected int hp;
    protected int maxMana;
    protected int mana;
    protected String role;
    protected String god;
    protected List<Skill> skills;
    protected Map<String, Integer> statusEffects;
    
    public Character(String name, String title, String backstory, int hp, int mana, String role, String god) {
        this.name = name;
        this.title = title;
        this.backstory = backstory;
        this.maxHp = hp;
        this.hp = hp;
        this.maxMana = mana;
        this.mana = mana;
        this.role = role;
        this.god = god;
        this.skills = new ArrayList<>();
        this.statusEffects = new HashMap<>();
        initializeSkills(); // Each child class will define their own skills
    }
    
    // Abstract method that each hero must implement
    protected abstract void initializeSkills();
    
    // Common methods for all characters
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }
    
    public void heal(int healAmount) {
        hp = Math.min(maxHp, hp + healAmount);
    }
    
    public void useMana(int amount) {
        mana = Math.max(0, mana - amount);
    }
    
    public void restoreMana(int amount) {
        mana = Math.min(maxMana, mana + amount);
    }
    
    public boolean isAlive() {
        return hp > 0;
    }
    
    public void reduceCooldowns() {
        for (Skill skill : skills) {
            skill.reduceCooldown();
        }
    }
    
    // Display methods
    public void displayInfo() {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ " + name + " - " + title);
        System.out.println("║ God: " + god + " | Role: " + role);
        System.out.println("║ HP: " + hp + "/" + maxHp + " | MP: " + mana + "/" + maxMana);
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }
    
    public void displayBackstory() {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ " + name + " - " + title);
        System.out.println("║ Blessed by: " + god);
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        String[] lines = backstory.split("(?<=\\.) ");
        for (String line : lines) {
            System.out.println("║ " + line.trim());
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }
    
    public void displaySkills() {
        System.out.println("\n" + name + "'s Skills:");
        for (int i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            String status = skill.isReady() ? "READY" : "CD: " + skill.getCurrentCooldown();
            String godGift = (i == 2) ? " [GOD-GIFT]" : "";
            System.out.printf("%d. %s (Cost: %d MP) - %s%s\n", 
                i + 1, skill.getName(), skill.getCost(), status, godGift);
        }
    }
    
    // Reset hero to full health/mana
    public void reset() {
        hp = maxHp;
        mana = maxMana;
        for (Skill skill : skills) {
            skill.setCurrentCooldown(0);
        }
        statusEffects.clear();
    }
    
    // Getters
    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getBackstory() { return backstory; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getMana() { return mana; }
    public int getMaxMana() { return maxMana; }
    public String getRole() { return role; }
    public String getGod() { return god; }
    public List<Skill> getSkills() { return skills; }
}

// Individual Hero Classes
class Achiron extends Character {
    public Achiron() {
        super("Achiron", "The Unyielding Warrior", 
            "A descendant of Achilles, Achiron was trained by old warlords who believed pain forged immortality. " +
            "Scarred in a hundred duels, he refuses to bow, claiming even the gods cannot break his will.",
            1800, 300, "Warrior/Bruiser", "Ares");
    }
    
    @Override
    protected void initializeSkills() {
        skills.add(new Skill("Spear Thrust", 80, 1, 200, 0, "attack"));
        skills.add(new Skill("Shield Bash", 100, 3, 120, 0, "attack"));
        skills.add(new Skill("Wrath of Ares", 150, 5, 0, 0, "buff"));
    }
}

class Atalyn extends Character {
    public Atalyn() {
        super("Atalyn", "Huntress of Artemis",
            "Raised in forests where mortals fear to tread, Atalyn's arrow never misses. " +
            "Said to be blessed at birth by Artemis, she hunts not for food, but for perfection in the chase.",
            1500, 350, "Ranged DPS", "Artemis");
    }
    
    @Override
    protected void initializeSkills() {
        skills.add(new Skill("Piercing Arrow", 70, 2, 160, 0, "attack"));
        skills.add(new Skill("Hunter's Reflex", 80, 2, 0, 0, "evade"));
        skills.add(new Skill("Moonlit Mark", 140, 5, 0, 0, "team_buff"));
    }
}

class Orris extends Character {
    public Orris() {
        super("Orris", "The Cunning Tactician",
            "A sailor who returned from Troy with more tales than scars, Orris fights with wit as much as steel. " +
            "Where others charge blindly, he calculates ten moves ahead.",
            1400, 400, "Control/Utility", "Athena");
    }
    
    @Override
    protected void initializeSkills() {
        skills.add(new Skill("Trick Shot", 60, 2, 150, 0, "attack"));
        skills.add(new Skill("Tactical Trap", 90, 3, 150, 0, "attack"));
        skills.add(new Skill("Wisdom's Gambit", 160, 6, 0, 0, "reflect"));
    }
}

class Heralde extends Character {
    public Heralde() {
        super("Heralde", "The Titan-Slayer",
            "Legends say Heralde strangled a lion with his bare hands as a boy. " +
            "His rage against monsters earned Zeus' favor, but each victory leaves him hungrier for the next challenge.",
            2000, 250, "Tanky Fighter", "Zeus");
    }
    
    @Override
    protected void initializeSkills() {
        skills.add(new Skill("Lion's Strike", 90, 2, 220, 0, "attack"));
        skills.add(new Skill("Iron Hide", 100, 3, 0, 0, "defense"));
        skills.add(new Skill("Thunder Wrath", 180, 6, 400, 0, "ultimate"));
    }
}

class Orven extends Character {
    public Orven() {
        super("Orven", "Bard of the Underworld",
            "Orven once descended into Hades armed with only his lyre. Though he failed to rescue his love, " +
            "his songs moved even the dead. Now, his music heals the living and haunts the damned.",
            1200, 500, "Support/Healer", "Apollo");
    }
    
    @Override
    protected void initializeSkills() {
        skills.add(new Skill("Courageous Hymn", 80, 2, 0, 0, "buff"));
        skills.add(new Skill("Healing Melody", 100, 2, 0, 180, "heal"));
        skills.add(new Skill("Radiant Song", 200, 6, 0, 999, "mass_heal"));
    }
}

class Perrin extends Character {
    public Perrin() {
        super("Perrin", "Gorgon Slayer",
            "Armed with a mirrored shield, Perrin stood against the Gorgon and triumphed. " +
            "Ever since, he has walked the battlefield with Hermes' swiftness, striking before enemies even know fear.",
            1600, 320, "Agile Fighter", "Hermes");
    }
    
    @Override
    protected void initializeSkills() {
        skills.add(new Skill("Swift Strike", 60, 1, 180, 0, "attack"));
        skills.add(new Skill("Guardian's Shield", 90, 3, 0, 0, "block"));
        skills.add(new Skill("Wings of Hermes", 150, 5, 0, 0, "speed"));
    }
}

class Medra extends Character {
    public Medra() {
        super("Medra", "Sorceress of Colchis",
            "Exiled for forbidden magic, Medra carved power from betrayal and flame. " +
            "Whispers say Hecate herself walks beside her, feeding her the tongues of shadow and fire.",
            1300, 480, "Mage/Debuffer", "Hecate");
    }
    
    @Override
    protected void initializeSkills() {
        skills.add(new Skill("Fire Hex", 80, 2, 150, 0, "attack"));
        skills.add(new Skill("Binding Curse", 100, 3, 0, 0, "debuff"));
        skills.add(new Skill("Witchfire Eclipse", 180, 5, 250, 0, "aoe"));
    }
}

class Thayen extends Character {
    public Thayen() {
        super("Thayen", "Champion of Athens",
            "Once a prince, Thayen descended into the Labyrinth to slay the Minotaur. " +
            "His triumph turned him into a symbol of Athens — a leader who wields both blade and destiny.",
            1700, 350, "Balanced Fighter", "Poseidon");
    }
    
    @Override
    protected void initializeSkills() {
        skills.add(new Skill("Labyrinth Strike", 80, 2, 200, 0, "attack"));
        skills.add(new Skill("Athenian Resolve", 90, 3, 0, 0, "team_defense"));
        skills.add(new Skill("Ocean's Fury", 160, 5, 300, 0, "aoe"));
    }
}

// OP Test Character
class Chronos extends Character {
    public Chronos() {
        super("Chronos", "Master of Time and Destruction",
            "A being that transcends mortal understanding, Chronos exists outside of time itself. " +
            "Created by the gods as the ultimate test for heroes, he wields power that can reshape reality. " +
            "Legends say he was banished to the deepest realms for his overwhelming might, only emerging to test the most worthy champions.",
            5000, 1000, "Godlike/Destroyer", "All Gods");
    }
    
    @Override
    protected void initializeSkills() {
        skills.add(new Skill("Temporal Strike", 50, 1, 800, 0, "attack"));
        skills.add(new Skill("Reality Shatter", 100, 2, 1200, 0, "attack"));
        skills.add(new Skill("End of Time", 200, 3, 2000, 0, "ultimate"));
    }
}

// Battle system class
class BattleSystem {
    private Character player1;
    private Character player2;
    private Scanner scanner;
    private List<String> battleLog;
    
    public BattleSystem() {
        scanner = new Scanner(System.in);
        battleLog = new ArrayList<>();
    }
    
    public void startBattle(Character h1, Character h2) {
        player1 = h1;
        player2 = h2;
        battleLog.clear();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                           ⚔️  BATTLE BEGINS!  ⚔️");
        System.out.println("=".repeat(80));
        System.out.println(player1.getName() + " VS " + player2.getName());
        System.out.println("=".repeat(80));
        
        boolean playerTurn = true;
        
        while (player1.isAlive() && player2.isAlive()) {
            if (playerTurn) {
                playerTurn(player1, player2);
            } else {
                aiTurn(player2, player1);
            }
            playerTurn = !playerTurn;
            
            // Mana regeneration
            player1.restoreMana(50);
            player2.restoreMana(50);
            
            // Cooldown reduction
            player1.reduceCooldowns();
            player2.reduceCooldowns();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        announceWinner();
    }
    
    private void playerTurn(Character attacker, Character defender) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           " + attacker.getName() + "'s Turn");
        System.out.println("=".repeat(50));
        
        displayBattleStatus();
        attacker.displaySkills();
        
        System.out.print("\nChoose your action (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (choice >= 1 && choice <= 3) {
            useSkill(attacker, defender, choice - 1);
        } else {
            System.out.println("Invalid choice! Turn skipped.");
        }
    }
    
    private void aiTurn(Character attacker, Character defender) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           " + attacker.getName() + "'s Turn (AI)");
        System.out.println("=".repeat(50));
        
        // Simple AI: try to use most powerful available skill
        List<Integer> availableSkills = new ArrayList<>();
        for (int i = 0; i < attacker.getSkills().size(); i++) {
            Skill skill = attacker.getSkills().get(i);
            if (skill.isReady() && attacker.getMana() >= skill.getCost()) {
                availableSkills.add(i);
            }
        }
        
        if (!availableSkills.isEmpty()) {
            int chosenSkill = availableSkills.get(new Random().nextInt(availableSkills.size()));
            useSkill(attacker, defender, chosenSkill);
        } else {
            System.out.println(attacker.getName() + " has no available skills! Turn skipped.");
            addToBattleLog(attacker.getName() + " skips turn - no available skills.");
        }
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void useSkill(Character attacker, Character defender, int skillIndex) {
        Skill skill = attacker.getSkills().get(skillIndex);
        
        if (!skill.isReady()) {
            System.out.println(skill.getName() + " is on cooldown!");
            return;
        }
        
        if (attacker.getMana() < skill.getCost()) {
            System.out.println("Not enough mana!");
            return;
        }
        
        attacker.useMana(skill.getCost());
        skill.setCurrentCooldown(skill.getCooldown());
        
        System.out.println("\n⚡ " + attacker.getName() + " uses " + skill.getName() + "!");
        
        executeSkillEffect(attacker, defender, skill);
    }
    
    private void executeSkillEffect(Character attacker, Character defender, Skill skill) {
        String logMessage = attacker.getName() + " uses " + skill.getName() + "!";
        
        switch (skill.getType()) {
            case "attack":
                int baseDamage = skill.getDamage();
                int finalDamage;
                boolean isCrit = DamageCalculator.isCriticalHit();
                
                if (isCrit) {
                    finalDamage = DamageCalculator.calculateCriticalDamage(baseDamage);
                    defender.takeDamage(finalDamage);
                    logMessage += " **CRITICAL HIT** for " + finalDamage + " damage!";
                    System.out.println("💥⚡ **CRITICAL HIT!** " + defender.getName() + " takes " + finalDamage + " damage!");
                } else {
                    finalDamage = DamageCalculator.calculateDamage(baseDamage);
                    defender.takeDamage(finalDamage);
                    logMessage += " Deals " + finalDamage + " damage!";
                    System.out.println("💥 " + defender.getName() + " takes " + finalDamage + " damage!");
                }
                break;
                
            case "heal":
                int baseHeal = skill.getHeal();
                int finalHeal = DamageCalculator.calculateDamage(baseHeal); // Randomize healing too
                attacker.heal(finalHeal);
                logMessage += " Heals for " + finalHeal + " HP!";
                System.out.println("💚 " + attacker.getName() + " heals for " + finalHeal + " HP!");
                break;
                
            case "ultimate":
                handleUltimateSkill(attacker, defender, skill);
                logMessage += " **ULTIMATE ATTACK**";
                break;
                
            case "buff":
                handleBuffSkill(attacker, skill);
                logMessage += " Gains powerful buffs!";
                break;
                
            case "team_buff":
                logMessage += " Boosts team power!";
                System.out.println("✨ Team receives powerful blessings!");
                break;
                
            case "debuff":
                logMessage += " Weakens the enemy!";
                System.out.println("💀 " + defender.getName() + " is weakened!");
                break;
                
            case "aoe":
                int baseAoeDamage = skill.getDamage();
                int finalAoeDamage = DamageCalculator.calculateDamage(baseAoeDamage);
                defender.takeDamage(finalAoeDamage);
                logMessage += " Devastating AoE attack for " + finalAoeDamage + " damage!";
                System.out.println("🌪️ Devastating area attack hits for " + finalAoeDamage + " damage!");
                break;
                
            default:
                logMessage += " Special effect activated!";
                System.out.println("✨ Special effect activated!");
        }
        
        addToBattleLog(logMessage);
    }
    
    private void handleUltimateSkill(Character attacker, Character defender, Skill skill) {
        int baseDamage = skill.getDamage();
        int finalDamage = DamageCalculator.calculateDamage(baseDamage);
        defender.takeDamage(finalDamage);
        System.out.println("⚡⚡⚡ ULTIMATE ATTACK! " + finalDamage + " TRUE DAMAGE! ⚡⚡⚡");
        
        // Special ultimate effects based on hero
        if (attacker.getName().equals("Heralde")) {
            System.out.println("🌩️ Zeus' lightning strikes from the heavens!");
        } else if (attacker.getName().equals("Chronos")) {
            System.out.println("⏰ Time itself bends to destroy the enemy!");
        }
    }
    
    private void handleBuffSkill(Character attacker, Skill skill) {
        System.out.println("⬆️ " + attacker.getName() + " gains powerful buffs!");
        
        // Simulate buff effects (in a full implementation, these would modify stats)
        if (skill.getName().contains("Wrath")) {
            System.out.println("🔥 Attack power increased! Armor ignored!");
        } else if (skill.getName().contains("Wings")) {
            System.out.println("🏃‍♂️ Speed greatly increased! Extra turn granted!");
        }
    }
    
    private void displayBattleStatus() {
        System.out.println("\n📊 BATTLE STATUS:");
        System.out.printf("%-20s HP: %d/%d | MP: %d/%d\n", 
            player1.getName(), player1.getHp(), player1.getMaxHp(), 
            player1.getMana(), player1.getMaxMana());
        System.out.printf("%-20s HP: %d/%d | MP: %d/%d\n", 
            player2.getName(), player2.getHp(), player2.getMaxHp(), 
            player2.getMana(), player2.getMaxMana());
    }
    
    private void announceWinner() {
        System.out.println("\n" + "=".repeat(80));
        if (player1.isAlive()) {
            System.out.println("🏆 " + player1.getName() + " WINS! 🏆");
        } else {
            System.out.println("🏆 " + player2.getName() + " WINS! 🏆");
        }
        System.out.println("=".repeat(80));
        
        // Display battle log summary
        System.out.println("\n📜 Battle Summary:");
        for (String log : battleLog) {
            System.out.println("• " + log);
        }
    }
    
    private void addToBattleLog(String message) {
        battleLog.add(message);
    }
}

// Character Factory class for creating heroes
class CharacterFactory {
    public static List<Character> createAllHeroes() {
        List<Character> heroes = new ArrayList<>();
        heroes.add(new Achiron());
        heroes.add(new Atalyn());
        heroes.add(new Orris());
        heroes.add(new Heralde());
        heroes.add(new Orven());
        heroes.add(new Perrin());
        heroes.add(new Medra());
        heroes.add(new Thayen());
        heroes.add(new Chronos()); // OP test character
        return heroes;
    }
    
    public static Character createHero(String heroName) {
        switch (heroName.toLowerCase()) {
            case "achiron": return new Achiron();
            case "atalyn": return new Atalyn();
            case "orris": return new Orris();
            case "heralde": return new Heralde();
            case "orven": return new Orven();
            case "perrin": return new Perrin();
            case "medra": return new Medra();
            case "thayen": return new Thayen();
            case "chronos": return new Chronos();
            default: return new Achiron(); // Default hero
        }
    }
}

// Main game class
public class LigaOlympicaGame {
    private List<Character> heroes;
    private Scanner scanner;
    private BattleSystem battleSystem;
    
    public LigaOlympicaGame() {
        heroes = CharacterFactory.createAllHeroes();
        scanner = new Scanner(System.in);
        battleSystem = new BattleSystem();
    }
    
    public void displayMainMenu() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("    ⚔️  ⚡ LIGA OLYMPICA ⚡  ⚔️");
        System.out.println("         Battle Arena of the Gods");
        System.out.println("=".repeat(80));
        System.out.println("1. ⚔️  PvP - Battle Against Another Player");
        System.out.println("2. 🤖 PvC - Challenge the AI");
        System.out.println("3. 🏆 Arcade Mode - Fight 7 Heroes, Win Grade 5.0!");
        System.out.println("4. 📖 View Hero Backstories");
        System.out.println("5. 🧪 Test Mode - Play as OP Character (Chronos)");
        System.out.println("6. 🚪 Exit");
        System.out.println("=".repeat(80));
        System.out.print("Choose your destiny: ");
    }
    
    public void displayHeroSelection() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    🏛️  CHOOSE YOUR HERO  🏛️");
        System.out.println("=".repeat(80));
        
        // Only show first 8 heroes in normal selection (hide Chronos)
        int maxHeroes = Math.min(8, heroes.size());
        for (int i = 0; i < maxHeroes; i++) {
            Character hero = heroes.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, hero.getName(), hero.getTitle());
            System.out.printf("   🏛️ %s | %s | HP: %d | MP: %d\n", 
                hero.getGod(), hero.getRole(), hero.getMaxHp(), hero.getMaxMana());
            System.out.println();
        }
        System.out.println("=".repeat(80));
    }
    
    public void displayHeroBackstories() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    📖 HERO LEGENDS 📖");
        System.out.println("=".repeat(80));
        
        // Show backstories for first 8 heroes only (exclude Chronos)
        for (int i = 0; i < Math.min(8, heroes.size()); i++) {
            heroes.get(i).displayBackstory();
            System.out.println();
        }
        
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    public Character selectHero(String playerName) {
        System.out.println("\n" + playerName + ", choose your hero:");
        displayHeroSelection();
        System.out.print("Enter your choice (1-8): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (choice >= 1 && choice <= 8) {
            Character selectedHero = CharacterFactory.createHero(heroes.get(choice - 1).getName());
            selectedHero.displayBackstory();
            System.out.println("\n✨ You have chosen " + selectedHero.getName() + "! ✨");
            return selectedHero;
        }
        
        System.out.println("Invalid choice! Selecting first hero...");
        return CharacterFactory.createHero("Achiron");
    }
    
    public void playPvP() {
        System.out.println("\n🏛️ PvP MODE - Two Warriors Enter, One Emerges Victorious! 🏛️");
        
        Character player1 = selectHero("Player 1");
        Character player2 = selectHero("Player 2");
        
        battleSystem.startBattle(player1, player2);
    }
    
    public void playPvC() {
        System.out.println("\n🤖 PvC MODE - Face the Challenge of AI! 🤖");
        
        Character player = selectHero("Player");
        Character ai = CharacterFactory.createHero(heroes.get(new Random().nextInt(8)).getName()); // Exclude Chronos
        
        System.out.println("\nYour opponent is: " + ai.getName() + " - " + ai.getTitle());
        ai.displayBackstory();
        
        battleSystem.startBattle(player, ai);
    }
    
    public void playArcade() {
        System.out.println("\n🏆 ARCADE MODE - The Ultimate Challenge! 🏆");
        System.out.println("Fight through 7 heroes to earn the legendary Grade 5.0!");
        
        Character player = selectHero("Champion");
        List<Character> opponents = new ArrayList<>();
        
        // Create opponents (exclude Chronos and the selected player)
        for (int i = 0; i < 8; i++) {
            if (!heroes.get(i).getName().equals(player.getName())) {
                opponents.add(CharacterFactory.createHero(heroes.get(i).getName()));
            }
        }
        
        int victories = 0;
        
        for (int i = 0; i < 7 && player.isAlive(); i++) {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("                ARCADE CHALLENGE " + (i + 1) + "/7");
            System.out.println("                Current Victories: " + victories);
            System.out.println("=".repeat(80));
            
            Character opponent = opponents.get(i % opponents.size());
            opponent.reset(); // Reset opponent to full health
            
            System.out.println("Your next opponent: " + opponent.getName());
            opponent.displayBackstory();
            
            battleSystem.startBattle(player, opponent);
            
            if (player.isAlive()) {
                victories++;
                System.out.println("\n🎉 Victory! Healing and preparing for next battle...");
                player.heal(200); // Heal between battles
                player.restoreMana(player.getMaxMana()); // Restore full mana
                
                if (victories < 7) {
                    System.out.println("Press Enter to continue to next battle...");
                    scanner.nextLine();
                }
            } else {
                break;
            }
        }
        
        // Final results
        System.out.println("\n" + "=".repeat(80));
        if (victories == 7) {
            System.out.println("🏆🏆🏆 PERFECT VICTORY! ARCADE COMPLETED! 🏆🏆🏆");
            System.out.println("         🌟🌟🌟 GRADE: 5.0 🌟🌟🌟");
            System.out.println("    You have proven yourself a true Olympian!");
        } else {
            System.out.println("💀 Arcade Challenge Failed!");
            System.out.println("Victories: " + victories + "/7");
            System.out.println("Grade: " + String.format("%.1f", (victories / 7.0) * 5.0));
        }
        System.out.println("=".repeat(80));
    }
    
    public void playTestMode() {
        System.out.println("\n🧪 TEST MODE - Play as the Overpowered Chronos! 🧪");
        System.out.println("Perfect for testing the arcade end game!");
        
        // Get Chronos (the OP character)
        Character chronos = new Chronos();
        chronos.reset();
        
        chronos.displayBackstory();
        System.out.println("\n⚡ You are now Chronos, Master of Time! ⚡");
        
        // Start arcade with Chronos
        List<Character> opponents = new ArrayList<>();
        for (int i = 0; i < 8; i++) { // All 8 normal heroes
            opponents.add(CharacterFactory.createHero(heroes.get(i).getName()));
        }
        
        int victories = 0;
        
        for (int i = 0; i < 7 && chronos.isAlive(); i++) {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("                TEST ARCADE CHALLENGE " + (i + 1) + "/7");
            System.out.println("                Current Victories: " + victories);
            System.out.println("=".repeat(80));
            
            Character opponent = opponents.get(i % opponents.size());
            opponent.reset(); // Reset opponent to full health
            
            System.out.println("Your next opponent: " + opponent.getName());
            opponent.displayBackstory();
            
            battleSystem.startBattle(chronos, opponent);
            
            if (chronos.isAlive()) {
                victories++;
                System.out.println("\n🎉 Victory! Preparing for next battle...");
                chronos.heal(1000); // Massive healing for OP character
                chronos.restoreMana(chronos.getMaxMana());
                
                if (victories < 7) {
                    System.out.println("Press Enter to continue to next battle...");
                    scanner.nextLine();
                }
            } else {
                break;
            }
        }
        
        // Final results
        System.out.println("\n" + "=".repeat(80));
        if (victories == 7) {
            System.out.println("🏆🏆🏆 CHRONOS DOMINATES THE ARCADE! 🏆🏆🏆");
            System.out.println("         🌟🌟🌟 GRADE: 5.0 🌟🌟🌟");
            System.out.println("    ⏰ TIME ITSELF BOWS TO YOUR POWER! ⏰");
            System.out.println("    🎊 ARCADE END GAME TESTED SUCCESSFULLY! 🎊");
        } else {
            System.out.println("💀 Even Chronos can fall...");
            System.out.println("Victories: " + victories + "/7");
        }
        System.out.println("=".repeat(80));
    }
    
    public void run() {
        System.out.println("🏛️ Welcome to the Battle Arena of the Gods! 🏛️");
        
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    playPvP();
                    break;
                case 2:
                    playPvC();
                    break;
                case 3:
                    playArcade();
                    break;
                case 4:
                    displayHeroBackstories();
                    break;
                case 5:
                    playTestMode();
                    break;
                case 6:
                    System.out.println("⚔️ May the gods watch over you, warrior! ⚔️");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    public static void main(String[] args) {
        LigaOlympicaGame game = new LigaOlympicaGame();
        game.run();
    }
} */