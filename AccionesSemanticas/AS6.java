package AccionesSemanticas;
import CompiladoresMain.*;

public class AS6 extends AccionSemantica {
    @Override
    public void ejecutar(Token token, char c) {
        token.setLexema(token.getLexema() + c);
        AtributosTokens atributosTokens = AnalizadorLexico.tablaSimbolos.get(token.getLexema());
        token.setId(atributosTokens.getIdToken());
        atributosTokens.incrementarCantidad();
    }

    @Override
    public String toString() {
        return "AS6";
    }
}