package ligaolympica;

public class Main {
    public static void main(String[] args) {
         System.out.print("╔");
        for (int i = 0; i < 60; i++) {
            System.out.print("═");
        }
        System.out.print("╗\n");
        
        // Empty space
        System.out.print("║");
        for (int i = 0; i < 60; i++) {
            System.out.print(" ");
        }
        System.out.print("║\n");
        
        // Title line
        System.out.print("║");
        for (int i = 0; i < 23; i++) {
            System.out.print(" ");
        }
        System.out.print("LIGA OLYMPICA");
        for (int i = 0; i < 24; i++) {
            System.out.print(" ");
        }
        System.out.print("║\n");
        
        // Empty space
        System.out.print("║");
        for (int i = 0; i < 60; i++) {
            System.out.print(" ");
        }
        System.out.print("║\n");
        
        // Bottom border
        System.out.print("╚");
        for (int i = 0; i < 60; i++) {
            System.out.print("═");
        }
        System.out.print("╝\n");
        
        Game game = new Game();
        game.start();
    }
}