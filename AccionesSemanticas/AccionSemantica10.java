package edu.compiler.analizadorlexico.as;

import edu.compiler.analizadorlexico.AnalizadorLexico;
import edu.compiler.analizadorlexico.AtributosTokens;
import edu.compiler.analizadorlexico.Tipotoken;
import edu.compiler.analizadorlexico.Token;
import edu.compiler.analizadorsemantico.TiposDeUso;

/*
    Consumir el ultimo caracter, sin agregarlo al lexema. Ver si ya existe en la TS sino agregarlo.
 */
public class AccionSemantica10 extends AccionSemantica {
    @Override
    public void ejecutar(Token token, char c) {
        AtributosTokens atributosTokens = AnalizadorLexico.tablaSimbolos.get(token.getLexema());
        if (atributosTokens != null) {
            atributosTokens.incrementarCantidad();
            token.setId(atributosTokens.getCantidad());
        } else {
            atributosTokens = new AtributosTokens(1, Tipotoken.CADENAS);
            atributosTokens.setUso(TiposDeUso.cadena);
            token.setId(atributosTokens.getToken());
            AnalizadorLexico.tablaSimbolos.put(token.getLexema(), atributosTokens);
        }
    }

    @Override
    public String toString() {
        return "AS10";
    }
}
