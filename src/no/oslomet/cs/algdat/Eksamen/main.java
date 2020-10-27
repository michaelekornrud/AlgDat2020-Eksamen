package no.oslomet.cs.algdat.Eksamen;

import org.w3c.dom.Node;

import java.util.Comparator;

public class main {
    public static void main(String [] args){

        ////Oppgave 0 -- Sjekker for syntaksfeil//////////////////////////
        /*EksamenSBinTre<String> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall()); // Utskrift: 0*/

        ////Oppgave 1  ///////////////////////
        /*Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre1 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre1.leggInn(verdi);
        System.out.println(tre1.antall()); // Utskrift: 10

        ////Oppgave 2 ///////////////////////
        Integer[] a1 = {4,7,2,9,4,10,8,7,4,6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a1) tre.leggInn(verdi);
        System.out.println("Tre: " + tre.toStringPostOrder());

        System.out.println(tre.antall()); // Utskrift: 10
        System.out.println(tre.antall(5)); // Utskrift: 0
        System.out.println(tre.antall(4)); // Utskrift: 3
        System.out.println(tre.antall(7)); // Utskrift: 2
        System.out.println(tre.antall(10)); // Utskrift: 1

        //// Oppgave 3 ////////////////////
        System.out.println("Oppg3: ");
        System.out.println(tre.toStringPostOrder());*/


        //// Oppgave 6 ///////////////////
        int[] a2 = {4,7,2,9,4,10,8,7,4,6,1};
        EksamenSBinTre<Integer> tre2 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a2) tre2.leggInn(verdi);

        System.out.println(tre2.fjernAlle(4)); // 3
        tre2.fjernAlle(7);
        System.out.println("Fjern: ");
        tre2.fjern(8);
        System.out.println(tre2.antall()); // 5
        System.out.println(tre2.toString());

        // [1, 2, 6, 9, 10] [10, 9, 6, 2, 1]
        // OBS: Hvis du ikke har gjort oppgave 4 kan du her bruke toString()
    }
}
