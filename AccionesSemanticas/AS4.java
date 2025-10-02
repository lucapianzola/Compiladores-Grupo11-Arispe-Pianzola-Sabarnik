package AccionesSemanticas;
import CompiladoresMain.*;

// ---  Identificar tokens de palabras reservadas e indentificadores ---

public class AS4 extends AccionSemantica{
    @Override
    public void ejecutar(Token token, char c) {
        AnalizadorLexico.indice_caracter_leer--;

        AtributosTokens atributosTokens;
        if (token.getLexema().length() > 20) { //Se recorta si excede los 20 caracteres
                String identificador_cortado = token.getLexema().substring(0, 20);
                AnalizadorLexico.errores_y_warnings.add("Linea " + AnalizadorLexico.numero_linea +
                        " / Posicion " + (AnalizadorLexico.indice_caracter_leer - token.getLexema().length()) +
                        " - WARNING: identificador muy largo, se considera solo: " + identificador_cortado);
                token.setLexema(identificador_cortado);
            }
            atributosTokens = AnalizadorLexico.tablaSimbolos.get(token.getLexema());
            if (atributosTokens != null) { //Esta en la TS
                token.setId(atributosTokens.getIdToken());
                atributosTokens.incrementarCantidad();
            } else {
                atributosTokens = new AtributosTokens(1, TiposToken.IDENTIFICADOR);
                AnalizadorLexico.tablaSimbolos.put(token.getLexema(), atributosTokens);
            }
            token.setId(atributosTokens.getIdToken());
        }


    @Override
    public String toString() {
        return "AS4";
    }
}