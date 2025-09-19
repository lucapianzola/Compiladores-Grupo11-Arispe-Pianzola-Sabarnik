package edu.compiler.analizadorlexico.as;

import edu.compiler.analizadorlexico.Token;

/*
    Concatenar caracter
 */
public class AccionSemanticaConcat extends AccionSemantica{
    @Override
    public void ejecutar(Token token, char c) {
        token.setLexema(token.getLexema() + c);
    }

    @Override
    public String toString() {
        return "AS2";
    }
}
