package CompiladoresMain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProbadorSimple {

    public static void main(String[] args) {
        // --- ACA DEFINIMOS LOS CASOS DE PRUEBA ---
        Map<String, String> casosDePrueba = new LinkedHashMap<>();
    //    casosDePrueba.put("Caso 1: Identificadores y Palabras Reservadas", "123L var_1% do long until");
    //    casosDePrueba.put("Caso 2: Constantes Long y Dfloat", "123L 45.D+2 .99D-10");
    //    casosDePrueba.put("Caso 3: Cadenas y Comentarios", "\"hola mundo\" ## esto es un comentario ## IDENTIFICADOR_FINAL");
    //    casosDePrueba.put("Caso 4: Operadores", ":= >= <= =! ==");
    //    casosDePrueba.put("Caso 5: Error de Símbolo", "\"esto & es un error\"");
    //    casosDePrueba.put("Caso 6: Identificador Largo (Warning)", "ESTE%ES%UN%IDENTIFICADOR%DEMASIADO%LARGO%PARA%EL%LIMITE");

        // --- EL MOTOR DE PRUEBAS (CORREGIDO) ---
        for (Map.Entry<String, String> testCase : casosDePrueba.entrySet()) {
            System.out.println("\n=======================================================");
            System.out.println("--- Ejecutando Test: " + testCase.getKey() + " ---");
            System.out.println("=======================================================");

            Path tempFile = null;
            try {
                // Crear un archivo temporal con el código de prueba
                tempFile = Files.createTempFile("test_", ".txt");
                Files.writeString(tempFile, testCase.getValue());

                // Instanciar el Analizador Léxico con la RUTA del archivo temporal
                AnalizadorLexico lex = new AnalizadorLexico(tempFile.toString());
                
                System.out.println("--- Lista de Tokens ---");
                
                // --- BUCLE CORREGIDO ---
                int tokenId;
                while ((tokenId = lex.yylex()) != -1) { // -1 es fin de archivo
                    // Obtenemos el objeto Token completo desde la variable pública yylval
                    Token t = lex.yylval; 
                    if (t != null) {
                        System.out.println(t.toString());
                    }
                }
                // --- FIN DEL BUCLE CORREGIDO ---

                // Imprimir los resultados finales (descomentando la tabla de símbolos)
                lex.printTablaSimbolos();
                lex.printErrors();
                lex.printWarnings();

            } catch (IOException e) {
                System.err.println("Error durante la prueba: " + e.getMessage());
            } finally {
                // Asegurarse de borrar el archivo temporal al final
                if (tempFile != null) {
                    try {
                        Files.deleteIfExists(tempFile);
                    } catch (IOException e) {
                        System.err.println("No se pudo borrar el archivo temporal: " + tempFile.toString());
                    }
                }
            }
        }
    }
}  
 