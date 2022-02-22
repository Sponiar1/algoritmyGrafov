/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Martin
 */
public class CPM {

    public static void main(String[] args) throws FileNotFoundException{
        int pocetVrcholov = 0;
        int pocetHran = 0;
        
        System.out.println("Zvolte meno suboru: " + "\n" +
                "1 - CPM_midi.hrn" + "\n" + 
                "2 - CPM_mini.hrn" + "\n" +
                "3 - CPM_stred.hrn");
        
        String nazovSuboru = "";
        String nazovSuboru2 = "";
        Scanner nazov = new Scanner(System.in);
        int subor = nazov.nextInt();
        switch (subor) {
            case 1:
                nazovSuboru = "CPM_midi.hrn";
                nazovSuboru2 = "CPM_midi.tim";
                break;
            case 2:
                nazovSuboru = "CPM_mini.hrn";
                nazovSuboru2 = "CPM_mini.tim";
                break;
            case 3:
                nazovSuboru = "CPM_stred.hrn";
                nazovSuboru2 = "CPM_stred.tim";
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
        
        j = 1;
        int[] P = new int[pocetVrcholov + 1];
        try{
            FileInputStream fis = new FileInputStream(nazovSuboru2);
            Scanner citac = new Scanner(fis);
            while (citac.hasNextLine()) {
                int u = citac.nextInt();
                System.out.println(u);
                P[j] = u;
                j++;
            }
            citac.close();
        }
        catch (IOException ioex){
            System.out.println("Doslo k chybe pri citani suboru " + ioex.getMessage());
        }
        
        Algoritmus algo = new Algoritmus (H, pocetVrcholov, pocetHran, P);
        algo.CPM(algo.ocislovanie());
    }
}
