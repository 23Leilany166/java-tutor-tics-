// Problema3_16.java
package com.ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Problema3_16 {

    /**
     * V[i][j] = litros del tipo j en el año i
     * i = 1..N, j = 1..5
     *
     * Regresa:
     *  - "tipo"  : int[6]  con totales por tipo (1..5)
     *  - "tot"   : int[N+1] con totales por año (1..N)
     *  - "anioMaxTipo2": año con mayor producción del tipo 2
     *  - "maxTipo2"    : cantidad máxima del tipo 2
     *  - "sinTipo3"    : lista de años donde el tipo 3 fue 0
     */
    public static Map<String,Object> resolver(int[][] V, int N){
        int[] tipo = new int[6];     // tipo[1..5] -> TIPO1..TIPO5
        int[] tot  = new int[N+1];   // tot[1..N]  -> TOTVIN por año

        // Recorrer la matriz: años (i) y tipos (j)
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= 5; j++) {
                tipo[j] += V[i][j];   // total por tipo
                tot[i]  += V[i][j];   // total por año
            }
        }

        // Encontrar año con mayor producción del tipo 2
        int anioMax = 1;
        int max2 = V[1][2];
        for(int i = 2; i <= N; i++) {
            if(V[i][2] > max2) {
                max2 = V[i][2];
                anioMax = i;
            }
        }

        // Años donde el tipo 3 tuvo producción 0
        List<Integer> sin3 = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(V[i][3] == 0) {
                sin3.add(i);
            }
        }

        // Empaquetar resultados
        Map<String,Object> r = new HashMap<>();
        r.put("tipo", tipo);
        r.put("tot",  tot);
        r.put("anioMaxTipo2", anioMax);
        r.put("maxTipo2",     max2);
        r.put("sinTipo3",     sin3);
        return r;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // Leer N = número de años
        System.out.print("Ingresa el número de años (N): ");
        int N = sc.nextInt();

        // Matriz V[1..N][1..5]
        int[][] V = new int[N+1][6];
        System.out.println("Ingresa los valores de producción por año y por tipo (5 valores por cada año):");

        for(int i = 1; i <= N; i++) {
            System.out.println("Año " + i + " - ingresa 5 valores (tipos 1 a 5):");
            for(int j = 1; j <= 5; j++) {
                System.out.print("  Tipo " + j + ": ");
                V[i][j] = sc.nextInt();
            }
        }

        // Calcular resultados
        Map<String,Object> r = resolver(V, N);
        int[] tipo = (int[]) r.get("tipo");
        int[] tot  = (int[]) r.get("tot");
        int anioMax = (int) r.get("anioMaxTipo2");
        int max2    = (int) r.get("maxTipo2");
        @SuppressWarnings("unchecked")
        List<Integer> sin3 = (List<Integer>) r.get("sinTipo3");

        // ===== Salidas que pide el enunciado =====

        // 1) Totales por tipo de vino (1..5)
        System.out.println("Totales por tipo de vino:");
        for (int j = 1; j <= 5; j++) {
            System.out.println("Tipo " + j + ": " + tipo[j]);
        }

        // 2) Totales por año
        System.out.println("Totales por año:");
        for (int i = 1; i <= N; i++) {
            System.out.println("Año " + i + ": " + tot[i]);
        }

        // 3) Año con mayor producción del tipo 2 y su cantidad
        System.out.println(anioMax + " " + max2);

        // 4) Años donde el tipo 3 tuvo producción 0
        System.out.println(sin3);

        sc.close();
    }
}
