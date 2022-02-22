/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapornycyklus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Martin
 */
public class ZapornyCyklus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        int pocetVrcholov = 0;
        int pocetHran = 0;
        
        System.out.println("Zvolte meno suboru: " + "\n" +
                "1 - CYKL_Maxi.hrn" + "\n" + 
                "2 - CYKL_Mini.hrn");
        
        String nazovSuboru = "";
        Scanner nazov = new Scanner(System.in);
        int subor = nazov.nextInt();
        switch (subor) {
            case 1:
                nazovSuboru = "CYKL_Maxi.hrn";
                break;
            case 2:
                nazovSuboru = "CYKL_Mini.hrn";
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
        AlgoritmusCyklu algo = new AlgoritmusCyklu (H, pocetVrcholov, pocetHran);
        algo.najkratsiaCesta();
    }
    
}
