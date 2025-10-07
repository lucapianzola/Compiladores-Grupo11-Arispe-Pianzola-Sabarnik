%{
#include <stdio.h>
#include <stdlib.h>

int yylex(void);
void yyerror(const char *s);
%}

/* -------------------------------------------------------------------
   TOKENS
------------------------------------------------------------------- */
%token IDENTIFICADOR NUMERO CADENA
%token TIPO_INT TIPO_FLOAT TIPO_STRING
%token IF ELSE WHILE FOR RETURN PRINT INPUT
%token LITERAL_TRUE LITERAL_FALSE
%token MAS MENOS MULT DIV MOD
%token ASIGNACION IGUAL MAYOR MENOR MAYORIGUAL MENORIGUAL DISTINTO
%token PUNTOYCOMA COMA
%token PARENTESIS_A PARENTESIS_C LLAVE_A LLAVE_C CORCHETE_A CORCHETE_C
%token AND OR NOT
%token WHITESPACE

%left OR
%left AND
%left IGUAL DISTINTO
%left MAYOR MENOR MAYORIGUAL MENORIGUAL
%left MAS MENOS
%left MULT DIV MOD
%right NOT
%right ASIGNACION

%start programa

/* -------------------------------------------------------------------
   REGLAS
------------------------------------------------------------------- */

%%

programa
    : lista_sentencias
    ;

lista_sentencias
    : lista_sentencias sentencia
    | sentencia
    ;

sentencia
    : definicion
    | instruccion
    ;

 /* -------------------------------------------------------------------
    Definiciones (factorización aplicada aquí)
 ------------------------------------------------------------------- */

definicion
    : tipo IDENTIFICADOR resto_definicion
    ;

resto_definicion
    : '(' parametros_formales ')' bloque_ejecutable   /* Definición de función */
    | resto_variables                                 /* Declaración de variable */
    ;

resto_variables
    : lista_variables_cont PUNTOYCOMA
    ;

lista_variables_cont
    : COMA lista_variables
    | /* vacío: una sola variable */
    ;

lista_variables
    : IDENTIFICADOR
    | IDENTIFICADOR COMA lista_variables
    ;

parametros_formales
    : /* vacío */
    | lista_parametros
    ;

lista_parametros
    : parametro
    | lista_parametros COMA parametro
    ;

parametro
    : tipo IDENTIFICADOR
    ;

tipo
    : TIPO_INT
    | TIPO_FLOAT
    | TIPO_STRING
    ;

 /* -------------------------------------------------------------------
    Instrucciones / Bloques
 ------------------------------------------------------------------- */

instruccion
    : bloque_ejecutable
    | expresion PUNTOYCOMA
    | RETURN expresion PUNTOYCOMA
    | PRINT expresion PUNTOYCOMA
    | INPUT IDENTIFICADOR PUNTOYCOMA
    | IF expresion bloque_ejecutable else_opcional
    | WHILE expresion bloque_ejecutable
    | FOR PARENTESIS_A expresion PUNTOYCOMA expresion PUNTOYCOMA expresion PARENTESIS_C bloque_ejecutable
    ;

else_opcional
    : /* vacío */
    | ELSE bloque_ejecutable
    ;

bloque_ejecutable
    : LLAVE_A lista_sentencias LLAVE_C
    ;

 /* -------------------------------------------------------------------
    Expresiones
 ------------------------------------------------------------------- */

expresion
    : expresion MAS termino
    | expresion MENOS termino
    | termino
    ;

termino
    : termino MULT factor
    | termino DIV factor
    | factor
    ;

factor
    : NUMERO
    | CADENA
    | IDENTIFICADOR
    | invocacion
    | PARENTESIS_A expresion PARENTESIS_C
    | LITERAL_TRUE
    | LITERAL_FALSE
    ;

invocacion
    : IDENTIFICADOR PARENTESIS_A lista_argumentos PARENTESIS_C
    ;

lista_argumentos
    : /* vacío */
    | lista_argumentos_no_vacia
    ;

lista_argumentos_no_vacia
    : expresion
    | lista_argumentos_no_vacia COMA expresion
    ;

%%

/* -------------------------------------------------------------------
   CÓDIGO C AUXILIAR
------------------------------------------------------------------- */

void yyerror(const char *s) {
    fprintf(stderr, "Error sintáctico: %s\n", s);
}

int main(void) {
    return yyparse();
}
