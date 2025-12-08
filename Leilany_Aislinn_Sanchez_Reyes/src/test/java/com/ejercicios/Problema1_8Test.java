package com.ejercicios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problema1_8Test {

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
    void testDistancia_Clasica_3_4_5() {
        // Entrada: 0 0 3 4 → distancia = 5.0
        String input = "0\n0\n3\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problema1_8.main(new String[]{});

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("5.0"), 
            "La distancia debe ser 5.0 para los puntos (0,0) y (3,4)");
    }

    @Test
    void testDistancia_Cero() {
        // Entrada: 2 2 2 2 → distancia = 0.0
        String input = "2\n2\n2\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problema1_8.main(new String[]{});

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("0.0"), 
            "La distancia debe ser 0.0 para puntos iguales");
    }
}
