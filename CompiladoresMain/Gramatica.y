%{
// --- Bloque de código Java ---
// Este código se copia directamente en el parser generado.
// Aquí puedes poner imports y variables auxiliares.
%}

%start programa

// --- DECLARACIÓN DE TOKENS ---
// Estos son los tokens que tu Analizador Léxico (TP1) debe devolver.

// Tokens con valor asociado (identificadores, constantes, etc.)
%token <sval> IDENTIFICADOR CADENA
%token <ival> CTE_LONG
%token <dval> CTE_DFLOAT

// Palabras Reservadas y Operadores Compuestos
%token IF ELSE ENDIF PRINT RETURN
%token LONG DFLOAT VAR DO UNTIL
%token CV SL LE
%token ASIGNACION MAYOR_IGUAL MENOR_IGUAL DISTINTO IGUAL
%token FLECHA // Token para '->'

// --- PRECEDENCIA DE OPERADORES ARITMÉTICOS ---
// Esto resuelve conflictos shift-reduce en las expresiones.
%left '+' '-'
%left '*' '/'

%%

/* --- REGLAS DE LA GRAMÁTICA --- */

programa
    : IDENTIFICADOR '{' lista_sentencias '}'
    ;

lista_sentencias
    : /* vacío */
    | lista_sentencias sentencia
    ;

sentencia
    : declarativa ';'
    | ejecutable ';'
    ;

/* --- SENTENCIAS DECLARATIVAS --- */
// Tema 10: Inferencia Opcional
declarativa
    : tipo lista_variables      // Declaración explícita: long X, Y;
    | VAR asignacion            // Declaración por inferencia: var X := 1.2;
    | funcion_def
    ;

lista_variables
    : IDENTIFICADOR
    | lista_variables ',' IDENTIFICADOR
    ;

tipo
    : LONG
    | DFLOAT
    ;

// Tema 20: Retornos Múltiples
funcion_def
    : lista_tipos IDENTIFICADOR '(' parametros_formales_opt ')' '{' cuerpo_funcion '}'
    ;

lista_tipos
    : tipo
    | lista_tipos ',' tipo
    ;

parametros_formales_opt
    : /* vacío */
    | parametros_formales
    ;

parametros_formales
    : parametro_formal
    | parametros_formales ',' parametro_formal
    ;

// Tema 24: Pasaje de Parámetros Copia-Valor
parametro_formal
    : opt_sem_pasaje tipo IDENTIFICADOR
    ;

opt_sem_pasaje
    : /* vacío */
    | CV SL
    | CV LE
    ;

cuerpo_funcion
    : lista_sentencias
    ;

/* --- SENTENCIAS EJECUTABLES --- */
ejecutable
    : asignacion
    | asignacion_multiple
    | if_statement
    | do_until_statement        // Tema 13
    | print_statement
    | return_statement
    | lambda_inline             // Tema 27
    | invocacion
    ;

// Asignación simple con :=
asignacion
    : lhs ASIGNACION expresion
    ;

// Tema 23: Prefijado Opcional
lhs
    : IDENTIFICADOR
    | IDENTIFICADOR '.' IDENTIFICADOR
    ;

// Tema 16: Asignaciones Múltiples con =
asignacion_multiple
    : lista_lhs '=' lista_rhs
    ;

lista_lhs
    : lhs
    | lista_lhs ',' lhs
    ;

lista_rhs
    : expresion_o_invocacion
    | lista_rhs ',' expresion_o_invocacion
    ;

expresion_o_invocacion
    : expresion
    | invocacion
    ;

/* --- CONDICIONES (LA CORRECCIÓN PRINCIPAL) --- */
condicion
    : expresion comparador expresion
    ;

comparador
    : '>' | '<' | MAYOR_IGUAL | MENOR_IGUAL | IGUAL | DISTINTO
    ;

/* --- ESTRUCTURAS DE CONTROL --- */
if_statement
    : IF '(' condicion ')' bloque_ejecutable else_opt ENDIF
    ;

else_opt
    : /* vacío */
    | ELSE bloque_ejecutable
    ;

// Tema 13: do-until
do_until_statement
    : DO bloque_ejecutable UNTIL '(' condicion ')'
    ;

bloque_ejecutable
    : ejecutable
    | '{' lista_sentencias '}'
    ;

/* --- EXPRESIONES Y FACTORES --- */
expresion
    : termino
    | expresion '+' termino
    | expresion '-' termino
    ;

termino
    : factor
    | termino '*' factor
    | termino '/' factor
    ;

factor
    : lhs
    | CTE_LONG
    | CTE_DFLOAT
    | invocacion
    ;

/* --- INVOCACIÓN A FUNCIONES --- */
invocacion
    : IDENTIFICADOR '(' lista_param_real_opt ')'
    ;

lista_param_real_opt
    : /* vacío */
    | lista_param_real
    ;

lista_param_real
    : parametro_real
    | lista_param_real ',' parametro_real
    ;

parametro_real
    : expresion FLECHA IDENTIFICADOR
    ;

/* --- OTRAS SENTENCIAS --- */
print_statement
    : PRINT '(' CADENA ')'
    | PRINT '(' expresion ')'
    ;

// Tema 20: Retornos Múltiples
return_statement
    : RETURN '(' lista_rhs ')'
    ;

// Tema 27: Lambda en línea
lambda_inline
    : '(' tipo IDENTIFICADOR ')' '{' lista_sentencias '}' '(' argumento ')'
    ;

argumento
    : IDENTIFICADOR | CTE_LONG | CTE_DFLOAT
    ;

%%

/* --- CÓDIGO AUXILIAR --- */
// Aquí va el código Java que necesites, como el método yyerror.
public void yyerror(String s) {
    // Implementar manejo de errores
}

// Main de ejemplo
public static void main(String[] args) {
    // Lógica para iniciar el parser
}