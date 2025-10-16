package ligaolympica.demo;

import ligaolympica.character.*;

public class OOPDemo {

    // Encapsulation example: private field with controlled access
    public static class EncapsulatedItem {
        private int secretValue;

        public EncapsulatedItem(int initial) {
            setSecretValue(initial);
        }

        public int getSecretValue() {
            return secretValue;
        }

        // setter enforces a simple rule: value must be non-negative
        public void setSecretValue(int v) {
            if (v < 0) v = 0;
            this.secretValue = v;
        }
    }

    public static void runDemo() {
        System.out.println("\n=== OOP Concepts Demo ===\n");

        // 1) Encapsulation
        System.out.println("1) Encapsulation:");
        EncapsulatedItem item = new EncapsulatedItem(42);
        System.out.println("   initial secretValue = " + item.getSecretValue());
        item.setSecretValue(-10);
        System.out.println("   after setting -10 (setter enforces >=0): " + item.getSecretValue());

        // 2) Abstraction / Interface
        System.out.println("\n2) Abstraction (Interface):");
        CharacterInterface ci = new Achiron();
        System.out.println("   Interface reference type: CharacterInterface -> " + ci.getName());
        System.out.println("   Interface exposes methods like getHealth() and skill1() (abstract contract).");

        // 3) Inheritance
        System.out.println("\n3) Inheritance:");
        GameCharacter ach = new Achiron();
        System.out.println("   Achiron extends GameCharacter and inherits methods such as heal() and displayStats().");
        System.out.println("   Achiron health before heal: " + ach.getHealth());
        ach.heal(100);
        System.out.println("   Achiron health after heal: " + ach.getHealth());

        // 4) Polymorphism
        System.out.println("\n4) Polymorphism:");
        GameCharacter[] roster = new GameCharacter[] { new Achiron(), new Atalyn(), new Orven() };
        GameCharacter dummyTarget = new Heralde();
        System.out.println("   We hold different concrete characters in a GameCharacter[] and call skill1() polymorphically:");
        for (GameCharacter g : roster) {
            System.out.println("    -> " + g.getName() + " uses skill1 on " + dummyTarget.getName());
            g.skill1(dummyTarget);
            // show resulting health of dummy target
            System.out.println("       Target health now: " + dummyTarget.getHealth() + "/" + dummyTarget.getMaxHealth());
        }

        System.out.println("\n=== End of OOP Demo ===\n");
    }
}
