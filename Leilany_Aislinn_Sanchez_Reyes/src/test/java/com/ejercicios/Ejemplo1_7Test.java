package com.ejercicios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Ejemplo1_7Test {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    private final java.io.InputStream standardIn = System.in;

    @BeforeEach
    public void setUp() {
        outputStreamCaptor.reset(); 
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    // Ajusta esto según cómo imprima tu main
    private double getOutput() {
        String s = outputStreamCaptor.toString().trim();
        String[] partes = s.split("\\s+");
        String ultima = partes[partes.length - 1];
        return Double.parseDouble(ultima);
    }

    @Test
    void testCaso1() {
        int A = 1, B = 2;
        String input = A + "\n" + B + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ejemplo1_7.main(new String[]{});

        double esperado = ((A + B) * (A + B)) / 3.0;
        assertEquals(esperado, getOutput(), 0.0001);
    }

    @Test
    void testCaso2() {
        int A = 2, B = 2;
        String input = A + "\n" + B + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ejemplo1_7.main(new String[]{});

        double esperado = ((A + B) * (A + B)) / 3.0;
        assertEquals(esperado, getOutput(), 0.0001);
    }

    @Test
    void testCaso3() {
        int A = -2, B = 2;
        String input = A + "\n" + B + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ejemplo1_7.main(new String[]{});

        double esperado = ((A + B) * (A + B)) / 3.0;
        assertEquals(esperado, getOutput(), 0.0001);
    }
}
