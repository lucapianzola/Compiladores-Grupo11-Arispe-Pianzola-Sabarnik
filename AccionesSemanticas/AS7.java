package AccionesSemanticas;
import CompiladoresMain.*;

public class AS7 extends AccionSemantica{
    @Override
    public void ejecutar(Token token, char c) {
        AnalizadorLexico.indice_caracter_leer--;

        AtributosTokens atributosTokens;
        if(TiposToken.esReservada(token.getLexema())) {
            atributosTokens= AnalizadorLexico.tablaSimbolos.get(token.getLexema().toUpperCase());
            atributosTokens.incrementarCantidad();
            token.setId(atributosTokens.getIdToken());
    }
    }

    @Override
    public String toString() {
        return "AS7";
    }
}