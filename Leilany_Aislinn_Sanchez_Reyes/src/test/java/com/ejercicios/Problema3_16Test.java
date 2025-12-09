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
        // Limpia el buffer por si algún test previo dejó basura
        outputStreamCaptor.reset();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
        outputStreamCaptor.reset();
    }

    @Test
    void testResolver_calculaTotalesYMaxYZeros() {
        int N = 2;
        int[][] V = new int[N + 1][6];

        // Datos de prueba (índice 1..N, 1..5)
        V[1] = new int[]{0, 10, 20, 0, 5, 3};
        V[2] = new int[]{0, 2, 50, 7, 0, 1};

        Map<String, Object> r = Problema3_16.resolver(V, N);

        int[] tipo = (int[]) r.get("tipo");
        int[] tot = (int[]) r.get("tot");

        // Verificaciones principales
        assertEquals(12, tipo[1]); // 10 + 2
        assertEquals(70, tipo[2]); // 20 + 50
        assertEquals(7,  tipo[3]); // 0 + 7
        assertEquals(5,  tipo[4]); // 5 + 0
        assertEquals(4,  tipo[5]); // 3 + 1

        assertEquals(38, tot[1]);  // 10 + 20 + 0 + 5 + 3
        assertEquals(60, tot[2]);  // 2 + 50 + 7 + 0 + 1

        // Estructura esperada
        assertEquals(6, tipo.length);      // tipo[0..5], se usan 1..5
        assertEquals(N + 1, tot.length);   // tot[0..N], se usan 1..N

        // Máximo del tipo 2
        assertEquals(2, (int) r.get("anioMaxTipo2"));
        assertEquals(50, (int) r.get("maxTipo2"));

        // Años sin tipo 3
        assertEquals(java.util.List.of(1), r.get("sinTipo3"));
    }

    @Test
    void testMain_EntradaCaso1() {
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

        // El main imprime, entre otras cosas:
        // 2 50
        // [1]
        assertTrue(output.contains("2 50"),
                "Debe imprimir '2 50' como año y máximo del tipo 2");
        assertTrue(output.contains("[1]"),
                "Debe imprimir '[1]' como lista de años sin tipo 3");
    }

    @Test
    void testMain_EntradaCaso2_DistintosValores() {
        // Otro caso:
        // N = 3
        // Año 1: 5  5  0  0  0   -> tipo2=5, tipo3=0 (entra a sinTipo3)
        // Año 2: 3  8  1  0  0   -> tipo2=8, tipo3>0
        // Año 3: 1  3  0  0  0   -> tipo2=3, tipo3=0 (entra a sinTipo3)
        //
        // Máximo del tipo2 = 8 en el año 2  -> "2 8"
        // Años sin tipo3 = [1, 3]
        String input = ""
                + "3\n"
                + "5 5 0 0 0\n"
                + "3 8 1 0 0\n"
                + "1 3 0 0 0\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problema3_16.main(new String[]{});

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("2 8"),
                "Debe imprimir '2 8' como año y máximo del tipo 2");
        assertTrue(output.contains("[1, 3]"),
                "Debe imprimir '[1, 3]' como lista de años sin tipo 3");
    }
}
