import java.io.IOException;

public class Syntaxe {

    public static char c;
    public static int e = 0;


    public static boolean estCaractereBlanc(char c) {
        return c == ' ' || c == '\n' || c == '\t';
    }

    static void lecture() throws IOException {
        do {
            c = (char) System.in.read();
        } while (estCaractereBlanc(c));
    }
    public static void flushInputBuffer() throws IOException {
        while (System.in.read() != '\n' && System.in.read() != -1) ;
    }

    static void erreurSyntaxe() throws IOException {
        System.out.println("La syntaxe de l'expression est erronee");
        flushInputBuffer();
    }

    public static boolean reconnaitreOperateurMultiplicatif() {
        return c == '*' || c == '/';
    }

    public static boolean reconnaitreOperateurAdditif() {
        return c == '+' || c == '-';
    }

    public static boolean reconnaitreChiffre() {
        return c >= '0' && c <= '9';
    }

    public static int valeurChiffre() {
        return c - '0';
    }

    public static int reconnaitreNombre(int value) throws IOException {
        if (reconnaitreChiffre()) {
            value = value * 10 + valeurChiffre();
            lecture();
            return reconnaitreChiffre() ? reconnaitreNombre(value) : value;
        } else {
            return value;
        }
    }

    public static int reconnaitreFacteur() throws IOException {
        int result = 0;
        if (reconnaitreChiffre()) {
            result = reconnaitreNombre(0);
        } else if (c == '(') {
            lecture();
            result = reconnaitreExpression();
            if (c != ')') {
                e = 1;
            }
            lecture();
        } else {
            e = 1;
        }
        return result;
    }

    public static int reconnaitreTerme() throws IOException {
        int result = reconnaitreFacteur();
        if (reconnaitreOperateurMultiplicatif()) {
            char operation = c;
            lecture();
            int membre = reconnaitreTerme();
            if (operation == '*')
                result *= membre;
            else {
                if (membre == 0) {
                    e = 1;
                    return 0;
                }
                result /= membre;
            }
        }
        return result;
    }

    public static int reconnaitreExpression() throws IOException {
        int result = reconnaitreTerme();
        if (reconnaitreOperateurAdditif()) {
            char operation = c;
            lecture();
            int membre = reconnaitreExpression();
            if (operation == '+')
                result += membre;
            else
                result -= membre;
        }
        return result;
    }

}
