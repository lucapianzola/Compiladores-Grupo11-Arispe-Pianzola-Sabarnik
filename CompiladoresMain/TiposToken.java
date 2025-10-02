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
    public static final int ELSE = 264;
    public static final int ENDIF = 267;
    public static final int PRINT = 264;
    public static final int RETURN = 265;

    // Específicas de los temas TP1
    public static final int LONG = 266;
    public static final int DFLOAT = 267;
    public static final int VAR = 268;
    public static final int DO = 269;
    public static final int UNTIL = 270;

    // Específicas de los temas TP2
    public static final int CV = 271;
    public static final int SL = 272;
    public static final int LE = 273;

    // --- Operadores y Puntuación de Múltiples Caracteres ---
    public static final int ASIGNACION = 280;      // :=
    public static final int MAYOR_IGUAL = 281;     // >=
    public static final int MENOR_IGUAL = 282;     // <=
    public static final int DISTINTO = 283;        // !=
    public static final int IGUAL = 284;           // ==

    public static boolean esReservada(String lexema) {
        lexema = lexema.toUpperCase();
        if (lexema.equals("IF") ||
                lexema.equals("ELSE") ||
                lexema.equals("RETURN") ||
                lexema.equals("ENDIF") ||
                lexema.equals("PRINT") ||
                lexema.equals("LONG") ||
                lexema.equals("DFLOAT") ||
                lexema.equals("VAR") ||
                lexema.equals("DO") ||
                lexema.equals("UNTIL") ||
                lexema.equals("CV") ||
                lexema.equals("SL") ||
                lexema.equals("LE")
        ) return true;
        return false;
    }
}
