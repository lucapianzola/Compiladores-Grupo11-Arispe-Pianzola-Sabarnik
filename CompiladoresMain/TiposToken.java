package CompiladoresMain;

public class TiposToken {
    // --- Tokens de Categorías Generales ---
    public static final int IDENTIFICADOR = 257;
    public static final int CTE_LONG = 258;
    public static final int CTE_DFLOAT = 259;
    public static final int CADENA = 260;

    // --- Palabras Reservadas ---
    // Generales cargadas en TS para que funcione el Lenguaje
    public static final int IF = 262;
    public static final int ELSE = 263;
    public static final int ENDIF = 265;
    public static final int PRINT = 266;
    public static final int RETURN = 264;

    // Específicas de los temas TP1
    public static final int LONG = 267;
    public static final int DFLOAT = 268;
    public static final int VAR = 290;
    public static final int DO = 270;
    public static final int UNTIL = 271;

    // Específicas de los temas TP2
    public static final int CV = 272;
    public static final int SL = 273;
    public static final int LE = 274;
    public static final int FLECHA = 279;

    // --- Operadores y Puntuación de Múltiples Caracteres ---
    public static final int ASIGNACION = 278;      // :=
    public static final int MAYOR_IGUAL = 275;     // >=
    public static final int MENOR_IGUAL = 276;     // <=
    public static final int DISTINTO = 277;        // !=
    public static final int IGUAL = 280;           // ==

    public static boolean esReservada(String lexema) {
        if (    lexema.equals("if")     ||
                lexema.equals("else")   ||
                lexema.equals("return") ||
                lexema.equals("endif")  ||
                lexema.equals("printif")  ||
                lexema.equals("long")   ||
                lexema.equals("dfloat") ||
                lexema.equals("var")    ||
                lexema.equals("do")     ||
                lexema.equals("until")  ||
                lexema.equals("cv")     ||
                lexema.equals("sl")     ||
                lexema.equals("le")
        ) return true;
        return false;
    }
}
