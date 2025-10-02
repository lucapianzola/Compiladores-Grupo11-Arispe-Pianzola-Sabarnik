package AccionesSemanticas;
import CompiladoresMain.*;

/*
    AS2: Establece el carácter como lexema y retorna el token asociado
    para símbolos de un solo carácter como ',' ';' '+' etc.
 */
public class AS2 extends AccionSemantica {

    @Override
    public void ejecutar(Token token, char c) {
        token.setLexema(String.valueOf(c));
        AtributosTokens atributosTokens = AnalizadorLexico.tablaSimbolos.get(token.getLexema());
        atributosTokens.incrementarCantidad();
        token.setId(atributosTokens.getIdToken());
    }

    @Override
    public String toString() {
        return "AS2";
    }
}
