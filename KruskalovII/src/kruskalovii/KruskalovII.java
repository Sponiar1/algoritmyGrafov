/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskalovii;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Martin
 */
public class KruskalovII {

    
    public static void main(String[] args) throws FileNotFoundException{
        int pocetVrcholov = 0;
        int pocetHran = 0;
        
        System.out.println("Zvolte meno suboru: " + "\n" +
                "1 - Florida.hrn" + "\n" + 
                "2 - NewYork.hrn" + "\n" +
                "3 - pr1.hrn" + "\n" + 
                "4 - SlovRep.hrn" + "\n" +
                "5 - Strakonice.hrn" + "\n" +
                "6 - Test_mini.hrn");
        
        String nazovSuboru = "";
        Scanner nazov = new Scanner(System.in);
        int subor = nazov.nextInt();
        switch (subor) {
            case 1:
                nazovSuboru = "Florida.hrn";
                break;
            case 2:
                nazovSuboru = "NewYork.hrn";
                break;
            case 3:
                nazovSuboru = "pr1.hrn";
                break;
            case 4:
                nazovSuboru = "SlovRep.hrn";
                break;
            case 5:
                nazovSuboru = "Strakonice.hrn";
                break;
            case 6:
                nazovSuboru = "Test_mini.hrn";
                break;
        }
        
        try{
            FileInputStream fis = new FileInputStream(nazovSuboru);
            Scanner citac = new Scanner(fis);
            
            while(citac.hasNextInt()) {
                int u = citac.nextInt();
                int v = citac.nextInt();
                int c = citac.nextInt();
            
            pocetHran++;
            
            if (pocetVrcholov < u) {
                pocetVrcholov = u;
            }
            if (pocetVrcholov < v) {
                pocetVrcholov = v;
            }
            }
        
                    
                
            citac.close();
        }
        catch (IOException ioex){
            System.out.println("Doslo k chybe pri citani suboru " + ioex.getMessage());
        }
        int[][] H = new int[pocetHran+1][3];
        H[0][0] = 0;
        H[0][1] = 0;
        H[0][2] = 0;
        
        int j = 1;
        try{
            FileInputStream fis = new FileInputStream(nazovSuboru);
            Scanner citac = new Scanner(fis);
            while (citac.hasNextLine()) {
                int u = citac.nextInt();
                System.out.print(u+" ");
                int v = citac.nextInt();
                System.out.print(v+" ");
                int c = citac.nextInt();
                System.out.println(c);
                H[j][0] = u;
                H[j][1] = v;
                H[j][2] = c;
                j++;
            }
            citac.close();
        }
        catch (IOException ioex){
            System.out.println("Doslo k chybe pri citani suboru " + ioex.getMessage());
        }
        
        for (int i = 1; i < pocetHran + 1; i++) {
            for (int k = i; k < pocetHran + 1; k++) {
                if (H[k][2] <= H[i][2] ) {
                    int pom = H[i][2];
                    H[i][2] = H[k][2];
                    H[k][2] = pom;
                    pom = H[i][1];
                    H[i][1] = H[k][1];
                    H[k][1] = pom;
                    pom = H[i][0];
                    H[i][0] = H[k][0];
                    H[k][0] = pom;
                }
            }
        }
        
        Algoritmus algo = new Algoritmus (H, pocetVrcholov, pocetHran);
        algo.kruskal();
    }
}
