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