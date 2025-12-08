package com.ejercicios;

import java.util.Scanner;

public class Problema1_8 {

    public static double distancia(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();

        System.out.println(distancia(x1, y1, x2, y2));

        sc.close();
    }
}
