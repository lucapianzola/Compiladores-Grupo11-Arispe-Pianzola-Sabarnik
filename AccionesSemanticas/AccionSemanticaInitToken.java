package edu.compiler.analizadorlexico.as;

import edu.compiler.analizadorlexico.Tipotoken;
import edu.compiler.analizadorlexico.Token;

/*
    Inicializar Token (Lexema)
 */
public class AccionSemantica1 extends AccionSemantica{
    private String lexema;

    public AccionSemantica1() {
    }

    @Override
    public void ejecutar(Token token, char c) {
        token.setLexema(String.valueOf(c));
    }

    @Override
    public String toString() {
        return "AS1";
    }
}
