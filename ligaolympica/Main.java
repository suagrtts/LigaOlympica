package ligaolympica;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n");
        printStarBurst();
        printColorfulTitle();
        printOlympicRings();

        System.out.println("\n");
        printWarriorArt();

        System.out.println("\n");

        printAnimatedBorder("═");
        centerPrint("IN THE MYSTICAL REALM OF OLYMPICA", 25);
        printAnimatedBorder("═");

        System.out.println();
        typewriterGlow("           Legendary warriors gather from across the realm...", 20);
        typewriterGlow("           Each bearing ancient powers and unmatched skill...", 20);
        typewriterGlow("           Only ONE shall claim the throne of champions!", 20);

        System.out.println("\n");

        // Epic tournament announcement
        printTournamentBanner();

        System.out.println("\n");

        // Creators section with style
        printCreatorsSection();

        System.out.println("\n");

        // Epic call to action
        printCallToAction();

        try {
            System.in.read();
        } catch (IOException e) {
        }

        // Transition effect
        printTransition();

        Game game = new Game();
        game.start();
    }

    static void printStarBurst() {
        String[] stars = {
            "                    ✦       ✧       ★       ✧       ✦",
            "                ✧       ★       ✦       ★       ✧",
            "            ★       ✦       ✧       ✦       ★"
        };

        for (String line : stars) {
            System.out.println(line);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        System.out.println();
    }

    static void printColorfulTitle() {
        String[] title = {
            "     ╔══════════════════════════════════════════════════════════════════════════════════╗",
            "     ║                                                                                  ║",
            "     ║                      ██╗     ██╗ ██████╗  █████╗                                 ║",
            "     ║                      ██║     ██║██╔════╝ ██╔══██╗                                ║",
            "     ║                      ██║     ██║██║  ███╗███████║                                ║",
            "     ║                      ██║     ██║██║   ██║██╔══██║                                ║",
            "     ║                      ███████╗██║╚██████╔╝██║  ██║                                ║",
            "     ║                      ╚══════╝╚═╝ ╚═════╝ ╚═╝  ╚═╝                                ║",
            "     ║                                                                                  ║",
            "     ║          ██████╗ ██╗     ██╗   ██╗███╗   ███╗██████╗ ██╗ ██████╗ █████╗          ║",
            "     ║         ██╔═══██╗██║     ╚██╗ ██╔╝████╗ ████║██╔══██╗██║██╔════╝██╔══██╗         ║",
            "     ║         ██║   ██║██║      ╚████╔╝ ██╔████╔██║██████╔╝██║██║     ███████║         ║",
            "     ║         ██║   ██║██║       ╚██╔╝  ██║╚██╔╝██║██╔═══╝ ██║██║     ██╔══██║         ║",
            "     ║         ╚██████╔╝███████╗   ██║   ██║ ╚═╝ ██║██║     ██║╚██████╗██║  ██║         ║",
            "     ║              ╚═════╝ ╚══════╝   ╚═╝   ╚═╝     ╚═╝╚═╝     ╚═╝ ╚═╝╚═╝  ╚═╝         ║",
            "     ║                                                                                  ║",
            "     ╚══════════════════════════════════════════════════════════════════════════════════╝"
        };

        for (String line : title) {
            System.out.println(line);
            try { Thread.sleep(5); } catch (InterruptedException e) {}
        }
    }

    static void printOlympicRings() {
        String[] rings = {
            "                         ████      ████      ████",
            "                       ██    ██  ██    ██  ██    ██",
            "                       ██    ████    ████    ████",
            "                         ████  ██  ██  ██  ██  ████",
            "                             ██████████████████    ██",
            "                               ████      ████      ██",
            "                                            ██████████"
        };

        for (String line : rings) {
            System.out.println(line);
            try { Thread.sleep(5); } catch (InterruptedException e) {}
        }
    }

    static void printWarriorArt() {
        String[] warrior = {
            "                              ⚔️  THE ARENA AWAITS  ⚔️",
            "",
            "                                  /\\    /\\",
            "                                 /  \\__/  \\",
            "                                |   o  o   |",
            "                                |     >    |",
            "                                 \\  \\__/  /",
            "                                  |____|",
            "                               ___/|  |\\___",
            "                              /   /|  |\\   \\",
            "                             |___| |  | |___|",
            "                                  ||  ||",
            "                                  ||  ||",
            "                                 _||  ||_",
            "                                |__________|"
        };

        for (String line : warrior) {
            centerPrint(line, 5);
        }
    }

    static void printTournamentBanner() {
        String[] banner = {
            "        ╔═══════════════════════════════════════════════════════════╗",
            "        ║         ⚡ THE TOURNAMENT OF LEGENDS BEGINS ⚡           ║",
            "        ║                                                           ║",
            "        ║          🏆  Victory awaits the bold                      ║",
            "        ║          ⚔️  Glory favors the brave                       ║",
            "        ║          👑  The throne demands the strongest             ║",
            "        ║                                                           ║",
            "        ╚═══════════════════════════════════════════════════════════╝"
        };

        for (String line : banner) {
            System.out.println(line);
            try { Thread.sleep(80); } catch (InterruptedException e) {}
        }
    }

    static void printCreatorsSection() {
        String[] creators = {
            "               ╔═══════════════════════════════════════════╗",
            "               ║          FORGED BY LEGENDS:               ║",
            "               ╠═══════════════════════════════════════════╣",
            "               ║                                           ║",
            "               ║               ⚡ Rovpoli ⚡              ║",
            "               ║               🔥 kd 🔥                   ║",
            "               ║               ⭐ biji ⭐                 ║",
            "               ║               ⚔️  selwyn ⚔️              ║",
            "               ║                                           ║",
            "               ╚═══════════════════════════════════════════╝"
        };

        for (String line : creators) {
            System.out.println(line);
            try { Thread.sleep(70); } catch (InterruptedException e) {}
        }
    }

    static void printCallToAction() {
        printAnimatedBorder("▬");
        System.out.println();

        String[] pulse = {
            "              ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓",
            "              ┃  ⚡ PRESS ENTER TO BEGIN YOUR DESTINY ⚡ ┃",
            "              ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"
        };

        for (int i = 0; i < 3; i++) {
            for (String line : pulse) {
                System.out.println(line);
            }
            try { Thread.sleep(300); } catch (InterruptedException e) {}
            if (i < 2) {
                System.out.print("\033[3A"); // Move cursor up 3 lines
            }
        }

        System.out.println("\n");
        printAnimatedBorder("▬");
    }

    static void printTransition() {
        System.out.println("\n\n");
        String[] transition = {
            "                    ⚡━━━━━━━━━━━━━━━━━━━━━━━━━━━━⚡",
            "                       THE BATTLE BEGINS NOW!",
            "                    ⚡━━━━━━━━━━━━━━━━━━━━━━━━━━━━⚡"
        };

        for (String line : transition) {
            centerPrint(line, 30);
        }
        System.out.println("\n");
    }

    static void printAnimatedBorder(String symbol) {
        String border = "        ";
        for (int i = 0; i < 63; i++) {
            border += symbol;
        }

        for (char c : border.toCharArray()) {
            System.out.print(c);
            try { Thread.sleep(5); } catch (InterruptedException e) {}
        }
        System.out.println();
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

    static void typewriterGlow(String text, int delay) {
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
        int width = 80;
        int padding = (width - text.length()) / 2;

        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }

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