/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zakladnyalgoritmus;

/**
 *
 * @author Martin
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Algoritmus {
    private int[][] H;
    private int pocetVrcholov;
    private int pocetHran;
    
    public Algoritmus(int[][] hrany, int vrcholy, int pocetHran) {
        this.H = hrany;
        this.pocetVrcholov = vrcholy;
        this.pocetHran = pocetHran - 1;
    }
    
    public int[] najkratsiaCesta (int z, int kam) {
        if ((z < 1) || (z > this.pocetVrcholov)) {
            return null;
        }
        double[] t = new double[this.pocetVrcholov+1];
        for (int i = 0; i < this.pocetVrcholov+1; i++) {
            t[i] = Integer.MAX_VALUE / 2;
        }
        t[z] = 0;
        
        
        int[] x = new int[this.pocetVrcholov+1];
        for (int i = 0; i < this.pocetVrcholov+1; i++) {
            x[i] = 0;
        } 
        x[z] = 0;
        
        int zlepsenie = 1;
        
        while (zlepsenie == 1) {
            zlepsenie = 0;
            for(int k = 1; k < (pocetHran+1); k++){
                int i = H[k][0];
                int j = H[k][1];
                int cij = H[k][2]; 
                if (t[j]>t[i] + cij){ 
                    t[j] = t[i] + cij;
                    x[j] = i; 
                    zlepsenie = 1;
                    }
            }
             
        }
        
        int[] cesta  = new int[this.pocetVrcholov+1];
        cesta[this.pocetVrcholov] = kam;
        for (int i = this.pocetVrcholov; i-1 > 0; i--){
            cesta[i-1] = x[kam];
            kam = x[kam]; 
            }
        return cesta;
    }
    
    
}
