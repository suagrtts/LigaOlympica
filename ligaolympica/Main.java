package ligaolympica;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n\n");

        typewriter("        _     ___ ___    _      ___  _    __   __ __  ___ ___ ___ __    _", 10);
        typewriter("       | |   |_ _/ __|  /_\\    / _ \\| |   \\ \\ / /|  \\/  | _ \\_ _/ __|  /_\\ ", 10);
        typewriter("       | |__  | | (_ | / _ \\  | (_) | |__  \\ V / | |\\/| |  _/| | (__  / _ \\ ", 10);
        typewriter("       |____||___\\___|/_/ \\_\\  \\___/|____|  |_|  |_|  |_|_| |___\\___|/_/ \\_\\ ", 10);

        System.out.println("\n");

        // Game background/lore
        centerPrint("In the mystical realm of Olympica, legendary warriors gather", 20);
        centerPrint("to prove their strength in the ultimate tournament of champions.", 20);
        centerPrint("Each fighter possesses unique abilities passed down through generations,", 20);
        centerPrint("and only the strongest will claim victory in the Liga Olympica.", 20);

        System.out.println("\n");

        // Creators section
        centerPrint("═══════════════════════════════════════════════════════════", 15);
        centerPrint("Created by:", 20);
        centerPrint("Rovpoli", 20);
        centerPrint("kd", 20);
        centerPrint("biji", 20);
        centerPrint("selwyn", 20);
        centerPrint("═══════════════════════════════════════════════════════════", 15);

        System.out.println("\n");
        centerPrint("Press Enter to begin your journey...", 30);

        try {
            System.in.read();
        } catch (IOException e) {
        }

        System.out.println("\n");

        Game game = new Game();
        game.start();
    }

    static void typewriter(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    static void centerPrint(String text, int delay) {
        int width = 80; // Assume console width of 80 characters
        int padding = (width - text.length()) / 2;

        // Print leading spaces
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }

        // Print text with typewriter effect
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}