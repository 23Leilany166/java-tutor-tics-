package com.ejercicios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problema3_16Test {

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
    void calculaTotalesYMaxYZeros_resolver() {
        int N = 2;
        int[][] V = new int[N + 1][6];

        // Usamos el mismo arreglo que tu test original
        V[1] = new int[]{0, 10, 20, 0, 5, 3};
        V[2] = new int[]{0, 2, 50, 7, 0, 1};

        Map<String, Object> r = Problema3_16.resolver(V, N);

        int[] tipo = (int[]) r.get("tipo");
        int[] tot = (int[]) r.get("tot");

        // Mismas verificaciones que tu test original
        assertEquals(12, tipo[1]);
        assertEquals(70, tipo[2]);
        assertEquals(7, tipo[3]);

        assertEquals(38, tot[1]);
        assertEquals(60, tot[2]);

        assertEquals(2, (int) r.get("anioMaxTipo2"));
        assertEquals(50, (int) r.get("maxTipo2"));
        assertEquals(java.util.List.of(1), r.get("sinTipo3"));
    }

    @Test
    void mainImprimeAnioMaxYListaSinTipo3() {
        // Simular entrada:
        // N = 2
        // Año 1: 10 20 0 5 3
        // Año 2: 2 50 7 0 1
        String input = ""
                + "2\n"
                + "10 20 0 5 3\n"
                + "2 50 7 0 1\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problema3_16.main(new String[]{});

        String output = outputStreamCaptor.toString();

        // El main imprime:
        // 2 50
        // [1]
        assertTrue(output.contains("2 50"), "Debe imprimir '2 50' como año y máximo del tipo 2");
        assertTrue(output.contains("[1]"), "Debe imprimir '[1]' como lista de años sin tipo 3");
    }
}
