/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskalovii;

/**
 *
 * @author Martin
 */
public class Algoritmus {
    private int[][] H;
    private int pocetVrcholov;
    private int pocetHran;
    private int[] k;
    private int cena;
    private int[][] kostra;
    
    public Algoritmus(int[][] hrany, int vrcholy, int pocetHran){
        this.H = hrany;
        this.pocetVrcholov = vrcholy;
        this.pocetHran = pocetHran;
        this.k = new int[pocetVrcholov+1]; //komponent hrany
        this.cena = 0;
        this.kostra = new int[this.pocetVrcholov + 1][2];
    }
    
    
    public void kruskal () {
        int pom = 1;
        for (int i = 1; i <= pocetVrcholov; i++) {
            k[i] = i;
	}
        for (int i = 1; i <= pocetHran; i++) {
            int u = k[H[i][0]];
            int v = k[H[i][1]];
            if (u != v) {
                cena += H[i][2];
                if (u < v) {
                    for (int j = 1; j <= pocetVrcholov; j++) {
                        if (k[H[j][1]] == v) {
                            k[H[j][1]] = k[H[i][0]];
                        }
                    }
                } else {
                    for (int j = 1; j <= pocetVrcholov; j++) {
                        if (k[H[j][0]] == v) {
                            k[H[j][0]] = k[H[i][1]];
                        }
                    }
                }
                kostra[pom][0] = H[i][0];
                System.out.println(i + " " + kostra[pom][0]);
                kostra[pom][1] = H[i][1];
                pom++;
                if (kostra[pocetVrcholov - 1][0] != 0) {
                    break;
                }
            }
        }
        
        System.out.println("Cena kostry je: " + cena);
        System.out.print("Hrany kostry: ");
        for (int i = 1; i < pocetVrcholov; i++) {
            System.out.print("{" + kostra[i][0] + ", " + kostra[i][1] + "} ");
        }
    }
}


