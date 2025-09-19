package edu.compiler.analizadorlexico.as;

import edu.compiler.analizadorlexico.AnalizadorLexico;
import edu.compiler.analizadorlexico.AtributosTokens;
import edu.compiler.analizadorlexico.Tipotoken;
import edu.compiler.analizadorlexico.Token;
import edu.compiler.analizadorsemantico.TiposDeUso;

/*
  Identificar tokens de palabras reservadas e indentificadores
*/
public class AccionSemanticaPRyIden extends AccionSemantica{
    @Override
    public void ejecutar(Token token, char c) {
        AnalizadorLexico.indice_caracter_leer--;

        AtributosTokens atributosTokens;
        if(Tipotoken.esReservada(token.getLexema())) {
            //Si es palabra reservada(mayus o minuscula) aumenta uno la cantidad
            atributosTokens= AnalizadorLexico.tablaSimbolos.get(token.getLexema().toUpperCase());
            atributosTokens.incrementarCantidad();
            token.setId(atributosTokens.getToken());
        } else { //Es identificador
            if (token.getLexema().length() > 15) { //Se recorta si excede los 15 caracteres
                String identificador_cortado = token.getLexema().substring(0, 15);
                AnalizadorLexico.errores_y_warnings.add("Linea " + AnalizadorLexico.numero_linea +
                        " / Posicion " + (AnalizadorLexico.indice_caracter_leer - token.getLexema().length()) +
                        " - WARNING: identificador muy largo, se considera solo: " + identificador_cortado);
                token.setLexema(identificador_cortado);
            }
            atributosTokens = AnalizadorLexico.tablaSimbolos.get(token.getLexema());
            if (atributosTokens != null) { //Esta en la TS
                token.setId(atributosTokens.getToken());
                atributosTokens.incrementarCantidad();
            } else {
                atributosTokens = new AtributosTokens(1, Tipotoken.IDENTIFICADOR);
                AnalizadorLexico.tablaSimbolos.put(token.getLexema(), atributosTokens);
            }
            token.setId(atributosTokens.getToken());
        }

    }

    @Override
    public String toString() {
        return "AS3";
    }
}