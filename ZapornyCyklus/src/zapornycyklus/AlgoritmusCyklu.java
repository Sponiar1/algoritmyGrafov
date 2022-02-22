/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapornycyklus;

/**
 *
 * @author Martin
 */
public class AlgoritmusCyklu {
    private int[][] H;
    private int pocetVrcholov;
    private boolean[] z; 
    private int pocetHran;
    private int nE;
    private int[] E;
    private int[] t;
    private int[] x;
    private int[] cyklus;
    
    public AlgoritmusCyklu(int[][] hrany, int vrcholy, int pocetHran){
        this.H = hrany;
        this.pocetVrcholov = vrcholy;
        this.z = new boolean[this.pocetVrcholov+1];
        this.pocetHran = pocetHran;
        this.nE = 0;
        this.E = new int[pocetVrcholov+1];
        this.t = new int[this.pocetVrcholov+1];
        this.x = new int[this.pocetVrcholov+1];
        this.cyklus = new int[this.pocetVrcholov+1];
    }
    
    
    public void najkratsiaCesta () {
        
        
        for (int i = 0; i <= pocetVrcholov; i++) 
        {
            x[i] = 0;
            t[i] = 0;
            E[i] = i;
            z[i] = true; 
	}

        
        nE = pocetVrcholov; 
	z[0] = false;
        while (nE > 0) {
            int r = extract_min();
            for (int k = 1; k < (pocetHran + 1); k++) {
                if (r == H[k][0]) {
                    int j = H[k][1];
                    int crj = H[k][2];

                    if (t[j] > t[r] + crj) {
                        t[j] = t[r] + crj;
                        x[j] = r;
                        insert_do_E(j);
                        //hľadanie záporného cyklu
                        cyklus[1] = j;
                        for (int c = 1; c <= pocetVrcholov; c++) {
                            int temp = x[cyklus[c]];
                            if (temp == 0) {
                                break;
                            }
                            cyklus[c + 1] = temp; //ak temp =/= 0 našli sme ďalší vrchol cyklu
                            if (temp == j) { //teraz sme našli záporný cyklus
                                System.out.println("Nasli sme cyklus: ");
                                for (int t = c + 1; t >= 1; t--) {
                                    System.out.print(cyklus[t] + " ");
                                }
                                return;
                            }
                        }
                    }
                }
            }
	}
        
        System.out.println("Cyklus nenajdeny");
    }
    
    public void insert_do_E(int b) {
        if (z[b] == false) 
        {      
		nE = nE + 1;      
		E[nE] = b;        
		z[b] = true;
	}
    }

    
    
    public int extract_min() {
        int temp_min = Integer.MAX_VALUE;
        int imin = 0; 
        for (int k = 1; k <= nE; k++) 
        {
                if (temp_min > this.t[E[k]]) 
                {
                        temp_min = t[E[k]];
                        imin = k;
                }
        }
        int r = E[imin];
        z[r] = false;   
        E[imin] = E[nE]; 
        E[nE] = 0;       
        nE = nE - 1;
        return r;
    }
}
