/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labelsets;

/**
 *
 * @author Martin
 */
public class AlgoritmusS {
    private int[][] H;
    private int pocetVrcholov;
    private boolean[] z; 
    private int[] S;
    private int pocetHran;
    private int nE;
    private int[] E;
    private int[] t;
    private int[] x;
    
    public AlgoritmusS(int[][] hrany, int vrcholy, int pocetHran){
        this.H = hrany;
        this.pocetVrcholov = vrcholy;
        this.z = new boolean[this.pocetVrcholov+1];
        this.pocetHran = pocetHran;
        this.nE = 0;
        this.E = new int[pocetVrcholov+1];
        this.t = new int[this.pocetVrcholov+1];
        this.x = new int[this.pocetVrcholov+1];
        this.S = new int[this.pocetHran + 2];
    }
    
    
    public void najkratsiaCesta (int od, int kam) {
        
        
        for (int i = 0; i <= pocetVrcholov; i++) 
        {
            x[i] = 0;
            t[i] = Integer.MAX_VALUE;
            z[i] = false; 
	}

        t[od] = 0;
        
        nE = 1;       
	E[nE] = od;
	z[od] = true;
	
        MakeS();
        
        while (nE > 0) {
            int r = extract_min();
            for(int k = S[r]; k < S[r+1]; k++){
                if(r == H[k][0]) {
                int j = H[k][1];
                int crj = H[k][2]; 
                
                if (t[j] > t[r] + crj) { 
                        t[j] = t[r] + crj;
                        x[j] = r;
                        insert_do_E(j);
                }
                }
            }
	}
        
        System.out.println("Vzdialenost " + od + " -> " + kam + " = " + t[kam]);
	for (int i = kam; x[i] > 0; i = x[i]) 
        {
		System.out.print("" + i + "<-");
	}
        System.out.println("" + od);
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
    
    public void MakeS(){
        int m = pocetHran;
        int n = pocetVrcholov;
        // vynulujem pole indexov na prvu incidentnu hranu
        for (int i = 1; i <= m; i++) S[i] = 0;

        // usporiadam hrany
        sortH();
        
	// postupne prejdem vsetky hrany [k]
        for (int k = 1; k <= m; k++) 
        {
		// zistim cislo prveho vrcholu hrany
                int i = H[k][0];
		
                // ak je to prva hrana incidentna s vrcholom i, 
                // tak si zapamatam jej index
                if (S[i] == 0) 
                { 
                    S[i] = k; 
                }
	}
        
	// S[n+1]-1 ma byt index poslednej hrany (incidentnej s vrcholom n)
        S[n + 1] = m + 1;
        
        // doplnim indexy pre izolovane vrcholy
	for (int i = n; i >= 1; i--) 
        {
            if (S[i] == 0) 
            { 
                S[i] = S[i + 1]; 
            }
	}
    }
    
    public void sortH()
    {
        int m = pocetHran;
	boolean zlepsenie = true;
	
        // cyklus skonci, az ked nenajdm zlepsenie, 
        // t.j. ked budu vsetky hrany spravne zoradene
        while (zlepsenie) 
        {
            zlepsenie = false;
            for (int k = 1; k < m; k ++) 
            {
                if (H[k][0] > H[k + 1][0]) 
                {
                    // hrany na indexe [k] a [k+1] maju byt v opacnom poradi,
                    // musim ich vymenit
                    swapH(k, k + 1);
                    // nasiel som zlepsenie, poznacim si priznak ze mam pokracovat dalej
                    zlepsenie = true;
                }
            }
	}
    }
    
    private void swapH(int i, int j)
    {
        // postupne vymenim pociatocny vrchol, koncovy vrchol a aj cenu hrany
        for (int k = 0; k < 3; k++)
        {
            int tmp = H[i][k];
            H[i][k] = H[j][k];
            H[j][k] = tmp;
        }
    }
}
