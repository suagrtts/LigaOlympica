package ligaolympica.character;

public interface CharacterInterface {
    String getName();
    int getHealth();
    int getMaxHealth();
    int getMana();
    int getMaxMana();
    boolean isAlive();

    void skill1(GameCharacter target);
    void skill2(GameCharacter target);
    void skill3(GameCharacter target);

    void takeTurn(GameCharacter target);
}
