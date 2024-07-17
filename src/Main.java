import java.io.IOException;

public class Main {



    public static void main(String[] args) {
        try {
            while (true) {
                System.out.print("A toi : ");
                Syntaxe.lecture();
                if (Syntaxe.c == '.') {
                    System.out.println("Au revoir...");
                    System.exit(0);
                }
                int result = Syntaxe.reconnaitreExpression();
                if (Syntaxe.c == '=' && Syntaxe.e == 0) {
                    System.out.printf("La syntaxe de l'expression est correcte\nSa valeur est : %d\n", result);
                } else {
                    Syntaxe.erreurSyntaxe();
                }

                Syntaxe.e = 0;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}