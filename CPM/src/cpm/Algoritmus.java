/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpm;

/**
 *
 * @author Martin
 */
public class Algoritmus {

    private int[][] H;
    private int pocetVrcholov;
    private int pocetHran;
    private int[] S;
    private int[] t;
    private int[] x;
    private int[] Z; //najskor mozne zaciatky cinnosti
    private int[] K; //najneskor nutne konce cinnosti
    private int[] P; //P[i] trvanie cinnosti i
    private int[] y; //naslednik kritickej cesty
    private int[] R;

    public Algoritmus(int[][] hrany, int vrcholy, int pocetHran, int[] cas) {
        this.H = hrany;
        this.pocetVrcholov = vrcholy;
        this.pocetHran = pocetHran - 1;
        this.S = new int[this.pocetHran + 2];
        this.t = new int[this.pocetVrcholov + 1];
        this.x = new int[this.pocetVrcholov + 1];
        this.Z = new int[pocetVrcholov + 1];
        this.K = new int[pocetVrcholov + 1];
        this.P = cas;
        this.y = new int[pocetVrcholov + 1];
        this.R = new int[pocetVrcholov + 1];
    }


    public int[] ocislovanie() {
        int[] d = new int[pocetVrcholov + 1];
        for (int i = 1; i < d.length; i++) {
            for (int j = 1; j <= pocetHran; j++) {
                if (i == H[j][1]) {
                    d[i] = d[i] + 1;
                }
            }
        }
        int[] V = new int[pocetVrcholov + 1]; // budúca postupnosť monotonneho usporiadania
        int nV = 0; //aktualny pocet prvkov monotonnej postupnosti
        for (int i = 1; i <= pocetVrcholov; i++) {
            if (d[i] == 0) {
                nV++;
                V[nV] = i;
            }
        }
        MakeS();
        for (int i = 1; i < pocetVrcholov; i++) {
            for (int k = S[i]; k < S[i + 1]; k++) {
                int j = H[k][1];
                d[j]--;
                if (d[j] == 0){
                    nV++;
                    V[nV] = j;
                }
            }
        }
        return V;

    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void CPM(int[] U) {
        int[] V = U;
        //vypocitame najskor mozne zacitky cinnosti
        for (int i = 1; i <= pocetVrcholov; i++) {
            Z[i] = 0;
            x[i] = 0;
        }
        
        for (int i = 1; i < pocetVrcholov; i++) {
            int r = V[i];
            for (int k = S[r]; k < S[r+1]; k++) {
                int w = H[k][1];
                if (Z[w] <= Z[r] + P[r]) {
                    Z[w] = Z[r] + P[r];
                    x[w] = r;
                }
            }
            
           
        }
        //vypocitame trvanie projektu T
        int T = 0;
        for (int i = 1; i <= pocetVrcholov; i++) {
                if(T < Z[i] + P[i]) {
                    T = Z[i] + P[i];
                }
        }
        
        //vypocitame najneskor nutne konce cinnosti
        for (int i = 1; i <= pocetVrcholov; i++) {
            K[i] = T;
            y[i] = 0;
        }
        for (int i = pocetVrcholov; i >= 1; i--) {
            int r = V[i];
            for (int j = S[r]; j < S[r+1]; j++) {
                int w = H[j][1];
                if (K[r] > K[w] - P[w]) {
                    K[r] = K[w] - P[w];
                    y[r] = w;
                }
            }
        }
        
        for (int i = 1; i <= pocetVrcholov; i++) {
                R[i] = K[i] - Z[i] - P[i];
        }
        //Vypočítať časové rezervy(K-Z-P)
        //kritická činnosť rezerva = 0
        //kritická cesta
        for (int i = 1; i <= pocetVrcholov; i++) {
                System.out.println("Cinnost " + i + ", trvanie - " + P[i] +
                        ", najskor mozny zaciatok - " + Z[i] + ", najneskor nutny koniec - " +
                        K[i] + ", rezerva: " + R[i]);
                }
        //dlzka trvania projektu (T)
        System.out.println("Trvanie projektu: " + T);
        //vypis kritickej cesty  
        System.out.print("Kritická cesta: ");
	for (int i = pocetVrcholov; x[i] > 0; i = x[i]) 
        {
		System.out.print("" + i + "<-");
	}
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
