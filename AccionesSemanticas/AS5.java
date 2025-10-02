package AccionesSemanticas;
import CompiladoresMain.*;

public class AS5 extends AccionSemantica {
    @Override
    public void ejecutar(Token token, char c) {
        AnalizadorLexico.indice_caracter_leer--;
        AtributosTokens atributosTokens = AnalizadorLexico.tablaSimbolos.get(token.getLexema());
        token.setId(atributosTokens.getIdToken());
    }

    @Override
    public String toString() {
        return "AS6";
    }
}