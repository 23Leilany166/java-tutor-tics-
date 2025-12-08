package com.ejercicios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problema2_3Test {

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
    void raicesReales_1_menos3_2() {
        // Ecuación: x^2 - 3x + 2 = 0 → raíces: 2.0 y 1.0
        String input = "1\n-3\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problema2_3.main(new String[]{});

        String output = outputStreamCaptor.toString();
        // El main imprime: "2.0 1.0" (con espacio)
        assertTrue(output.contains("2.0"),
                "La primera raíz debe ser 2.0");
        assertTrue(output.contains("1.0"),
                "La segunda raíz debe ser 1.0");
    }

    @Test
    void noCuadratica_aCero() {
        // a = 0 → no es ecuación cuadrática
        String input = "0\n2\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problema2_3.main(new String[]{});

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("No hay raíces reales"),
                "Cuando a = 0 debe indicar que no hay raíces reales");
    }

    @Test
    void discriminanteNegativo() {
        // x^2 + 1 = 0 → Δ = -4 → no hay raíces reales
        String input = "1\n0\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problema2_3.main(new String[]{});

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("No hay raíces reales"),
                "Cuando el discriminante es negativo debe indicar que no hay raíces reales");
    }
}
