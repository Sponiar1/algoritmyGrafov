/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monotonne;

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

    public Algoritmus(int[][] hrany, int vrcholy, int pocetHran) {
        this.H = hrany;
        this.pocetVrcholov = vrcholy;
        this.pocetHran = pocetHran - 1;
        this.S = new int[this.pocetHran + 2];
        this.t = new int[this.pocetVrcholov + 1];
        this.x = new int[this.pocetVrcholov + 1];
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
        for (int i = 1; i <= pocetVrcholov; i++) {
            for (int k = S[i]; k < S[i + 1]; k++) {
                int j = H[k][1];
                d[j]--;
                if (d[j] == 0) {
                nV++;
                V[nV] = j;
                }
            }
        }
        return V;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void najkratsia(int[] usporiadanie) {
        int V[] = usporiadanie;
        int start = 1;
        for (int i = 1; i <= pocetVrcholov; i++) {
            t[i] = Integer.MAX_VALUE / 2;
            x[i] = 0;
        }
        t[start] = 0;

        //hladame index istart na ktorom sa v poli V nachadza start
        int istart = 0;
        for (int i = 1; i <= pocetVrcholov; i++) {
            if (V[i] == start) {
                istart = i;
                break;
            }
            if (istart == 0) {
                System.out.print("Fatal error, start nenajdeny v poli V");
            }
        }

        for (int i = istart; i <= pocetVrcholov; i++) {
            int r = V[i];
            for (int k = S[r]; k < S[r + 1]; k++) {
                int j = H[k][1];
                int crj = H[k][2];
                if (t[j] > t[i] + crj) {
                    t[j] = t[i] + crj;
                    x[j] = i;
                }
            }
        }
        
        System.out.println("Vzdialenost " + start + " -> " + pocetVrcholov + " = " + t[pocetVrcholov]);
	for (int i = pocetVrcholov; x[i] > 0; i = x[i]) 
        {
		System.out.print("" + i + "<-");
	}
        System.out.println("" + start);
        
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
