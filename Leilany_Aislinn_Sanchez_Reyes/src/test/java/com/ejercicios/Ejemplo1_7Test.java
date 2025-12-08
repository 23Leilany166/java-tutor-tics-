package com.ejercicios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Ejemplo1_7Test {

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
    void testCaso1_A1_B2() {
        String input = "1\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ejemplo1_7.main(new String[]{});

        assertTrue(outputStreamCaptor.toString().contains("3.0"),
                "El resultado debe ser 3.0 cuando A=1 y B=2");
    }

    @Test
    void testCaso2_A2_B2() {
        String input = "2\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ejemplo1_7.main(new String[]{});

        assertTrue(outputStreamCaptor.toString().contains("5.3333333333"),
                "El resultado debe ser 5.3333333333 cuando A=2 y B=2");
    }

    @Test
    void testCaso3_Amenos2_B2() {
        String input = "-2\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ejemplo1_7.main(new String[]{});

        assertTrue(outputStreamCaptor.toString().contains("0.0"),
                "El resultado debe ser 0.0 cuando A=-2 y B=2");
    }
}
