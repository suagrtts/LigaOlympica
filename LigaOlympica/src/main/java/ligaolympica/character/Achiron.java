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