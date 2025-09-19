package edu.compiler.analizadorlexico.as;

import edu.compiler.analizadorlexico.Tipotoken;
import edu.compiler.analizadorlexico.Token;

/*
    AS2: Establece el carácter como lexema y retorna el token asociado
    para símbolos de un solo carácter como ',' ';' '+' etc.
 */
public class AccionSemanticaCaracterSpecial extends AccionSemantica {

    @Override
    public void ejecutar(Token token, char c) {
        token.setLexema(String.valueOf(c));

        switch (c) {
            case ',':
                token.setTipo(Tipotoken.COMA);
                break;
            case ';':
                token.setTipo(Tipotoken.PUNTO_Y_COMA);
                break;
            case '(':
                token.setTipo(Tipotoken.PARENTESIS_ABRE);
                break;
            case ')':
                token.setTipo(Tipotoken.PARENTESIS_CIERRA);
                break;
            case '+':
                token.setTipo(Tipotoken.SUMA);
                break;
            case '-':
                token.setTipo(Tipotoken.RESTA);
                break;
            case '*':
                token.setTipo(Tipotoken.MULTIPLICACION);
                break;
            case '/':
                token.setTipo(Tipotoken.DIVISION);
                break;
            case '=':
                token.setTipo(Tipotoken.ASIGNACION_SIMPLE);
                break;
            default:
                // Podés tener un token especial de error si querés
                token.setTipo(-1); 
                break;
        }
    }

    @Override
    public String toString() {
        return "AS2";
    }
}
