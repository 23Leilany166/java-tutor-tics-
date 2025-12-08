package com.ejercicios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Ejemplo3_5Test {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    private final java.io.InputStream standardIn = System.in;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    @Test
    void testCubosHastaSentinela() {
        // Entrada simulada:
        // 2 → imprime 8
        // 3 → imprime 27
        // -1 → termina
        String input = "2\n3\n-1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Ejecutar el main de Ejemplo3_5
        Ejemplo3_5.main(new String[]{});

        // Capturar salida
        String output = outputStreamCaptor.toString();

        // Verificaciones
        assertTrue(output.contains("8"), "Debe imprimir 8 cuando la entrada es 2");
        assertTrue(output.contains("27"), "Debe imprimir 27 cuando la entrada es 3");
    }

    @Test
    void testNoImprimeNegativos() {
        // Entrada: -5 → no imprime nada, luego -1 → termina
        String input = "-5\n-1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ejemplo3_5.main(new String[]{});

        String output = outputStreamCaptor.toString().trim();

        assertEquals("", output, "No debe imprimir nada cuando los valores son negativos, excepto -1");
    }

    @Test
    void testCuboDeCeroNoImprime() {
        // Entrada: 0 → como NO es > 0, no imprime
        // Luego -1 → termina
        String input = "0\n-1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ejemplo3_5.main(new String[]{});

        String output = outputStreamCaptor.toString().trim();

        assertEquals("", output, "No debe imprimir nada cuando la entrada es 0");
    }
}
