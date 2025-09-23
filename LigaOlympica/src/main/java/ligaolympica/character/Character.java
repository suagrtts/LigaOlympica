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
